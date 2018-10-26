package com.frankchang.snowing_on_surfaceview;

import java.util.Random;


public class Snow {

    // 物件
    private Random random = new Random();
    // 變數
    private int x;
    private int y;
    private int snowSize;


    public Snow(int x, int y) {
        this.x = x;     // 初始化 X 軸
        this.y = y;     // 初始化 Y 軸
        // 亂數取得大小變量
        snowSize = random.nextInt(40);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSnowSize() {
        return snowSize;
    }

}
