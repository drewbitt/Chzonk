package com.teamb.chzonk.ui.reader

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
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
import kotlin.math.min

@SuppressLint("Registered")
open class ReaderComicActivity : ReaderBaseActivity() {

    private lateinit var readerComicAdapter: ReaderComicAdapter
    private lateinit var viewPager: ViewPager
    private lateinit var readerViewModel: ReaderViewModel
    private val showRTLFAB: Boolean = false
    private var fabPostion: Int = 0
    private var upPostion: Int = 0
    private var downPostion: Int = 0

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

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_DPAD_CENTER, KeyEvent.KEYCODE_ENTER -> {enterCodeFun(fabPostion)}
            KeyEvent.KEYCODE_DPAD_UP -> { if (fabPostion == 0) fabPostion = 1 else fabPostion = 2 }
            KeyEvent.KEYCODE_DPAD_DOWN -> {if (fabPostion == 2) fabPostion = 1 else fabPostion = 0}
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun colorSelectedFab(fabPosition: Int) {
        when(fabPosition) {
            1 -> {
                val fab = findViewById<FloatingActionButton>(R.id.action_c) as FloatingActionButton
                //fab.alpha = Int().toFloat()}
            }
            2 -> {}
            else -> {}
        }
    }

    private fun enterCodeFun(numOfPresses: Int) {
        when (numOfPresses) {
            0 -> {expandOrCollapseMenu()}
            1 -> { onProgressBarClick() }
            2 -> { onPageClick() }
        }
    }

    private fun expandOrCollapseMenu() {
        fabPostion = 0
        val menu = findViewById<FloatingActionsMenu>(R.id.multiple_actions) as FloatingActionsMenu
        if (menu.isExpanded) {
            menu.collapse()
        } else {
            menu.expand()
        }
    }


    private fun setUpProgressFAB() {
        val faba = findViewById<FloatingActionButton>(R.id.action_c) as FloatingActionButton
        faba.setOnClickListener{onProgressBarClick()}
    }

    private fun onProgressBarClick() {
        val fab = findViewById<FloatingActionButton>(R.id.action_c) as FloatingActionButton
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
        faba.setOnClickListener{onPageClick()}
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

    private fun onPageClick() {
        readerViewModel.isSinglePageView.value = !readerViewModel.isSinglePageView.value!!
        val faba = findViewById<FloatingActionButton>(R.id.action_a) as FloatingActionButton
        if (!readerViewModel.isSinglePageView.value!!) {
            faba.setImageDrawable(getDrawable(R.drawable.ic_filter_1_black_24dp))
            faba.title = "Single Page View"
        } else {
            faba.setImageDrawable(getDrawable(R.drawable.ic_filter_2_black_24dp))
            faba.title = "Dual Page View"
        }
        collapseFABMenu()
    }

    private fun collapseFABMenu() {
        fabPostion = 0
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
