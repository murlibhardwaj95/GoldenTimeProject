package com.goldentime.syon.goldentime.registration;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.goldentime.syon.goldentime.BuildConfig;
import com.goldentime.syon.goldentime.R;
import com.goldentime.syon.goldentime.base.BaseActivity;
import com.goldentime.syon.goldentime.myutils.ConstantsValue;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalInformationFragment extends Fragment {

    private BaseActivity mActivity;
    Button btn_next;
    RadioGroup rg_sex;
    RadioButton rb_gender;
    String name, email, address, gender, state, contact, country, dob;
    EditText et_name, et_email, et_dob, et_address, et_state, et_country, et_mobile_no;
    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + ".FRAGMENT_TAG";

    public PersonalInformationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mActivity = (BaseActivity) getActivity();
        View view = inflater.inflate(R.layout.fragment_personal_information, container, false);
        btn_next = view.findViewById(R.id.btn_next);
        init(view);

        return view;
    }

    private void init(View view) {
        et_name = view.findViewById(R.id.et_name);
        et_email = view.findViewById(R.id.et_email);
        rg_sex = view.findViewById(R.id.radioGrp);
        et_address = view.findViewById(R.id.et_address);
        et_country = view.findViewById(R.id.et_country);
        et_dob = view.findViewById(R.id.et_dob);
        et_state = view.findViewById(R.id.et_state);
        et_mobile_no = view.findViewById(R.id.et_mobile_no);
        et_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        Calendar myCalendar = Calendar.getInstance();
                        myCalendar.set(Calendar.YEAR, selectedyear);
                        myCalendar.set(Calendar.MONTH, selectedmonth);
                        myCalendar.set(Calendar.DAY_OF_MONTH, selectedday);
                        String myFormat = "dd/MM/yyyy"; //Change as you need
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
                        et_dob.setText(sdf.format(myCalendar.getTime()));

                        int mDay = selectedday;
                        int mMonth = selectedmonth;
                        int mYear = selectedyear;
                    }
                }, mYear, mMonth, mDay);
                //mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });
        doOperation(view);
    }

    private void validation() {

        if (TextUtils.isEmpty(et_name.getText().toString().trim())) {
            mActivity.displayToast(getContext(), getString(R.string.enter_name));
        } else if (TextUtils.isEmpty(et_email.getText().toString().trim())) {
            mActivity.displayToast(getContext(), getString(R.string.enter_email));
        } else if (!emailValidator(et_email.getText().toString().trim())) {
            mActivity.displayToast(getContext(), getString(R.string.valid_email));
        } else if (TextUtils.isEmpty(et_dob.getText().toString().trim())) {
            mActivity.displayToast(getContext(), getString(R.string.enter_dob));
        } else if (TextUtils.isEmpty(gender)) {
            mActivity.displayToast(getContext(), getString(R.string.enter_gender));
        } else if (TextUtils.isEmpty(et_mobile_no.getText().toString().trim())) {
            mActivity.displayToast(getContext(), getString(R.string.enter_number));
        } else if (et_mobile_no.getText().toString().trim().length() < 10) {
            mActivity.displayToast(getContext(), getString(R.string.valid_number));
        } else if (TextUtils.isEmpty(et_address.getText().toString().trim())) {
            mActivity.displayToast(getContext(), getString(R.string.enter_address));
        } else if (TextUtils.isEmpty(et_state.getText().toString().trim())) {
            mActivity.displayToast(getContext(), getString(R.string.enter_state));
        } else if (TextUtils.isEmpty(et_country.getText().toString().trim())) {
            mActivity.displayToast(getContext(), getString(R.string.enter_country));
        } else {
            name = et_name.getText().toString().trim();
            email = et_email.getText().toString().trim();
            //gender = et_gender.getText().toString().trim();
            address = et_address.getText().toString().trim();
            country = et_country.getText().toString().trim();
            state = et_state.getText().toString().trim();
            dob = et_dob.getText().toString().trim();
            contact = et_mobile_no.getText().toString();
            LoginInformationFragment logininfo = new LoginInformationFragment();
            Bundle args = new Bundle();
            args.putString(ConstantsValue.NAME, name);
            args.putString(ConstantsValue.EMAIL, email);
            args.putString(ConstantsValue.ADDRESS, address);
            args.putString(ConstantsValue.STATE, state);
            args.putString(ConstantsValue.COUNTRY, country);
            args.putString(ConstantsValue.GENDER, gender);
            args.putString(ConstantsValue.DOB, dob);
            args.putString(ConstantsValue.CONTACT, contact);
            logininfo.setArguments(args);

            mActivity.replaceFragment(R.id.fragment_container,
                    logininfo, FRAGMENT_TAG, null);
        }
    }

    private void doOperation(final View view) {
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = rg_sex.getCheckedRadioButtonId();
                rb_gender = view.findViewById(selectedId);
                gender = rb_gender.getText().toString();
                validation();
            }
        });
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
