package com.teamb.chzonk.dagger

import android.content.Context
import com.teamb.chzonk.util.SharedPrefsHelper
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    // may need scope
    fun provideSharedPreferencesHelper(context: Context) = SharedPrefsHelper(context)
}
