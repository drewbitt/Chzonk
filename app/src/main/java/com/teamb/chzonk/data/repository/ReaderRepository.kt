package com.teamb.chzonk.data.repository

import com.teamb.chzonk.Constants
import com.teamb.chzonk.Settings
import com.teamb.chzonk.data.model.Book
import com.teamb.chzonk.data.model.Page
import com.teamb.locallib.Main
import timber.log.Timber

class ReaderRepository(private val mainLocal: Main) { // includes functions like for dimensions

    private val readerList = mutableListOf<Page>()

    internal fun getListSize() = readerList.size

    internal fun getReaderListSize(book: Book) = createLocalList(book).size

    internal fun getItemAt(book: Book, position: Int): Page? {
        try {
            return createLocalList(book)[position]
        } catch (e: IndexOutOfBoundsException) {
            Timber.e("Reader item not found at position[$position]")
        }
        return null
    }

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
                it.page0 = renamePage(it)
            }
        }
        sortedBy { it.index }

        return this
    }

    private fun renamePage(it: Page): String {
        return when (it.page0) {
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

    private fun MutableList<Page>.sortByRtl(): MutableList<Page> {
        // rtl mode needs to switch currentPage positions if non-single
        forEach {
            if (it.page1 != Constants.KEY_SINGLE_PAGE) {
                val pageUrlList = mutableListOf<String>()
                pageUrlList.add(it.page0)
                pageUrlList.add(it.page1)

                when (Settings.RTL) {
                    true -> pageUrlList.sortByDescending { it }
                    false -> pageUrlList.sortBy { it }
                }

                it.page0 = pageUrlList[0]
                it.page1 = pageUrlList[1]
            }
        }
        return this
    }
}
