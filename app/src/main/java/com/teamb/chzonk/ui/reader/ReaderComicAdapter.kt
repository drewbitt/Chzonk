package com.teamb.chzonk.ui.reader

import androidx.fragment.app.FragmentStatePagerAdapter
import com.teamb.chzonk.DaggerApp
import com.teamb.chzonk.data.ReaderViewModel
import com.teamb.chzonk.data.ViewModel
import javax.inject.Inject

class ReaderComicAdapter internal constructor(private val readerComicActivity: ReaderComicActivity,
    private val readerViewModel: ReaderViewModel
) :
    FragmentStatePagerAdapter(readerComicActivity.supportFragmentManager) {

    init {
        DaggerApp.appComponent.inject(this)
    }

    @Inject
    lateinit var viewModel: ViewModel

    override fun getCount(): Int = viewModel.getReaderListSize(readerComicActivity.currentBook)

    override fun getItem(position: Int): ReaderComicFragment {
        val book = readerViewModel.currentBook.value!!
        val isSingleview = readerViewModel.isSinglePageView.value!!
        // new instance of reader comic fragment but do in a different way
        return ReaderComicFragment.newInstance(book, position)
    }

    override fun getItemPosition(`object`: Any) = androidx.viewpager.widget.PagerAdapter.POSITION_NONE

}
