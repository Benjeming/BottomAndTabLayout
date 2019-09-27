package com.ludans.bottomandtablayout.allFragment.BaiKe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ludans.bottomandtablayout.R;
import com.ludans.bottomandtablayout.allAdapter.BaiKeAdapter.BaiKeListAdapter;
import com.ludans.bottomandtablayout.allBean.CropBean;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

@SuppressLint("ValidFragment")
public class FragmentBaiKeList extends Fragment implements AdapterView.OnItemClickListener {
    private List<CropBean> mData = new ArrayList<>();
    private Context context;
    private FragmentManager fragmentManager = getFragmentManager();
    private final String BASE_URL = "http://crop.agridata.cn";
    private FragmentBaiKeList fragmentBaiKeList;

    public FragmentBaiKeList() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    public FragmentBaiKeList(List<CropBean> mData, FragmentManager fragmentManager) {
        this.mData = mData;
        this.fragmentManager = fragmentManager;
        Log.d(TAG, "FragmentBaiKeList: 进入了BaikeList 的构造函数");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_baike_list, container, false);
        ListView listView = view.findViewById(R.id.baike_list);
        listView.setOnItemClickListener(this);
//        Log.d(TAG, "onCreateView: 初始化List");
        BaiKeListAdapter adapter = new BaiKeListAdapter(mData, getContext());
//        Log.d(TAG, "onCreateView: mData的值：===" + mData);
        listView.setAdapter(adapter);
        //设置适配器

        return view;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

        new Thread() {
            @Override
            public void run() {
                FragmentWebView wb = new FragmentWebView();
                Log.d(TAG, "返回WebView的id："+getFragmentManager().getFragments()
                +"\n"+"Primary:"+getFragmentManager().findFragmentById(R.id.activity_fragment)
                        +"\n数量："+getFragmentManager().getBackStackEntryCount()
                );
                Bundle bundle = getArguments();

                String urlTemp = bundle.getString("url");
                Log.d(TAG, "run: 重新调用"+urlTemp);
                ArrayList<CropBean> leftUrl1 = (ArrayList<CropBean>) bundle.getSerializable("leftUrl");
                Log.d(TAG, "run: 重新调用上:\n"+leftUrl1.toString());
                ArrayList<CropBean> urlList = leftUrl1;
                String restUrl = urlList.get(0).getContent().get(position).getCrop_disasters_url();
                Log.d(TAG, "重新调用下: "+restUrl);
                bundle.putString("url",urlTemp);
                bundle.putSerializable("leftUrl",restUrl);
                wb.setArguments(bundle);
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.activity_fragment,wb);
                ft.addToBackStack(null);
                ft.commit();


            }
        }.start();
    }
}
