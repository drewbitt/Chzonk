package com.teamb.chzonk.data.repository

import com.teamb.locallib.Main

class LocallibRepository(private val mainLocal: Main) {

    internal fun cleanupParser() = mainLocal.cleanupParser()

    internal fun getLocalImageInputStream(position: Int) = mainLocal.getImageInputStreamAt(position)

    internal fun getLocalImageInputStreamSingleInstance(filePath: String, position: Int) =
        mainLocal.getImageInputStreamSingleInstance(filePath, position)
}
