package com.city.game2048;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    private static MainActivity mainActivity = null;
    private int score = 0;
    private TextView tvScore;
    private Button btn_replay;

    public MainActivity() {
        mainActivity = this;
    }

    public static MainActivity getMainActivity() {
        return mainActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvScore = findViewById(R.id.tvScore);
        btn_replay=findViewById(R.id.btn_replay);
        btn_replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameView.getGameView().startGame();
            }
        });
    }

    public void clearScore() {
        this.score = 0;
        showScore();
    }

    public void showScore(){
        tvScore.setText(String.valueOf(score));
    }

    public void addScore(int s){
        this.score+=s;
        showScore();
    }

    public int getScore() {
        return this.score;
    }
}
