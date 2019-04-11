package com.teamb.locallib.parser

import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry
import org.apache.commons.compress.archivers.sevenz.SevenZFile
import java.io.ByteArrayInputStream
import java.io.File
import java.io.IOException
import java.io.InputStream

class Parser7z : ParserClass(), ParserInterface {

    private lateinit var entries: MutableList<SevenZEntry>

    private inner class SevenZEntry(internal val entry: SevenZArchiveEntry, internal val bytes: ByteArray)

    @Throws(IOException::class)
    override fun parse(file: File) {
        entries = ArrayList()

        val sevenZFile = SevenZFile(file)
        var entry: SevenZArchiveEntry? = sevenZFile.nextEntry
        while (entry != null) {
            if (entry.isDirectory) {
                continue
            }
            val name = entry.name
            if (isImage(name)) {
                val content = ByteArray(entry.size.toInt())
                sevenZFile.read(content)
                entries.add(SevenZEntry(entry, content))
            }
            // not handling comicinfo.xml
            entry = sevenZFile.nextEntry
        }

        entries.sortBy { it.entry.name }
    }

    override fun numPages() = entries.size

    @Throws(IOException::class)
    override fun getPage(num: Int): InputStream = ByteArrayInputStream(entries[num].bytes)

    @Throws(IOException::class)
    override fun destroy() {
    }
}
