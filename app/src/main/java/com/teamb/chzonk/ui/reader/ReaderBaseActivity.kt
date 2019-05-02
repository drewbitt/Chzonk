package com.teamb.chzonk.ui.reader

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.teamb.chzonk.R
import com.teamb.chzonk.data.model.Page
import com.teamb.chzonk.ui.base.BaseActivity

@SuppressLint("Registered")
open class ReaderBaseActivity : BaseActivity() {
    // Skeleton for things that would go in a base activity and could be extended in the future
    // bindview stuff here, internal vars, helper functions

    private val dualPaneLiveData = MutableLiveData<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reader)
        title = currentBook.title
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        title = currentBook.title
    }

    protected fun populateSinglePanelList() {
        viewModel.setSingleList(viewModel.createList(currentBook))
    }

    private fun List<Page>.toLocalDualPaneList() = viewModel.singleToDualLocal(this).apply {
        (this as MutableLiveData<List<Page>>).observe(this@ReaderBaseActivity, Observer { result -> result?.let { handleDualPaneResult(it) } })
    }

    private fun handleDualPaneResult(list: List<Page>) {
        viewModel.setDualList(list)
        dualPaneLiveData.value = true
    }

    protected fun populateDualPaneList(): MutableLiveData<Boolean> {
        // if (dualPaneLiveData.hasObservers()) dualPaneLiveData.removeObservers(life)
        viewModel.getSingleList().toLocalDualPaneList()
        return dualPaneLiveData
    }

/*    protected fun finishBook() {
        currentBook.isFinished = true // is that enough?
    }*/

    internal open fun goToPreviousPage() {
        //override in children
    }

    internal open fun goToNextPage() {
        //override in children
    }

}
