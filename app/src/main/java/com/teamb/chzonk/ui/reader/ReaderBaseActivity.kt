package com.teamb.chzonk.ui.reader

import android.annotation.SuppressLint
import com.teamb.chzonk.ui.base.BaseActivity

@SuppressLint("Registered")
open class ReaderBaseActivity : BaseActivity() {
    // Skeleton for things that would go in a base activity and could be extended in the future
    // bindview stuff here, internal vars, helper functions

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
