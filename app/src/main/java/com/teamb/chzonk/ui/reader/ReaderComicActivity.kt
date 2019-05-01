package com.teamb.chzonk.ui.reader

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.duolingo.open.rtlviewpager.RtlViewPager
import com.getbase.floatingactionbutton.FloatingActionButton
import com.getbase.floatingactionbutton.FloatingActionsMenu
import com.teamb.chzonk.R
import com.teamb.chzonk.data.ReaderViewModel
import android.view.View.GONE
import android.view.View.VISIBLE

@SuppressLint("Registered")
open class ReaderComicActivity : ReaderBaseActivity() {

    private lateinit var readerComicAdapter: ReaderComicAdapter
    private lateinit var viewPager: ViewPager
    private lateinit var readerViewModel: ReaderViewModel
    private val showRTLFAB: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        readerViewModel = ViewModelProviders.of(this).get(ReaderViewModel::class.java)
        populateList()
        viewPager = findViewById<RtlViewPager>(R.id.viewPager) as RtlViewPager
        readerComicAdapter = ReaderComicAdapter( this@ReaderComicActivity, readerViewModel, viewModel)
        viewPager.adapter = readerComicAdapter

        readerViewModel.currentBook.value = currentBook

        setUpPageViewFAB()
        setUpRTLFAB()
        setUpProgressFAB()

        readerViewModel.getCurrentPage().observe(this, Observer { setUpProgressBar(it) })
        readerViewModel.getLayoutDirection().observe(this,
            Observer { findViewById<RelativeLayout>(R.id.reader_layout).layoutDirection = it })


    }

    private fun setUpProgressFAB() {
        val faba = findViewById<FloatingActionButton>(R.id.action_c) as FloatingActionButton
        faba.setOnClickListener{onProgressBarClick(faba)}
    }

    private fun onProgressBarClick(fab: FloatingActionButton) {
        val progressBar = findViewById<ProgressBar>(R.id.determinateBar)
        if (progressBar.visibility == VISIBLE) {
            progressBar.visibility = GONE
            fab.title = "Show Progress Bar"
        } else {
            progressBar.visibility = VISIBLE
            fab.title = "Hide Progress Bar"
        }
        collapseFABMenu()
    }

    private fun setUpPageViewFAB() {
        val faba = findViewById<FloatingActionButton>(R.id.action_a) as FloatingActionButton
        faba.setOnClickListener{onPageClick(!readerViewModel.isSinglePageView.value!!)}
    }

    private fun setUpRTLFAB() {
        val fabb = findViewById<FloatingActionButton>(R.id.action_b) as FloatingActionButton
        if (showRTLFAB) {
            if (readerViewModel.layoutDirection.value!! == 0) {
                fabb.setOnClickListener { onRTLClick(1) }
            } else {
                fabb.setOnClickListener { onRTLClick(0) }
            }
        } else {
            fabb.visibility = GONE
        }

    }

    private fun onRTLClick(int: Int) {
        readerViewModel.layoutDirection.value = int
        val faba = findViewById<FloatingActionButton>(R.id.action_b) as FloatingActionButton
        if (int == 0) {
            faba.setImageDrawable(getDrawable(R.drawable.ic_chevron_left_black_24dp))
        } else {
            faba.setImageDrawable(getDrawable(R.drawable.ic_chevron_right_black_24dp))
        }
        collapseFABMenu()
    }

    private fun onPageClick(boolean: Boolean) {
        readerViewModel.isSinglePageView.value = boolean
        val faba = findViewById<FloatingActionButton>(R.id.action_a) as FloatingActionButton
        if (!boolean) {
            faba.setImageDrawable(getDrawable(R.drawable.ic_filter_1_black_24dp))
            faba.title = "Single Page View"
        } else {
            faba.setImageDrawable(getDrawable(R.drawable.ic_filter_2_black_24dp))
            faba.title = "Dual Page View"
        }
        collapseFABMenu()
    }

    private fun collapseFABMenu() {
        val fabMenu = findViewById<FloatingActionsMenu>(R.id.multiple_actions)
        fabMenu.collapse()
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


    // override children
    // menu functions
    // functions to go to other pages
}
