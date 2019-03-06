package com.goldentime.syon.goldentime.myutils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class MyUtils {

    private static final String PREFS_NAME = "com.syon.book";
    public static ProgressDialog progressDialog;
    public static void hideSoftKeyboard(View view) {
        InputMethodManager in = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
    public static String getString(Context c, String key) {
        SharedPreferences settings = c.getSharedPreferences(PREFS_NAME, 0);
        return settings.getString(key, "");
    }


    public static void setString(Context c, String key, String value) {
        SharedPreferences settings = c.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static int getUserId(Context c, String key) {
        SharedPreferences settings = c.getSharedPreferences(key, 0);
        return settings.getInt(key, 0);
    }

    public static void setUserId(Context c, String key, int value) {
        SharedPreferences settings = c.getSharedPreferences(key, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        editor.commit();
    }
    public static void showLoading(Context context, String msg) {

        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog.setMessage(msg);

            progressDialog.show();
        } else {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage(msg);

            progressDialog.show();
        }
    }
    public static void hideLoading(){
        if(progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }
    public boolean isNetworkAvailable(Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

}
