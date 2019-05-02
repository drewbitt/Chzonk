package com.teamb.chzonk.ui.reader

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import com.teamb.chzonk.R
import com.teamb.chzonk.ui.base.BaseActivity

@SuppressLint("Registered")
open class ReaderBaseActivity : BaseActivity() {
    // Skeleton for things that would go in a base activity and could be extended in the future
    // bindview stuff here, internal vars, helper functions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reader)
        title = currentBook.title
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        title = currentBook.title
    }


    protected fun populateSinglePanelList() {
        viewModel.setSingleList(viewModel.createList(currentBook))
    }

    protected fun populateDualPanelList() {
        // for now, only single
        viewModel.setSingleList(viewModel.createList(currentBook))
    }

/*    protected fun finishBook() {
        currentBook.isFinished = true // is that enough?
    }*/
    
    private fun setProgress() {
        // based off of current page #
    }
}
