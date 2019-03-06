package com.goldentime.syon.goldentime;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.goldentime.syon.goldentime.base.BaseActivity;
import com.goldentime.syon.goldentime.login.LoginActivity;
import com.goldentime.syon.goldentime.myutils.ConstantsValue;
import com.goldentime.syon.goldentime.myutils.MyUtils;
import com.goldentime.syon.goldentime.welcome.MainActivity;

public class SplashActivity extends BaseActivity {

    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
    }

    private void init() {
        new Handler().postDelayed(new Runnable() {
            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */
            @Override
            public void run() {
                int userId = MyUtils.getUserId(SplashActivity.this, ConstantsValue.USER_ID);
                if (userId == 0) {
                    startActivity(new Intent(SplashActivity.this, LoginRegistrationActivity.class));
                } else {
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                }
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
