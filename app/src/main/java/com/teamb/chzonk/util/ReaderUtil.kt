package com.teamb.chzonk.util

import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import com.teamb.chzonk.Constants
import com.teamb.chzonk.DaggerApp
import com.teamb.chzonk.data.ViewModel
import com.teamb.chzonk.data.model.Page
import com.teamb.chzonk.data.room.FileDao
import java.io.InputStream
import javax.inject.Inject


class SingleToDualList(list: List<Page>) { // fix this badly when have time
    val liveDataResult = MutableLiveData<List<Page>>()

    private val resultList = mutableListOf<Page>()

    init {
        DaggerApp.appComponent.inject(this)
    }

    @Inject lateinit var fileDao: FileDao
    @Inject lateinit var executors: AppExecutors
    @Inject lateinit var viewModel: ViewModel

    init {
        list.forEachIndexed { index, page ->
            viewModel.getLocalImageInputStream(index).observeForever {
                val isFirst = index == 0
                val isWide = it?.let {
                    val isBitmapWide = isBitmapWide(it)
                    it.close()
                    isBitmapWide
                } ?: false
                if (isFirst || isWide) page.page1 = Constants.KEY_SINGLE_PAGE
                resultList.add(page)
                val isEnd = resultList.size == list.size
                if (isEnd) liveDataResult.value = resultList.processWideList()
            }
        }
    }

    private fun isBitmapWide(inputStream: InputStream): Boolean {
        val bitmapOptions = BitmapFactory.Options()
        bitmapOptions.inJustDecodeBounds = true
        BitmapFactory.decodeStream(inputStream, null, bitmapOptions)
        return bitmapOptions.outWidth >= bitmapOptions.outHeight
    }

    private fun MutableList<Page>.processWideList(): MutableList<Page> {
        //sort (again)
        sortBy { it.page0 }

        //process dual pane
        var listSize = size

        for (index in 2..listSize) {
            if (index < listSize) {
                val previousPosition = index - 1
                val previousItem = get(previousPosition)
                val currentItem = get(index)

                val isPreviousSingle = previousItem.page1 == Constants.KEY_SINGLE_PAGE
                val isCurrentSingle = currentItem.page1 == Constants.KEY_SINGLE_PAGE
                if (!isPreviousSingle && !isCurrentSingle) {
                    previousItem.page1 = currentItem.page0
                    remove(currentItem)
                    listSize -= 1
                }
            }
        }

        //quick fix for empty values
        forEach {
            if (it.page1.isEmpty()) it.page1 = Constants.KEY_SINGLE_PAGE
        }

        return this
    }
}