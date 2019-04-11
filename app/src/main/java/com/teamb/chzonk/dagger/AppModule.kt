package com.teamb.chzonk.dagger

import android.content.Context
import com.teamb.chzonk.data.ViewModel
import com.teamb.chzonk.data.repository.LocallibRepository
import com.teamb.chzonk.data.repository.ReaderRepository
import com.teamb.chzonk.util.AppExecutors
import com.teamb.chzonk.util.SharedPrefsHelper
import com.teamb.locallib.Main
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideSharedPreferencesHelper(context: Context) = SharedPrefsHelper(context)

    @Provides
    fun provideViewModel(locallibRepository: LocallibRepository, readerRepository: ReaderRepository) =
        ViewModel(locallibRepository, readerRepository)

    @Provides
    fun provideLocallibRepository(mainLocal: Main) = LocallibRepository(mainLocal)

    @Provides
    fun provideReaderRepository() = ReaderRepository()// also needs things passed like locallib

    @Provides
    fun provideLocalLib(appExecutors: AppExecutors) = Main(appExecutors.diskIO, appExecutors.mainThread)

    @Provides
    fun provideAppExecutors() = AppExecutors()
}
