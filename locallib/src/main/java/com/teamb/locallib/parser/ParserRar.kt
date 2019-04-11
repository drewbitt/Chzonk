package com.teamb.locallib.parser

import com.github.junrar.Archive
import com.github.junrar.exception.RarException
import com.github.junrar.impl.FileVolumeManager
import com.github.junrar.rarfile.FileHeader
import java.io.File
import java.io.IOException
import java.io.InputStream

class ParserRar : ParserClass(), ParserInterface {

    private lateinit var arch: Archive
    private val headers = ArrayList<FileHeader>()
    private var solidFileExtracted = false

    override fun parse(file: File) {
        try {
            arch = Archive(FileVolumeManager(file))
        } catch (e: RarException) {
            throw IOException("Unable to open rar archive")
        }

        var header = arch.nextFileHeader()
        while (header != null) {
            if (!header.isDirectory) {
                val name = getName(header)
                if (isImage(name)) {
                    headers.add(header)
                }
            }

            header = arch.nextFileHeader()
        }
        headers.sortBy { getName(it) }
    }

    private fun getName(header: FileHeader): String {
        return if (header.isUnicode) header.fileNameW else header.fileNameString
    }

    override fun getPage(num: Int): InputStream {
        if (arch.mainHeader.isSolid) {
            // solid archives require special treatment
            synchronized(this) {
                if (!solidFileExtracted) {
                    for (h in arch.fileHeaders) {
                        if (!h.isDirectory && isImage(getName(h))) {
                            return arch.getInputStream(h)
                        }
                    }
                    solidFileExtracted = true
                }
            }
        }
        return arch.getInputStream(headers[num])
    }

    override fun numPages() = headers.size

    override fun destroy() = arch.close()
}
