package com.teamb.chzonk.ui.reader

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import com.teamb.chzonk.R
import com.teamb.chzonk.Settings

@SuppressLint("Registered")
open class ReaderComicActivity : ReaderComicActivityImpl1Hardware() {

    private lateinit var readerComicAdapter: ReaderComicAdapter
    protected lateinit var viewPager: ReaderViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewPager = findViewById(R.id.viewPager)
        readerComicAdapter = ReaderComicAdapter(this@ReaderComicActivity)
        viewPager.adapter = readerComicAdapter
        populateList()

        setUpPageViewFAB()
        setUpRTLFAB()
        setUpProgressFAB()

        /*readerViewModel.getCurrentPage().observe(this, Observer { setUpProgressBar(it) })
        readerViewModel.getLayoutDirection().observe(this,
            Observer { findViewById<RelativeLayout>(R.id.reader_layout).layoutDirection = it })*/
    }

    private fun onListChanged() {
        viewModel.setReaderListType()
        viewPager.adapter = null
        viewPager.adapter = ReaderComicAdapter(this@ReaderComicActivity)
        val position = viewModel.getReaderTrueIndexAt(currentBook.currentPage)
        viewPager.currentItem = position
    }

    private fun startSingleMode() {
        populateSinglePanelList()
        onListChanged()
    }

    private fun startDualMode() {
        populateSinglePanelList()
        when (viewModel.isReaderDualPaneListEmpty()) {
            true ->
                populateDualPaneList().observe(this, Observer { onListChanged()})
            false ->
                onListChanged()
        }
    }

    private fun populateList() {
        when (Settings.DUAL_READER) {
            true -> startDualMode()
            false -> startSingleMode()
        }
    }

    protected fun refreshViewpager() {
        try {
            val position = viewPager.currentItem
            viewPager.adapter = null
            viewPager.adapter = ReaderComicAdapter(this@ReaderComicActivity)
            viewPager.currentItem = position
        } catch (e: Exception) {
            // do nothing
        }
    }

    private fun setUpProgressBar(int: Int) {
        val progressBar = findViewById<ProgressBar>(R.id.determinateBar)
        progressBar.max = viewModel.getReaderListSize(currentBook)
        progressBar.progress = int
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        populateList()
    }

    override fun goToPreviousPage() {
        viewPager.currentItem = viewPager.currentItem - 1
    }

    override fun goToNextPage() {
        viewPager.currentItem = viewPager.currentItem + 1
    }

    protected fun initListeners() {
        viewPager.clearOnPageChangeListeners()
        viewPager.addOnPageChangeListener(object : androidx.viewpager.widget.ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
            }
        })
    }
}
