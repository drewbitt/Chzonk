package com.teamb.local_library.parser
import java.io.File
import java.io.IOException
import java.io.InputStream

interface ParserInterface {

    var path: String

    @Throws(IOException::class)
    fun parse(file: File)

    @Throws(IOException::class)
    fun destroy()

    @Throws(IOException::class)
    fun getPage(num: Int): InputStream

    fun numPages(): Int
}
