package com.city.androidstore;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

// 创建一个类继承BaseAdapter
class MyBaseAdapter extends BaseAdapter {
    String[] names;
    int[] icons;
    private Activity MainActivity;

    MyBaseAdapter(String[] names,int[] icons,Activity MainActivity){
        this.names=names;
        this.icons=icons;
        this.MainActivity=MainActivity;
    }

    // 得到item的总数
    public int getCount() {
        // 返回ListView Item条目的总数
        return names.length;
    }

    // 得到Item代表的对象
    public Object getItem(int position) {
        // 返回ListView Item条目代表的对象
        return names[position];
    }

    // 得到Item的id
    public long getItemId(int position) {
        // 返回ListView Item的id
        return position;
    }

    // 得到Item的View视图
    public View getView(int position, View convertView, ViewGroup parent) {
        // 将list_item.xml文件找出来并转换成View对象
        View view = View.inflate((Activity) MainActivity, R.layout.list_item, null); // 找到list_item.xml中创建的TextView
        TextView mTextView = (TextView) view.findViewById(R.id.tv_list);
        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        mTextView.setText(names[position]);
        imageView.setBackgroundResource(icons[position]);
        return view;
    }


}