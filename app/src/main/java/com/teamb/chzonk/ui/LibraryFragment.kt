package com.teamb.chzonk.ui


import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.core.content.ContextCompat
import androidx.leanback.app.BackgroundManager
import androidx.leanback.app.VerticalGridSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.OnItemViewSelectedListener
import androidx.leanback.widget.Presenter
import androidx.leanback.widget.Row
import androidx.leanback.widget.RowPresenter
import androidx.leanback.widget.VerticalGridPresenter
import com.bumptech.glide.Glide
import com.teamb.chzonk.R
import com.teamb.chzonk.data.model.Book
import androidx.leanback.widget.OnItemViewClickedListener
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.request.RequestOptions
import com.teamb.chzonk.DaggerApp
import com.teamb.chzonk.data.ViewModel
import com.teamb.chzonk.data.model.GlideModel
import java.util.Timer
import javax.inject.Inject

class LibraryFragment : VerticalGridSupportFragment() {

    private var numColumns = 4
    private val comicAdapter = ArrayObjectAdapter(ComicCardPresenter())
    private val gridPresenterC = VerticalGridPresenter()
    private lateinit var mBackgroundManager: BackgroundManager
    private var mDefaultBackground: Drawable? = null
    private lateinit var mMetrics: DisplayMetrics
    private var mBackgroundTimer: Timer? = null
//    private lateinit var viewModel:ViewModel
    private lateinit var comicList: List<Book>

    init {
        DaggerApp.appComponent.inject(this)
    }
    @Inject
    lateinit var viewModel: ViewModel

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
        val book = Book()
        book.autoId = 0
        book.id = 1002
        book.title = "TestBoi"
        book.content = "Test"
        book.currentPage = 0
        book.totalPages = 20
        book.isFinished = false
        book.filePath = "/sdcard/Download/The Darkness - Superman 001 (2005) (Troll-DCP).cbr"
//        ChzonkDatabase().fileDao().insertFile(book)

        var comicAdapter = ArrayObjectAdapter(ComicCardPresenter())
//        comicList = viewModel.getFileList().value!!
//        gridPresenter = gridPresenterC
//        val c = ComicFile(0,0,"TempBook", " d", 0, 20, "/sdcard/Download/The Darkness - Superman 001 (2005) (Troll-DCP).cbr")
//        FileRepository.InsertFile(c)
//        try {
//            comicList = viewModel.getFileList().value!!
//        }catch (e: Exception){
//            Timber.e("Retrieving comic lists from FileRepository failed[${e.message}]")
//        }
////        comicList.value!!.size
//        for (book in comicList) {
//            val firstPage = viewModel.createList(book)[0]
//            firstPage.page0
            val options = RequestOptions().format(DecodeFormat.PREFER_RGB_565).skipMemoryCache(true)
//            Glide.with(context!!)
//                .asBitmap()
//                .load(GlideModel(book, 0, true))
//                .apply(options)
//                .into()

//            comicAdapter.add(GlideModel(book, 0, true))
            comicAdapter.add(Glide.with(ComicCardPresenter().getCardView().context!!)
                .asBitmap()
                .load(GlideModel(book, 0, true))
                .apply(options)
                .into(ComicCardPresenter().getCardView().mainImageView)
            )

//            comicAdapter.add(comic.currentPage)
//                    Glide.with(cardView.context)
//                    .asBitmap()
//                    .load(GlideModel(book, 0, true))
//                    .apply(options)
//                    .into(cardView.mainImageView)

//            Need to change, currently book (file paths)
//        }
//        comicAdapter.add(context!!.getDrawable(R.drawable.default_image))
//        comicAdapter.add(context!!.getDrawable(R.drawable.default_image))
//        comicAdapter.add(context!!.getDrawable(R.drawable.default_image))
//        comicAdapter.add(context!!.getDrawable(R.drawable.default_image))
//        comicAdapter.add(context!!.getDrawable(R.drawable.default_image))
//        comicAdapter.add(context!!.getDrawable(R.drawable.default_image))
//        comicAdapter.add(context!!.getDrawable(R.drawable.default_image))
//        comicAdapter.add(context!!.getDrawable(R.drawable.default_image))
        adapter = comicAdapter
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
}
