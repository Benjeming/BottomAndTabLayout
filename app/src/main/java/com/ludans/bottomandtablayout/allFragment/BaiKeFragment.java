package com.ludans.bottomandtablayout.allFragment;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ludans.bottomandtablayout.R;
import com.ludans.bottomandtablayout.allBean.CropBean;
import com.ludans.bottomandtablayout.allFragment.BaiKe.FragmentBaiKeList;
import com.ludans.bottomandtablayout.allFragment.BaiKe.FragmentWebView;
import com.ludans.bottomandtablayout.utils.OkHttpsUtils;
import com.ludans.bottomandtablayout.utils.PathConfing;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

public class BaiKeFragment extends Fragment implements View.OnClickListener { // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<CropBean> mCropData = new ArrayList<>();
    private PathConfing pathConfing = new PathConfing();
    private FragmentManager fManager = getFragmentManager();

    @SuppressLint("ValidFragment")
    public BaiKeFragment(FragmentManager fManager) {

        this.fManager = fManager;
    }

    public BaiKeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment biakeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BaiKeFragment newInstance(String param1, String param2) {
        BaiKeFragment fragment = new BaiKeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.baike_main_fragment, container, false);
        initView(view);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void initView(View view) {
        ImageButton imageButton1 = view.findViewById(R.id.image_chaye);
        ImageButton imageButton2 = view.findViewById(R.id.image_mianhua);
        ImageButton imageButton3 = view.findViewById(R.id.image_shengxian);
        ImageButton imageButton4 = view.findViewById(R.id.image_shuidao);
        ImageButton imageButton5 = view.findViewById(R.id.image_tobacco);
        ImageButton imageButton6 = view.findViewById(R.id.image_youcai);

        imageButton1.setOnClickListener(this);
        imageButton2.setOnClickListener(this);
        imageButton3.setOnClickListener(this);
        imageButton4.setOnClickListener(this);
        imageButton5.setOnClickListener(this);
        imageButton6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_chaye:
                Toast.makeText(getContext(), "点击了茶叶", Toast.LENGTH_SHORT).show();
                //联网获取json数据，然后解析，获取列表
                try {
                    parseJsonWithGson(pathConfing.PATH_CHAYE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.image_mianhua:
                Toast.makeText(getContext(), "点击了棉花", Toast.LENGTH_SHORT).show();
                try {
                    parseJsonWithGson(pathConfing.PATH_MIANHUA);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.image_shengxian:
                Toast.makeText(getContext(), "点击了玉米", Toast.LENGTH_SHORT).show();
                try {
                    parseJsonWithGson(pathConfing.PATH_YUMI);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.image_shuidao:
                Toast.makeText(getContext(), "点击了水稻", Toast.LENGTH_SHORT).show();
                try {
                    parseJsonWithGson(pathConfing.PATH_SHUIDAO);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.image_tobacco:
                Toast.makeText(getContext(), "点击了烟草", Toast.LENGTH_SHORT).show();
                try {
                    parseJsonWithGson(pathConfing.PATH_YANCAO);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.image_youcai:
                Toast.makeText(getContext(), "点击了油菜", Toast.LENGTH_SHORT).show();
                try {
                    parseJsonWithGson(pathConfing.PATH_YOUCAI);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

        }
    }

    private void parseJsonWithGson(String address) throws IOException {
        // 联网获取json数据  ，  并解析json数据

//        Log.d(TAG, "onClick: 进入okhttp");
        OkHttpsUtils.sendRequestWithOkHttp(address, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e(TAG, "onFailure: 获取JSON数据失败！！！");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String body = response.body().string();
//                        Log.d(TAG, "onResponse: 获取json数据成功======"+body);

                Gson gson = new Gson();
                //将网络上的数据解析成功，然后放入到mCropData 集合中。
                mCropData = gson.fromJson(body, new TypeToken<ArrayList<CropBean>>() {
                }.getType());

//                ============================传值==============================
                FragmentBaiKeList fb = new FragmentBaiKeList(mCropData, getFragmentManager());

                Bundle bundle = new Bundle();
                bundle.putString("url",mCropData.get(0).getType());
                bundle.putSerializable("leftUrl", (ArrayList<CropBean>) mCropData);

                fb.setArguments(bundle);


//  *==============================================================================================================================
//  *             这个部分是传值的
//  *            Bundle bundle = new Bundle();
//  *          Log.d(TAG, "我要知道地址啊: "+mCropData.get(0).getType());
//  *             bundle.putString("url",mCropData.get(0).getType());
//  *              setArguments(bundle);
//  *             String dddddd = bundle.getString("url");
//  *            Log.d(TAG, "onResponse: 你有没有包装啊"+dddddd);
//  *                      Log.d(TAG, "onResponse: Json数据========"+mCropData.get(0).getType().toString());
//  *=========================================================================================================================

                //将mCropData数据装填到 列表中——（）
                //将数据传递个下一个Fragment


                FragmentTransaction ft = getFragmentManager().beginTransaction()
                        .replace(R.id.activity_fragment, fb);
                ft.addToBackStack(null);
                ft.commit();

            }
        });
    }
}
