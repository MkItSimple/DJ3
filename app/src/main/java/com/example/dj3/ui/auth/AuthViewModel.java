package com.example.dj3.ui.auth;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.dj3.models.User;
import com.example.dj3.network.auth.AuthApi;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";

    private AuthApi authApi;

    @Inject
    public AuthViewModel(AuthApi authApi) {
        this.authApi = authApi;
        Log.d(TAG, "AuthViewModel: viewmodel is working...");
//        if (this.authApi == null){ Log.d(TAG, "AuthViewModel: auth api is NULL");
//        }  else { Log.d(TAG, "AuthViewModel: auth api is NOT NULL"); }
        authApi.getUser(1)
                .toObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(User user) {
                        Log.d(TAG, "onNext: " + user.getEmail());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ",  e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
