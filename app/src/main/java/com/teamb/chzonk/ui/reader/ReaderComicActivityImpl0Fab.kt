package com.teamb.chzonk.ui.reader

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProviders
import com.getbase.floatingactionbutton.FloatingActionButton
import com.getbase.floatingactionbutton.FloatingActionsMenu
import com.teamb.chzonk.R
import com.teamb.chzonk.data.ReaderViewModel

@SuppressLint("Registered")
open class ReaderComicActivityImpl0Fab : ReaderBaseActivity() {

    protected lateinit var readerViewModel: ReaderViewModel
    protected var fabPostion: Int = 0
    protected val showRTLFAB: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        readerViewModel = ViewModelProviders.of(this).get(ReaderViewModel::class.java)
    }

    protected fun colorSelectedFab(fabPosition: Int) {
        val faba = findViewById<FloatingActionButton>(R.id.action_a) as FloatingActionButton
        val fabc = findViewById<FloatingActionButton>(R.id.action_c) as FloatingActionButton
        when (fabPosition) {
            1 -> {
                fabc.alpha = 0.5.toFloat()
                faba.alpha = 1.toFloat()
            }
            2 -> {
                faba.alpha = 0.5.toFloat()
                fabc.alpha = 1.toFloat()
            }
            else -> {
                faba.alpha = 1.toFloat()
                fabc.alpha = 1.toFloat()
            }
        }
    }

    protected fun enterCodeFun(numOfPresses: Int) {
        when (numOfPresses) {
            0 -> { expandOrCollapseMenu() }
            1 -> { onProgressBarClick() }
            2 -> { onPageClick() }
        }
    }

    protected fun expandOrCollapseMenu() {
        fabPostion = 0
        val menu = findViewById<FloatingActionsMenu>(R.id.multiple_actions) as FloatingActionsMenu
        if (menu.isExpanded) {
            menu.collapse()
        } else {
            menu.expand()
        }
    }

    protected fun setUpProgressFAB() {
        val faba = findViewById<FloatingActionButton>(R.id.action_c) as FloatingActionButton
        faba.setOnClickListener { onProgressBarClick() }
    }

    protected fun onProgressBarClick() {
        val fab = findViewById<FloatingActionButton>(R.id.action_c) as FloatingActionButton
        val progressBar = findViewById<ProgressBar>(R.id.determinateBar)
        if (progressBar.visibility == View.VISIBLE) {
            progressBar.visibility = View.GONE
            fab.title = "Show Progress Bar"
        } else {
            progressBar.visibility = View.VISIBLE
            fab.title = "Hide Progress Bar"
        }
        collapseFABMenu()
    }

    protected fun setUpPageViewFAB() {
        val faba = findViewById<FloatingActionButton>(R.id.action_a) as FloatingActionButton
        faba.setOnClickListener { onPageClick() }
    }

    protected fun setUpRTLFAB() {
        val fabb = findViewById<FloatingActionButton>(R.id.action_b) as FloatingActionButton
        if (showRTLFAB) {
            if (readerViewModel.layoutDirection.value!! == 0) {
                fabb.setOnClickListener { onRTLClick(1) }
            } else {
                fabb.setOnClickListener { onRTLClick(0) }
            }
        } else {
            fabb.visibility = View.GONE
        }
    }

    protected fun onRTLClick(int: Int) {
        readerViewModel.layoutDirection.value = int
        val faba = findViewById<FloatingActionButton>(R.id.action_b) as FloatingActionButton
        if (int == 0) {
            faba.setImageDrawable(getDrawable(R.drawable.ic_chevron_left_black_24dp))
        } else {
            faba.setImageDrawable(getDrawable(R.drawable.ic_chevron_right_black_24dp))
        }
        collapseFABMenu()
    }

    protected fun onPageClick() {
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

    protected fun collapseFABMenu() {
        fabPostion = 0
        val fabMenu = findViewById<FloatingActionsMenu>(R.id.multiple_actions)
        fabMenu.collapse()
    }
}