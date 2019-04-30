package com.teamb.chzonk.ui.library

import android.content.Context
import android.view.ContextThemeWrapper
import android.view.ViewGroup
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.teamb.chzonk.R
import com.teamb.chzonk.data.model.GlideModel
import com.teamb.chzonk.ui.library.model.Card

class LibraryPresenter constructor(context: Context, cardThemeResId: Int = R.style.DefaultCardTheme) : Presenter() {
    private val mContext: Context

    init {
        mContext = ContextThemeWrapper(context, cardThemeResId)
    }

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
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) = onUnbindViewHolder(viewHolder?.view as ImageCardView)

    private fun onUnbindViewHolder(cardView: ImageCardView) {
        // Nothing to clean up
    }

    private fun onCreateView(): ImageCardView {
        return ImageCardView(mContext)
    }
}
