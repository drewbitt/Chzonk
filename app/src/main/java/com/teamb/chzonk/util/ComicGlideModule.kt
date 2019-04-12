package com.teamb.chzonk.util

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.teamb.chzonk.data.model.GlideModel
import java.io.InputStream

@GlideModule
class ComicGlideModule : AppGlideModule() {

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        super.registerComponents(context, glide, registry)
        val locallibFactory = LocallibFactory.Factory()
        registry.replace(GlideModel::class.java, InputStream::class.java, locallibFactory)
    }
}
