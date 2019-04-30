package com.teamb.chzonk.util

import androidx.lifecycle.MutableLiveData
import com.teamb.chzonk.data.model.Book
import com.teamb.chzonk.data.model.ComicFile
import com.teamb.chzonk.data.repository.FileRepository
import timber.log.Timber
import java.io.File

internal fun localListOfComicFiles(path: String): MutableList<ComicFile> {
    val fileList = mutableListOf<ComicFile>()

    val filePath = File(path)
    if (filePath.exists() && filePath.isDirectory) {
        filePath.listFiles().forEach {
            if (it.extension == "cbr" || it.extension == "cbz" || it.extension == "cb7") {
                fileList.add(fileToComicFile(it))
            }
        }
    }
    return fileList
}

internal class InsertFile(book: Book) : FileRepository() {
    val liveData = MutableLiveData<List<Book>>()

    internal val file = book.toComicFile()

    init {
        executors.diskIO.execute {
            try {
                fileDao.insertFile(file)
                val result = fileDao.getAllBookFiles()
                executors.mainThread.execute { liveData.value = result.fileListToBookList() }
            } catch (e: Exception) {
                Timber.e("message[${e.message}] $book")
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

internal class DeleteFile(book: Book) : FileRepository() {
    internal val liveData = MutableLiveData<List<Book>>()

    private val file = book.toComicFile()

    init {
        executors.diskIO.execute {
            try {
                fileDao.getAllBookFiles()
                    .filter { it.autoId == file.autoId }
                    .forEach { fileDao.deleteFile(it)}
                val result = fileDao.getAllBookFiles().fileListToBookList()
                executors.mainThread.execute { liveData.value = result}
            } catch (e: Exception) {
                Timber.e("message[${e.message}] $file")
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
    book.title = this.title
    book.currentPage = this.currentPage
    book.isFinished = this.isFinished
    book.filePath = this.filePath
    return book
}

internal fun Book.toComicFile(): ComicFile {
    val comicFile = ComicFile()
    comicFile.autoId = this.autoId
    comicFile.title = this.title
    comicFile.currentPage = this.currentPage
    comicFile.isFinished = this.isFinished
    comicFile.filePath = this.filePath
    return comicFile
}

internal fun fileToComicFile(file: File): ComicFile {
    return ComicFile().apply {
        title = file.name
        currentPage = 0
        filePath = file.absolutePath
    }
}
