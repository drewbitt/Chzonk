package com.teamb.chzonk.ui.reader

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import com.teamb.chzonk.ui.base.BaseActivity

@SuppressLint("Registered")
open class ReaderBaseActivity : BaseActivity() {
    // Skeleton for things that would go in a base activity and could be extended in the future
    // bindview stuff here, internal vars, helper functions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_reader)
        // title = viewModel.currentBook.value!!.title // title is already set
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        title = currentBook.title
    }


    protected open fun setImage() {
        // override in children
    }

    protected fun overlayImage() {
        // glide function
    }

    protected open fun setButtons() {
        // override in children
    }

    private fun setProgress() {
        // based off of current page #
    }

    protected fun setPageNumberText() {
        // set the page number text bindview
    }
}
