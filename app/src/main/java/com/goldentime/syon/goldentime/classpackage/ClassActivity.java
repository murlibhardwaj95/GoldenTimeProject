package com.goldentime.syon.goldentime.classpackage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
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
import com.goldentime.syon.goldentime.myutils.ConstantsValue;
import com.goldentime.syon.goldentime.myutils.MyUtils;
import com.goldentime.syon.goldentime.series.SeriedAdapter;
import com.goldentime.syon.goldentime.series.SeriesActivity;
import com.goldentime.syon.goldentime.series.SeriesBean;
import com.goldentime.syon.goldentime.series.SeriesModel;
import com.goldentime.syon.goldentime.welcome.MainActivity;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClassActivity extends BaseActivity {

    RecyclerView rv_class;
    ImageView iv_home,iv_back;
    TextView tv_heading,tv_no_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        rv_class = findViewById(R.id.rv_class);
        iv_home = findViewById(R.id.iv_home);
        iv_back = findViewById(R.id.iv_back);
        tv_no_data = findViewById(R.id.tv_no_data_found);
        iv_home.setVisibility(View.VISIBLE);
        iv_back.setVisibility(View.VISIBLE);
        tv_heading = findViewById(R.id.tv_heading);
        tv_heading.setText(getString(R.string.classes));
        iv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ClassActivity.this, MainActivity.class));
                finish();

            }
        });
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClassActivity.super.onBackPressed();
                finish();
            }
        });
        apiOperation();
    }

    private void apiOperation() {
        Intent intent = getIntent();
        int series_id = intent.getIntExtra(ConstantsValue.SERIES_ID, 0);
        if (isNetworkAvailable(this)) {
            MyUtils.showLoading(this, getString(R.string.is_loading));
            try {
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                ApiInterface apiInterface = ApiClient.getApiClient(builder).create(ApiInterface.class);
                Call<ClassModel> classModelCall = apiInterface.getClassList(series_id);
                classModelCall.enqueue(new Callback<ClassModel>() {
                    @Override
                    public void onResponse(Call<ClassModel> call, Response<ClassModel> response) {
                        MyUtils.hideLoading();
                        if(response.code() == 200) {
                            ClassModel classModel = response.body();
                            List<ClassBean> classBeans = classModel.getData();
                            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ClassActivity.this, 2);
                            rv_class.setLayoutManager(layoutManager);
                            rv_class.setAdapter(new ClassAdapter(ClassActivity.this, classBeans));
                        }
                        else{
                            rv_class.setVisibility(View.GONE);
                            tv_no_data.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onFailure(Call<ClassModel> call, Throwable t) {
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
