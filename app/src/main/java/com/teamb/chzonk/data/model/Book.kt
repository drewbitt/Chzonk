package com.teamb.chzonk.data.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.File

@SuppressLint("ParcelCreator")
@Parcelize
data class Book(
    override var autoId: Int = 0,
    override var id: Int = 0,
    override var title: String = "",
    override var content: String = "",
    override var currentPage: Int = 0,
    override var totalPages: Int = 0,
    override var filePath: String = "",
    override var isFinished: Boolean = false
) : BookData(), Parcelable {
// need to implement getting the cover, helper methods

    fun fileExists() = File(filePath).exists()

    fun isValidComicExtension() = filePath.endsWith(".cb7", ignoreCase = true)
        || filePath.endsWith(".cbr", ignoreCase = true)
        || filePath.endsWith(".cbz", ignoreCase = true)
}