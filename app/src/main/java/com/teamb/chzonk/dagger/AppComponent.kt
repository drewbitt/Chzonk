package com.teamb.chzonk.dagger

import android.content.Context
import com.teamb.chzonk.DaggerApp
import com.teamb.chzonk.data.glide.ComicGlideModule
import com.teamb.chzonk.data.glide.LocallibDataFetcher
import com.teamb.chzonk.data.repository.FileRepository
import com.teamb.chzonk.ui.MainFragment
import com.teamb.chzonk.ui.reader.ReaderComicAdapter
import com.teamb.chzonk.ui.settings.SettingsFragment
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
    fun inject(fileRepository: FileRepository)
    fun inject(mainFragment: MainFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }
}
