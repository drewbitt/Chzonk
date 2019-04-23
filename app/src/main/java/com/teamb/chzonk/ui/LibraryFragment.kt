package com.teamb.chzonk.ui

//import android.content.Context
import android.database.Cursor
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.DisplayMetrics
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.leanback.app.BackgroundManager
//import androidx.leanback.app.VerticalGridFragment
import androidx.leanback.app.VerticalGridSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
//import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.OnItemViewSelectedListener
import androidx.leanback.widget.Presenter
import androidx.leanback.widget.Row
import androidx.leanback.widget.RowPresenter
import androidx.leanback.widget.VerticalGridPresenter
//import androidx.lifecycle.MutableLiveData
//import com.bumptech.glide.Glide
import com.teamb.chzonk.R
//import com.teamb.chzonk.data.glide.ComicGlideModule
//import com.teamb.chzonk.data.model.Book
//import com.teamb.chzonk.data.model.BookData
//import com.teamb.chzonk.data.repository.FileRepository
//import com.teamb.chzonk.data.repository.LocallibRepository
//import com.teamb.locallib.Main
////import org.jetbrains.anko.image
//import timber.log.Timber

import androidx.leanback.widget.OnItemViewClickedListener
//import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
//import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.ViewModelProviders
//import com.bumptech.glide.Glide
//import com.bumptech.glide.request.RequestOptions
//import com.teamb.chzonk.data.glide.LocallibDataFetcher
//import com.teamb.chzonk.data.model.ComicFile
//import org.jetbrains.annotations.NotNull
import java.util.Timer

class LibraryFragment : VerticalGridSupportFragment(), LoaderManager.LoaderCallbacks<Cursor> {

    private var numColumns = 4
    private val comicAdapter = ArrayObjectAdapter(ComicCardPresenter())
    private val gridPresenterC = VerticalGridPresenter()
    private lateinit var mBackgroundManager: BackgroundManager
    private var mDefaultBackground: Drawable? = null
    private lateinit var mMetrics: DisplayMetrics
    private var mBackgroundTimer: Timer? = null
    private lateinit var viewModel:ViewModel
//    private lateinit var comicList: MutableLiveData<List<Book>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Library"   //Can also set a badge icon for the title
//        prepareBackgroundManager()
//        if (savedInstanceState == null) {
//            prepareEntranceTransition()
//        }
        setupFragment()
//        setupEventListeners()
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProviders.of(activity!!).get(ViewModel::class.java)
//    }

    private fun prepareBackgroundManager() {

        mBackgroundManager = BackgroundManager.getInstance(activity)
        mBackgroundManager.attach(activity!!.window)
        mDefaultBackground = ContextCompat.getDrawable(context!!, R.drawable.default_background)
        mMetrics = DisplayMetrics()
        activity!!.windowManager.defaultDisplay.getMetrics(mMetrics)
    }

    private fun setupFragment() {
        val gridPresenter = VerticalGridPresenter()
        gridPresenter.numberOfColumns = numColumns
        setGridPresenter(gridPresenter)
        loaderManager.initLoader(1, null, this)
//        var comicAdapter = ArrayObjectAdapter(ComicCardPresenter())
//        gridPresenter = gridPresenterC
//        val c = ComicFile(0,0,"TempBook", " d", 0, 20, "/sdcard/Download/The Darkness - Superman 001 (2005) (Troll-DCP).cbr")
//        FileRepository.InsertFile(c)
//        try {
//            viewModel?.
//            comicList = viewModel.getFileListLiveData()
//        }catch (e: Exception){
//            Timber.e("Retrieving comic lists from FileRepository failed[${e.message}]")
//        }
////        comicList.value!!.size
//        for (comic in comicList.value!!.listIterator()) {
//            Glide.with(context!!)
//                .load(comic)
//                .apply(RequestOptions.errorOf(R.drawable.default_background))
//                .into()
//            comicAdapter.add(comic.currentPage)
////            Need to change, currently book (file paths)
//        }
//        comicAdapter.add(context!!.getDrawable(R.drawable.default_image))
//        comicAdapter.add(context!!.getDrawable(R.drawable.default_image))
//        comicAdapter.add(context!!.getDrawable(R.drawable.default_image))
//        comicAdapter.add(context!!.getDrawable(R.drawable.default_image))
//        comicAdapter.add(context!!.getDrawable(R.drawable.default_image))
//        comicAdapter.add(context!!.getDrawable(R.drawable.default_image))
//        comicAdapter.add(context!!.getDrawable(R.drawable.default_image))
//        comicAdapter.add(context!!.getDrawable(R.drawable.default_image))
//        adapter = comicAdapter
    }

    private inner class ItemViewSelectedListener : OnItemViewSelectedListener {
        override fun onItemSelected(
            itemViewHolder: Presenter.ViewHolder,
            item: Any,
            rowViewHolder: RowPresenter.ViewHolder,
            row: Row
        ) {
        }
    }

    private inner class ItemViewClickedListener : OnItemViewClickedListener {
        override fun onItemClicked(
            itemViewHolder: Presenter.ViewHolder, item: Any,
            rowViewHolder: RowPresenter.ViewHolder, row: Row
        ) {
            // write program here
        }
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
