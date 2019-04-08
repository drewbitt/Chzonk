package com.teamb.chzonk.dagger

import com.teamb.chzonk.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
@Suppress("FunctionNaming")
@SuppressWarnings("unchecked")
abstract class InjectorsModule {

    @ContributesAndroidInjector()
    abstract fun MainActivity(): MainActivity
}
