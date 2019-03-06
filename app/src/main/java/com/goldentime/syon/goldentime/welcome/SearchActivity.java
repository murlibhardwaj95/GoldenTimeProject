package com.goldentime.syon.goldentime.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.goldentime.syon.goldentime.R;
import com.goldentime.syon.goldentime.base.ApiClient;
import com.goldentime.syon.goldentime.base.ApiInterface;
import com.goldentime.syon.goldentime.base.BaseActivity;
import com.goldentime.syon.goldentime.classpackage.ClassAdapter;
import com.goldentime.syon.goldentime.classpackage.ClassBean;
import com.goldentime.syon.goldentime.classpackage.ClassModel;
import com.goldentime.syon.goldentime.myutils.ConstantsValue;
import com.goldentime.syon.goldentime.myutils.MyUtils;
import com.goldentime.syon.goldentime.series.SeriedAdapter;
import com.goldentime.syon.goldentime.series.SeriesBean;
import com.goldentime.syon.goldentime.series.SeriesModel;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends BaseActivity {

    RecyclerView recyclerView;
    ApiInterface apiInterface;
    ImageView iv_back;
    TextView tv_heading,tv_no_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.recycler_view);
        iv_back = findViewById(R.id.iv_back);
        tv_no_data = findViewById(R.id.tv_no_data_found);
        tv_heading = findViewById(R.id.tv_heading);
        tv_heading.setText(R.string.search);
        recyclerView.setBackgroundResource(R.drawable.project_backgroud_xhdpi);
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchActivity.super.onBackPressed();
                finish();
            }
        });
        callApi();
    }

    private void callApi() {
        Intent intent = getIntent();
        String search = intent.getStringExtra(ConstantsValue.SEARCH);
        if (isNetworkAvailable(this)) {
            MyUtils.showLoading(this, getString(R.string.is_loading));
            try {
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                apiInterface = ApiClient.getApiClient(builder).create(ApiInterface.class);
                Call<ClassModel> searchList = apiInterface.getSearchList(search);
                searchList.enqueue(new Callback<ClassModel>() {
                    @Override
                    public void onResponse(Call<ClassModel> call, Response<ClassModel> response) {
                        MyUtils.hideLoading();
                        if (response.code() == 200) {
                            ClassModel searchModel = response.body();
                            List<ClassBean> searchBeans = searchModel.getData();
                            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(SearchActivity.this, 2);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(new ClassAdapter(SearchActivity.this, searchBeans));
                        }
                        else {
                            recyclerView.setVisibility(View.GONE);
                            tv_no_data.setVisibility(View.VISIBLE);
                        }
                    }
                    @Override
                    public void onFailure(Call<ClassModel> call, Throwable t) {
                        MyUtils.hideLoading();
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
