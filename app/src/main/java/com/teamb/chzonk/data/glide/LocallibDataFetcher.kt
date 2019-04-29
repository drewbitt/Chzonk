package com.teamb.chzonk.data.glide

import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.data.DataFetcher
import com.teamb.chzonk.DaggerApp
import com.teamb.chzonk.data.ViewModel
import com.teamb.chzonk.data.model.GlideModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.InputStream
import javax.inject.Inject

class LocallibDataFetcher internal constructor(private val glideModel: GlideModel) : DataFetcher<InputStream> {

    init {
        DaggerApp.appComponent.inject(this)
    }

    @Inject
    lateinit var viewModel: ViewModel
    lateinit var inputStream: InputStream

    override fun getDataClass(): Class<InputStream> = InputStream::class.java

    override fun cleanup() {
        try {
            inputStream.close()
        } catch (e: Exception) { Timber.e("Data fetching could not close when cleaning up[${e.message}]") }
    }

    override fun getDataSource(): DataSource = DataSource.LOCAL

    override fun cancel() {
        try {
            inputStream.close()
        } catch (e: Exception) {
            Timber.e("Data fetching could not close when cancelling[${e.message}]")
        }
    }

    override fun loadData(priority: Priority, callback: DataFetcher.DataCallback<in InputStream>) {
        when (glideModel.loadSingleInstance) {
            true -> singleInstance(callback)
            false -> multipleInstance(callback)
        }
    }

    private fun singleInstance(callback: DataFetcher.DataCallback<in InputStream>) {
        GlobalScope.launch(Dispatchers.Main) {
            viewModel.getLocalImageInputStreamSingleInstance(glideModel.book.filePath, glideModel.position)
                .observeForever { result -> handleResult(callback, result)
            }
        }
    }

    private fun multipleInstance(callback: DataFetcher.DataCallback<in InputStream>) {
        GlobalScope.launch(Dispatchers.Main) {
            viewModel.getLocalImageInputStream(glideModel.position).observeForever {
                    result -> handleResult(callback, result)
            }
        }
    }

    private fun handleResult(callback: DataFetcher.DataCallback<in InputStream>, result: InputStream?) {
        if (result == null) {
            callback.onLoadFailed(java.lang.Exception("Load Failed"))
        } else {
            inputStream = result
            callback.onDataReady(inputStream)
        }
    }
}
