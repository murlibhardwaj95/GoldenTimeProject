package com.goldentime.syon.goldentime.question;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.goldentime.syon.goldentime.BuildConfig;
import com.goldentime.syon.goldentime.R;
import com.goldentime.syon.goldentime.ResultActivity;
import com.goldentime.syon.goldentime.base.ApiClient;
import com.goldentime.syon.goldentime.base.ApiInterface;
import com.goldentime.syon.goldentime.base.BaseActivity;
import com.goldentime.syon.goldentime.myutils.ConstantsValue;
import com.goldentime.syon.goldentime.myutils.MyUtils;
import com.goldentime.syon.goldentime.welcome.MainActivity;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FillTheBlanksFragment extends Fragment implements View.OnClickListener {

    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + ".FRAGMENT_TAG";
    static int count = 1;
    BaseActivity mActivity;
    TextView tv_no_data, tv_text_questions;
    ImageView iv_home, iv_back;
    List<Choice> choices;
    ArrayList<Choice> choicesSpinner;
    ArrayList<String> strings;
    List<QuestionBean> questionBeans;
    Button nxt, pre, btn_done, btn_result;
    String correctAnswer, checkAnswer;
    Spinner spin_answer;

    public FillTheBlanksFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mActivity = (BaseActivity) getActivity();
        View view = inflater.inflate(R.layout.fragment_fill_the_blanks, container, false);
        doOperation(view);
        return view;
    }

    private void doOperation(View view) {
        iv_home = view.findViewById(R.id.iv_home);
        iv_back = view.findViewById(R.id.iv_back);
        nxt = view.findViewById(R.id.nxt);
        pre = view.findViewById(R.id.pre);
        btn_done = view.findViewById(R.id.done);
        btn_result = view.findViewById(R.id.result);
        spin_answer = view.findViewById(R.id.spin_answer);
     /*   btn_true = view.findViewById(R.id.btn_true);
        btn_false = view.findViewById(R.id.btn_false);
      */
        tv_text_questions = view.findViewById(R.id.tv_question);
        tv_no_data = view.findViewById(R.id.tv_no_data_found);
        iv_home.setVisibility(View.VISIBLE);
        iv_back.setVisibility(View.VISIBLE);
        iv_home.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        btn_done.setOnClickListener(this);
        btn_result.setOnClickListener(this);
        nxt.setOnClickListener(this);
        pre.setOnClickListener(this);
        dialogMethod();

    }

    private void dialogMethod() {
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_fill_the_blanks);
        Button btn_test = dialog.findViewById(R.id.btn_test);
        final Button btn_learn = dialog.findViewById(R.id.btn_learn);
        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callApiLearn();
                pre.setEnabled(false);
                nxt.setEnabled(false);
                dialog.dismiss();
            }
        });
        btn_learn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callApiLearn();
                pre.setVisibility(View.GONE);
                nxt.setEnabled(false);
                btn_done.setText(getString(R.string.check));
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.show();
    }

   /* private void callApiTest() {
        int chapterID = getArguments().getInt(ConstantsValue.CHAPTER_ID);
        if (mActivity.isNetworkAvailable(getContext())) {
            MyUtils.showLoading(getContext(), getString(R.string.is_loading));
            try {
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                ApiInterface apiInterface = ApiClient.getApiClient(builder).create(ApiInterface.class);
                Call<QuestionModel> questionList = apiInterface.getQuestionList(chapterID, 1);
                questionList.enqueue(new Callback<QuestionModel>() {
                    @Override
                    public void onResponse(Call<QuestionModel> call, Response<QuestionModel> response) {
                        MyUtils.hideLoading();
                        if (response.code() == 200) {
                            QuestionModel questionModel = response.body();
                            questionBeans = questionModel.getData();

                        } else {
                            tv_no_data.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onFailure(Call<QuestionModel> call, Throwable t) {
                        MyUtils.hideLoading();
                        mActivity.displayToast(getContext(), getString(R.string.please_try_after_sometime));
                    }
                });
            } catch (Exception e) {
                mActivity.displayToast(getContext(), getString(R.string.no_data_found));
            }
        } else {
            mActivity.displayToast(getContext(), getString(R.string.check_internet_connection));
        }

    }*/

    private void callApiLearn() {

        int chapterID = getArguments().getInt(ConstantsValue.CHAPTER_ID);
        if (mActivity.isNetworkAvailable(getContext())) {
            MyUtils.showLoading(getContext(), getString(R.string.is_loading));
            try {
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                ApiInterface apiInterface = ApiClient.getApiClient(builder).create(ApiInterface.class);
                Call<QuestionModel> questionList = apiInterface.getQuestionList(chapterID, 1);
                questionList.enqueue(new Callback<QuestionModel>() {
                    @Override
                    public void onResponse(Call<QuestionModel> call, Response<QuestionModel> response) {
                        MyUtils.hideLoading();
                        if (response.code() == 200) {
                            QuestionModel questionModel = response.body();
                            questionBeans = questionModel.getData();
                            tv_text_questions.setText(questionBeans.get(count).getQText().toString());
                            choices = questionBeans.get(count).getOptions();
                            setDataOnSpinner();
                        } else {
                            tv_no_data.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onFailure(Call<QuestionModel> call, Throwable t) {
                        MyUtils.hideLoading();
                        mActivity.displayToast(getContext(), getString(R.string.please_try_after_sometime));
                    }
                });
            } catch (Exception e) {
                mActivity.displayToast(getContext(), getString(R.string.no_data_found));
            }
        } else {
            mActivity.displayToast(getContext(), getString(R.string.check_internet_connection));
        }
    }

    private void setDataOnSpinner() {
        choicesSpinner = new ArrayList<>();
        strings = new ArrayList<>();
        for (int i = 0; i < choices.size(); i++) {
            strings.add(choices.get(i).getQChoice());
            choicesSpinner.add(choices.get(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_dropdown_item, strings);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_answer.setAdapter(adapter);
        spin_answer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                checkAnswer = choicesSpinner.get(position).getQChoice();
                btn_done.setBackgroundColor(getResources().getColor(R.color.white));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_home:
                break;
            case R.id.iv_back:
                break;
            case R.id.nxt:
                preformNextOperation();
                break;
            case R.id.pre:
                performnPreviousOpertaion();
                break;
            case R.id.done:
                doneButtonOperation();
                break;
            case R.id.result:
                startActivity(new Intent(getContext(),ResultActivity.class));
                break;
          /*  case R.id.btn_true:
                break;
            case R.id.btn_false:
                break;*/
        }
    }

    private void doneButtonOperation() {
        if (btn_done.getText().equals(getString(R.string.check))) {
            if (checkAnswer.equals(questionBeans.get(count).getQAns())) {
                nxt.setEnabled(true);
            } else if(count == questionBeans.size()) {
                btn_result.setVisibility(View.VISIBLE);
                btn_done.setVisibility(View.GONE);
            }else {
                btn_done.setBackgroundColor(getResources().getColor(R.color.lose_red));
            }

        } else{
            nxt.setEnabled(true);
            pre.setEnabled(true);
        }
    }

    private void performnPreviousOpertaion() {
        count--;
        if (count <= 1) {
            pre.setEnabled(false);
        } else {
            tv_text_questions.setText(questionBeans.get(count).getQText());
            choices = questionBeans.get(count).getOptions();
            setDataOnSpinner();
            pre.setEnabled(true);
        }
    }

    private void preformNextOperation() {
        count++;
        if (count <= questionBeans.size() - 1) {
            nxt.setEnabled(true);
            tv_text_questions.setText(questionBeans.get(count).getQText().toString());
            choices = questionBeans.get(count).getOptions();
            setDataOnSpinner();
        } else {
            nxt.setEnabled(false);
        }
    }
}
