package com.teamb.chzonk.dagger

import android.content.Context
import com.teamb.chzonk.data.ViewModel
import com.teamb.chzonk.data.repository.LocallibRepository
import com.teamb.chzonk.data.repository.ReaderRepository
import com.teamb.chzonk.util.SharedPrefsHelper
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideSharedPreferencesHelper(context: Context) = SharedPrefsHelper(context)

    @Provides
    fun provideViewModel(locallibRepository: LocallibRepository, readerRepository: ReaderRepository) =
        ViewModel(locallibRepository, readerRepository)
}
