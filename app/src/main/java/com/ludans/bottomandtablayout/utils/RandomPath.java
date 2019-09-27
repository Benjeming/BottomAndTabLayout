package com.ludans.bottomandtablayout.utils;

import android.util.Log;

import java.util.Random;

public class RandomPath {
    private Random random;
    private int type;
    private static final String TAG = "RandomPath";
    //"常德农业" ,包含两条URl 常德动态 和乡村振兴
    private static final String[] homePath = {
            "http://download.ludashi123.cn/ChangDeNews/files/常德动态.json"
            , "http://download.ludashi123.cn/ChangDeNews/files/乡村振兴.json"};
    //"农业指导",包括，农事指导，现代农业，质量安全
    private static final String[] nongPath = {
            "http://download.ludashi123.cn/ChangDeNews/files/%E5%86%9C%E4%BA%8B%E6%8C%87%E5%AF%BC.json"
            , "http://download.ludashi123.cn/ChangDeNews/files/%E7%8E%B0%E4%BB%A3%E5%86%9C%E4%B8%9A.json"
            , "http://download.ludashi123.cn/ChangDeNews/files/质量安全.json"};
    //"三农观点"，包括三农论坛，领导讲话
    private static final String[] sanPath = {
            "http://download.ludashi123.cn/ChangDeNews/files/%E4%B8%89%E5%86%9C%E8%AE%BA%E5%9D%9B.json",
    };

    public RandomPath( int type) {
        this.type = type;
    }

    public String getPath() {
        if (getType() == 1) {
            Log.d(TAG, "Random首页中的网站："+getHomePath());
            return getHomePath();
        }
        if (getType() == 2) {
            Log.d(TAG, "Random指导中的网站："+getNongPath());
            return getNongPath();
        }
        if (getType() == 3) {
            Log.d(TAG, "Random中的网站："+getSanPath());
            return getSanPath();
        }
        return "不符合条件";
    }

    public int getType() {
        return type;
    }



    public String getHomePath() {
        random = new Random();
        int i = random.nextInt(homePath.length );
        return homePath[i];
    }

    public String getNongPath() {
        random = new Random();
        int i = random.nextInt(nongPath.length);
        return nongPath[i];
    }

    public String getSanPath() {
        return sanPath[0];
    }
}
