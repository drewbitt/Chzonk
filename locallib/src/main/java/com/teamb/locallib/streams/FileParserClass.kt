package com.teamb.locallib.streams

import com.teamb.locallib.parser.Parser7z
import com.teamb.locallib.parser.ParserRar
import com.teamb.locallib.parser.ParserZip
import com.teamb.locallib.parser.ParserInterface
import timber.log.Timber
import java.io.File
import java.lang.Exception

class FileParserClass(path: String) {

    lateinit var parser: ParserInterface

    init {
        when {
            path.toLowerCase().matches(".*\\.(zip|cbz)$".toRegex()) -> parser = ParserZip()
            path.toLowerCase().matches(".*\\.(rar|cbr)$".toRegex()) -> parser = ParserRar()
            path.toLowerCase().matches(".*\\.(7z|cb7)$".toRegex()) -> parser = Parser7z()
        }

        try {
            parser.path = path
            parser.parse(File(path))
            Timber.d("File parsed: pages [${parser.numPages()} path[$path]")
        } catch (e: Exception) {
            Timber.e("[${e.message}")
            e.printStackTrace()
        }
    }
}
