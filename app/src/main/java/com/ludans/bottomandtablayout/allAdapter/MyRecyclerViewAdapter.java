package com.ludans.bottomandtablayout.allAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.ludans.bottomandtablayout.R;
import com.ludans.bottomandtablayout.allBean.ChangDeNewsBean;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter {

    private Context context;

    private ArrayList<ChangDeNewsBean> mDate;
    private static final String TAG = "MyRecyclerViewAdapter";
    private  FragmentManager fm;
    public MyRecyclerViewAdapter(Context context, ArrayList<ChangDeNewsBean> mDate) {
        this.context = context;
        this.mDate = mDate;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Bind Layout from item that CardView
        // 绑定 来自CardView  item 布局
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_pager_card, viewGroup, false);
        return new ViewHolder(view);
    }


//    public void onBindViewHolder(@NonNull NoImageView holder, final int position, @NonNull List payloads) {
//        holder.titleText.setText(mDate.get(position).getTitle());
//        holder.timeText.setText(mDate.get(position).getTime());
//        Log.d(TAG, "标题： " + mDate.get(position).getTitle());
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            }
//        });
//    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {

        ViewHolder viewHolder1 = (ViewHolder) viewHolder;
        viewHolder1.titleText.setText(mDate.get(i).getTitle());
        viewHolder1.timeText.setText(mDate.get(i).getTime());

        viewHolder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"你点击了"+i+1+"个item",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDate.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder{
        //        Setting clickLister ?
//        为什么需要设置点击事件

        //       initial weight in constructor
//        在 构造函数中 初始化控件
        private TextView titleText;
        private TextView timeText;

        public ViewHolder(View itemView) {
            super(itemView);
            //            initial weight

            titleText = itemView.findViewById(R.id.hot_title);
            timeText = itemView.findViewById(R.id.hot_time);

        }
    }


}
