package com.teamb.chzonk.ui.library.model

import android.widget.ImageView
import com.teamb.chzonk.data.model.Book
import com.teamb.chzonk.ui.MainActivity

data class Card(
    var book: Book,
    var image: ImageView = ImageView(MainActivity())
)