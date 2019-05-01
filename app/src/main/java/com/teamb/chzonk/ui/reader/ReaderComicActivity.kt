package com.teamb.chzonk.ui.reader

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View.VISIBLE
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.getbase.floatingactionbutton.FloatingActionButton
import com.teamb.chzonk.R
import com.teamb.chzonk.data.ReaderViewModel
import com.teamb.chzonk.data.ViewModel

@SuppressLint("Registered")
open class ReaderComicActivity : ReaderBaseActivity() {

    private lateinit var readerComicAdapter: ReaderComicAdapter
    private lateinit var viewPager: ViewPager
    private lateinit var readerViewModel: ReaderViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        readerViewModel = ViewModelProviders.of(this).get(ReaderViewModel::class.java)
        populateList()
        viewPager = findViewById<ViewPager>(R.id.viewPager) as ViewPager
        readerComicAdapter = ReaderComicAdapter( this@ReaderComicActivity, viewModel)
        viewPager.adapter = readerComicAdapter

        setUpFABs()

        readerViewModel.getCurrentPage().observe(this, Observer { setUpProgressBar(it) })


    }

    private fun setUpFABs() {
        var faba = findViewById<FloatingActionButton>(R.id.action_a) as FloatingActionButton
        var fabb = findViewById<FloatingActionButton>(R.id.action_b) as FloatingActionButton


        faba.setOnClickListener{onClick(true)}
        fabb.setOnClickListener{onClick(false)}
    }

    private fun onClick(boolean: Boolean) {
        readerViewModel.isSinglePageView.value = boolean
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
