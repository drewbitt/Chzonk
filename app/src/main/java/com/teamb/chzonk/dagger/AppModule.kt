package com.teamb.chzonk.dagger

import android.content.Context
import androidx.room.Room
import com.teamb.chzonk.Constants
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
    fun provideDao(context: Context) = Room
        .databaseBuilder(context, ChzonkDatabase::class.java, Constants.DATABASE_NAME)
        .build()
        .fileDao()

    @Provides
    fun provideSharedPreferencesHelper(context: Context) = SharedPrefsHelper(context)

    @Provides
    fun provideViewModel(
        fileRepository: FileRepository,
        locallibRepository: LocallibRepository,
        readerRepository: ReaderRepository
    ) = ViewModel(fileRepository, locallibRepository, readerRepository)

    @Provides
    fun provideLocallibRepository(mainLocal: Main) = LocallibRepository(mainLocal)

    @Provides
    fun provideReaderRepository(mainLocal: Main) = ReaderRepository(mainLocal)

    @Provides
    fun provideLocalLib(appExecutors: AppExecutors) = Main(appExecutors.diskIO, appExecutors.mainThread)

    @Provides
    fun provideAppExecutors() = AppExecutors()

    @Provides
    fun provideFileRepository() = FileRepository()
}
