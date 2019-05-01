package com.teamb.chzonk.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReaderViewModel : ViewModel() {

    val isSinglePageView: MutableLiveData<Boolean> = MutableLiveData(true)
    val currentPage: MutableLiveData<Int> = MutableLiveData(0)

    internal fun getIsSinglePageView() : LiveData<Boolean> {
        return isSinglePageView
    }

    internal fun getCurrentPage() : LiveData<Int> {
        return currentPage
    }






}