package com.teamb.chzonk.ui.library

import android.app.Activity
import android.os.Handler
import androidx.leanback.app.RowsFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.teamb.chzonk.DaggerApp
import com.teamb.chzonk.data.ViewModel
import com.teamb.chzonk.data.model.Book
import com.teamb.chzonk.data.model.GlideModel
import com.teamb.chzonk.ui.MainActivity
import com.teamb.chzonk.ui.library.model.Card
import com.teamb.chzonk.ui.library.model.CardRow
import com.teamb.chzonk.util.fileListToBookList
import javax.inject.Inject

class LibraryFragment : RowsFragment() {
    private val mRowsAdapter: ArrayObjectAdapter

    init {
        val selector = ListRowPresenter()
        selector.setNumRows(2)
        mRowsAdapter = ArrayObjectAdapter(selector)
        adapter = mRowsAdapter

        DaggerApp.appComponent.inject(this)
    }

    @Inject
    lateinit var viewModel: ViewModel

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        Handler().postDelayed({ loadData() }, 200)
    }

    private fun loadData() {
        viewModel.getFileListLiveData().observe(MainActivity(), Observer { result ->
            result?.let {
                val cardRow = fillCardRow(result.fileListToBookList())
                mRowsAdapter.add(createCardRow(cardRow))
                mainFragmentAdapter.fragmentHost.notifyDataReady(mainFragmentAdapter)
            }
        })
    }

    private fun fillCardRow(list: List<Book>): CardRow {
        val listCards = mutableListOf<Card>()
        list.forEach {
            val card = Card(it)
            loadImage(it, card)
            listCards.add(card)
        }
        return CardRow(listCards)
    }

    private fun loadImage(book: Book, card: Card) {
        val glideBook = GlideModel(book, 0, true)

        Glide.with(MainActivity()) // I use MainActivity here and in Card, createCardRow etc... uhh
            .load(glideBook)
            .apply(RequestOptions()
                .fitCenter())
            .into(card.image)
    }

    private fun createCardRow(cardRow: CardRow): ListRow {
        val iconCardPresenter = LibraryPresenter(MainActivity())
        val adapter = ArrayObjectAdapter()
        cardRow.cards.forEach {
            adapter.add(it)
        }

        val headerItem = HeaderItem("TEST TITLE")
        return CardListRow(headerItem, adapter, cardRow)
    }
}
