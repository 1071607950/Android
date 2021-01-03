package com.city.test;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class muldia extends AppCompatActivity {

    private CharSequence[] items = new CharSequence[]{"旅游","美食","看电影","运动"};
    private boolean[] checkedItems = new boolean[]{false,true,false,false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.muldia);

    }

    public void Click1(View v){
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("请添加兴趣爱好!")
                .setIcon(R.drawable.ic_lanuncher)
                .setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        checkedItems[which] = isChecked;
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuffer stringBuffer = new StringBuffer();
                        for (int i=0;i<=checkedItems.length-1;i++){
                            if (checkedItems[i]){
                                stringBuffer.append(items[i]).append(" ");
                            }
                        }
                        if (stringBuffer!=null){
                            Toast.makeText(muldia.this,stringBuffer.toString(),Toast.LENGTH_LONG).show();
                        }
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
