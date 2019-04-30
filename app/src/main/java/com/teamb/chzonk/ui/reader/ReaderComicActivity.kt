package com.teamb.chzonk.ui.reader

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle

@SuppressLint("Registered")
open class ReaderComicActivity : ReaderBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()
        populateList()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        initUi()
        populateList()
    }

    protected fun initUi() {
        // inflate layouts

    }
    // override children
    // menu functions
    // functions to go to other pages
}
