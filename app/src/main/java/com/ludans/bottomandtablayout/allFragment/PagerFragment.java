package com.ludans.bottomandtablayout.allFragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ludans.bottomandtablayout.R;
import com.ludans.bottomandtablayout.allAdapter.MyRecyclerViewAdapter;
import com.ludans.bottomandtablayout.allBean.ChangDeNewsBean;
import com.ludans.bottomandtablayout.utils.OkHttpsUtils;
import com.ludans.bottomandtablayout.utils.RandomPath;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class PagerFragment extends Fragment {


    //自定义
    public SwipeRefreshLayout swipeRefreshLayout;
    public RecyclerView recyclerView;
    public List<ChangDeNewsBean> mDate;
    public MyRecyclerViewAdapter adapter;
    public RandomPath randomPath;
    private static final String TAG = "PagerFragment";

    public PagerFragment() {
        // Required empty public constructor
    }


    public static PagerFragment newInstance(String param1, String param2) {
        PagerFragment fragment = new PagerFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_pager, container, false);
        return rootView;
    }

    public void initView(View rootView, int typeUrl) {

        //设置RecyclerView适配器
        mDate = new ArrayList<>();
        TextView textView = rootView.findViewById(R.id.pager_text);
        // RecyclerView 初始化
        recyclerView = rootView.findViewById(R.id.pager_recycler);
        // swipeRefreshLayout 初始化
        swipeRefreshLayout = rootView.findViewById(R.id.pager_refresh);
        swipeRefreshLayout.setRefreshing(true);

//        network by OkHttp frame to get json data
        creatThread(typeUrl);
//        Setting RecyclerView and Bind adapter
        //联网请求
        //数据刷新

    }

    public void creatThread(final int urlType) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                postJson(randomPath, urlType);
            }
        }.start();
    }


    public void postJson(RandomPath randomPath, int type) {
        randomPath = new RandomPath(type);
        String homePath = randomPath.getPath();
        Log.d(TAG, "postJson: 随机url：" + homePath);

        OkHttpsUtils.sendRequestWithOkHttp(homePath, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d(TAG, "onFailure: 网络连接失败！");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.d(TAG, "onResponse: 网络连接成功！！");
                String body = response.body().string();
                Gson gson = new Gson();
                mDate = gson.fromJson(body, new TypeToken<ArrayList<ChangDeNewsBean>>() {
                }.getType());
                Log.d(TAG, "onResponse: Gson 解析成功！");
                adapter = new MyRecyclerViewAdapter(getContext(), (ArrayList<ChangDeNewsBean>) mDate);

//            Setting Adapter and Setting mData
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(adapter);
                swipeRefreshLayout.setRefreshing(false);
                Message message = new Message();
                message.obj = body;
                handler.sendMessage(message);
            }
        });
    }

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String responseBody = (String) msg.obj;
            Gson gson = new Gson();
            mDate = gson.fromJson(responseBody, new TypeToken<ArrayList<ChangDeNewsBean>>() {
            }.getType());
            Log.d(TAG, "onResponse: Gson 解析成功！");
            adapter = new MyRecyclerViewAdapter(getContext(), (ArrayList<ChangDeNewsBean>) mDate);

//            Setting Adapter and Setting mData


            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapter);
            swipeRefreshLayout.setRefreshing(false);

        }
    };
}
