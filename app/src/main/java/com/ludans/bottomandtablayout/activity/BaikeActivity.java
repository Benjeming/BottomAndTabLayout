package com.ludans.bottomandtablayout.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

import com.ludans.bottomandtablayout.R;
import com.ludans.bottomandtablayout.utils.OkHttpsUtils;
import com.ludans.bottomandtablayout.utils.PathConfing;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BaikeActivity extends AppCompatActivity {
    private static final String TAG = BaikeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitu_webview);

        //获取  具体病害的URl

        //获取url后进行连接,拼接URL   BaseUrl  + TypeUrl + 爬取的Url
        String url = getIntent().getStringExtra("minuUrl");
        String typeUrl = getIntent().getStringExtra("typeUrl");
        Log.d(TAG, "网站： "+typeUrl+"/"+url);





    }
}
