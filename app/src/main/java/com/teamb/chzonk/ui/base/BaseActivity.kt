package com.teamb.chzonk.ui.base

import android.annotation.SuppressLint
import com.teamb.chzonk.data.model.Book
import com.teamb.chzonk.util.SharedPrefsHelper
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

@SuppressLint("Registered")
open class BaseActivity: DaggerAppCompatActivity() {

    @Inject lateinit var sharedPrefsHelper: SharedPrefsHelper
    @Inject lateinit var currentBook: Book

    // include methods for starting activities, like for the reader
}