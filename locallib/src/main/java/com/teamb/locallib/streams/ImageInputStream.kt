package com.teamb.locallib.streams

import androidx.lifecycle.MutableLiveData
import com.teamb.locallib.Main
import timber.log.Timber
import java.io.InputStream
import java.lang.Exception

class ImageInputStream(main: Main, position: Int) {

    private val parser = main.parser
    internal val liveData = MutableLiveData<InputStream>()

    init {
        main.diskIO.execute {
            try {
                val result = parser.getPage(position)
                main.mainThread.execute {
                    liveData.value = result
                }
            } catch (e: Exception) {
                Timber.e("message[${e.message}]")
                main.mainThread.execute { liveData.value = null }
            } catch (e: OutOfMemoryError) {
                Timber.e("message[${e.message}]")
                main.mainThread.execute { liveData.value = null }
            }
        }
    }
}
