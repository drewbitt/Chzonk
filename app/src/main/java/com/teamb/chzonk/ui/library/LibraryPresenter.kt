package com.teamb.chzonk.ui.library

import android.content.Context
import android.view.ContextThemeWrapper
import android.view.ViewGroup
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import com.teamb.chzonk.R
import com.teamb.chzonk.ui.library.model.Card

class LibraryPresenter constructor(context:Context, cardThemeResId:Int = R.style.DefaultCardTheme): Presenter() {
    private val mContext:Context

    init{
        mContext = ContextThemeWrapper(context, cardThemeResId)
    }

    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        val cardView = onCreateView()
        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
        val card= item as Card
        val cardView: ImageCardView = viewHolder?.view as ImageCardView

        cardView.tag = card
        cardView.mainImage = card.image.drawable
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
       onUnbindViewHolder(viewHolder?.view as ImageCardView)
    }

    fun onUnbindViewHolder(cardView: ImageCardView) {
        // Nothing to clean up
    }

    protected fun onCreateView(): ImageCardView {
        return ImageCardView(mContext)
    }
}