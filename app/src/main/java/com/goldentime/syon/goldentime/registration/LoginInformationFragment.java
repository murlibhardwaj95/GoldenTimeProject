package com.goldentime.syon.goldentime.registration;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.facebook.login.Login;
import com.goldentime.syon.goldentime.R;
import com.goldentime.syon.goldentime.base.ApiClient;
import com.goldentime.syon.goldentime.base.ApiInterface;
import com.goldentime.syon.goldentime.base.BaseActivity;
import com.goldentime.syon.goldentime.login.LoginActivity;
import com.goldentime.syon.goldentime.myutils.ConstantsValue;
import com.goldentime.syon.goldentime.myutils.MyUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginInformationFragment extends Fragment {

    private BaseActivity mActivity;
    String name, email, address, gender, state, country, dob, userid, passwrod, board, school, usertype;
    String mobile_no;
    EditText et_userid, et_password, et_board, et_school;
    Button btn_signUp;
    Spinner spin_usertype;
    ApiInterface apiInterface;

    public LoginInformationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mActivity = (BaseActivity) getActivity();
        View view = inflater.inflate(R.layout.fragment_login_information, container, false);

        init(view);

        return view;
    }

    private void init(View view) {
        et_userid = view.findViewById(R.id.et_userid);
        et_password = view.findViewById(R.id.et_password);
        et_board = view.findViewById(R.id.et_board);
        et_school = view.findViewById(R.id.et_school);
        btn_signUp = view.findViewById(R.id.btn_sign_up);
        spin_usertype = view.findViewById(R.id.spinner_usertype);
        name = getArguments().getString(ConstantsValue.NAME);
        dob = getArguments().getString(ConstantsValue.DOB);
        gender = getArguments().getString(ConstantsValue.GENDER);
        address = getArguments().getString(ConstantsValue.ADDRESS);
        mobile_no = getArguments().getString(ConstantsValue.CONTACT);
        country = getArguments().getString(ConstantsValue.COUNTRY);
        state = getArguments().getString(ConstantsValue.STATE);
        email = getArguments().getString(ConstantsValue.EMAIL);

        String[] use_type  = {"Principal","Teacher","Parent","Student","Others"};
        ArrayAdapter<String> Adapterclass = new ArrayAdapter<String>(getContext(), android.R.layout.select_dialog_singlechoice, use_type);
        spin_usertype.setAdapter(Adapterclass);
        spin_usertype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                usertype = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                usertype = "others";
            }
        });
        validation();
    }
    public boolean isNetworkAvailable(Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
    private void validation() {
        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(et_userid.getText().toString().trim())) {
                    mActivity.displayToast(getContext(), getString(R.string.user_id_validation));
                } else if (TextUtils.isEmpty(et_password.getText().toString().trim())) {
                    mActivity.displayToast(getContext(), getString(R.string.password_validation));
                } else if (TextUtils.isEmpty(et_board.getText().toString().trim())) {
                    mActivity.displayToast(getContext(), getString(R.string.enter_board));
                } else if (TextUtils.isEmpty(et_school.getText().toString().trim())) {
                    mActivity.displayToast(getContext(), getString(R.string.enter_school));
                } else {
                    userid = et_userid.getText().toString();
                    passwrod = et_password.getText().toString();
                    board = et_board.getText().toString();
                    school = et_school.getText().toString();
                    if (isNetworkAvailable(getContext())) {
                        MyUtils.showLoading(getActivity(), getString(R.string.is_loading));
                        OkHttpClient.Builder builder = new OkHttpClient.Builder();
                        apiInterface = ApiClient.getApiClient(builder).create(ApiInterface.class);
                        Call<RegistrationModel> call = apiInterface.getRegistration(userid, passwrod, "0", name, email, mobile_no, usertype, gender, dob, country, state, address, board, school);
                        call.enqueue(new Callback<RegistrationModel>() {
                            @Override
                            public void onResponse(Call<RegistrationModel> call, Response<RegistrationModel> response) {
                                MyUtils.hideLoading();
                                RegistrationModel registrationModel = response.body();
                                mActivity.displayToast(getContext(), registrationModel.getData());
                                startActivity(new Intent(getActivity(), LoginActivity.class));
                            }

                            @Override
                            public void onFailure(Call<RegistrationModel> call, Throwable t) {
                                MyUtils.hideLoading();
                                mActivity.displayToast(getContext(), getString(R.string.please_try_after_sometime));
                            }
                        });
                    }
                    else{
                        mActivity.displayToast(getContext(),getString(R.string.check_internet_connection));
                    }
                }
            }
        });
    }
}