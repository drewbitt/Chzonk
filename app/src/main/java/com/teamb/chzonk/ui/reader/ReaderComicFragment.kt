package com.teamb.chzonk.ui.reader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.request.RequestOptions
import com.teamb.chzonk.Constants.ARG_BOOK
import com.teamb.chzonk.Constants.ARG_POSITION
import com.teamb.chzonk.DaggerApp
import com.teamb.chzonk.R
import com.teamb.chzonk.data.ReaderViewModel
import com.teamb.chzonk.data.ViewModel
import com.teamb.chzonk.data.model.Book
import com.teamb.chzonk.data.model.GlideModel
import javax.inject.Inject

open class ReaderComicFragment : Fragment() {

    @Inject
    lateinit var viewModel: ViewModel

    lateinit var book: Book
    lateinit var readerComicActivity: ReaderComicActivity
    lateinit var readerViewModel: ReaderViewModel
    private var isOnePageSetUp: Boolean = false

    protected var position = 0

    init {
        DaggerApp.appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        readerComicActivity = activity as ReaderComicActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState:
    Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        // Creates the view controlled by the fragment
        val view = inflater.inflate(R.layout.fragment_comic_reader, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        readerViewModel = ViewModelProviders.of(activity as ReaderComicActivity).get(ReaderViewModel::class.java)

        val pageObserver = Observer<Boolean>() {
            loadImages(view!!)
            calculateNextPageToShow()
        }

        readerViewModel.getIsSinglePageView().observe(this, pageObserver)

        arguments?.apply {
            position = getInt(ARG_POSITION)
            book = getParcelable(ARG_BOOK)!!
        }

        readerViewModel.currentPage.value = position
        loadImages(view!!)


    }


    private fun loadImages(view: View) {
        if (readerViewModel.getIsSinglePageView().value!!) {
            onePageImageLoad(view)
        } else {
            dualPageImageLoad(view)
        }
    }

    private fun onePageImageLoad(view: View) {
        val page0 = try {
            getPage0().toInt()
        } catch (e: NumberFormatException) {
            0
        }

        // load GlideImage
        // needs reference to imageView
        val imageView1 = view.findViewById<View>(R.id.imageView1)
        val imageView2 = view.findViewById<View>(R.id.imageView2)
        imageView2.visibility = GONE
        (imageView1 as ImageView).loadImage(GlideModel(book, page0, false))
    }

    private fun dualPageImageLoad(view: View) {
        val page0 = try {
            getPage0().toInt()
        } catch (e: NumberFormatException) {
            0
        }
        val imageView1 = view.findViewById<View>(R.id.imageView1)
        val imageView2 = view.findViewById<View>(R.id.imageView2)
        imageView2.visibility = VISIBLE
        (imageView1 as ImageView).loadImage(GlideModel(book, page0, false))


        if (page0 + 1 < viewModel.getReaderListSize(book)) {
            imageView2.visibility = VISIBLE
            (imageView2 as ImageView).loadImage(GlideModel(book, page0 + 1, false))
        } else {
            imageView2.visibility = GONE
        }
    }

    private fun getPage0() = viewModel.getReaderItemAt(book, position)?.page0 ?: ""

    protected fun ImageView.loadImage(source: Any) {

        val requestOptions = RequestOptions()
            .format(DecodeFormat.PREFER_RGB_565)
            .fitCenter()
            //.override(Target.SIZE_ORIGINAL)
            .skipMemoryCache(true)

        Glide.with(this@ReaderComicFragment)
            .asBitmap()
            .load(source)
            .apply(requestOptions)
            .into(this)
    }

    private fun calculateNextPageToShow() {
        if (view!!.findViewById<View>(R.id.imageView2).visibility == VISIBLE) {
            readerViewModel.nextPageToShow.value = getPage0().toInt() + 2
        } else {
            readerViewModel.nextPageToShow.value = getPage0().toInt() + 1
        }

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

