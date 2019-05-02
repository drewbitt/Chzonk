package com.teamb.chzonk.ui.reader

import android.annotation.SuppressLint
import android.view.KeyEvent
import android.view.View
import android.widget.RelativeLayout
import com.teamb.chzonk.R

@SuppressLint("Registered")
open class ReaderComicActivityImpl1Hardware : ReaderComicActivityImpl0Fab() {

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (findViewById<RelativeLayout>(R.id.fab_layout).visibility == View.GONE) {
            fabPostion = 0
        }
        when (keyCode) {
            KeyEvent.KEYCODE_DPAD_CENTER, KeyEvent.KEYCODE_ENTER -> {
                enterCodeFun(fabPostion)
            }
            KeyEvent.KEYCODE_DPAD_UP -> {
                if (fabPostion == 0) fabPostion = 1 else fabPostion = 2
                colorSelectedFab(fabPostion)
            }
            KeyEvent.KEYCODE_DPAD_DOWN -> {
                if (fabPostion == 2) fabPostion = 1 else fabPostion = 0
                colorSelectedFab(fabPostion)
            }
        }
        return super.onKeyDown(keyCode, event)
    }
}