package com.teamb.chzonk.ui.base

import android.annotation.SuppressLint
import android.content.Intent
import com.teamb.chzonk.Constants
import com.teamb.chzonk.data.model.Book
import com.teamb.chzonk.util.SharedPrefsHelper
import dagger.android.DaggerActivity
import javax.inject.Inject

@SuppressLint("Registered")
open class BaseActivity : DaggerActivity() {

    @Inject lateinit var sharedPrefsHelper: SharedPrefsHelper

    internal lateinit var currentBook: Book

    // include methods for starting activities, like for the reader

    private fun initIntent(intent: Intent) = intent.apply {
        currentBook = getParcelableExtra(Constants.ARG_BOOK) ?: Book()
    }
}
