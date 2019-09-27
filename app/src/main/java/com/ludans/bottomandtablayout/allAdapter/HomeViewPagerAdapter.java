package com.ludans.bottomandtablayout.allAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import com.ludans.bottomandtablayout.allFragment.Home.PagerChangDeFragment;
import com.ludans.bottomandtablayout.allFragment.Home.PagerPointFragment;
import com.ludans.bottomandtablayout.allFragment.Home.PagerZhiDaoFragment;
import com.ludans.bottomandtablayout.allFragment.PagerFragment;

public class HomeViewPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = "HomeViewPagerAdapter";
    private Fragment curr;

    private String[] mTitle = new String[]{"常德农业", "农业指导", "三农观点"};

    private Context context;

    public HomeViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
//        Log.e(TAG, "HomeViewPagerAdapter: 适配器创建成功" + mTitle[1]);
        this.context = context;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public Fragment getItem(int i) {
//        返回具体的Item

        Log.d(TAG, "获得的标题："+getPageTitle(i));
        if ("常德农业".equals(getPageTitle(i))){
            return PagerChangDeFragment.newInstance(1,null);
        }
        if (getPageTitle(i).equals("农业指导")){
            return PagerZhiDaoFragment.newInstance(2,null);
        }
        if (getPageTitle(i).equals("三农观点")){
            return PagerPointFragment.newInstance(3,null);
        }
        return null;
    }
// ===================================================================================

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        curr = (Fragment) object;
        super.setPrimaryItem(container, position, object);
    }

    //===============================================================================================
    @Override
    public int getCount() {
        return mTitle.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle[position];
    }
}
