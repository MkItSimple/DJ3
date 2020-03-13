package com.example.dj3.di.auth;

import com.example.dj3.di.ViewModelKey;
import com.example.dj3.ui.auth.AuthViewModel;
import com.example.dj3.viewmodels.ViewModelProviderFactory;

import androidx.lifecycle.ViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel bindAuthViewModel(AuthViewModel viewModel);
}
