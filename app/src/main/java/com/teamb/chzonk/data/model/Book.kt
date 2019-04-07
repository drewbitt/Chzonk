package com.teamb.chzonk.data.model

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize
import androidx.room.PrimaryKey

@SuppressLint("ParcelCreator")
@Parcelize
@Entity
data class Book(
    @PrimaryKey(autoGenerate = true) override var autoId: Int = 0,
    override var id: Int = 0,
    override var title: String = "",
    override var content: String = "",
    override var currentPage: Int = 0,
    override var totalPages: Int = 0,
    override var filePath: String = "",
    override var isFinished: Boolean = false) : BookData(), Parcelable {

    // need to implement getting the cover, helper methods
    }