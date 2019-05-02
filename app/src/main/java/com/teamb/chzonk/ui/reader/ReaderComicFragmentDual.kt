package com.teamb.chzonk.ui.reader

import android.os.Bundle
import android.view.View
import com.teamb.chzonk.Constants.ARG_BOOK
import com.teamb.chzonk.Constants.ARG_POSITION
import com.teamb.chzonk.data.model.Book
import com.teamb.chzonk.data.model.GlideModel
import kotlinx.android.synthetic.main.image_view.*

class ReaderComicFragmentDual: ReaderComicFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val page0 = getPage0().toInt()
        val page1 = getPage1().toInt()

        // would determine if needs to be single here ideally
        imageView1.loadImage(GlideModel(book, page0, false))
        imageView1.loadImage(GlideModel(book, page0+1, false))
    }

    companion object {
        fun newInstance(book: Book, position: Int): ReaderComicFragmentDual {
            return ReaderComicFragmentDual().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_BOOK, book)
                    putInt(ARG_POSITION, position)
                }
            }
        }
    }
}