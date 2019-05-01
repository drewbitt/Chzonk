package com.teamb.chzonk.dagger

import android.content.Context
import androidx.room.Room
import com.teamb.chzonk.Constants
import com.teamb.chzonk.dagger.scopes.AppScope
import com.teamb.chzonk.data.ViewModel
import com.teamb.chzonk.data.repository.FileRepository
import com.teamb.chzonk.data.repository.LocallibRepository
import com.teamb.chzonk.data.repository.ReaderRepository
import com.teamb.chzonk.data.room.ChzonkDatabase
import com.teamb.chzonk.util.AppExecutors
import com.teamb.chzonk.util.SharedPrefsHelper
import com.teamb.locallib.Main
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    @AppScope
    fun provideDao(context: Context) = Room
        .databaseBuilder(context, ChzonkDatabase::class.java, Constants.DATABASE_NAME)
        .build()
        .fileDao()

    @Provides
    @AppScope
    fun provideSharedPreferencesHelper(context: Context) = SharedPrefsHelper(context)

    @Provides
    @AppScope
    fun provideViewModel(
        fileRepository: FileRepository,
        locallibRepository: LocallibRepository,
        readerRepository: ReaderRepository
    ) = ViewModel(fileRepository, locallibRepository, readerRepository)

    @Provides
    @AppScope
    fun provideLocallibRepository(mainLocal: Main) = LocallibRepository(mainLocal)

    @Provides
    @AppScope
    fun provideReaderRepository(mainLocal: Main) = ReaderRepository(mainLocal)

    @Provides
    @AppScope
    fun provideLocalLib(appExecutors: AppExecutors) = Main(appExecutors.diskIO, appExecutors.mainThread)

    @Provides
    @AppScope
    fun provideAppExecutors() = AppExecutors()

    @Provides
    @AppScope
    fun provideFileRepository() = FileRepository()
}
