package com.teamb.chzonk.data.repository

import com.teamb.chzonk.DaggerApp
import com.teamb.chzonk.data.model.Book
import com.teamb.chzonk.data.room.FileDao
import com.teamb.chzonk.util.AppExecutors
import com.teamb.chzonk.util.GetList
import com.teamb.chzonk.util.InsertFile
import com.teamb.chzonk.util.localListOfComicFiles
import com.teamb.chzonk.util.toBook
import javax.inject.Inject

open class FileRepository {

    init {
        DaggerApp.appComponent.inject(this)
    }

    @Inject lateinit var fileDao: FileDao
    @Inject lateinit var executors: AppExecutors

    internal fun addBook(book: Book) = InsertFile(book).liveData

    internal fun addBooks(list: List<Book>) = list.forEach {
        addBook(it)
    }

    internal fun getList() = GetList().liveData // MutableLiveData<List<Book>>()

    internal fun getListLiveData() = fileDao.getAllBookFilesLiveData() // LiveData<List<ComicFile>>

    internal fun firstAddFiles(path: String) {
        // this should be some sort of a refresh command, possibly checking if same path as before,
        // adding new books but not re-adding the same, removing extras, etc but for now just implementing
        // "add everything in path" for a first starting command

        val fileList = localListOfComicFiles(path)
        addBooks(fileList.map {
            it.toBook()
        }.toMutableList())
    }
}
