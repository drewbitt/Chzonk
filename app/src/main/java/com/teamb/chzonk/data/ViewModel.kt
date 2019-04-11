package com.teamb.chzonk.data
import androidx.lifecycle.ViewModel
import com.teamb.chzonk.data.model.Book
import com.teamb.chzonk.data.repository.LocallibRepository
import com.teamb.chzonk.data.repository.ReaderRepository

class ViewModel(
    internal var locallibRepository: LocallibRepository,
    internal var readerRepository: ReaderRepository
) : ViewModel() {

    // readerRepository
    internal fun getReaderItemAt(position: Int) = readerRepository.getItemAt(position)

    internal fun getReaderListSize() = readerRepository.getListSize()

    internal fun createList(book: Book) = readerRepository.createLocalList(book)

    // locallib repository

    internal fun cleanupParser() = locallibRepository.cleanupParser()

    internal fun getLocalImageInputStream(position: Int) = locallibRepository.getLocalImageInputStream(position)

    internal fun getLocalImageInputStreamSingleInstance(filePath: String, position: Int) =
        locallibRepository.getLocalImageInputStreamSingleInstance(filePath, position)

}

