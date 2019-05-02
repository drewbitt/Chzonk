package com.teamb.chzonk.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teamb.chzonk.data.model.Book

class ReaderViewModel : ViewModel() {

    val currentBook: MutableLiveData<Book> = MutableLiveData()
    val nextPageToShow: MutableLiveData<Int> = MutableLiveData(0)
    val currentPage: MutableLiveData<Int> = MutableLiveData(0)
    val layoutDirection: MutableLiveData<Int> = MutableLiveData(0)

    internal fun getCurrentPage() : LiveData<Int> {
        return currentPage
    }

    internal fun getLayoutDirection() : LiveData<Int> {
        return layoutDirection
    }

    internal fun getCurrentBook() : LiveData<Book> {
        return currentBook
    }

    internal fun getNextPageToShow() : LiveData<Int> {
        return nextPageToShow
    }






}