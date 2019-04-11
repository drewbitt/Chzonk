package com.teamb.locallib.parser

import java.io.File
import java.io.IOException
import java.util.zip.ZipEntry
import java.util.zip.ZipFile

class ParserZip : ParserClass(), ParserInterface {

    private lateinit var entries: ArrayList<ZipEntry>
    private lateinit var zipFile: ZipFile

    @Throws(IOException::class)
    override fun parse(file: File) {
        zipFile = ZipFile(file.absolutePath)
        entries = ArrayList()

        val e = zipFile.entries()
        while (e.hasMoreElements()) {
            e.nextElement()?.apply {
                if (!isDirectory) if (isImage(name)) entries.add(this)
                // not handling comicinfo.xml
            }
        }
        entries.sortBy { it.name }
    }

    override fun numPages() = entries.size

    @Throws(IOException::class)
    override fun getPage(num: Int) = zipFile.getInputStream(entries[num])

    @Throws(IOException::class)
    override fun destroy() = zipFile.close()
}
