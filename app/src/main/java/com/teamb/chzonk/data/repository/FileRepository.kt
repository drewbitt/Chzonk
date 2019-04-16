package com.teamb.chzonk.data.repository

import androidx.lifecycle.MutableLiveData
import com.teamb.chzonk.DaggerApp
import com.teamb.chzonk.data.model.Book
import com.teamb.chzonk.data.model.ComicFile
import com.teamb.chzonk.data.room.FileDao
import com.teamb.chzonk.util.AppExecutors
import timber.log.Timber
import javax.inject.Inject

open class FileRepository {

    init {
        DaggerApp.appComponent.inject(this)
    }

    @Inject lateinit var fileDao: FileDao
    @Inject lateinit var executors: AppExecutors

    internal fun addFile(file: ComicFile) = InsertFile(file).liveData

    internal fun getList() = GetList().liveData // MutableLiveData<List<Book>>()

    internal fun getListLiveData() = fileDao.getAllBookFilesLiveData() // LiveData<List<ComicFile>>

    internal fun addBooks(list: List<Book>, savePath: String) = list.forEach {
        // addFile(it)
    }

    /*<--->*/
    internal class InsertFile(file: ComicFile) : FileRepository() {
        val liveData = MutableLiveData<List<Book>>()

        init {
            executors.diskIO.execute {
                try {
                    fileDao.insertFile(file)
                    val result = fileDao.getAllBookFiles()
                    executors.mainThread.execute { liveData.value = result.fileListToBookList() }
                } catch (e: Exception) {
                    Timber.e("message[${e.message}] $file")
                    executors.mainThread.execute { liveData.value = null }
                }
            }
        }
    }

    internal class GetList : FileRepository() {
        internal val liveData = MutableLiveData<List<Book>>()

        init {
            executors.diskIO.execute {
                try {
                    val result = fileDao.getAllBookFiles().fileListToBookList()
                    executors.mainThread.execute { liveData.value = result }
                } catch (e: Exception) {
                    Timber.e("message[${e.message}]")
                    executors.mainThread.execute { liveData.value = null }
                }
            }
        }
    }

    internal fun List<ComicFile>.fileListToBookList(): List<Book> {
        val bookList = mutableListOf<Book>()
        forEach { bookList.add(it.toBook()) }
        return bookList
    }

    internal fun ComicFile.toBook(): Book {
        val book = Book()
        book.autoId = this.autoId
        book.id = this.id
        book.title = this.title
        book.content = this.content
        book.currentPage = this.currentPage
        book.totalPages = this.totalPages
        book.isFinished = this.isFinished
        book.filePath = this.filePath
        return book
    }
}
