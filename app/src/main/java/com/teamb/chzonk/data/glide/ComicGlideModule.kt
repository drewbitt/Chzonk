package com.teamb.chzonk.data.glide

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.teamb.chzonk.data.model.GlideModel
import timber.log.Timber
import java.io.InputStream

@GlideModule
class ComicGlideModule : AppGlideModule() {

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        super.registerComponents(context, glide, registry)
        val locallibFactory = LocallibFactory.Factory()
        Timber.log(1, "Hit")
        registry.replace(GlideModel::class.java, InputStream::class.java, locallibFactory)
    }
    override fun isManifestParsingEnabled(): Boolean {
        return false
    }
}
