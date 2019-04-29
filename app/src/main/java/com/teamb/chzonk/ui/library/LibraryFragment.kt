package com.teamb.chzonk.ui.library

import android.os.Bundle
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
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
                val cardRow = fillCardRow(result.fileListToBookList())
//                mRowsAdapter.add(createCardRow(cardRow))
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

    private fun createCardRow(cardRow: CardRow)/*: ListRow*/ {
//        val cardPresenter = LibraryPresenter(activity!!)
        val cardPresenter = LibraryPresenter(activity!!)
        var categoryName = "Library"
        var adapter = ArrayObjectAdapter(cardPresenter)
//        cardRow.cards.forEach {
        for (i in 1..cardRow.cards.size step 1){
            adapter.add(cardRow.cards[i-1])
            if (i%4 == 0){
                val headerItem = HeaderItem(categoryName)
//                adapter.add(cardRow.cards[j])
                mRowsAdapter.add(CardListRow(headerItem, adapter, cardRow))
                categoryName = ""
                adapter = ArrayObjectAdapter(cardPresenter)
            }else if ( i == cardRow.cards.size){
                val headerItem = HeaderItem(categoryName)
                mRowsAdapter.add(CardListRow(headerItem, adapter, cardRow))
            }
        }

//        val headerItem = HeaderItem(categoryName)
//        return CardListRow(headerItem, adapter, cardRow)
    }
}
