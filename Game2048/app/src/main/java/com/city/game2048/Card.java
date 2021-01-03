package com.city.game2048;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;


public class Card extends FrameLayout {

    private TextView label;
    private int num = 0;
    private Button button;

    public Card(Context context) {
        super(context);
        label = new TextView(getContext());
        label.setTextSize(32);
        label.setBackgroundColor(0xffcdc0b4);
        label.setGravity(Gravity.CENTER);
        LayoutParams lp = new LayoutParams(-1, -1);
        lp.setMargins(10, 10, 0, 0);
        addView(label, lp);

        setNum(0);
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;

        if (num <= 0) {
            label.setText("");
        } else {
            label.setText(String.valueOf(num));
        }

    }

    public void setColor(int backgroundColor,int textColor){
        label.setTextColor(textColor);
        label.setBackgroundColor(backgroundColor);
    }

    public boolean equals(Card o) {
        return getNum() == o.getNum();
    }

}
