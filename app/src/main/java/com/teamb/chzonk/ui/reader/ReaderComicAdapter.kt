package com.teamb.chzonk.ui.reader

import android.os.Parcelable
import android.view.ViewGroup
import androidx.fragment.app.FragmentStatePagerAdapter
import com.teamb.chzonk.Constants
import com.teamb.chzonk.DaggerApp
import com.teamb.chzonk.Settings
import com.teamb.chzonk.data.ViewModel
import javax.inject.Inject

class ReaderComicAdapter internal constructor(
    private val readerComicActivity: ReaderComicActivity) :
    FragmentStatePagerAdapter(readerComicActivity.supportFragmentManager) {

    init {
        DaggerApp.appComponent.inject(this)
    }

    @Inject
    lateinit var viewModel: ViewModel

    internal var mCurrentFragment: ReaderComicFragment? = null

    override fun getCount(): Int = viewModel.getReaderListSize(readerComicActivity.currentBook)

    override fun setPrimaryItem(container: ViewGroup, position: Int, `object`: Any) {
        if (mCurrentFragment !== `object`) {
            mCurrentFragment = `object` as ReaderComicFragment
        }
        super.setPrimaryItem(container, position, `object`)
    }

    override fun getItem(position: Int): ReaderComicFragment {
        val book = readerComicActivity.currentBook
        val isPositionDual = getIsPositionDual(position)
        // new instance of reader comic fragment but do in a different way

        return when (Settings.DUAL_READER && isPositionDual) {
            true -> ReaderComicFragmentDual.newInstance(book, position)
            false -> ReaderComicFragmentSingle.newInstance(book, position)
        }
    }

    override fun saveState(): Parcelable? {
        return null
    }

    override fun restoreState(state: Parcelable?, loader: ClassLoader?) {}

    override fun getItemPosition(`object`: Any) = androidx.viewpager.widget.PagerAdapter.POSITION_NONE

    private fun getIsPositionDual(position: Int) = viewModel.getReaderItemAt(position)?.page1 != Constants.KEY_SINGLE_PAGE
}
