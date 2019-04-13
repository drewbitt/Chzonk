package com.teamb.chzonk.ui.base;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;
import dagger.internal.Beta;
import javax.inject.Inject;


// A copy of DaggerActivity but extending FragmentActivity instead
@Beta
public abstract class NewDaggerActivity extends FragmentActivity implements HasFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public AndroidInjector<Fragment> fragmentInjector() {
        return fragmentInjector;
    }
}