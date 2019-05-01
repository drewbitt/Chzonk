package com.teamb.chzonk.dagger

import com.teamb.chzonk.dagger.scopes.BaseScope
import com.teamb.chzonk.dagger.scopes.MainScope
import com.teamb.chzonk.dagger.scopes.ReaderScope
import com.teamb.chzonk.ui.MainActivity
import com.teamb.chzonk.ui.base.BaseActivity
import com.teamb.chzonk.ui.reader.ReaderBaseActivity
import com.teamb.chzonk.ui.reader.ReaderComicActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class InjectorsModule {

    @ContributesAndroidInjector
    @MainScope
    abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector
    @BaseScope
    abstract fun baseActivity(): BaseActivity

    @ContributesAndroidInjector
    @ReaderScope
    abstract fun readerComicActivity(): ReaderComicActivity

    @ContributesAndroidInjector
    @ReaderScope
    abstract fun readerBaseActivity(): ReaderBaseActivity
}
