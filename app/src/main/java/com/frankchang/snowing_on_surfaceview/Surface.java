package com.frankchang.snowing_on_surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Surface extends SurfaceView implements SurfaceHolder.Callback {

    // 常數
    private static final int SNOW_COUNT = 100;  // 雪的顆數
    // 物件
    private final SurfaceHolder holder;
    private final Paint pen;
    private List<Snow> snows = new ArrayList<>();
    private Random random = new Random();
    // 變數
    private int height;


    public Surface(Context context) {
        super(context);

        // 建立 SurfaceView
        holder = getHolder();
        holder.addCallback(this);
        // 建立畫筆
        pen = new Paint();
        pen.setColor(Color.WHITE);
        pen.setAntiAlias(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        int width = getWidth(); // 畫布寬
        height = getHeight();   // 畫布高

        // 產生雪
        for (int i = 0; i < SNOW_COUNT; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            Snow snow = new Snow(x, y);
            snows.add(snow);
        }

        // 開始下雪
        new Snowing().start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // no use
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // no use
    }

    public void drawCanvas() {
        // 鎖定畫布
        Canvas canvas = holder.lockCanvas();
        // 設定畫布底色
        canvas.drawColor(Color.BLACK);

        // 畫出全部的雪
        for (Snow snow : snows) {
            canvas.drawOval(snow.getX(), snow.getY(), snow.getX() + snow.getSnowSize(),
                    snow.getY() + snow.getSnowSize(), pen);
        }

        // 畫上畫布，並解鎖畫布
        holder.unlockCanvasAndPost(canvas);
    }


    private class Snowing extends Thread {

        @Override
        public void run() {
            super.run();

            while (true) {
                // 設定所有雪的 X 軸、Y 軸位置
                for (Snow snow : snows) {
                    int x = snow.getX();
                    int y = snow.getY();

                    if (y == height) {
                        y = 0;
                    } else {
                        y++;
                    }
                    x = x + random.nextInt(3) - 1;

                    snow.setX(x);
                    snow.setY(y);
                }

                // 畫下雪動畫
                drawCanvas();

                // 雪落下速度
                try {
                    sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
