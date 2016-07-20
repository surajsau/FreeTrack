package com.halfplatepoha.freetrack.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.angelhack.freetrack.BuildConfig;
import com.angelhack.freetrack.R;
import com.angelhack.freetrack.base.activity.BaseActivity;
import com.angelhack.freetrack.utils.TFPreference;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;

public class SplashActivity extends BaseActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TFPreference.init(this);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        if(!BuildConfig.DEBUG) {
            Fabric.with(this, new Crashlytics(), new Twitter(authConfig));
        }else{
            Fabric.with(this, new Twitter(authConfig), new Answers());

        }
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(TFPreference.getBooleanFromPref(IS_LOGGEDIN, false)){
                    showHome();
                }else{

                    showLogin();
                }

            }
        }, 2000);

    }

    private void showHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
        
    }

    private void showLogin() {

        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }
}
