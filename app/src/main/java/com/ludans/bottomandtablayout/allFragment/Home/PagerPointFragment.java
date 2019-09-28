package com.ludans.bottomandtablayout.allFragment.Home;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
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
import com.ludans.bottomandtablayout.allFragment.PagerFragment;
import com.ludans.bottomandtablayout.utils.OkHttpsUtils;
import com.ludans.bottomandtablayout.utils.RandomPath;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class PagerPointFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private int mParam1;
    private String mParam2;

    private static final String TAG = "PagerPointFragment";


    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private List<ChangDeNewsBean> mDate;
    private MyRecyclerViewAdapter adapter;
    private RandomPath randomPath ;

    public PagerPointFragment() {
        // Required empty public constructor
    }

    public static PagerPointFragment newInstance(int param1, String param2) {
        PagerPointFragment fragment = new PagerPointFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "三农观点onCreate----->");
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "三农观点onCreateView----->");
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_pager, container, false);
        initView(rootView,3);
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

    public void creatThread( final int urlType) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                postJson( urlType);
            }
        }.start();
    }


    public void postJson( int type) {
        randomPath = new RandomPath(type);
        String homePath = randomPath.getPath();
//        Log.d(TAG, "postJson: 随机url：" + homePath);

        OkHttpsUtils.sendRequestWithOkHttp(homePath, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
//                Log.d(TAG, "onFailure: 网络连接失败！");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.d(TAG, "onResponse: 网络连接成功！！");
                String body = response.body().string();



//            Setting Adapter and Setting mData

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
//            Log.d(TAG, "onResponse: Gson 解析成功！");
            adapter = new MyRecyclerViewAdapter(getContext(), (ArrayList<ChangDeNewsBean>) mDate);

//            Setting Adapter and Setting mData


            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapter);
            swipeRefreshLayout.setRefreshing(false);

        }
    };


    public void onStart() {
        super.onStart();
        Log.d(TAG, "三农观点onStart------>");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "三农观点onAttach------> ");
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.d(TAG, "三农观点onActivityCreated-----------> ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "三农观点onResume-----> ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "三农观点onPause:----->");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "三农观点onStop:----->");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "三农观点onDestroyView----->");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "三农观点onDestroy:----->");
    }
}
