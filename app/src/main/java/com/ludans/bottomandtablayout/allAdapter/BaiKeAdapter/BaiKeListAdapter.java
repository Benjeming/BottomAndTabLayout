package com.ludans.bottomandtablayout.allAdapter.BaiKeAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ludans.bottomandtablayout.R;
import com.ludans.bottomandtablayout.allBean.CropBean;

import java.util.ArrayList;
import java.util.List;

public class BaiKeListAdapter extends BaseAdapter {
    private static final String TAG = "BaiKeListAdapter";
    private List<CropBean> mData = new ArrayList<>();
    private Context context;


    public BaiKeListAdapter(List<CropBean> mData, Context context) {
        this.mData = mData;
        this.context = context;
        Log.d(TAG, "BaiKeListAdapter: 进入了适配器的构造函数");
    }

    private class ViewHodler {
        TextView textView;
    };

    @Override
    public int getCount() {
        return mData.get(0).getContent().size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler viewHodler;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_baike, parent, false);
            viewHodler = new ViewHodler();
            viewHodler.textView = convertView.findViewById(R.id.item_baike_text);
            convertView.setTag(viewHodler);
        } else {
            viewHodler = (ViewHodler) convertView.getTag();
        }
//        Log.d(TAG, "getView: 获取的病害信息：" +mData.get(0).getContent().size() );
        viewHodler.textView.setText(mData.get(0).getContent().get(position).getCrop_disasters_name());
        return convertView;

//        =======================================================================================

//        TextView textView = convertView.findViewById(R.id.item_baike_text);
//        textView.setText(mData.get(0).getContent().get(position).getCrop_disasters_name());
//        return convertView;
    }
}
