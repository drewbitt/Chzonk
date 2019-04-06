package com.teamb.local_library.streams

import com.teamb.local_library.parser.Parser7z
import com.teamb.local_library.parser.ParserRar
import com.teamb.local_library.parser.ParserZip
import com.teamb.local_library.parser.ParserInterface

class FileParserClass(path: String) {

    lateinit var parser: ParserInterface

    init {
        when {
            path.toLowerCase().matches(".*\\.(zip|cbz)$".toRegex()) -> parser = ParserZip()
            path.toLowerCase().matches(".*\\.(rar|cbr)$".toRegex()) -> parser = ParserRar()
            path.toLowerCase().matches(".*\\.(7z|cb7)$".toRegex()) -> parser = Parser7z()
        }
    }
}
