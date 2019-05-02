package com.teamb.chzonk.ui.reader

import android.content.Context
import android.util.AttributeSet
import com.booking.rtlviewpager.RtlViewPager
import com.teamb.chzonk.Settings

class ReaderViewPager(context: Context?, attrs: AttributeSet?): RtlViewPager(context, attrs) {

    override fun isRtl() = Settings.RTL

}