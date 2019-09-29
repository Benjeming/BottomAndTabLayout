package com.ludans.bottomandtablayout.allFragment.BaiKe;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ludans.bottomandtablayout.R;

public class WebViewOne extends AppCompatActivity {
    private static final String TAG = "WebViewOne";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);

        String url = "http://crop.agridata.cn/disease/01%EF%BC%8D%E6%B0%B4%E7%A8%BB/0001-0003%20%E6%B0%B4%E7%A8%BB%E6%81%B6%E8%8B%97%E7%97%85.htm";
       WebViewOne webViewOne = new WebViewOne();
        WebView webView =findViewById(R.id.web_view);


    }

}
