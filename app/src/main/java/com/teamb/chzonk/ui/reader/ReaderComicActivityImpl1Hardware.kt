package com.teamb.chzonk.ui.reader

import android.annotation.SuppressLint
import android.view.KeyEvent
import com.getbase.floatingactionbutton.FloatingActionsMenu
import com.teamb.chzonk.R

@SuppressLint("Registered")
open class ReaderComicActivityImpl1Hardware : ReaderComicActivityImpl0Fab() {

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (!(findViewById<FloatingActionsMenu>(R.id.multiple_actions) as FloatingActionsMenu).isExpanded) {
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