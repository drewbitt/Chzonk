package com.teamb.chzonk.data.model

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.io.File

@SuppressLint("ParcelCreator")
@Parcelize
@Entity
data class Book(
    @PrimaryKey(autoGenerate = true) override var autoId: Int = 0,
    override var title: String = "",
    override var currentPage: Int = 0,
    override var filePath: String = "",
    override var isFinished: Boolean = false
) : BookData(), Parcelable {

    fun fileExists() = File(filePath).exists()

    fun isValidComicExtension() = filePath.endsWith(".cb7", ignoreCase = true) ||
        filePath.endsWith(".cbr", ignoreCase = true) ||
        filePath.endsWith(".cbz", ignoreCase = true)
}
