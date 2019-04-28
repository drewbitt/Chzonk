package com.teamb.chzonk.ui.library.model

import android.widget.ImageView
import com.teamb.chzonk.data.model.Book

data class Card(
    var book: Book,
    var image: ImageView
)