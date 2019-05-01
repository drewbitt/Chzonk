package com.teamb.chzonk.ui.reader

import androidx.fragment.app.FragmentStatePagerAdapter
import com.teamb.chzonk.data.ReaderViewModel
import com.teamb.chzonk.data.ViewModel

class ReaderComicAdapter internal constructor(private val readerComicActivity: ReaderComicActivity,
    private val readerViewModel: ReaderViewModel, private val viewModel: ViewModel
) :
    FragmentStatePagerAdapter(readerComicActivity.supportFragmentManager) {

    private var currentPosition: Int = 0
    // may need some overrides still

    override fun getCount(): Int = viewModel.getReaderListSize(readerComicActivity.currentBook)

    override fun getItem(position: Int): ReaderComicFragment {
        val book = readerViewModel.currentBook.value!!
        val isSingleview = readerViewModel.isSinglePageView.value!!
        // new instance of reader comic fragment but do in a different way
        return ReaderComicFragment.newInstance(book, position)
    }

    fun getCurrentPosition(): Int {
        return currentPosition
    }


}
