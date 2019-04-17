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

    // internal var mCurrentFragment: ReaderComicFragment? = null

    override fun getCount(): Int {
        TODO("not implemented")
    }

    override fun getItem(position: Int): ReaderComicFragment {
        var book = readerComicActivity.currentBook
        // new instance of reader comic fragment but do in a different way
        return ReaderComicFragment()
    }
}
