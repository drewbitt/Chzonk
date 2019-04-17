package com.teamb.chzonk.ui

import android.os.Bundle
import com.teamb.chzonk.R
import com.teamb.chzonk.ui.base.BaseActivity

class LibraryActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.library_fragment)
    }
}
