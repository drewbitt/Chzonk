package com.teamb.chzonk.util

import com.bumptech.glide.load.Options
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.ModelLoaderFactory
import com.bumptech.glide.load.model.MultiModelLoaderFactory
import com.bumptech.glide.signature.ObjectKey
import com.teamb.chzonk.data.model.GlideModel
import java.io.InputStream

internal class LocallibFactory : ModelLoader<GlideModel, InputStream> {
    override fun handles(model: GlideModel): Boolean {
        return true
    }

    override fun buildLoadData(
        model: GlideModel,
        width: Int,
        height: Int,
        options: Options
    ): ModelLoader.LoadData<InputStream>? {
        val key = ObjectKey("${model.book.filePath}:${model.position}")
        return ModelLoader.LoadData(key, LocallibDataFetcher(model))
    }
    internal class Factory : ModelLoaderFactory<GlideModel, InputStream> {
        override fun build(multiFactory: MultiModelLoaderFactory): ModelLoader<GlideModel, InputStream> {
            return LocallibFactory()
        }

        override fun teardown() {
            TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
        }
    }
}
