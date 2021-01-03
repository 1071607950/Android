package com.city.game2048;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GameView extends GridLayout {
    private int num = 4;
    private static GameView gameView = null;
    private Card[][] cardsMap = new Card[num][num];
    private List<Point> emptPoints = new ArrayList<Point>();

    public static GameView getGameView() {
        return gameView;
    }


    public GameView(Context context) {
        super(context);
        gameView = this;
        initGameView();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        gameView = this;
        initGameView();
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        gameView = this;
        initGameView();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        int cardWidth = (Math.min(w, h) - 10) / num;

        addCards(cardWidth, cardWidth);

        startGame();
    }

    private void initGameView() {
        setColumnCount(num);
        setBackgroundColor(0xffbbada0);
        setOnTouchListener(new OnTouchListener() {
            private float startX, startY, offsetX, offsetY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        startY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        offsetX = event.getX() - startX;
                        offsetY = event.getY() - startY;
                        if (Math.abs(offsetX) > Math.abs(offsetY)) {
                            if (offsetX < -5) {
                                swipeLeft();
                            } else if (offsetX > 5) {
                                swipeRight();
                            }
                        } else {
                            if (offsetY < -5) {
                                swipeUp();
                            } else if (offsetY > 5) {
                                swipeDown();
                            }
                        }
                        break;
                }
                changecolor();
                return true;
            }
        });
    }

    private void addCards(int cardWidth, int cardHeight) {

        Card c;

        for (int y = 0; y < num; y++) {
            for (int x = 0; x < num; x++) {
                c = new Card(getContext());
                c.setNum(0);
                addView(c, cardWidth, cardHeight);
                cardsMap[x][y] = c;
            }
        }
    }

    public void startGame() {

        MainActivity.getMainActivity().clearScore();

        for (int y = 0; y < num; y++) {
            for (int x = 0; x < num; x++) {
                cardsMap[x][y].setNum(0);
            }
        }

        addRandomNum();
        addRandomNum();

        changecolor();
    }

    private void addRandomNum() {

        emptPoints.clear();

        for (int y = 0; y < num; y++) {
            for (int x = 0; x < num; x++) {
                if (cardsMap[x][y].getNum() <= 0) {
                    emptPoints.add(new Point(x, y));
                }
            }
        }

        Point p = emptPoints.remove(Double.valueOf(Math.random() * emptPoints.size()).intValue());
        cardsMap[p.x][p.y].setNum(Math.random() > 0.1 ? 2 : 4);

    }

    private void changecolor() {
        for (int y = 0; y < num; y++) {
            for (int x = 0; x < num; x++) {
                if (cardsMap[x][y].getNum() <= 0) {
                    cardsMap[x][y].setColor(0xffcdc0b4, 0xffcdc0b4);
                } else if (cardsMap[x][y].getNum() == 2) {
                    cardsMap[x][y].setColor(0xffeee4da, 0xff776e65);
                } else if (cardsMap[x][y].getNum() == 4) {
                    cardsMap[x][y].setColor(0xffede0c8, 0xff776e65);
                } else if (cardsMap[x][y].getNum() == 8) {
                    cardsMap[x][y].setColor(0xfff2b179, 0xfff9f6f2);
                } else if (cardsMap[x][y].getNum() == 16) {
                    cardsMap[x][y].setColor(0xfff59563, 0xfff9f6f2);
                } else if (cardsMap[x][y].getNum() == 32) {
                    cardsMap[x][y].setColor(0xfff67c5f, 0xfff9f6f2);
                } else if (cardsMap[x][y].getNum() == 64) {
                    cardsMap[x][y].setColor(0xfff65e3b, 0xfff9f6f2);
                } else if (cardsMap[x][y].getNum() == 128) {
                    cardsMap[x][y].setColor(0xffedcf72, 0xfff9f6f2);
                } else if (cardsMap[x][y].getNum() == 256) {
                    cardsMap[x][y].setColor(0xffedcc61, 0xfff9f6f2);
                } else if (cardsMap[x][y].getNum() == 512) {
                    cardsMap[x][y].setColor(0xffedc850, 0xfff9f6f2);
                } else if (cardsMap[x][y].getNum() == 1024) {
                    cardsMap[x][y].setColor(0xffedc53f, 0xfff9f6f2);
                } else if (cardsMap[x][y].getNum() == 2048) {
                    cardsMap[x][y].setColor(0xffedc22e, 0xfff9f6f2);
                }
            }
        }
    }

    public void checkComplete() {

        boolean complete = true;
        ALL:
        for (int y = 0; y < num; y++) {
            for (int x = 0; x < num; x++) {
                if (cardsMap[x][y].getNum() == 0 ||
                        (x > 0 && cardsMap[x][y].equals(cardsMap[x - 1][y])) ||
                        (x < num - 1 && cardsMap[x][y].equals(cardsMap[x + 1][y])) ||
                        (y > 0 && cardsMap[x][y].equals(cardsMap[x][y - 1])) ||
                        (y < num - 1 && cardsMap[x][y].equals(cardsMap[x][y + 1]))) {
                    complete = false;
                    break ALL;
                } else if (cardsMap[x][y].getNum() == 2048) {
                    complete = true;
                }
            }
        }
        if (complete) {
            new AlertDialog.Builder(getContext())
                    .setTitle("你好")
                    .setMessage("游戏结束,得分:" + MainActivity.getMainActivity().getScore())
                    .setPositiveButton("重来", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startGame();
                        }
                    }).show();
            Toast.makeText(MainActivity.getMainActivity(),"游戏结束：得分:"+MainActivity.getMainActivity().getScore(),Toast.LENGTH_SHORT).show();
        }
    }


    private void swipeLeft() {
        boolean merge = false;
        for (int y = 0; y < num; y++) {
            for (int x = 0; x < num; x++) {
                for (int x1 = x + 1; x1 < num; x1++) {
                    if (cardsMap[x1][y].getNum() > 0) {
                        if (cardsMap[x][y].getNum() <= 0) {
                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
                            cardsMap[x1][y].setNum(0);
                            x--;
                            merge = true;
                        } else if (cardsMap[x][y].equals(cardsMap[x1][y])) {
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
                            cardsMap[x1][y].setNum(0);
                            MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
                            merge = true;
                        }
                        break;
                    }
                }
            }
        }
        if (merge) {
            addRandomNum();
            checkComplete();
        }
    }

    private void swipeRight() {
        boolean merge = false;
        for (int y = 0; y < num; y++) {
            for (int x = num - 1; x >= 0; x--) {
                for (int x1 = x - 1; x1 >= 0; x1--) {
                    if (cardsMap[x1][y].getNum() > 0) {
                        if (cardsMap[x][y].getNum() <= 0) {
                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
                            cardsMap[x1][y].setNum(0);
                            x++;
                            merge = true;
                        } else if (cardsMap[x][y].equals(cardsMap[x1][y])) {
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
                            cardsMap[x1][y].setNum(0);
                            MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
                            merge = true;
                        }
                        break;
                    }
                }
            }
        }
        if (merge) {
            addRandomNum();
            checkComplete();
        }
    }

    private void swipeUp() {
        boolean merge = false;
        for (int x = 0; x < num; x++) {
            for (int y = 0; y < num; y++) {
                for (int y1 = y + 1; y1 < num; y1++) {
                    if (cardsMap[x][y1].getNum() > 0) {
                        if (cardsMap[x][y].getNum() <= 0) {
                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
                            cardsMap[x][y1].setNum(0);
                            y--;
                            merge = true;
                        } else if (cardsMap[x][y].equals(cardsMap[x][y1])) {
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
                            cardsMap[x][y1].setNum(0);
                            MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
                            merge = true;
                        }
                        break;
                    }
                }
            }
        }
        if (merge) {
            addRandomNum();
            checkComplete();
        }
    }

    private void swipeDown() {
        boolean merge = false;
        for (int x = 0; x < num; x++) {
            for (int y = num - 1; y >= 0; y--) {
                for (int y1 = y - 1; y1 >= 0; y1--) {
                    if (cardsMap[x][y1].getNum() > 0) {
                        if (cardsMap[x][y].getNum() <= 0) {
                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
                            cardsMap[x][y1].setNum(0);
                            y++;
                            merge = true;
                        } else if (cardsMap[x][y].equals(cardsMap[x][y1])) {
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
                            cardsMap[x][y1].setNum(0);
                            MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
                            merge = true;
                        }
                        break;
                    }
                }
            }
        }
        if (merge) {
            addRandomNum();
            checkComplete();
        }
    }

}
