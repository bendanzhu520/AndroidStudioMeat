package com.winorout.meat.signin.chosecountry.tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.winorout.meat.R;

import java.util.List;

public class Letter_Adapter extends BaseAdapter {
    private List<Country_name> list;
    private LayoutInflater inflater;

    public Letter_Adapter(Context context, List<Country_name> list) {
        inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.letter__item, null);
            holder.tv_word = (TextView) convertView.findViewById(R.id.contacts_word);
            holder.tv_name = (TextView) convertView.findViewById(R.id.contacts_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String word = list.get(position).getHeaderWord();
        holder.tv_word.setText(word);
        holder.tv_name.setText(list.get(position).getName());
        //将相同字母开头的合并在一起
        if (position == 0) {
            //第一个是一定显示的
            holder.tv_word.setVisibility(View.VISIBLE);
        } else {
            //后一个与前一个对比,判断首字母是否相同，相同则隐藏
            String headerWord = list.get(position - 1).getHeaderWord();
            if (word.equals(headerWord)) {
                holder.tv_word.setVisibility(View.GONE);
            } else {
                holder.tv_word.setVisibility(View.VISIBLE);
            }
        }
        return convertView;
    }

    private class ViewHolder {
        private TextView tv_word;
        private TextView tv_name;
    }
}
