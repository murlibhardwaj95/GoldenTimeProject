package com.goldentime.syon.goldentime.registration;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.goldentime.syon.goldentime.R;
import com.goldentime.syon.goldentime.base.BaseActivity;
import com.goldentime.syon.goldentime.myutils.MyUtils;

public class RegistrationActivity extends BaseActivity {

    RelativeLayout rl_signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        rl_signup = findViewById(R.id.rl_registration);
        rl_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUtils.hideSoftKeyboard(v);
            }
        });
        addFragmentScreen();
    }

    private void addFragmentScreen() {
        addFragment(R.id.fragment_container,
                new PersonalInformationFragment(),
                PersonalInformationFragment.FRAGMENT_TAG);
    }

    @Override
    public void onBackPressed() {
        RegistrationActivity.super.onBackPressed();
    }
}
