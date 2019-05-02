package com.teamb.chzonk.ui.library

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.teamb.chzonk.DaggerApp
import com.teamb.chzonk.R
import com.teamb.chzonk.data.ViewModel
import com.teamb.chzonk.data.model.GlideModel
import com.teamb.chzonk.ui.library.model.Card
import javax.inject.Inject

class LibraryPresenter constructor(context: Context, cardThemeResId: Int = R.style.DefaultCardTheme) : Presenter() {
    private val mContext: Context

    init {
        mContext = ContextThemeWrapper(context, cardThemeResId)
        DaggerApp.appComponent.inject(this)
    }
    @Inject
    lateinit var viewModel: ViewModel

    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        val cardView = onCreateView()
        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
        val card = item as Card
        val cardView: ImageCardView = viewHolder?.view as ImageCardView
        cardView.tag = card
        cardView.titleText = card.book.title
        Glide.with(viewHolder.view.context)
            .load(GlideModel(card.book, 0, true))
            .apply(
                RequestOptions()
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
            .into(cardView.mainImageView)
        if (card.book.isFinished) {
            cardView.mainImageView.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY)
            cardView.mainImageView.foreground = mContext.getDrawable(R.drawable.checked)
        }else{
            cardView.mainImageView.colorFilter = null
            cardView.mainImageView.foreground = null
        }

        viewHolder.view.setOnLongClickListener(View.OnLongClickListener {
            val popupView = LayoutInflater.from(mContext).inflate(R.layout.popup_window, null)
            val image = popupView.findViewById<ImageView>(R.id.comicImage)
            val title = popupView.findViewById<View>(R.id.title) as TextView
            val isFinished = popupView.findViewById<View>(R.id.readBookCheck) as CheckBox

            val builder = AlertDialog.Builder(viewHolder.view.context)
            builder.setView(popupView)
                .setPositiveButton("Save") { dialog, which ->
                    card.book.isFinished = isFinished.isChecked
                    viewModel.updateFinished(card.book)
                    if(card.book.isFinished) {
                        cardView.mainImageView.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY)
                        cardView.mainImageView.foreground = mContext.getDrawable(R.drawable.checked)
                        cardView.refreshDrawableState()
                    }else{
                        cardView.mainImageView.colorFilter = null
                        cardView.mainImageView.foreground = null
                        cardView.refreshDrawableState()
                    }
                }
                .setNegativeButton(
                    "Cancel"
                ) { dialog, which -> dialog.cancel() }

            builder.setTitle("Book Info")
            builder.create()

            // Pass book title and isFinished to appropriate box on alert dialog
            Glide.with(viewHolder.view.context)
                .load(GlideModel(card.book, 0, true))
                .apply(
                    RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
                .into(image)
            title.text = card.book.title
            isFinished.isChecked = card.book.isFinished

            builder.show()
            return@OnLongClickListener true
        })
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) = onUnbindViewHolder(viewHolder?.view as ImageCardView)

    private fun onUnbindViewHolder(cardView: ImageCardView) {
        // Nothing to clean up
    }

    private fun onCreateView(): ImageCardView {
        return ImageCardView(mContext)
    }
    private fun clickedLong() {
    }
}
