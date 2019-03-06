package com.goldentime.syon.goldentime.lesson;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.goldentime.syon.goldentime.R;
import com.goldentime.syon.goldentime.base.ApiClient;
import com.goldentime.syon.goldentime.base.ApiInterface;
import com.goldentime.syon.goldentime.base.BaseActivity;
import com.goldentime.syon.goldentime.myutils.ConstantsValue;
import com.goldentime.syon.goldentime.myutils.MyUtils;
import com.goldentime.syon.goldentime.welcome.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LessonActivity extends BaseActivity {

    RecyclerView chapter_recycler_view;
    TextView tv_heading,tv_book_name,tv_book_name_below, tv_no_data,tv_book_name_side,tv_book_series_side ,tv_activity, tv_excersize, tv_chapter, tv_pages;
    String img,class_name,series_name;
    LinearLayout book_text_description,book_text_img;
    RelativeLayout book_img;
    ImageView img_book,img_home,img_back;
    int class_id, page, act, excersize, chapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
        init();
    }

    private void init() {
        Bundle bundle = this.getIntent().getExtras();
        img = bundle.getString(ConstantsValue.PHOTO);
        class_id = bundle.getInt(ConstantsValue.CLASS_ID, 0);
        class_name = bundle.getString(ConstantsValue.CLASS_NAME);
        series_name = bundle.getString(ConstantsValue.SERIES_NAME);
        page = bundle.getInt(ConstantsValue.PAGE_COUNT, 0);
        act = bundle.getInt(ConstantsValue.ACITIVITY, 0);
        excersize = bundle.getInt(ConstantsValue.EXCERSIZE, 0);
        chapter = bundle.getInt(ConstantsValue.CHAPTER, 0);
        tv_heading = findViewById(R.id.tv_heading);
        tv_heading.setText(R.string.chapter);
        //String book_name = bundle.getString(ConstantsValue.BOOK_NAME,"The Magic of Pixels");
        chapter_recycler_view = findViewById(R.id.lesson_recycler_view);
        tv_book_name = findViewById(R.id.book_name);
        img_back = findViewById(R.id.iv_back);
        img_home = findViewById(R.id.iv_home);
        img_book = findViewById(R.id.series_bok);
        tv_book_name_below = findViewById(R.id.series_name);
        tv_book_name_side = findViewById(R.id.tv_book_series_class);
        tv_book_series_side = findViewById(R.id.tv_book_series_name);
        tv_activity = findViewById(R.id.tv_activity);
        tv_excersize = findViewById(R.id.tv_exercize);
        book_text_description = findViewById(R.id.book_description);
        book_img = findViewById(R.id.books_img);
        book_text_img = findViewById(R.id.book_text_img);
        tv_no_data = findViewById(R.id.tv_no_data_found);
        tv_chapter = findViewById(R.id.tv_chapter);
        tv_pages = findViewById(R.id.tv_pages);
        Picasso.with(this).load(img).into(img_book);
        tv_pages.setText("" + page);
        tv_chapter.setText("" + chapter);
        tv_excersize.setText("" + excersize);
        tv_activity.setText("" + act);
        tv_book_name_side.setText(class_name);
        tv_book_name_below.setText(class_name);
        tv_book_series_side.setText(series_name);
        img_back.setVisibility(View.VISIBLE);
        img_home.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LessonActivity.super.onBackPressed();
                finish();
            }
        });
        img_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LessonActivity.this, MainActivity.class));
                finish();
            }
        });
        apiCall();

    }

    private void apiCall() {
        if (isNetworkAvailable(this)) {
            MyUtils.showLoading(this, getString(R.string.is_loading));
            try {
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                ApiInterface apiInterface = ApiClient.getApiClient(builder).create(ApiInterface.class);
                Call<LessonModel> lessonModelCall = apiInterface.getLessonList(class_id);
                lessonModelCall.enqueue(new Callback<LessonModel>() {
                    @Override
                    public void onResponse(Call<LessonModel> call, Response<LessonModel> response) {
                        MyUtils.hideLoading();
                      if(response.code()==200){
                        LessonModel lessonModel = response.body();
                        List<LessonBean> lessonBeans = lessonModel.getData();
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(LessonActivity.this);
                        chapter_recycler_view.setLayoutManager(layoutManager);
                        chapter_recycler_view.setAdapter(new LessonAdapter(LessonActivity.this, lessonBeans,class_name));
                    }
                    else{
                          book_text_img.setVisibility(View.GONE);
                          book_text_description.setVisibility(View.GONE);
                          book_img.setVisibility(View.GONE);
                          chapter_recycler_view.setVisibility(View.GONE);
                          tv_no_data.setVisibility(View.VISIBLE);
                      }
                    }

                    @Override
                    public void onFailure(Call<LessonModel> call, Throwable t) {
                        MyUtils.hideLoading();
                        displayToast(getApplicationContext(), getString(R.string.please_try_after_sometime));
                    }
                });
            } catch (Exception e) {
                displayToast(this, getString(R.string.no_data_found));
            }
        } else {
            displayToast(this, getString(R.string.check_internet_connection));
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

