package com.city.androidstore;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private ListView mListView;
    // 需要适配的数据
    private String[] names = { "京东商城", "QQ", "QQ斗地主", "新浪微博", "天猫",
            "UC浏览器", "微信" ,"QQ1","QQ2"};

    private int[]  icons = {R.drawable.jd,R.drawable.qq,R.drawable.qq_dizhu,
            R.drawable.sina,R.drawable.tmall,R.drawable.uc,
            R.drawable.weixin,R.drawable.qq,R.drawable.qq};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 初始化ListView控件
        mListView = (ListView) findViewById(R.id.lv);
        // 创建一个Adapter的实例
        MyBaseAdapter mAdapter = new MyBaseAdapter(names,icons,this);
        // 设置Adapter
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = view.findViewById(R.id.tv_list);
                Toast.makeText(MainActivity.this,tv.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        // ArrayAdapter
        // mListView.setAdapter(new ArrayAdapter<String>(this,
        // R.layout.list_item,
        // R.id.tv_list, names));

    }


}
