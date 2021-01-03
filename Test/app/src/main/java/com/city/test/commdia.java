package com.city.test;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;

public class commdia extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commdia);
    }




    @Override
    public void onBackPressed() {
        AlertDialog comdialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("普通对话框")//设置对话框的标题
                .setIcon(R.drawable.ic_lanuncher)//设置标题图标
                .setMessage("是否确定退出应用?")//设置对话框的提示信息
                //添加确定按钮
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        commdia.this.finish();
                    }
                })
                //添加取消按钮
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        comdialog = builder.create();
        comdialog.show();
    }
}
