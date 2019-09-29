package com.ludans.bottomandtablayout.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.webkit.WebView;
import android.widget.TextView;

import com.ludans.bottomandtablayout.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import java.io.IOException;

public class NewsContent extends AppCompatActivity {

    private static final String TAG = NewsContent.class.getSimpleName();
    private TextView textView;
    private TextView textView_title;
    private ProgressDialog dialog;



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_news_content);
//        toolbar = new Toolbar(this);
        Toolbar toolbar = findViewById(R.id.news_content_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用




        textView = findViewById(R.id.fg_newsContent_text);
        textView_title = findViewById(R.id.fg_newsContent_text_title);
        dialog = ProgressDialog.show(this, "温馨提示", "请稍等，正在加载中....", true, true);


        new Thread(new Runnable() {
            @Override
            public void run() {
                String TATGET_URL = getIntent().getStringExtra("url");

                String content_total = "";
                try {
                    Document doc = Jsoup.connect(TATGET_URL).get();
                    Elements elementsId_Zoom = doc.getElementsByAttributeValue("id", "zoom");
                    String clean = Jsoup.clean(elementsId_Zoom.first().html(), Whitelist.basic());
                    Log.e(TAG, "联网抓取.....");
                    String replace = clean.replace("<br>", "\n")
                            .replace("&nbsp;", " ")
                            .replace("<p>", "\t")
                            .replace("</p>", "\n");
                    content_total = "\t" + replace + "\n";//content_total 是数据

                    Message msg = new Message();
                    msg.obj = content_total;
                    handler.sendMessage(msg);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }).start();
    }


    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            textView_title.setText(getIntent().getStringExtra("title"));
            textView.setText((String) msg.obj);
            dialog.dismiss();
        }
    };
}
