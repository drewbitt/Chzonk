package com.teamb.chzonk.util

import android.content.Context
import android.os.Environment
import com.teamb.chzonk.Constants.KEY_DOWNLOAD_DIRECTORY
import com.teamb.chzonk.Constants.KEY_DUAL_PAGES
import com.teamb.chzonk.Settings.DOWNLOAD_DIRECTORY
import com.teamb.chzonk.Settings.DUAL_READER
import org.jetbrains.anko.defaultSharedPreferences

class SharedPrefsHelper(context: Context) {

    private val sharedPreferences = context.defaultSharedPreferences

    fun restoreSettings() {
        DOWNLOAD_DIRECTORY = sharedPreferences.getString(KEY_DOWNLOAD_DIRECTORY,
            Environment.getExternalStorageDirectory().absolutePath + "/Download/")
    }

    fun saveDownloadDirectory() {
        sharedPreferences.edit().putString(KEY_DOWNLOAD_DIRECTORY, DOWNLOAD_DIRECTORY).apply()
    }

    fun saveDualPane() {
        sharedPreferences.edit().putBoolean(KEY_DUAL_PAGES, DUAL_READER).commit()
    }
}
