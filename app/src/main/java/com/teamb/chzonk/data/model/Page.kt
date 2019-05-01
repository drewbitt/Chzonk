package com.teamb.chzonk.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Page(var index: Int, var page0: String, var page1: String) : Parcelable
