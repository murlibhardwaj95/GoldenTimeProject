package com.goldentime.syon.goldentime;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.goldentime.syon.goldentime.R;
import com.goldentime.syon.goldentime.base.BaseActivity;
import com.goldentime.syon.goldentime.login.LoginActivity;
import com.goldentime.syon.goldentime.registration.RegistrationActivity;

public class LoginRegistrationActivity extends BaseActivity implements View.OnClickListener{

    Button login,registration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_registration);
        login = findViewById(R.id.btn_login_screen);
        registration = findViewById(R.id.btn_registration_screen);
        login.setOnClickListener(this);
        registration.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login_screen:
                startActivity(new Intent(this,LoginActivity.class));
                break;
            case R.id.btn_registration_screen:
                startActivity(new Intent(this,RegistrationActivity.class));
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
