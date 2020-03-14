package com.example.dj3.ui.auth;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.ViewModel;

import com.example.dj3.SessionManager;
import com.example.dj3.models.User;
import com.example.dj3.network.auth.AuthApi;

import org.reactivestreams.Publisher;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {
    private static final String TAG = "AuthViewModel";

    // inject
    private final SessionManager sessionManager; // @Singleton scoped dependency
    private final AuthApi authApi; // @AuthScope scoped dependency

    @Inject
    public AuthViewModel(AuthApi authApi, SessionManager sessionManager) {
        this.sessionManager = sessionManager;
        this.authApi = authApi;
        Log.d(TAG, "AuthViewModel: viewmodel is working...");
    }

    public LiveData<AuthResource<User>> observeAuthState(){
        return sessionManager.getAuthUser();
    }

    public void authenticateWithId(int userId) {
        Log.d(TAG, "attemptLogin: attempting to login.");
        sessionManager.authenticateWithId(queryUserId(userId));
    }

    private LiveData<AuthResource<User>> queryUserId(int userId) {

        return LiveDataReactiveStreams.fromPublisher(
                authApi.getUser(userId)

                 // If error happens do this . .
                .onErrorReturn(new Function<Throwable, User>() {
                    @Override
                    public User apply(Throwable throwable) throws Exception {
                        User errorUser = new User();
                        errorUser.setId(-1);
                        return errorUser;
                    }
                })

                // If there is no error do this
                .map(new Function<User, AuthResource<User>>() {
                    @Override
                    public AuthResource<User> apply(User user) throws Exception {
                        if (user.getId() == -1){
                            return AuthResource.error("Could not authenticate", null);
                        }
                        return AuthResource.authenticated(user);
                    }
                })
                        .subscribeOn(Schedulers.io())
        );
    }
}
