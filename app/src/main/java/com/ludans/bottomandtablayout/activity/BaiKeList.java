package com.ludans.bottomandtablayout.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ludans.bottomandtablayout.R;
import com.ludans.bottomandtablayout.allAdapter.BaiKeAdapter.BaiKeListAdapter;
import com.ludans.bottomandtablayout.allBean.CropBean;
import com.ludans.bottomandtablayout.utils.OkHttpsUtils;
import com.ludans.bottomandtablayout.utils.PathConfing;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BaiKeList extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ArrayList<CropBean> mData;
    private static final String TAG = BaiKeList.class.getSimpleName();
    private ListView listView;
    private BaiKeListAdapter adapter;
//    private PathConfing pathConfing = new PathConfing();

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
        setContentView(R.layout.fragment_baike_list);
        Toolbar toolbar = findViewById(R.id.news_content_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("常德农技通 - 病虫害列表");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用

//        初始化控件
        listView = findViewById(R.id.baike_list);
        listView.setOnItemClickListener(this);

        mData = new ArrayList<>();
//        获取传过来的  TypeUrl
        String typeUrl = getIntent().getStringExtra("typeUrl");
//        用OkHttp 对网站获取数据
        try {
            getJsonDataFromWeb(typeUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        解析数据






    }
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String JsonData = (String) msg.obj;
            Gson gson = new Gson();
            mData = gson.fromJson(JsonData, new TypeToken<ArrayList<CropBean>>() {
            }.getType());
            adapter = new BaiKeListAdapter(mData, getApplicationContext());
            listView.setAdapter(adapter);
        }
    };

    private void getJsonDataFromWeb(String typeUrl) throws IOException {
        OkHttpsUtils.sendRequestWithOkHttp(typeUrl, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d(TAG, "onFailure: 连接失败...");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String body = response.body().string();
//                Log.d(TAG, "onResponse: 数据是："+mData.get(0).getContent().get(5).getCrop_disasters_name().toString());
                Message msg = new Message();
                msg.obj = body;
                handler.sendMessage(msg);
            }
        });
    }


    private void initView() {

//        Log.d(TAG, "initView: 进入了BaiKeList " + mData.get(0).getContent().toString());


        Log.d(TAG, "数据是否存在：" + mData.get(0).getContent().toString());

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this,ArticleContent.class);
        intent.putExtra("typeUrl",mData.get(0).getType());
        intent.putExtra("minuUrl",mData.get(0).getContent().get(position).getCrop_disasters_url());
        startActivity(intent);
    }
}
