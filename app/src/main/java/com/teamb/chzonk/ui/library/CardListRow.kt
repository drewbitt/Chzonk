package com.teamb.chzonk.ui.library

import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ObjectAdapter
import com.teamb.chzonk.ui.library.model.CardRow

class CardListRow(header: HeaderItem, adapter: ObjectAdapter, cardRow: CardRow) : ListRow(header, adapter) {

    var mCardRow: CardRow? = null

    init {
        mCardRow = cardRow
    }
}
