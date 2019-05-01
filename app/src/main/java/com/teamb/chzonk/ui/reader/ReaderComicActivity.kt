package com.teamb.chzonk.ui.reader

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.teamb.chzonk.R

@SuppressLint("Registered")
open class ReaderComicActivity : ReaderBaseActivity() {

    private lateinit var readerComicAdapter: ReaderComicAdapter
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        populateList()
        viewPager = findViewById<ViewPager>(R.id.viewPager) as ViewPager
        readerComicAdapter = ReaderComicAdapter( this@ReaderComicActivity, viewModel)
        viewPager.adapter = readerComicAdapter
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        populateList()
    }

    // override children
    // menu functions
    // functions to go to other pages
}
