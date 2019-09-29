package com.ludans.bottomandtablayout.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ludans.bottomandtablayout.R;

import org.jetbrains.annotations.NotNull;

public class ArticleContent extends AppCompatActivity {

    private Context mContext;
    private Intent data;
    private String url;
    private Toolbar toolbar;
    private WebView webView = null;

    private ProgressDialog dialog = null;
    private static final String TAG = ArticleContent.class.getSimpleName();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitu_webview);
        toolbar = (Toolbar) findViewById(R.id.contentToolbar);
        toolbar.setTitle("今日头条 - 文章内容");
//        setSupportActionBar(toolbar);


        initView();
        // 启用支持JavaScript
        WebSettings webSettings = getWebSettings();
        // 进度条显示网页的加载过程
        download();
        // 优先使用缓存优化效率
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

    }  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @NotNull
    private WebSettings getWebSettings() {
        WebSettings webSettings = webView.getSettings();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true); //设置缩放工具
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheEnabled(true);// 应用可以有缓存
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);// 优先使用缓存
        webSettings.setAppCacheEnabled(true);// 缓存最多可以有10M
        return webSettings;
    }

    private void download() {


        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {

                if (newProgress == 100) {
                    // 加载完毕
                    closeDialog(newProgress);
                } else {
                    openDialog(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }

            private void openDialog(int newProgress) {
                if (dialog == null) {
                    dialog = new ProgressDialog(ArticleContent.this);
                    dialog.setTitle("正在加载");
                    dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    dialog.setProgress(newProgress);
                    dialog.show();
                } else {
                    dialog.setProgress(newProgress);
                }
            }

            private void closeDialog(int newProgress) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                    dialog = null;
                }
            }
        });
    }

    private void initView() {
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        webView = (WebView) findViewById(R.id.webView);
        webView.setInitialScale(70);
        data = getIntent();
        url = data.getStringExtra("url");
        Log.d(TAG, "是否收到url"+url);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }
}
