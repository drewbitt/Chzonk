package com.teamb.chzonk.dagger

import com.teamb.chzonk.ui.MainActivity
import com.teamb.chzonk.ui.base.BaseActivity
import com.teamb.chzonk.ui.reader.ReaderBaseActivity
import com.teamb.chzonk.ui.reader.ReaderComicActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class InjectorsModule {

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun baseActivity(): BaseActivity

    @ContributesAndroidInjector
    abstract fun readerComicActivity(): ReaderComicActivity

    @ContributesAndroidInjector
    abstract fun readerBaseActivity(): ReaderBaseActivity
}
