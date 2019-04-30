package com.teamb.chzonk.ui.reader

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.teamb.chzonk.Constants.ARG_BOOK
import com.teamb.chzonk.Constants.ARG_POSITION
import com.teamb.chzonk.data.ViewModel
import com.teamb.chzonk.data.model.Book
import dagger.android.support.DaggerFragment
import javax.inject.Inject

open class ReaderComicFragment : DaggerFragment() {

    @Inject lateinit var viewModel: ViewModel

    lateinit var book: Book
    lateinit var readerComicActivity: ReaderComicActivity

    protected var position = 0

  /*  override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comic_reader, container, false)
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        readerComicActivity = activity as ReaderComicActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.apply {
            position = getInt(ARG_POSITION)
            book = getParcelable(ARG_BOOK)!!
        }

        val page0 = getPage0()

        // load GlideImage
    }

    private fun getPage0() = viewModel.getReaderItemAt(position)?.page0

    protected fun ImageView.loadImage(source: Any, requestListener: RequestListener<Bitmap>) {

        val requestOptions = RequestOptions()
            .format(DecodeFormat.PREFER_RGB_565)
            .skipMemoryCache(true)
            .override(Target.SIZE_ORIGINAL)

        Glide.with(this@ReaderComicFragment)
            .asBitmap()
            .load(source)
            .apply(requestOptions)
            .listener(requestListener)
            .into(this)
    }

    companion object {
        fun newInstance(book: Book, position: Int): ReaderComicFragment {
            return ReaderComicFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_BOOK, book)
                    putInt(ARG_POSITION, position)
                }
            }
        }
    }

}

// Probably getting pages here, actually loading the images with glide
// I might make this a base class so that you could have separate dual and single panel classes, and bind
// their individual necessary views accordingly.
