package com.teamb.chzonk.data.glide

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator
import com.bumptech.glide.module.AppGlideModule
import com.teamb.chzonk.data.model.GlideModel
import java.io.InputStream

@GlideModule
class ComicGlideModule : AppGlideModule() {

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        val locallibFactory = LocallibFactory.Factory()
        registry.replace(GlideModel::class.java, InputStream::class.java, locallibFactory)
    }

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)

        val diskCacheSize = (10 * 104 * 1024).toLong() // 10mb
        val diskCacheFolder = Glide.getPhotoCacheDir(context)!!.absolutePath
        builder.setDiskCache(DiskLruCacheFactory(diskCacheFolder, diskCacheSize))

        val memoryCacheSize = MemorySizeCalculator.Builder(context)
            .setMemoryCacheScreens(3f)
            .build()
            .memoryCacheSize
            .toLong()
        builder.setMemoryCache(LruResourceCache(memoryCacheSize))
    }

}
