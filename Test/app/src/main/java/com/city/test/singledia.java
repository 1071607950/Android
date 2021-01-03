package com.city.test;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class singledia extends AppCompatActivity {

    private TextView tView;
    private int[] textSizeArr = {10,20,25,30,40};
    int textSize = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singledia);
        tView = findViewById(R.id.tv);
    }
    public void Click(View v){
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("设置字体大小")
                .setIcon(R.drawable.ic_lanuncher)
                .setSingleChoiceItems(new String[]{"小号", "默认", "中号", "大号", "超大"}, textSize, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textSize = which;
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tView.setTextSize(textSizeArr[textSize]);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        dialog = builder.create();
        dialog.show();
    }
}
