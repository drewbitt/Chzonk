package com.teamb.locallib

import com.teamb.locallib.parser.ParserInterface
import com.teamb.locallib.streams.FileParserClass
import com.teamb.locallib.streams.ImageInputStream
import com.teamb.locallib.streams.ImageInputStreamSingleInstance
import java.util.concurrent.Executor

class Main(val diskIO: Executor, val mainThread: Executor) {

    lateinit var parser: ParserInterface

    fun initParser(path: String): ParserInterface {
        parser = FileParserClass(path).parser
        return parser
    }

    fun cleanupParser() {
        parser.path = ""
        parser.destroy()
    }

    fun getImageInputStreamAt(position: Int) = ImageInputStream(this, position).liveData

    fun getImageInputStreamSingleInstance(path: String, position: Int) =
        ImageInputStreamSingleInstance(this, path, position).liveData
}
