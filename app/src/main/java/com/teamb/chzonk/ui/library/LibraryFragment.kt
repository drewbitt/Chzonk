package com.teamb.chzonk.ui.library

import android.os.Bundle
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRowPresenter
import androidx.lifecycle.Observer
import com.teamb.chzonk.DaggerApp
import com.teamb.chzonk.data.ViewModel
import com.teamb.chzonk.data.model.Book
import com.teamb.chzonk.ui.library.model.Card
import com.teamb.chzonk.ui.library.model.CardRow
import com.teamb.chzonk.util.fileListToBookList
import javax.inject.Inject

class LibraryFragment : RowsSupportFragment(){
    private val mRowsAdapter: ArrayObjectAdapter

    init {
        val selector = ListRowPresenter()
        selector.setNumRows(1)
        mRowsAdapter = ArrayObjectAdapter(selector)
        adapter = mRowsAdapter

        // implement onClickListener here

        DaggerApp.appComponent.inject(this)
    }

    @Inject
    lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle? ) {
        super.onCreate(savedInstanceState)
        createRows()
        mainFragmentAdapter.fragmentHost.notifyDataReady(mainFragmentAdapter)
    }

    private fun createRows() {
        viewModel.getFileListLiveData().observe(this, Observer { result ->
            result?.let {
                var cardRow = fillCardRow(result.fileListToBookList())
                cardRow = sortCardRow(cardRow)
                createCardRow(cardRow)
            }
        })
    }

    private fun fillCardRow(list: List<Book>): CardRow {
        val listCards = mutableListOf<Card>()
        list.forEach {
            val card = Card(it)
            listCards.add(card)
        }
        return CardRow(listCards)
    }

    private fun sortCardRow(cardRow: CardRow): CardRow {
        // sort cardrow by book title
        for (i in 0 until (cardRow.cards.size - 1) step 1)
            for (j in i + 1 until cardRow.cards.size step 1)
                if (cardRow.cards[i].book.title > cardRow.cards[j].book.title) {
                    val temp = cardRow.cards[i].book
                    cardRow.cards[i].book = cardRow.cards[j].book
                    cardRow.cards[j].book = temp
                }
        return cardRow
    }

    private fun createCardRow(cardRow: CardRow) {
        val cardPresenter = LibraryPresenter(activity!!)
        var categoryName = "Library"
        var adapter = ArrayObjectAdapter(cardPresenter)

        // seperate cardrow into cardlistrows of 4 cards
        for (i in 1..cardRow.cards.size step 1){
            adapter.add(cardRow.cards[i-1])
            if (i % 4 == 0){
                val headerItem = HeaderItem(categoryName)
                mRowsAdapter.add(CardListRow(headerItem, adapter, cardRow))
                categoryName = ""
                adapter = ArrayObjectAdapter(cardPresenter)
            }else if ( i == cardRow.cards.size){
                val headerItem = HeaderItem(categoryName)
                mRowsAdapter.add(CardListRow(headerItem, adapter, cardRow))
            }
        }
    }
}
