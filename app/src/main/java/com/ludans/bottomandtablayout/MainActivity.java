package com.ludans.bottomandtablayout;

import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Window;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ludans.bottomandtablayout.allFragment.BaiKeFragment;
import com.ludans.bottomandtablayout.allFragment.HomeFragment;


public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {
    private BottomNavigationBar bottomNavigationBar;
    private Toolbar mtoolBar;
    private HomeFragment homeFragment;
    private BaiKeFragment baiKeFragment;
    private static final String TAG = "MainActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
//        Log.e(TAG, "onCreate: 创建HomeFragemt" );
        homeFragment = new HomeFragment();
        baiKeFragment = new BaiKeFragment();


        initView();

    }

    private void initView() {
//        设置标题
        mtoolBar = (Toolbar) findViewById(R.id.toolBar);
        mtoolBar.setTitle("常德头条-首页");
//        setSupportActionBar(mtoolBar);

//        设置Fragment

        //设置底部导航
        bottomNavigationBar = findViewById(R.id.activity_bottom_navigation);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.shouye, "首页"))
                .addItem(new BottomNavigationItem(R.drawable.baike, "百科"))
                .addItem(new BottomNavigationItem(R.drawable.tupian, "图片"))
                .setFirstSelectedPosition(0)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_fragment, homeFragment).commit();
    }

    @Override
    public void onTabSelected(int position) {
        FragmentTransaction fm = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                mtoolBar.setTitle("常德头条-首页");
                fm.replace(R.id.activity_fragment, homeFragment).commit();
                break;
            case 1:
                mtoolBar.setTitle("常德头条-百科");
                fm.replace(R.id.activity_fragment, baiKeFragment).commit();
                break;
            case 2:
                mtoolBar.setTitle("常德头条-图片");
//                fm.replace(R.id.activity_fragment, homeFragment).commit();
                break;

        }
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
