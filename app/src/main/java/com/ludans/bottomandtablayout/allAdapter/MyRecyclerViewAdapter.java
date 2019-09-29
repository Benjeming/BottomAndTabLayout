package com.ludans.bottomandtablayout.allAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ludans.bottomandtablayout.R;
import com.ludans.bottomandtablayout.allBean.ChangDeNewsBean;

import java.util.ArrayList;

public  class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>{

    private Context context;
    private ArrayList<ChangDeNewsBean> mDate;
    private static final String TAG = "MyRecyclerViewAdapter";
    private FragmentManager fm;


    public MyRecyclerViewAdapter(Context context, ArrayList<ChangDeNewsBean> mDate) {
        this.context = context;
        this.mDate = mDate;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Bind Layout from item that CardView
        // 绑定 来自CardView  item 布局
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_pager_card, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.titleText.setText(mDate.get(i).getTitle());
        viewHolder.timeText.setText(mDate.get(i).getTime());

    }


    @Override
    public int getItemCount() {
        return mDate.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder{

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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener!=null){
                        onItemClickListener.OnItemClick(v,getLayoutPosition());
                    }
                }
            });
        }

    }

    public interface OnItemClickListener{
        public void OnItemClick(View v , int postion);
    }

    private OnItemClickListener onItemClickListener;


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
