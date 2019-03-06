package com.goldentime.syon.goldentime.welcome;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.goldentime.syon.goldentime.R;
import com.goldentime.syon.goldentime.base.ApiClient;
import com.goldentime.syon.goldentime.base.ApiInterface;
import com.goldentime.syon.goldentime.base.BaseActivity;
import com.goldentime.syon.goldentime.login.LoginActivity;
import com.goldentime.syon.goldentime.myutils.ConstantsValue;
import com.goldentime.syon.goldentime.myutils.MyUtils;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {


    RecyclerView recyclerView;
    ApiInterface apiInterface;
    LinearLayout ll_layout,search_bar;
    ImageView iv_logout,search;
    EditText search_item;
    TextView tv_heading,tv_no_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.recycler_view);

        iv_logout = findViewById(R.id.iv_logout);
        ll_layout = findViewById(R.id.linar_layout);
        tv_no_data = findViewById(R.id.tv_no_data_found);
        search_bar = findViewById(R.id.search_bar);
        search_item = findViewById(R.id.et_serach_bar);
        search = findViewById(R.id.iv_search);
        tv_heading = findViewById(R.id.tv_heading);
        tv_heading.setText(R.string.books);
        iv_logout.setVisibility(View.VISIBLE);
        search_bar.setVisibility(View.VISIBLE);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String search = search_item.getText().toString();
                startActivity(new Intent(MainActivity.this,SearchActivity.class).putExtra(ConstantsValue.SEARCH,search));
            }
        });
        ll_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUtils.hideSoftKeyboard(v);
            }
        });
        iv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage(R.string.logout_msg)
                        .setCancelable(false)
                        .setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                                    MyUtils.setUserId(MainActivity.this, ConstantsValue.USER_ID, 0);
                                    finishAffinity();
                                }
                            }
                        })
                        .setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        if(isNetworkAvailable(this)) {
            MyUtils.showLoading(this, getString(R.string.is_loading));
           try {
               OkHttpClient.Builder builder = new OkHttpClient.Builder();
               apiInterface = ApiClient.getApiClient(builder).create(ApiInterface.class);
               Call<BooksDetails> call = apiInterface.getSubjectList();
               call.enqueue(new Callback<BooksDetails>() {
                   @Override
                   public void onResponse(Call<BooksDetails> call, Response<BooksDetails> response) {
                       MyUtils.hideLoading();
                       if (response.code() == 200) {
                       String status = response.body().getStatus();
                       if (status.equals("ok")) {
                           List<BookDetailsBean> data = response.body().getData();
                           RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 2);
                           recyclerView.setLayoutManager(layoutManager);
                           recyclerView.setAdapter(new BooksListAdapter(MainActivity.this, data));
                       }
                       }else{
                           recyclerView.setVisibility(View.GONE);
                           tv_no_data.setVisibility(View.VISIBLE);
                       }
                   }

                   @Override
                   public void onFailure(Call<BooksDetails> call, Throwable t) {
                       MyUtils.hideLoading();

                   }
               });
           }catch (Exception e) {
               displayToast(this, getString(R.string.no_data_found));
           }
        }else{
            displayToast(this,getString(R.string.check_internet_connection));
        }

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
