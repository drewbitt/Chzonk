package com.teamb.chzonk.util

import com.teamb.chzonk.data.ViewModel
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.data.DataFetcher
import com.teamb.chzonk.DaggerApp
import com.teamb.chzonk.data.model.Glide
import java.io.InputStream
import javax.inject.Inject

class LocallibDataFetcher internal constructor(private val glideModel: Glide) : DataFetcher<InputStream> {
    init {
        DaggerApp.appComponent.inject(this)
    }

    @Inject
    lateinit var viewModel: ViewModel
    lateinit var inputStream: InputStream

    override fun getDataClass(): Class<InputStream> {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun cleanup() {
        try {
            inputStream.close()
        } catch (e: Exception) {}
    }

    override fun getDataSource(): DataSource {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun cancel() {
        try {
            inputStream.close()
        } catch (e: Exception) {}
    }

    override fun loadData(priority: Priority?, callback: DataFetcher.DataCallback<in InputStream>?) {
        when (glideModel.loadSingleInstance) {
            // put stuff
        }
    }

    private fun singleInstance(callback: DataFetcher.DataCallback<in InputStream>) {
    }
}
