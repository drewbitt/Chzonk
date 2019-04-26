package com.teamb.chzonk.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class ComicFile(
    @PrimaryKey(autoGenerate = true) override var autoId: Int = 0,
    override var title: String = "",
    override var currentPage: Int = 0,
    override var filePath: String = "",
    override var isFinished: Boolean = false
) : BookData(), Parcelable
