package com.goldentime.syon.goldentime.question;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.goldentime.syon.goldentime.R;
import com.goldentime.syon.goldentime.base.BaseActivity;
import com.goldentime.syon.goldentime.myutils.ConstantsValue;

public class QuestionActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        addfragmentMethod();
    }

    private void addfragmentMethod() {
        Intent intent = getIntent();
        String chapterName = intent.getStringExtra(ConstantsValue.CHAPTER_NAME);
        String className = intent.getStringExtra(ConstantsValue.CLASS_NAME);
        int chapterId = intent.getIntExtra(ConstantsValue.CHAPTER_ID, 0);

        //Put the value
        MainFragment ldf = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ConstantsValue.CHAPTER_NAME, chapterName);
        args.putString(ConstantsValue.CLASS_NAME, className);
        args.putInt(ConstantsValue.CHAPTER_ID,chapterId);
        ldf.setArguments(args);
        addFragment(R.id.fragment_container_question,ldf,MainFragment.FRAGMENT_TAG);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
