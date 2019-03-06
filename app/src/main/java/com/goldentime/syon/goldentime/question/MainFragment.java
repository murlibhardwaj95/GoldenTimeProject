package com.goldentime.syon.goldentime.question;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.goldentime.syon.goldentime.BuildConfig;
import com.goldentime.syon.goldentime.R;
import com.goldentime.syon.goldentime.base.BaseActivity;
import com.goldentime.syon.goldentime.lesson.LessonActivity;
import com.goldentime.syon.goldentime.myutils.ConstantsValue;
import com.goldentime.syon.goldentime.welcome.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements View.OnClickListener {

    BaseActivity mActivity;
    ImageView fill_blanks, true_false, correct_answer, iv_home, iv_back;
    TextView tv_chapter_name, tv_header;
    int chapterId;
    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + ".FRAGMENT_TAG";

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mActivity = (BaseActivity) getActivity();
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        String className = getArguments().getString(ConstantsValue.CLASS_NAME);
        String chapterName = getArguments().getString(ConstantsValue.CHAPTER_NAME);
        chapterId = getArguments().getInt(ConstantsValue.CHAPTER_ID);
        tv_header = view.findViewById(R.id.tv_heading);
        tv_chapter_name = view.findViewById(R.id.tv_chapter_name_frag);
        fill_blanks = view.findViewById(R.id.fill_the_blanks);
        true_false = view.findViewById(R.id.true_false);
        correct_answer = view.findViewById(R.id.correct_answer);
        iv_home = view.findViewById(R.id.iv_home);
        iv_back = view.findViewById(R.id.iv_back);
        iv_home.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        fill_blanks.setOnClickListener(this);
        true_false.setOnClickListener(this);
        correct_answer.setOnClickListener(this);
        iv_home.setVisibility(View.VISIBLE);
        iv_back.setVisibility(View.VISIBLE);
        tv_header.setText(className);
        tv_chapter_name.setText(chapterName);
//        doOperation();
    }
/*  private void doOperation() {

    }
*/
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_home:
                startActivity(new Intent(getContext(), MainActivity.class));
                mActivity.finish();
                break;
            case R.id.iv_back:
                break;
            case R.id.fill_the_blanks:
                FillTheBlanksFragment fillTheBlanksFragment  = new FillTheBlanksFragment();
                Bundle args = new Bundle();
                args.putInt(ConstantsValue.CHAPTER_ID,chapterId);
                fillTheBlanksFragment.setArguments(args);
                mActivity.replaceFragment(R.id.fragment_container_question, fillTheBlanksFragment, FillTheBlanksFragment.FRAGMENT_TAG, null);
                break;
            case R.id.true_false:
                TrueFalseFragment trueFalseFragment  = new TrueFalseFragment();
                Bundle argss = new Bundle();
                argss.putInt(ConstantsValue.CHAPTER_ID,chapterId);
                trueFalseFragment.setArguments(argss);
                mActivity.replaceFragment(R.id.fragment_container_question, trueFalseFragment, TrueFalseFragment.FRAGMENT_TAG, null);
                break;
            case R.id.correct_answer:
               CorrectAnswerFragment correctAnswerFragment  = new CorrectAnswerFragment();
                Bundle argsu = new Bundle();
                argsu.putInt(ConstantsValue.CHAPTER_ID,chapterId);
                correctAnswerFragment.setArguments(argsu);
                mActivity.replaceFragment(R.id.fragment_container_question, correctAnswerFragment, CorrectAnswerFragment.FRAGMENT_TAG, null);
                break;
        }

    }
}
