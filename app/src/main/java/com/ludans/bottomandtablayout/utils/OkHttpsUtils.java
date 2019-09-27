package com.ludans.bottomandtablayout.utils;


import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OkHttpsUtils {

    public static  void sendRequestWithOkHttp(String PATH, Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(PATH)
                .build();
        client.newCall(request).enqueue((okhttp3.Callback) callback);
    }
}
