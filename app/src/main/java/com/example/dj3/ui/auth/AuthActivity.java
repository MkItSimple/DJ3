package com.example.dj3.ui.auth;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.RequestManager;
import com.example.dj3.R;
import com.example.dj3.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity {

    private static final String TAG = "AuthActivity";

    private AuthViewModel viewModel;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    Drawable logo;

    @Inject
    RequestManager requestManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        viewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel.class);

        setLogo();
    }

    private void setLogo(){
        requestManager
                .load(logo)
                .into((ImageView)findViewById(R.id.login_logo));
    }
}
