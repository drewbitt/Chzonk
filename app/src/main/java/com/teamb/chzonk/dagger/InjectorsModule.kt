package com.teamb.chzonk.dagger

import com.teamb.chzonk.ui.MainActivity
import com.teamb.chzonk.ui.base.BaseActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
@Suppress("FunctionNaming")
@SuppressWarnings("unchecked")
abstract class InjectorsModule {

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun baseActivity(): BaseActivity
}
