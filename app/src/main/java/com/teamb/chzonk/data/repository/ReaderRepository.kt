package com.teamb.chzonk.data.repository

import com.teamb.chzonk.data.model.Book
import com.teamb.chzonk.data.model.Page
import com.teamb.locallib.Main
import timber.log.Timber
import java.lang.IndexOutOfBoundsException

class ReaderRepository(private val mainLocal: Main) { // includes functions like for dimensions

    private val readerList = mutableListOf<Page>()

    internal fun getListSize() = readerList.size

    internal fun getItemAt(position: Int): Page? {
        try {
            return readerList[position]
        } catch (e: IndexOutOfBoundsException) {
            Timber.e("Reader item not found at position[$position]")
        }
        return null
    }

    internal fun createLocalList(book: Book): MutableList<Page> {
        val parser = mainLocal.initParser(book.filePath)

        val bookList = mutableListOf<Page>()
        val numPages = parser.numPages()
        for (index in 0 until numPages) {
            bookList.add(Page(index, index.toString(), ""))
        }
        return bookList.format()
    }

    private fun MutableList<Page>.format(): MutableList<Page> {
        // fix zeros
        forEach {
            for (index in 0..9) {
                it.page0 = when (it.page0) {
                    "0" -> "00"
                    "1" -> "01"
                    "2" -> "02"
                    "3" -> "03"
                    "4" -> "04"
                    "5" -> "05"
                    "6" -> "06"
                    "7" -> "07"
                    "8" -> "08"
                    "9" -> "09"
                    else -> it.page0
                }
            }
        }
        sortedBy { it.index }

        return this
    }
}
