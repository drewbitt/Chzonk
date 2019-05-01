package com.teamb.chzonk.ui.reader

import androidx.fragment.app.FragmentStatePagerAdapter
import com.teamb.chzonk.DaggerApp
import com.teamb.chzonk.data.ViewModel
import javax.inject.Inject

class ReaderComicAdapter internal constructor(private val readerComicActivity: ReaderComicActivity) :
    FragmentStatePagerAdapter(readerComicActivity.supportFragmentManager) {

    init {
        DaggerApp.appComponent.inject(this)
    }

    @Inject lateinit var viewModel: ViewModel

    // may need some overrides still

    override fun getCount(): Int = viewModel.getReaderListSize()

    override fun getItem(position: Int): ReaderComicFragment {
        val book = readerComicActivity.currentBook
        // new instance of reader comic fragment but do in a different way
        return ReaderComicFragment.newInstance(book, position)
    }
}
