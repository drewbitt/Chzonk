package com.teamb.chzonk.data
import androidx.lifecycle.ViewModel
import com.teamb.chzonk.data.model.Book
import com.teamb.chzonk.data.model.Page
import com.teamb.chzonk.data.repository.FileRepository
import com.teamb.chzonk.data.repository.LocallibRepository
import com.teamb.chzonk.data.repository.ReaderRepository

class ViewModel(
    private val fileRepository: FileRepository,
    private var locallibRepository: LocallibRepository,
    private var readerRepository: ReaderRepository
) : ViewModel() {

    // readerRepository
    internal fun getReaderItemAt(position: Int) = readerRepository.getItemAt(position)

    internal fun getReaderItemAt(book: Book, position: Int) = readerRepository.getItemAt(book, position)

    internal fun getReaderListSize() = readerRepository.getListSize()

    internal fun createList(book: Book) = readerRepository.createLocalList(book)

    internal fun getReaderListSize(book: Book) = readerRepository.getReaderListSize(book)

    internal fun getSingleList() = readerRepository.getSinglePaneList()

    internal fun setSingleList(list: List<Page>) = readerRepository.setSinglePaneList(list)

    // internal fun setDualList(list: List<Page>) = readerRepository.setDualPaneList(list)

    internal fun setReaderListType() = readerRepository.setReaderListType()

    internal fun getReaderTrueIndexAt(position: Int) = readerRepository.getReaderTrueIndexAt(position)

    // locallib repository

    internal fun cleanupParser() = locallibRepository.cleanupParser()

    internal fun getLocalImageInputStream(position: Int) = locallibRepository.getLocalImageInputStream(position)

    internal fun getLocalImageInputStreamSingleInstance(filePath: String, position: Int) =
        locallibRepository.getLocalImageInputStreamSingleInstance(filePath, position)

    // local files
    internal fun getFileListLiveData() = fileRepository.getListLiveData() // direct dao call

    internal fun getFileList() = fileRepository.getList() // executors diskIo dao call

    internal fun addBook(book: Book) = fileRepository.addBook(book) // add book to room

    internal fun addBooks(list: List<Book>) {
        fileRepository.addBooks(list)
    }

    internal fun deleteFile(book: Book) = fileRepository.deleteFile(book)

    internal fun refreshFiles(path: String, firstRun: Boolean) = fileRepository.refreshFiles(path, firstRun)
}
