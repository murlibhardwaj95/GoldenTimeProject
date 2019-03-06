package com.goldentime.syon.goldentime.series;

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
import com.goldentime.syon.goldentime.welcome.BookDetailsBean;
import com.goldentime.syon.goldentime.welcome.BooksDetails;
import com.goldentime.syon.goldentime.welcome.BooksListAdapter;
import com.goldentime.syon.goldentime.welcome.MainActivity;
import com.goldentime.syon.goldentime.welcome.SearchActivity;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeriesActivity extends BaseActivity {

    RecyclerView rv_series;
    ApiInterface apiInterface;
    ImageView iv_home,iv_back;
    TextView tv_heading,tv_no_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series);
        rv_series = findViewById(R.id.rv_series);
        iv_home = findViewById(R.id.iv_home);
        iv_back = findViewById(R.id.iv_back);
        tv_no_data = findViewById(R.id.tv_no_data_found);
        iv_home.setVisibility(View.VISIBLE);
        iv_back.setVisibility(View.VISIBLE);
        tv_heading = findViewById(R.id.tv_heading);
        tv_heading.setText(R.string.books_series);
        iv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SeriesActivity.this, MainActivity.class));
                finish();
            }
        });
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SeriesActivity.super.onBackPressed();
                        finish();

            }
        });
        Intent intent = getIntent();
        int s_id = intent.getIntExtra(ConstantsValue.SUBJECT_ID, 0);
        if (isNetworkAvailable(this)) {
            MyUtils.showLoading(this, getString(R.string.is_loading));
            try {
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                apiInterface = ApiClient.getApiClient(builder).create(ApiInterface.class);
                Call<SeriesModel> seriesModelCall = apiInterface.getSeriesList(s_id);
                seriesModelCall.enqueue(new Callback<SeriesModel>() {
                    @Override
                    public void onResponse(Call<SeriesModel> call, Response<SeriesModel> response) {
                        MyUtils.hideLoading();
                        if (response.code() == 200) {
                            SeriesModel seriesModel = response.body();
                            List<SeriesBean> seriesBeans = seriesModel.getData();
                            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(SeriesActivity.this, 2);
                            rv_series.setLayoutManager(layoutManager);
                            rv_series.setAdapter(new SeriedAdapter(SeriesActivity.this, seriesBeans));
                        }
                        else{
                            rv_series.setVisibility(View.GONE);
                            tv_no_data.setVisibility(View.VISIBLE);
                        }
                    }
                    @Override
                    public void onFailure(Call<SeriesModel> call, Throwable t) {
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
