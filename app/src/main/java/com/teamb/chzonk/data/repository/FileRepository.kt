package com.teamb.chzonk.data.repository

import com.teamb.chzonk.DaggerApp
import com.teamb.chzonk.data.model.Book
import com.teamb.chzonk.data.model.ComicFile
import com.teamb.chzonk.data.room.FileDao
import com.teamb.chzonk.util.AppExecutors
import com.teamb.chzonk.util.DeleteFile
import com.teamb.chzonk.util.GetList
import com.teamb.chzonk.util.InsertFile
import com.teamb.chzonk.util.localListOfComicFiles
import com.teamb.chzonk.util.toBook
import timber.log.Timber
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

    // current implementation deletes off of autoId - may not be a good idea
    internal fun deleteFile(book: Book) = DeleteFile(book).liveData

    internal fun refreshFiles(path: String, firstRun: Boolean) {
        fun addAllInList(fileList: MutableList<ComicFile>) {
            Timber.d("Adding books to dao: {$fileList}")
            addBooks(fileList.map {
                it.toBook()
            }.toMutableList())
        }

        val fileList = localListOfComicFiles(path)
        Timber.d("Books found in path before refresh: {$fileList}")

        if (firstRun) {
            addAllInList(fileList)
            return
        }

        // this is terrible - observing forever because it is already broken without hashing/size checks
        // and I don't care to also properly implement async fileDao access for the daoList
        getList().observeForever { daoList ->
            Timber.d("Books found in dao before refresh: {$daoList}")
            if (daoList.any()) {
                // Not implementing as size or hash is not stored anywhere; determining duplicate off of purely filename
                // is a bad idea.
                /*
                fileList.forEach { fi ->
                    val newFile = File(fi.filePath)
                    daoList.forEach { dao ->
                        val fileDao = File(dao.filePath)
                        // check if names are the same
                        // I want to check for sizes/hash here but it's already moved; can't get the old size since
                        // not storing it.
                        if (fileDao.name == newFile.name) {
                            // found same file name! keep (for keeping progress) but change path
                            dao.filePath = fi.filePath
                            addBook(dao)
                            fileList.remove(fi)
                        }
                    }
                }
                */
                Timber.d("Deleting books from dao: {$daoList}")
                daoList.forEach {
                    deleteFile(it)
                }
                addAllInList(fileList)
            } else {
                // if nothing was in dao
                addAllInList(fileList)
            }
        }
    }
}
