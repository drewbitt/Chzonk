package com.teamb.chzonk.ui.reader

import androidx.fragment.app.FragmentStatePagerAdapter
import com.teamb.chzonk.data.ViewModel

class ReaderComicAdapter internal constructor(
    private val readerComicActivity: ReaderComicActivity,
    private val viewModel: ViewModel
) :
    FragmentStatePagerAdapter(readerComicActivity.supportFragmentManager) {

    // may need some overrides still

    override fun getCount(): Int = viewModel.getReaderListSize(readerComicActivity.currentBook)

    override fun getItem(position: Int): ReaderComicFragment {
        val book = readerComicActivity.currentBook
        return ReaderComicFragment.newInstance(book, position)
    }
}
