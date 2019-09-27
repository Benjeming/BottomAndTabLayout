package com.ludans.bottomandtablayout.allFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ludans.bottomandtablayout.R;
import com.ludans.bottomandtablayout.allAdapter.HomeViewPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    //    自定义
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private HomeViewPagerAdapter adapter;
    private static final String TAG = "HomeFragment";

    public HomeFragment() {
        // Required empty public constructor
//        Log.e(TAG, "HomeFragment: 创建成功" );
    }


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_home, container, false);


        initView(rootview);
        return rootview;
    }

    private void initView(View rootview) {
        // viewPager 初始化，设置适配器
//        Log.e(TAG, "initView: 初始化页面" );
        viewPager = rootview.findViewById(R.id.home_viewpager);
//        Log.e(TAG, "initView: 设置适配器" );
        adapter = new HomeViewPagerAdapter(getFragmentManager(), getContext());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(1);
//            tabLayout 初始化，绑定ViewPager
        tabLayout = rootview.findViewById(R.id.home_tabLayout);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        tabLayout.setupWithViewPager(viewPager);

    }

}
