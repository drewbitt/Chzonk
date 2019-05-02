package com.teamb.chzonk.dagger

import android.content.Context
import com.teamb.chzonk.DaggerApp
import com.teamb.chzonk.dagger.scopes.AppScope
import com.teamb.chzonk.data.glide.ComicGlideModule
import com.teamb.chzonk.data.glide.LocallibDataFetcher
import com.teamb.chzonk.data.repository.FileRepository
import com.teamb.chzonk.ui.MainFragment
import com.teamb.chzonk.ui.library.LibraryFragment
import com.teamb.chzonk.ui.library.LibraryPresenter
import com.teamb.chzonk.ui.reader.ReaderComicAdapter
import com.teamb.chzonk.ui.reader.ReaderComicFragment
import com.teamb.chzonk.ui.settings.SettingsFragment
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@AppScope
@Component(modules = [(AndroidSupportInjectionModule::class), (AppModule::class), (InjectorsModule::class)])
interface AppComponent : AndroidInjector<DaggerApp> {

    override fun inject(daggerApp: DaggerApp)

    fun inject(comicGlideModule: ComicGlideModule)
    fun inject(fileRepository: FileRepository)
    fun inject(libraryFragment: LibraryFragment)
    fun inject(locallibDataFetcher: LocallibDataFetcher)
    fun inject(mainFragment: MainFragment)
    fun inject(prefsFragment: SettingsFragment.PrefsFragment)
    fun inject(readerComicAdapter: ReaderComicAdapter)
    fun inject(readerComicFragment: ReaderComicFragment)
    fun inject(libraryPresenter: LibraryPresenter)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }
}
