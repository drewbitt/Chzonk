package com.teamb.chzonk.ui

//import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
//import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.request.RequestOptions
//import com.bumptech.glide.Glide
//import com.bumptech.glide.request.RequestOptions
import com.teamb.chzonk.R
//import com.teamb.chzonk.data.glide.ComicGlideModule
import com.teamb.chzonk.data.model.Book
import com.teamb.chzonk.data.model.GlideModel

//import com.teamb.chzonk.data.model.BookData

//import android.R


//import org.jetbrains.anko.image

class ComicCardPresenter : Presenter() {

    private var mSelectedBackgroundColor = -1
    private var mDefaultBackgroundColor = -1
    private var mDefaultCardImage: Drawable? = null
    private lateinit var cardView: ImageCardView
    private val width = 600
    private val height = 600

    override fun onCreateViewHolder(parent: ViewGroup): Presenter.ViewHolder {
        mDefaultBackgroundColor = ContextCompat.getColor(parent.context, R.color.default_background)
        mSelectedBackgroundColor = ContextCompat.getColor(parent.context, R.color.selected_background)
        mDefaultCardImage = parent.resources.getDrawable(R.drawable.default_image, null)

        val cardView = object : ImageCardView(parent.context) {
            override fun setSelected(selected: Boolean) {
                updateCardBackgroundColor(this, selected)
                super.setSelected(selected)
            }
        }

        cardView.layoutParams = ViewGroup.LayoutParams(600, 600)
        cardView.isFocusable = true
        cardView.isFocusableInTouchMode = true
        updateCardBackgroundColor(cardView, false)
        return Presenter.ViewHolder(cardView)
    }

    private fun updateCardBackgroundColor(view: ImageCardView, selected: Boolean) {
        val color = if (selected) mSelectedBackgroundColor else mDefaultBackgroundColor

        // Both background colors should be set because the view's
        // background is temporarily visible during animations.
        view.setBackgroundColor(color)
        view.findViewById<View>(com.teamb.chzonk.R.id.info_field).setBackgroundColor(color)
    }

    override fun onBindViewHolder(viewHolder: Presenter.ViewHolder, item: Any) {
        val comic = item as Drawable
        val book = Book()
        book.autoId = 0
        book.id = 1002
        book.title = "TestBoi"
        book.content = "Test"
        book.currentPage = 0
        book.totalPages = 20
        book.isFinished = false
        book.filePath = "/sdcard/Download/The Darkness - Superman 001 (2005) (Troll-DCP).cbr"
        cardView = viewHolder.view as ImageCardView
        cardView.setMainImageDimensions(600,600)
        val options = RequestOptions().format(DecodeFormat.PREFER_RGB_565).skipMemoryCache(true)
        Glide.with(cardView.context)
            .asBitmap()
            .load(GlideModel(book, 0, true))
            .apply(options)
            .into(cardView.mainImageView)


//        (viewHolder.view as ImageView).image = item as Drawable
    }

    override fun onUnbindViewHolder(viewHolder: Presenter.ViewHolder) {
        val cardView = viewHolder.view as ImageCardView

        // Remove references to images so that the garbage collector can free up memory.
        cardView.badgeImage = null
        cardView.mainImage = null
    }
    fun getCardView(): ImageCardView{
        return cardView
    }
}
