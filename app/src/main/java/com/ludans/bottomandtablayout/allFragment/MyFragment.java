package com.ludans.bottomandtablayout.allFragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ludans.bottomandtablayout.R;
import com.ludans.bottomandtablayout.activity.ShowActivity;


public class MyFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private CardView card_soft;
    private CardView card_connect;
    private CardView card_user;
    private CardView card_setting;
    private AlertDialog.Builder alertDialog;

    public MyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyFragment newInstance(String param1, String param2) {
        MyFragment fragment = new MyFragment();
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
        View view = inflater.inflate(R.layout.fragment_my, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        card_connect = view.findViewById(R.id.card_connect);
        card_setting = view.findViewById(R.id.card_setting);
        card_soft = view.findViewById(R.id.card_soft);
        card_user = view.findViewById(R.id.card_user);

        card_user.setOnClickListener(this);
        card_soft.setOnClickListener(this);
        card_setting.setOnClickListener(this);
        card_connect.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.card_connect:
                Intent email = new Intent(Intent.ACTION_SEND);
                email.setType("message/rfc822");
                email.putExtra(Intent.EXTRA_EMAIL, new String[] {"1243515833@qq.com"});
                email.putExtra(Intent.EXTRA_SUBJECT, "Hi, This is a test mail..");
                email.putExtra(Intent.EXTRA_TEXT   , "Did you get this mail? if so please reply back");
                startActivity(Intent.createChooser(email, "Choose an Email Client"));
                break;
            case R.id.card_setting:
                Intent intent1 = new Intent(getActivity(), ShowActivity.class);
                startActivity(intent1);
                break;
            case R.id.card_user:
                Toast.makeText(getActivity(),"请联系1243515833@qq.com",Toast.LENGTH_LONG).show();
                break;
            case R.id.card_soft:
                alertDialog = new AlertDialog.Builder(getActivity());
                alertDialog.setIcon(R.mipmap.ic_launcher_round)
                        .setTitle("常德农技通")
                        .setMessage("软件介绍\n" +
                                "  “常德农技通” App 是面向常德市农业资讯终端打造的，以简洁全面的表现形式，引导农业从事者获取详细资讯的开放平台。其旨在传播农业互联理念，倡导惠农新生活。“常德农技通”App具备三大功能，分别是常德农资讯、农百科与三农观点。“常德农技通”App力求搭建平台为用户提供资讯服务，内容上重视时效和排版，用网络传播方式传达“移动农业”新生活。\n" +
                                "\n" +
                                "软件背景\n" +
                                "  农业信息化是近年我国政府有关农业政策的热点领域。" +
                                "信息技术在农业中的作用越来越明显，移动互联网配合智能移动终端为解决农村信息化的“最后一公里”问题提供了方案，为农村信息化服务提供了新模式。" +
                                "常德市独特的气候条件和丰富的水土资源，造就了江南著名的“粮仓、酒市、烟都、纺城、茶乡”。" +
                                "全市粮食、棉花、油料、牲猪、蚕茧和水产品的总产均居全省之首，是全国重要的商品粮、棉、油、猪和鱼的生产基地。" +
                                "针对常德市农业发展特点，有针对性的研究基于移动互联的常德市农业科技信息服务系统促进农业从事者知识水平、技术水平提高，进一步促进全市农业发展。\n")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                alertDialog
                            }
                        });
                alertDialog.show();
                break;
        }
    }
}
