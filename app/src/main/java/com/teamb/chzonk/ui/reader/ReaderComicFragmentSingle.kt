package com.teamb.chzonk.ui.reader

import android.os.Bundle
import android.view.View
import com.teamb.chzonk.Constants
import com.teamb.chzonk.data.model.Book
import com.teamb.chzonk.data.model.GlideModel
import kotlinx.android.synthetic.main.image_view.*

class ReaderComicFragmentSingle: ReaderComicFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val page0 = getPage0().toInt()
        imageView2.visibility = View.GONE
        imageView1.loadImage(GlideModel(book, page0, false))
    }

    companion object {
        fun newInstance(book: Book, position: Int): ReaderComicFragmentSingle {
            return ReaderComicFragmentSingle().apply {
                arguments = Bundle().apply {
                    putParcelable(Constants.ARG_BOOK, book)
                    putInt(Constants.ARG_POSITION, position)
                }
            }
        }
    }

}