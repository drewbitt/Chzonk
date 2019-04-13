package com.teamb.chzonk

import com.teamb.chzonk.dagger.AppComponent
import com.teamb.chzonk.dagger.DaggerAppComponent
import com.teamb.chzonk.util.SharedPrefsHelper
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject

class DaggerApp : DaggerApplication() {

    @Inject
    lateinit var sharedPrefsHelper: SharedPrefsHelper

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        appComponent = DaggerAppComponent
            .builder()
            .context(this)
            .build()
        return appComponent
    }

    override fun onCreate() {
        super.onCreate()
        sharedPrefsHelper.restoreSettings()
    }

    companion object {
        lateinit var appComponent: AppComponent
    }
}
