package com.teamb.chzonk.dagger

import android.content.Context
import com.teamb.chzonk.DaggerApp
import com.teamb.chzonk.ui.reader.ReaderComicAdapter
import com.teamb.chzonk.ui.settings.SettingsFragment
import com.teamb.chzonk.util.ComicGlideModule
import com.teamb.chzonk.util.LocallibDataFetcher
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@Component(modules = [(AndroidInjectionModule::class), (AppModule::class), (InjectorsModule::class)])
interface AppComponent : AndroidInjector<DaggerApp> {

    override fun inject(daggerApp: DaggerApp)

    fun inject(prefsFragment: SettingsFragment.PrefsFragment)
    fun inject(readerComicAdapter: ReaderComicAdapter)
    fun inject(locallibDataFetcher: LocallibDataFetcher)
    fun inject(comicGlideModule: ComicGlideModule)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }
}
