package com.teamb.chzonk.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.leanback.app.VerticalGridFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import androidx.leanback.widget.VerticalGridPresenter
import androidx.lifecycle.MutableLiveData
import com.teamb.chzonk.R
import com.teamb.chzonk.data.model.Book
import com.teamb.chzonk.data.repository.FileRepository
import org.jetbrains.anko.image
import timber.log.Timber

class LibraryFragment : VerticalGridFragment(){

    private var numColumns = 4
    private var comicAdapter = ArrayObjectAdapter(ComicCardPresenter())

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    private fun setupFragment() {
        val gridPresenter = VerticalGridPresenter()
        gridPresenter.numberOfColumns = numColumns
        setGridPresenter(gridPresenter)
        var comicList = MutableLiveData<List<Book>>()
        try {
            comicList = FileRepository.GetList().liveData
        }catch (e: Exception){
            Timber.e("Retrieving comic lists from FileRepository failed[${e.message}]")
        }
        for (comic in comicList.value!!.listIterator()) {
            comicAdapter.add(comic)
        }
        adapter = comicAdapter
    }

    private inner class ComicCardPresenter : Presenter() {

        private lateinit var mContext: Context
        private lateinit var defaultComicImage: Drawable

        override fun onCreateViewHolder(parent: ViewGroup): Presenter.ViewHolder {
            mContext = parent.context
            defaultComicImage = mContext.getDrawable(R.drawable.default_image)!!

            val comicView = object : ImageView(context) {
                override fun setSelected(selected: Boolean) {
                    val selectedBackground = context.getColor(R.color.selected_background)
                    val defaultBackground = context.getColor(R.color.default_background)
                    val color = if (selected) selectedBackground else defaultBackground
                    findViewById<View>(R.id.info_field).setBackgroundColor(color)
                    super.setSelected(selected)
                }
            }
            comicView.isFocusable = true
            comicView.isFocusableInTouchMode = true
            comicView.layoutParams = ViewGroup.LayoutParams(GRID_ITEM_WIDTH, GRID_ITEM_HEIGHT)
            return Presenter.ViewHolder(comicView)

//            val view = TextView(parent.context)
//            view.layoutParams = ViewGroup.LayoutParams(GRID_ITEM_WIDTH, GRID_ITEM_HEIGHT)
//            view.isFocusable = true
//            view.isFocusableInTouchMode = true
//            view.setBackgroundColor(ContextCompat.getColor(context!!, R.color.default_background))
//            view.setTextColor(Color.WHITE)
//            view.gravity = Gravity.CENTER
//            return Presenter.ViewHolder(view)
        }

        override fun onBindViewHolder(viewHolder: Presenter.ViewHolder, item: Any) {
            (viewHolder.view as ImageView).image = item as Drawable
        }

        override fun onUnbindViewHolder(viewHolder: Presenter.ViewHolder) {}
    }

    companion object {
        private const val GRID_ITEM_WIDTH = 200
        private const val GRID_ITEM_HEIGHT = 200
        private const val NUM_ROWS = 6
    }

}
