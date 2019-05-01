package com.teamb.chzonk.ui.reader

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.teamb.chzonk.Constants.ARG_BOOK
import com.teamb.chzonk.Constants.ARG_POSITION
import com.teamb.chzonk.DaggerApp
import com.teamb.chzonk.R
import com.teamb.chzonk.data.ViewModel
import com.teamb.chzonk.data.model.Book
import dagger.android.support.DaggerFragment
import com.teamb.chzonk.data.model.GlideModel
import com.teamb.chzonk.data.model.Page
import com.teamb.chzonk.ui.base.BaseActivity
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

open class ReaderComicFragment : Fragment() {

    @Inject
    lateinit var viewModel: ViewModel

    lateinit var book: Book
    lateinit var readerComicActivity: ReaderComicActivity

    protected var position = 0

    init {
        DaggerApp.appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        readerComicActivity = activity as ReaderComicActivity
        //viewModel = (activity as BaseActivity).viewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState:
    Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        // Creates the view controlled by the fragment
        val view = inflater.inflate(R.layout.fragment_comic_reader, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.apply {
            position = getInt(ARG_POSITION)
            book = getParcelable(ARG_BOOK)!!
        }

        val page0 = try {
            getPage0().toInt()
        } catch (e: NumberFormatException) {
            0
        }

        // load GlideImage
        // needs reference to imageView
        val imageView = view.findViewById<View>(R.id.imageView)
        (imageView as ImageView).loadImage(GlideModel(book, page0, false))
    }

    private fun getPage0() = viewModel.getReaderItemAt(position)?.page0 ?: ""

    protected fun ImageView.loadImage(source: Any) {

        val requestOptions = RequestOptions()
            .format(DecodeFormat.PREFER_RGB_565)
            .skipMemoryCache(true)
            .override(Target.SIZE_ORIGINAL)

        Glide.with(this@ReaderComicFragment)
            .asBitmap()
            .load(source)
            .apply(requestOptions)
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
