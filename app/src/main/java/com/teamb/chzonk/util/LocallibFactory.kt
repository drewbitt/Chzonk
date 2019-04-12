package com.teamb.chzonk.util

import com.bumptech.glide.load.Options
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.ModelLoaderFactory
import com.bumptech.glide.load.model.MultiModelLoaderFactory
import com.bumptech.glide.signature.ObjectKey
import com.teamb.chzonk.data.model.Glide
import java.io.InputStream

internal class LocallibFactory : ModelLoader<Glide, InputStream> {
    override fun handles(model: Glide?): Boolean {
        return true
    }

    override fun buildLoadData(
        model: Glide,
        width: Int,
        height: Int,
        options: Options
    ): ModelLoader.LoadData<InputStream>? {
        val key = ObjectKey("${model.book.filePath}:${model.position}")
        return ModelLoader.LoadData(key, LocallibDataFetcher(model))
    }
    internal class Factory : ModelLoaderFactory<Glide, InputStream> {
        override fun build(multiFactory: MultiModelLoaderFactory?): ModelLoader<Glide, InputStream> {
            return LocallibFactory()
        }

        override fun teardown() {
            TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
        }
    }
}
