package com.teamb.chzonk.ui.reader

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.teamb.chzonk.R

@SuppressLint("Registered")
open class ReaderComicActivity : ReaderComicActivityImpl1Hardware() {

    private lateinit var readerComicAdapter: ReaderComicAdapter
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        populateList()
        viewPager = findViewById(R.id.viewPager)
        readerComicAdapter = ReaderComicAdapter( this@ReaderComicActivity, readerViewModel)
        viewPager.adapter = readerComicAdapter

        readerViewModel.currentBook.value = currentBook

        setUpPageViewFAB()
        setUpRTLFAB()
        setUpProgressFAB()

        readerViewModel.getCurrentPage().observe(this, Observer { setUpProgressBar(it) })
        readerViewModel.getLayoutDirection().observe(this,
            Observer { findViewById<RelativeLayout>(R.id.reader_layout).layoutDirection = it })

    }

    private fun setUpProgressBar(int: Int) {
        val progressBar = findViewById<ProgressBar>(R.id.determinateBar)
        progressBar.max = viewModel.getReaderListSize(currentBook) - 1
        progressBar.progress = int
    }


    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        populateList()
    }
}
