package com.example.dj3.di;

import com.example.dj3.di.auth.AuthModule;
import com.example.dj3.di.auth.AuthScope;
import com.example.dj3.di.auth.AuthViewModelsModule;
import com.example.dj3.ui.auth.AuthActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @AuthScope
    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class, AuthModule.class})
    abstract AuthActivity contributeAuthActivity();
}
