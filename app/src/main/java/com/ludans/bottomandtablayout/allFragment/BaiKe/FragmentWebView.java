package com.ludans.bottomandtablayout.allFragment.BaiKe;

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
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ludans.bottomandtablayout.R;
import com.ludans.bottomandtablayout.allBean.CropBean;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class FragmentWebView extends Fragment {
    private static final String TAG = "FragmentWebView";
    private final String BASE_URL ="http://crop.agridata.cn";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.webview,container,false);
        initView(view);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        Log.d(TAG, "返回总Fragment：\n"+getFragmentManager().getFragments());;
//        Log.d(TAG, "返回未移除的Fragment：\n"+getFragmentManager().findFragmentById(R.id.activity_fragment));;
////        ft.remove();
//        ft.hide(getFragmentManager().findFragmentById(R.id.activity_fragment)).commit();
////        ft.commit();
//        Log.d(TAG, "返回总Fragment：\n"+getFragmentManager().getFragments());;
//        Log.d(TAG, "返回移除后的Fragment：\n"+getFragmentManager().findFragmentById(R.id.activity_fragment));;
//
//        Log.d(TAG, "返回总Fragment：\n"+getFragmentManager().getFragments());;
//        Log.d(TAG, "onDestroyView: 返回占有R.id.act中的Fragment："+getFragmentManager().findFragmentById(R.id.activity_fragment));;




    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: 返回："+getFragmentManager().getBackStackEntryCount());
    }

    private void initView(View view) {
        WebView webView = view.findViewById(R.id.web_view);
        Bundle bundle =getArguments();
        String url = bundle.getString("url");
        String leftUrl = bundle.getString("leftUrl");
        String TARGET_URL = BASE_URL +url+"/"+leftUrl;
//        Log.d(TAG, "最终连接： "+TARGET_URL);


        webView.setInitialScale(65);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true); //设置缩放工具
        webSettings.setSupportZoom(true); //支持放大缩小

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(TARGET_URL);
//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//
//        ft.addToBackStack(null);
////        ft.remove(getParentFragment());
//        ft.commit();
    }
}
