package com.teamb.chzonk.data
import androidx.lifecycle.ViewModel
import com.teamb.chzonk.data.model.Book
import com.teamb.chzonk.data.model.ComicFile
import com.teamb.chzonk.data.repository.FileRepository
import com.teamb.chzonk.data.repository.LocallibRepository
import com.teamb.chzonk.data.repository.ReaderRepository
import timber.log.Timber

class ViewModel(
    private val fileRepository: FileRepository,
    private var locallibRepository: LocallibRepository,
    private var readerRepository: ReaderRepository
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

    // local files
    fun getFileListLiveData() = fileRepository.getListLiveData()

    fun getFileList() = fileRepository.getList()

    internal fun addFile(file: ComicFile) = fileRepository.addFile(file)

    internal fun startGetList(list: List<Book>, savePath: String) {
        list.forEach {
            it.currentPage = 0
            it.isFinished
        }
        when (savePath.isNotEmpty()) {
            true -> fileRepository.addBooks(list, savePath)
            false -> Timber.e("Error when fetching book list")
        }
    }
}
