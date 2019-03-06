package com.goldentime.syon.goldentime.login;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.goldentime.syon.goldentime.base.ApiClient;
import com.goldentime.syon.goldentime.base.ApiInterface;
import com.goldentime.syon.goldentime.base.BaseActivity;
import com.goldentime.syon.goldentime.myutils.ConstantsValue;
import com.goldentime.syon.goldentime.myutils.MyUtils;
import com.goldentime.syon.goldentime.R;
import com.goldentime.syon.goldentime.welcome.MainActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.security.AccessController.getContext;

public class LoginActivity extends BaseActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {


    private static final String TAG = LoginActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 9001;
    GoogleSignInClient mGoogleSignInClient;
    RelativeLayout layout_login;
    SignInButton btn_google;
    LoginButton btn_login_fb;
    Button btn_login, google_btn, fb_btn;
    String token, userid, password;
    EditText emailforget;
    TextView tv_forgetPassword;
    EditText et_email, et_password;
    CallbackManager callbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        init();
      //  facebookLogin();
        tokenGenerated();
    }


    private void tokenGenerated() {
        android.content.pm.Signature[] sign;

        try {
            sign = this.getPackageManager().getPackageInfo(this.getPackageName(), PackageManager.GET_SIGNATURES).signatures;
            token = sign[0].toCharsString();

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        layout_login = findViewById(R.id.layout_id);
        //btn_google = findViewById(R.id.btn_sign_in);
        btn_login = findViewById(R.id.btn_login);
       // google_btn = findViewById(R.id.google_btn);
        //fb_btn = findViewById(R.id.fb_btn);
        tv_forgetPassword = findViewById(R.id.tv_forgetPassword);
        et_email = findViewById(R.id.et_userid);
        et_password = findViewById(R.id.et_password);
        btn_login.setOnClickListener(this);
       /* btn_google.setOnClickListener(this);

        google_btn.setOnClickListener(this);
        fb_btn.setOnClickListener(this);*/
        tv_forgetPassword.setOnClickListener(this);

    }

    private void validation() {
        userid = et_email.getText().toString().trim();
        password = et_password.getText().toString();
        if (TextUtils.isEmpty(userid)) {
            displayToast(this, getString(R.string.enter_name));
        } else if (TextUtils.isEmpty(password)) {
            displayToast(this, getString(R.string.enter_email));
        } else {
            loginApi();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
          /*  case R.id.fb_btn:
                btn_login_fb.performClick();
                break;
          */  case R.id.layout_id:
                MyUtils.hideSoftKeyboard(v);
                break;
            case R.id.btn_login:
                validation();
                break;
            /*case R.id.google_btn:
                signIn();
                break;
            */case R.id.tv_forgetPassword:
                final Dialog dialogforget = new Dialog(this);
                dialogforget.setContentView(R.layout.layout_forget_password);
                ImageView cancel = (ImageView) dialogforget.findViewById(R.id.cancel);
                emailforget = (EditText) dialogforget.findViewById(R.id.email);
                Button submit = (Button) dialogforget.findViewById(R.id.submit);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogforget.dismiss();
                    }
                });
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        forgotPasswordApi();
                    }
                });
                dialogforget.setCancelable(false);
                dialogforget.show();
                break;
        }
    }

    private void loginApi() {

        if(isNetworkAvailable(this)) {
            MyUtils.showLoading(this, getString(R.string.is_loading));
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            ApiInterface apiInterface = ApiClient.getApiClient(builder).create(ApiInterface.class);
            Call<LoginResponseModel> call = apiInterface.getLoginDetails(userid, password, token);
            call.enqueue(new Callback<LoginResponseModel>() {
                @Override
                public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                  MyUtils.hideLoading();
                    LoginResponseModel loginResponseModel = response.body();
                    LoginResponseBean loginResponseBean = loginResponseModel.getData();
                    MyUtils.setUserId(LoginActivity.this, ConstantsValue.USER_ID, loginResponseBean.getLUserIdNo());
                    if (loginResponseModel.getStatus().equals("ok")) {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    } else {
                        displayToast(LoginActivity.this, "User not registered");
                    }
                }

                @Override
                public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                    MyUtils.hideLoading();
                    displayToast(getApplicationContext(), getString(R.string.please_try_after_sometime));
                }
            });
        }else{
            displayToast(this,getString(R.string.check_internet_connection));
        }
    }

    @Override
    public void onBackPressed() {
        LoginActivity.super.onBackPressed();
    }

    private void forgotPasswordApi() {
        String forgetEmail = emailforget.getText().toString();
        if (TextUtils.isEmpty(et_email.getText().toString().trim())) {
            displayToast(this, getString(R.string.enter_email));
        } else if (!emailValidator(forgetEmail)) {
            displayToast(this, getString(R.string.valid_email));
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    /*private void facebookLogin() {

        AppEventsLogger.activateApp(this);
        callbackManager = CallbackManager.Factory.create();
       // btn_login_fb = findViewById(R.id.btn_sign_up_fb);
        btn_login_fb.setReadPermissions(Arrays.asList(ConstantsValue.EMAIL_FB));
        btn_login_fb.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                displayToast(LoginActivity.this, getString(R.string.login_msg));
                Profile profile = Profile.getCurrentProfile();
                Log.i("Shreks", "" + profile);
            }
            @Override
            public void onCancel() {
                displayToast(LoginActivity.this, getString(R.string.login_failed));
            }
            @Override
            public void onError(FacebookException error) {
                displayToast(LoginActivity.this, getString(R.string.error));
            }
        });
    }*/

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            super.onActivityResult(requestCode, resultCode, data);
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            String name = account.getGivenName();
            String email = account.getEmail();
            // Signed in successfully, show authenticated UI.
            updateUI(true);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("signInResult:", "" + e.getStatusCode());
            updateUI(false);
        }
    }

    private void updateUI(Boolean isLogin) {
        if (isLogin) {
            displayToast(this, "Successfully Login");
        } else {

        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    public boolean emailValidator(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

}