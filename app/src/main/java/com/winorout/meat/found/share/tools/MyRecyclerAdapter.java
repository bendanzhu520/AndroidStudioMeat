package com.winorout.meat.found.share.tools;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.winorout.meat.R;

import java.util.List;
import java.util.Random;

/**
 * Created by Mr-x on 2017/04/11.
 */

/*
* 用来实现瀑布流信息展示的适配器
* 使用了Glide，依赖库：    compile 'com.github.bumptech.glide:glide:3.7.0'
* */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {

    private Context context;
    public List<json_analysis.ShareItem> list;
    public LayoutInflater inflater;
        private OnItemClickListener mOnItemClickListener;

        public interface OnItemClickListener {
            void onClick(int position);

            void onLongClick(int position);
        }

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.mOnItemClickListener = onItemClickListener;
        }

    public MyRecyclerAdapter(Context context, List<json_analysis.ShareItem> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.share_item, parent, false);
        MyViewHolder viewholder = new MyViewHolder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        Random rnd = new Random();
        int rndint = rnd.nextInt(70) + 80;
        holder.txt.setText(list.get(position).txt);
//            holder.img.setBackground(new BitmapDrawable(list.get(position).img));
        Glide.with(context).load(list.get(position).img).centerCrop().into(holder.img);
        holder.txt.setHeight(rndint + (position % 2) * 30);

            if (mOnItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onClick(position);
                    }
                });

                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        mOnItemClickListener.onLongClick(position);
                        return false;
                    }
                });
            }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt;
        ImageView img;

        public MyViewHolder(View itemView) {
            super(itemView);
            txt = (TextView) itemView.findViewById(R.id.share_itemtxt);
            img = (ImageView) itemView.findViewById(R.id.img);
        }
    }

}
