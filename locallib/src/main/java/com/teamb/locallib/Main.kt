package com.teamb.locallib

import com.teamb.locallib.parser.ParserInterface
import com.teamb.locallib.streams.FileParserClass

class Main {

    lateinit var parser: ParserInterface

    fun initParser(path: String): Parser {
        parser = FileParserClass(path).parser
        return parser
    }

    fun cleanupParser() {
        parser.path = ""
        parser.destroy()
    }

    // maybe functions to get input streams
}
