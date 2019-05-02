package com.teamb.chzonk.ui.reader

import androidx.fragment.app.FragmentStatePagerAdapter
import com.teamb.chzonk.DaggerApp
import com.teamb.chzonk.data.ReaderViewModel
import com.teamb.chzonk.data.ViewModel
import javax.inject.Inject

class ReaderComicAdapter internal constructor(
    private val readerComicActivity: ReaderComicActivity,
    private val readerViewModel: ReaderViewModel
) :
    FragmentStatePagerAdapter(readerComicActivity.supportFragmentManager) {

    @Inject
    lateinit var viewModel: ViewModel

    init {
        DaggerApp.appComponent.inject(this)
    }

    override fun getCount(): Int = viewModel.getReaderListSize(readerComicActivity.currentBook)

    override fun getItem(position: Int): ReaderComicFragment {
        val book = readerComicActivity.currentBook
        return ReaderComicFragment.newInstance(book, position)
    }

    override fun getItemPosition(`object`: Any) = androidx.viewpager.widget.PagerAdapter.POSITION_NONE
}
