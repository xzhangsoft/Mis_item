package com.example.canvas;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mis_item.R;

public class IronmanActivity extends AppCompatActivity {
    private ImageView iv_canvas;
    private Bitmap baseBitmap;
    private Canvas canvas;
    private Paint mPaint;

    private int canvasCenterX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ironman);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        bindViews();
    }

    public void onWindowFocusChanged(boolean focus) {
        super.onWindowFocusChanged(focus);
        // get the imageviews width and height here

        createNewCanvas();
        drawIronMan();
        iv_canvas.setImageBitmap(baseBitmap);
    }

    private void bindViews() {
        iv_canvas = (ImageView) findViewById(R.id.iv_ironman);
    }

    public void createNewCanvas() {
        baseBitmap = Bitmap.createBitmap(iv_canvas.getWidth(),
                iv_canvas.getHeight(), Bitmap.Config.ARGB_8888);
        canvas = new Canvas(baseBitmap);
        canvas.drawColor(Color.WHITE);
        canvasCenterX = iv_canvas.getWidth() / 2;
    }

    public void drawRedBg() {
        Path path = new Path();
        mPaint.setStrokeWidth(5);
        mPaint.setColor(0xffed1941);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        RectF rectF = new RectF();
        rectF.left = canvasCenterX - 150;
        rectF.top = 300;
        rectF.right = canvasCenterX + 150;
        rectF.bottom = 300 + 150;
        path.arcTo(rectF, 200, 140);
//        path.addArc(rectF, 200, 140);
//        path.lineTo(500, 500);
        canvas.drawPath(path, mPaint);
        rectF.left = canvasCenterX - 170;
        rectF.top = 300 + 50;
        rectF.right = canvasCenterX - 100;
        rectF.bottom = 300 + 350;
        path.arcTo(rectF, 90, 180);
        canvas.drawPath(path, mPaint);
        rectF.left = canvasCenterX + 100;
        rectF.top = 300 + 50;
        rectF.right = canvasCenterX + 170;
        rectF.bottom = 300 + 350;
        path.arcTo(rectF, 270, 180);
        canvas.drawPath(path, mPaint);


        path.lineTo(canvasCenterX + 60, 300 + 420);
        path.lineTo(canvasCenterX - 60, 300 + 420);
        path.lineTo(canvasCenterX - 140, 300 + 350);
//        path.lineTo(canvasCenterX+140, 300+350);
        canvas.drawPath(path, mPaint);
        path.reset();
    }

    public void drawOutline() {
        Path path = new Path();
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        RectF rectF = new RectF();
        rectF.left = canvasCenterX - 150;
        rectF.top = 300;
        rectF.right = canvasCenterX + 150;
        rectF.bottom = 300 + 150;
        path.arcTo(rectF, 200, 140);
//        path.addArc(rectF, 200, 140);
        canvas.drawPath(path, mPaint);
        rectF.left = canvasCenterX - 170;
        rectF.top = 300 + 50;
        rectF.right = canvasCenterX - 100;
        rectF.bottom = 300 + 350;
        path.addArc(rectF, 90, 180);
        canvas.drawPath(path, mPaint);
        rectF.left = canvasCenterX + 100;
        rectF.top = 300 + 50;
        rectF.right = canvasCenterX + 170;
        rectF.bottom = 300 + 350;
        path.addArc(rectF, 270, 180);
        canvas.drawPath(path, mPaint);
        path.moveTo(canvasCenterX - 140, 300 + 350);
        path.lineTo(canvasCenterX - 60, 300 + 420);
        path.lineTo(canvasCenterX + 60, 300 + 420);
        path.lineTo(canvasCenterX + 140, 300 + 350);
        canvas.drawPath(path, mPaint);
    }

    public void drawInnerOutline() {
        Path path = new Path();
        mPaint.setStrokeWidth(10);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        path.moveTo(canvasCenterX - 60, 300 + 420);
        path.lineTo(canvasCenterX - 75, 300 + 345);
        path.lineTo(canvasCenterX - 120, 300 + 320);
        RectF rectF = new RectF();
        rectF.left = canvasCenterX - 140;
        rectF.top = 300 + 80;
        rectF.right = canvasCenterX - 100;
        rectF.bottom = 300 + 320;
        path.addArc(rectF, 90, 180);
        path.lineTo(canvasCenterX - 60, 300 + 60);
        path.lineTo(canvasCenterX - 20, 300 + 130);
        path.lineTo(canvasCenterX + 20, 300 + 130);
        path.lineTo(canvasCenterX + 60, 300 + 60);
        path.lineTo(canvasCenterX + 120, 300 + 80);
        rectF.left = canvasCenterX + 100;
        rectF.top = 300 + 80;
        rectF.right = canvasCenterX + 140;
        rectF.bottom = 300 + 320;
        path.addArc(rectF, 270, 180);
        path.lineTo(canvasCenterX + 75, 300 + 345);
        path.lineTo(canvasCenterX + 60, 300 + 420);
        mPaint.setStrokeWidth(3);
        path.lineTo(canvasCenterX - 60, 300 + 420);
        canvas.drawPath(path, mPaint);
    }

    public void drawInnerBg() {
        Path path = new Path();
        mPaint.setStrokeWidth(10);
        mPaint.setColor(0Xfff5eda4);
        mPaint.setStyle(Paint.Style.FILL);
        path.moveTo(canvasCenterX - 60, 300 + 420);
        path.lineTo(canvasCenterX - 75, 300 + 345);
        path.lineTo(canvasCenterX - 120, 300 + 320);
        RectF rectF = new RectF();
        rectF.left = canvasCenterX - 140;
        rectF.top = 300 + 80;
        rectF.right = canvasCenterX - 100;
        rectF.bottom = 300 + 320;
        path.arcTo(rectF, 90, 180);
        path.lineTo(canvasCenterX - 60, 300 + 60);
        path.lineTo(canvasCenterX - 20, 300 + 130);
        path.lineTo(canvasCenterX + 20, 300 + 130);
        path.lineTo(canvasCenterX + 60, 300 + 60);
        path.lineTo(canvasCenterX + 120, 300 + 80);
        rectF.left = canvasCenterX + 100;
        rectF.top = 300 + 80;
        rectF.right = canvasCenterX + 140;
        rectF.bottom = 300 + 320;
        path.arcTo(rectF, 270, 180);
        path.lineTo(canvasCenterX + 75, 300 + 345);
        path.lineTo(canvasCenterX + 60, 300 + 420);
        path.lineTo(canvasCenterX - 60, 300 + 420);
        canvas.drawPath(path, mPaint);
    }

    public void drawEye() {
        mPaint.setStrokeWidth(5);
        //左眼眼白
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(0Xffd8e7f0);
        Path path = new Path();
        path.moveTo(canvasCenterX - 120, 300 + 190);
        path.lineTo(canvasCenterX - 40, 300 + 210);
        path.lineTo(canvasCenterX - 50, 300 + 225);
        RectF rectF = new RectF();
        rectF.left = canvasCenterX - 135;
        rectF.top = 300 + 140;
        rectF.right = canvasCenterX + 10;
        rectF.bottom = 300 + 225;
        path.arcTo(rectF, 90, 60);
        path.lineTo(canvasCenterX - 120, 300 + 190);
        canvas.drawPath(path, mPaint);
        //左眼轮廓
        path.reset();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        path.moveTo(canvasCenterX - 120, 300 + 190);
        path.lineTo(canvasCenterX - 40, 300 + 210);
        path.lineTo(canvasCenterX - 50, 300 + 225);
        rectF.left = canvasCenterX - 135;
        rectF.top = 300 + 140;
        rectF.right = canvasCenterX + 10;
        rectF.bottom = 300 + 225;
        path.arcTo(rectF, 90, 60);
        path.lineTo(canvasCenterX - 120, 300 + 190);
        canvas.drawPath(path, mPaint);

        //右眼眼白
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(0Xffd8e7f0);
        path.reset();
        path.moveTo(canvasCenterX + 120, 300 + 190);
        path.lineTo(canvasCenterX + 40, 300 + 210);
        path.lineTo(canvasCenterX + 50, 300 + 225);
        rectF.left = canvasCenterX - 10;
        rectF.top = 300 + 140;
        rectF.right = canvasCenterX + 135;
        rectF.bottom = 300 + 225;
        path.arcTo(rectF, 90, -60);
        path.lineTo(canvasCenterX + 120, 300 + 190);
        canvas.drawPath(path, mPaint);
        //右眼轮廓
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        path.reset();
        path.moveTo(canvasCenterX + 120, 300 + 190);
        path.lineTo(canvasCenterX + 40, 300 + 210);
        path.lineTo(canvasCenterX + 50, 300 + 225);
        rectF.left = canvasCenterX - 10;
        rectF.top = 300 + 140;
        rectF.right = canvasCenterX + 135;
        rectF.bottom = 300 + 225;
        path.arcTo(rectF, 90, -60);
        path.lineTo(canvasCenterX + 120, 300 + 190);
        canvas.drawPath(path, mPaint);

        path.reset();
        path.moveTo(canvasCenterX - 42, 300 + 212);
        path.lineTo(canvasCenterX, 300 + 215);
        path.lineTo(canvasCenterX + 42, 300 + 212);
        canvas.drawPath(path, mPaint);
    }

    public void drawMouth() {
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        Path path = new Path();
        path.moveTo(canvasCenterX - 72, 300 + 355);
        path.lineTo(canvasCenterX - 55, 300 + 365);
        path.lineTo(canvasCenterX - 40, 300 + 350);
        path.lineTo(canvasCenterX, 300 + 348);
        path.lineTo(canvasCenterX + 40, 300 + 350);
        path.lineTo(canvasCenterX + 55, 300 + 370);
        path.lineTo(canvasCenterX + 72, 300 + 355);
        canvas.drawPath(path, mPaint);
        path.reset();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(0xffed1941);
        path.moveTo(canvasCenterX - 55, 300 + 420);
        path.lineTo(canvasCenterX - 40, 300 + 390);
        path.lineTo(canvasCenterX + 40, 300 + 390);
        path.lineTo(canvasCenterX + 55, 300 + 420);
        path.lineTo(canvasCenterX - 55, 300 + 420);
        canvas.drawPath(path, mPaint);
        path.reset();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        path.moveTo(canvasCenterX - 55, 300 + 420);
        path.lineTo(canvasCenterX - 40, 300 + 390);
        path.lineTo(canvasCenterX + 40, 300 + 390);
        path.lineTo(canvasCenterX + 55, 300 + 420);
        path.lineTo(canvasCenterX - 55, 300 + 420);
        canvas.drawPath(path, mPaint);
    }

    public void drawHeart() {
        Path path = new Path();
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(0Xffdbfbff);

        path.moveTo(canvasCenterX - 80, 900);
        path.lineTo(canvasCenterX - 45, 875);
        path.lineTo(canvasCenterX - 30, 885);
        path.lineTo(canvasCenterX + 30, 885);
        path.lineTo(canvasCenterX + 45, 875);
        path.lineTo(canvasCenterX + 80, 900);
        path.lineTo(canvasCenterX + 25, 1010);
        path.lineTo(canvasCenterX - 25, 1010);
        path.lineTo(canvasCenterX - 80, 900);
        canvas.drawPath(path, mPaint);

        mPaint.setColor(0X99ffffff);

        path.moveTo(canvasCenterX - 80, 900);
        path.lineTo(canvasCenterX - 45, 875);
        path.lineTo(canvasCenterX - 30, 885);
        path.lineTo(canvasCenterX + 30, 885);
        path.lineTo(canvasCenterX + 45, 875);
        path.lineTo(canvasCenterX + 80, 900);
        path.lineTo(canvasCenterX + 25, 1010);
        path.lineTo(canvasCenterX - 25, 1010);
        path.lineTo(canvasCenterX - 80, 900);
        canvas.drawPath(path, mPaint);

        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(0Xffd0d4d9);
        path.moveTo(canvasCenterX - 80, 900);
        path.lineTo(canvasCenterX - 45, 875);
        path.lineTo(canvasCenterX - 30, 885);
        path.lineTo(canvasCenterX + 30, 885);
        path.lineTo(canvasCenterX + 45, 875);
        path.lineTo(canvasCenterX + 80, 900);
        path.lineTo(canvasCenterX + 25, 1010);
        path.lineTo(canvasCenterX - 25, 1010);
        path.lineTo(canvasCenterX - 80, 900);
        canvas.drawPath(path, mPaint);
    }

    public void drawHand() {
        Path path = new Path();
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        int handCenterX = 170;
        path.moveTo(handCenterX - 70, 600);
        path.lineTo(240, 600);
        RectF rectF = new RectF();
        rectF.left = handCenterX - 72;
        rectF.top = 600 - 30;
        rectF.right = handCenterX - 45;
        rectF.bottom = 600 + 30;
        path.arcTo(rectF, 180, 180);

        rectF.left = handCenterX - 40;
        rectF.top = 600 - 80;
        rectF.right = handCenterX - 17;
        rectF.bottom = 600 - 40;
        path.arcTo(rectF, 180, 180);
        path.lineTo(handCenterX - 14, 600);

        rectF.left = handCenterX - 12;
        rectF.top = 600 - 90;
        rectF.right = handCenterX + 12;
        rectF.bottom = 600 - 40;
        path.arcTo(rectF, 180, 180);
        path.lineTo(handCenterX + 14, 600);

        rectF.left = handCenterX + 16;
        rectF.top = 600 - 85;
        rectF.right = handCenterX + 40;
        rectF.bottom = 600 - 40;
        path.arcTo(rectF, 180, 180);
        path.lineTo(handCenterX + 42, 600);

        rectF.left = handCenterX + 45;
        rectF.top = 600 - 75;
        rectF.right = handCenterX + 68;
        rectF.bottom = 600 - 40;
        path.arcTo(rectF, 180, 180);
        path.lineTo(handCenterX + 70, 600);
        canvas.drawPath(path, mPaint);

        path.reset();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(0xffed1941);
        path.moveTo(handCenterX - 70, 600);
        rectF.left = handCenterX - 70;
        rectF.top = 600 - 120;
        rectF.right = handCenterX + 10;
        rectF.bottom = 600 + 120;
        path.arcTo(rectF, 180, -90);
        path.lineTo(handCenterX + 30, 600 + 120);
        rectF.left = handCenterX - 10;
        rectF.top = 600 - 120;
        rectF.right = handCenterX + 70;
        rectF.bottom = 600 + 120;
        path.arcTo(rectF, 90, -90);
        canvas.drawPath(path, mPaint);

        path.reset();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        path.moveTo(handCenterX - 70, 600);
        rectF.left = handCenterX - 70;
        rectF.top = 600 - 120;
        rectF.right = handCenterX + 10;
        rectF.bottom = 600 + 120;
        path.arcTo(rectF, 180, -90);
        path.lineTo(handCenterX + 30, 600 + 120);
        rectF.left = handCenterX - 10;
        rectF.top = 600 - 120;
        rectF.right = handCenterX + 70;
        rectF.bottom = 600 + 120;
        path.arcTo(rectF, 90, -90);
        canvas.drawPath(path, mPaint);

        mPaint.setStrokeWidth(2);
        path.reset();
        path.moveTo(handCenterX - 42, 600);
        path.lineTo(handCenterX - 42, 640);
        path.moveTo(handCenterX - 14, 600);
        path.lineTo(handCenterX - 14, 640);
        path.moveTo(handCenterX + 14, 600);
        path.lineTo(handCenterX + 14, 640);
        path.moveTo(handCenterX + 42, 600);
        path.lineTo(handCenterX + 42, 640);
        canvas.drawPath(path, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(0Xfff05b72);
        rectF.left = handCenterX - 64;
        rectF.top = 610;
        rectF.right = handCenterX - 48;
        rectF.bottom = 600 + 35;
        canvas.drawOval(rectF, mPaint);

        mPaint.setColor(0Xfffaa755);
        rectF.left = handCenterX - 36;
        rectF.top = 610;
        rectF.right = handCenterX - 18;
        rectF.bottom = 600 + 35;
        canvas.drawOval(rectF, mPaint);

        mPaint.setColor(0Xff4e72b8);
        rectF.left = handCenterX - 8;
        rectF.top = 610;
        rectF.right = handCenterX + 8;
        rectF.bottom = 600 + 35;
        canvas.drawOval(rectF, mPaint);

        mPaint.setColor(0Xfff391a9);
        rectF.left = handCenterX + 20;
        rectF.top = 610;
        rectF.right = handCenterX + 36;
        rectF.bottom = 600 + 35;
        canvas.drawOval(rectF, mPaint);

        mPaint.setColor(0Xffffe600);
        rectF.left = handCenterX + 48;
        rectF.top = 610;
        rectF.right = handCenterX + 64;
        rectF.bottom = 600 + 35;
        canvas.drawOval(rectF, mPaint);

        mPaint.setColor(0Xffbed742);
        rectF.left = handCenterX - 12;
        rectF.top = 600 + 55;
        rectF.right = handCenterX + 12;
        rectF.bottom = 600 + 90;
        canvas.drawOval(rectF, mPaint);
    }

    public void drawText() {
        Path path = new Path();
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(50);
        RectF rectF = new RectF();
        rectF.left = canvasCenterX- 300;
        rectF.top = 150;
        rectF.right = canvasCenterX +300;
        rectF.bottom = 400;
        path.arcTo(rectF, 180, 180);
        String text = "I LOVE YOU THREE THOUSAND";
        canvas.drawTextOnPath(text, path, 10, 10, mPaint);

        path.reset();
        rectF.left = canvasCenterX- 280;
        rectF.top = 900;
        rectF.right = canvasCenterX +280;
        rectF.bottom = 1150;
        path.arcTo(rectF, 180, -180);
        text = "TONY STARK HAS A HEART";
        canvas.drawTextOnPath(text, path, 10, 10, mPaint);
//        canvas.drawPath(path, mPaint);
    }

    public void drawIronMan() {
        drawRedBg();
        drawOutline();
        drawInnerBg();
        drawInnerOutline();
        drawEye();
        drawMouth();
        drawHeart();
        drawHand();
        drawText();
    }
}
