package com.example.dj3.di;

import com.example.dj3.di.auth.AuthModule;
import com.example.dj3.di.auth.AuthScope;
import com.example.dj3.di.auth.AuthViewModelsModule;
import com.example.dj3.di.main.MainFragmentBuildersModule;
import com.example.dj3.di.main.MainModule;
import com.example.dj3.di.main.MainScope;
import com.example.dj3.di.main.MainViewModelsModule;
import com.example.dj3.ui.auth.AuthActivity;
import com.example.dj3.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @AuthScope
    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class, AuthModule.class})
    abstract AuthActivity contributeAuthActivity();

    @MainScope
    @ContributesAndroidInjector(
            modules = {MainFragmentBuildersModule.class, MainViewModelsModule.class, MainModule.class})
    abstract MainActivity contributeMainActivity();
}
