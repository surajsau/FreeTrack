package com.halfplatepoha.freetrack.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.angelhack.freetrack.R;
import com.angelhack.freetrack.base.activity.BaseActivity;
import com.angelhack.freetrack.network.ResponseListener;
import com.angelhack.freetrack.network.TFApi;
import com.angelhack.freetrack.network.TFServiceGenerator;
import com.angelhack.freetrack.network.request.LoginRequest;
import com.angelhack.freetrack.utils.LoginType;
import com.angelhack.freetrack.utils.TFPreference;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

public class LoginActivity extends BaseActivity {

    private TwitterLoginButton loginButton;

    private TFApi mApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mApi = TFServiceGenerator.createService(TFApi.class);

        loginButton = (TwitterLoginButton) findViewById(R.id.login_button);

        loginButton = (TwitterLoginButton) findViewById(R.id.login_button);

        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {

                TwitterSession session = result.data;

                TwitterAuthClient authClient = new TwitterAuthClient();
                authClient.requestEmail(session, new Callback<String>() {
                    @Override
                    public void success(Result<String> result) {
                        TFPreference.setInPref(PREF_EMAIL, result.data);
                    }

                    @Override
                    public void failure(TwitterException exception) {
                        // Do something on failure
                        Log.d("TwitterKit", "requestEmail with Twitter failure", exception);

                    }
                });
                TFPreference.setInPref(PREF_LOGIN_TYPE, LoginType.TWITTER.name());
                TFPreference.setInPref(PREF_LOGIN_NAME, session.getUserName());

                callLoginApi(session.getUserName());

                showHome();

            }
            @Override
            public void failure(TwitterException exception) {
                showShortToast(getString(R.string.error_in_login_with_twiter));
                Log.d("TwitterKit", "Login with Twitter failure", exception);
            }
        });

    }

    private void showHome() {
        TFPreference.setInPref(IS_LOGGEDIN, true);
        Intent loginIntent = new Intent(this, HomeActivity.class);
        startActivity(loginIntent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (loginButton != null) {
            loginButton.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void callLoginApi(String username) {
        LoginRequest request = new LoginRequest();
        request.setHandle(username);
        request.setLoginType("twitter");

        mApi.login(request).enqueue(loginResponseListener);
    }

    private ResponseListener<Void> loginResponseListener = new ResponseListener<Void>() {
        @Override
        public void parseNetworkResponse(Void response) {

        }
    };
}
