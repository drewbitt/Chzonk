package com.teamb.chzonk.ui.reader

import androidx.fragment.app.FragmentStatePagerAdapter
import com.teamb.chzonk.DaggerApp
import com.teamb.chzonk.data.ViewModel
import com.teamb.chzonk.data.model.Book
import com.teamb.chzonk.data.model.Page
import javax.inject.Inject

class ReaderComicAdapter internal constructor(private val readerComicActivity: ReaderComicActivity, private val viewModel: ViewModel) :
    FragmentStatePagerAdapter(readerComicActivity.supportFragmentManager) {


    // may need some overrides still

    override fun getCount(): Int = viewModel.getReaderListSize(readerComicActivity.currentBook)

    override fun getItem(position: Int): ReaderComicFragment {
        val book = readerComicActivity.currentBook
        // new instance of reader comic fragment but do in a different way
        return ReaderComicFragment.newInstance(book, position)
    }
}
