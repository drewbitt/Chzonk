package com.teamb.chzonk.ui.reader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.request.RequestOptions
import com.teamb.chzonk.Constants.ARG_BOOK
import com.teamb.chzonk.Constants.ARG_POSITION
import com.teamb.chzonk.DaggerApp
import com.teamb.chzonk.R
import com.teamb.chzonk.data.ViewModel
import com.teamb.chzonk.data.model.Book
import javax.inject.Inject

open class ReaderComicFragment : Fragment() {

    @Inject
    lateinit var viewModel: ViewModel

    lateinit var book: Book
    lateinit var readerComicActivity: ReaderComicActivity
    private var isOnePageSetUp: Boolean = false

    protected var position = 0

    init {
        DaggerApp.appComponent.inject(this)
    }

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
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState:
            Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        // Creates the view controlled by the fragment
        val view = inflater.inflate(R.layout.fragment_comic_reader, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val pageObserver = Observer<Boolean>() {
            // loadImages(view!!)
            // calculateNextPageToShow()
        }

        // readerViewModel.currentPage.value = position
        // loadImages(view!!)
    }

    protected fun getPage0() = viewModel.getReaderItemAt(book, position)?.page0 ?: ""
    protected fun getPage1() = viewModel.getReaderItemAt(book, position)?.page1 ?: ""

    protected fun ImageView.loadImage(source: Any) {

        val requestOptions = RequestOptions()
            .format(DecodeFormat.PREFER_RGB_565)
            .fitCenter()
            // .override(Target.SIZE_ORIGINAL)
            .skipMemoryCache(true)

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
