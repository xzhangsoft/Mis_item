package com.example.canvas;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mis_item.R;

import java.io.IOException;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class Canvas2Activity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv_canvas;
    private Bitmap baseBitmap;
    private Canvas canvas;
    private Paint paint;
    Calendar now;
    private Button btn_line;
    private Button btn_rect;
    private Button btn_circle;
    private Button btn_oval;
    private Button btn_rRect;
    private Button btn_path;
    private Button btn_textPath;
    private Button btn_clock;
    private Button btn_paintStyle;
    private Button btn_pathTest;
    private Button btn_translate;
    private Button btn_rotate;
    private Button btn_scale;
    private Button btn_skew;
    private Button btn_clip;

    private Timer timer;
    private TimerTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas2);

        paint = new Paint();
        paint.setStrokeWidth(5);
        paint.setColor(Color.RED);

        bindViews();
    }

    @Override
    public void onWindowFocusChanged(boolean focus) {
        super.onWindowFocusChanged(focus);
        // get the imageviews width and height here
        if (baseBitmap == null) {
            createNewCanvas();
        }
    }

    public void createNewCanvas() {
        baseBitmap = Bitmap.createBitmap(iv_canvas.getWidth(),
                iv_canvas.getHeight(), Bitmap.Config.ARGB_8888);
        canvas = new Canvas(baseBitmap);
        canvas.drawColor(Color.GREEN);
        if (timer != null)
            timer.cancel();
        if (task != null)
            task.cancel();
    }

    private void bindViews() {
        iv_canvas = (ImageView) findViewById(R.id.iv_canvas);

        btn_line = (Button) findViewById(R.id.btn_line);
        btn_rect = (Button) findViewById(R.id.btn_rect);
        btn_circle = (Button) findViewById(R.id.btn_circle);
        btn_oval = (Button) findViewById(R.id.btn_oval);
        btn_rRect = (Button) findViewById(R.id.btn_rRect);
        btn_path = (Button) findViewById(R.id.btn_path);
        btn_textPath = (Button) findViewById(R.id.btn_textPath);
        btn_clock = (Button) findViewById(R.id.btn_clock);
        btn_paintStyle = (Button) findViewById(R.id.btn_paintStyle);
        btn_pathTest = (Button) findViewById(R.id.btn_pathTest);
        btn_translate = (Button) findViewById(R.id.btn_translate);
        btn_rotate = (Button) findViewById(R.id.btn_rotate);
        btn_scale = (Button) findViewById(R.id.btn_scale);
        btn_skew = (Button) findViewById(R.id.btn_skew);
        btn_clip = (Button) findViewById(R.id.btn_clip);

        btn_line.setOnClickListener(this);
        btn_rect.setOnClickListener(this);
        btn_circle.setOnClickListener(this);
        btn_oval.setOnClickListener(this);
        btn_rRect.setOnClickListener(this);
        btn_path.setOnClickListener(this);
        btn_textPath.setOnClickListener(this);
        btn_clock.setOnClickListener(this);
        btn_paintStyle.setOnClickListener(this);
        btn_pathTest.setOnClickListener(this);
        btn_translate.setOnClickListener(this);
        btn_rotate.setOnClickListener(this);
        btn_scale.setOnClickListener(this);
        btn_skew.setOnClickListener(this);
        btn_clip.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_line:
                canvasDrawLine();
                break;
            case R.id.btn_rect:
                canvasDrawRect();
                break;
            case R.id.btn_circle:
                canvasDrawCircle();
                break;
            case R.id.btn_oval:
                canvasDrawOval();
                break;
            case R.id.btn_rRect:
                canvasDrawRoundRect();
                break;
            case R.id.btn_path:
                canvasDrawPath();
                break;
            case R.id.btn_textPath:
                canvasDrawTextPath();
                break;
            case R.id.btn_clock:
                drawClock();
                break;
            case R.id.btn_paintStyle:
                switchPaint();
                break;
            case R.id.btn_pathTest:
                pathTest();
                break;
            case R.id.btn_translate:
                translateTest();
                break;
            case R.id.btn_rotate:
                rotate();
                break;
            case R.id.btn_scale:
                scale();
                break;
            case R.id.btn_skew:
                skew();
                break;
            case R.id.btn_clip:
                clip();
                break;
        }
    }

    public void clip() {
        if (baseBitmap != null) {
            createNewCanvas();
        }
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        canvas.drawColor(Color.BLUE);
        Path path = new Path();
        path.addCircle(120, 70, 50, Path.Direction.CCW);
        canvas.clipOutPath(path);
        canvas.drawColor(Color.GREEN);
        iv_canvas.setImageBitmap(baseBitmap);
    }

    public void pathTest() {
        if (baseBitmap != null) {
            createNewCanvas();
        }
        Path path = new Path();
        path.lineTo(100, 100);
        path.lineTo(800, 100);
        canvas.drawPath(path, paint);
        path.reset();//此方法可以将path的坐标重置到0，0， 同时之前的路径会消失，后面再执行drawpath，前面的路径不会执行
        path.lineTo(500, 800);
        path.lineTo(500, 600);
        canvas.drawPath(path, paint);
        iv_canvas.setImageBitmap(baseBitmap);
    }

    public void translateTest() {
        if (baseBitmap != null) {
            createNewCanvas();
        }
        canvas.drawCircle(100, 100, 90, paint);
        canvas.save();//保存当前canvas状态。
        canvas.translate(100,100);
        paint.setColor(Color.BLUE);
        canvas.drawCircle(100, 100, 90, paint);
        canvas.restore();//恢复之前保存的canvas状态。
        paint.setColor(Color.YELLOW);
        canvas.drawCircle(100, 100, 50, paint);

        iv_canvas.setImageBitmap(baseBitmap);
    }

    public void scale() {
        if (baseBitmap != null) {
            createNewCanvas();
        }
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        canvas.drawColor(Color.BLUE);
        canvas.save();
        canvas.scale(0.5f,0.5f);//x,y均缩小一半
        canvas.drawCircle(100,100,50,paint);
        canvas.restore();
        paint.setColor(Color.WHITE);
        canvas.drawCircle(100,100,50,paint);

        iv_canvas.setImageBitmap(baseBitmap);
    }

    public void rotate() {
        if (baseBitmap != null) {
            createNewCanvas();
        }
        paint.setAntiAlias(true);
        canvas.drawColor(Color.BLUE);
        paint.setColor(Color.WHITE);
        canvas.drawRect(new RectF(100,100,200,200),paint);
        canvas.save();
        canvas.rotate(45);
//        canvas.rotate(45,200,200);
        paint.setColor(Color.RED);
        canvas.drawRect(new RectF(100,100,200,200),paint);
        canvas.restore();

        iv_canvas.setImageBitmap(baseBitmap);
    }

    public void skew() {
        if (baseBitmap != null) {
            createNewCanvas();
        }
        paint.setAntiAlias(true);
        canvas.drawColor(Color.BLUE);
        paint.setColor(Color.WHITE);
        canvas.drawRect(new RectF(0,0,180,180),paint);
        canvas.save();
        canvas.skew(0,1);//sx 为倾斜角度的 tan 值；sy 为倾斜角度的 tan 值
        paint.setColor(Color.RED);
        canvas.drawRect(new RectF(0,0,180,180),paint);
        canvas.restore();
        iv_canvas.setImageBitmap(baseBitmap);
    }

    public void switchPaint() {
        if (paint.getStyle() == Paint.Style.FILL) {
            paint.setStyle(Paint.Style.STROKE);
        } else {
            paint.setStyle(Paint.Style.FILL);
        }
    }

    public void canvasDrawLine() {
        if (baseBitmap != null) {
            createNewCanvas();
        }
        canvas.drawLine(0, 100, 200, 100, paint);
        iv_canvas.setImageBitmap(baseBitmap);
    }

    public void canvasDrawRect() {
        if (baseBitmap != null) {
            createNewCanvas();
        }
        RectF rect = new RectF(50, 50, 200, 200);
        canvas.drawRect(rect, paint);
        iv_canvas.setImageBitmap(baseBitmap);
    }

    public void canvasDrawCircle() {
        if (baseBitmap != null) {
            createNewCanvas();
        }
        canvas.drawCircle(100, 100, 90, paint);
        iv_canvas.setImageBitmap(baseBitmap);
    }

    public void canvasDrawOval() {
        if (baseBitmap != null) {
            createNewCanvas();
        }
        RectF oval = new RectF(0, 0, 200, 300);
        canvas.drawOval(oval, paint);
        iv_canvas.setImageBitmap(baseBitmap);
    }

    //圆角矩形
    public void canvasDrawRoundRect() {
        if (baseBitmap != null) {
            createNewCanvas();
        }
        RectF rect = new RectF(50, 50, 200, 200);
        canvas.drawRoundRect(rect,
                30, //x轴的半径
                30, //y轴的半径
                paint);
        iv_canvas.setImageBitmap(baseBitmap);
    }

    //画路径
    public void canvasDrawPath() {
        if (baseBitmap != null) {
            createNewCanvas();
        }
        Path path = new Path(); //定义一条路径
        path.moveTo(10, 10); //移动到 坐标10,10
        path.lineTo(800, 600);
        path.lineTo(500, 800);
        path.lineTo(10, 10);
        canvas.drawPath(path, paint);
        iv_canvas.setImageBitmap(baseBitmap);
    }

    public void canvasDrawTextPath() {
        if (baseBitmap != null) {
            createNewCanvas();
        }
        Path path = new Path(); //定义一条路径
        path.moveTo(10, 10); //移动到 坐标10,10
        path.lineTo(10, 600);
        path.lineTo(600, 700);
        path.lineTo(600, 110);
        path.lineTo(50, 50);
        path.lineTo(50, 500);
        path.lineTo(500, 600);
        path.lineTo(500, 150);
// canvas.drawPath(path, paint);
        paint.setTextSize(50);
        String text = "RXJAVA DAGGER CORDOVA REACT-NATIVE ESLINT JASMINE OKHTTP RETROFIT KNIFE RXJAVA DAGGER CORDOVA REACT-NATIVE ESLINT JASMINE OKHTTP RETROFIT KNIFE " +
                "RXJAVA DAGGER CORDOVA REACT-NATIVE ESLINT JASMINE OKHTTP RETROFIT KNIFE RXJAVA DAGGER CORDOVA REACT-NATIVE ESLINT JASMINE OKHTTP RETROFIT KNIFE " +
                "RXJAVA DAGGER CORDOVA REACT-NATIVE ESLINT JASMINE OKHTTP RETROFIT KNIFE RXJAVA DAGGER CORDOVA REACT-NATIVE ESLINT JASMINE OKHTTP RETROFIT KNIFE " +
                "MASTER MAGGIE JAVASCRIPT WEBVIEW WEEX WEBPACK NODEJS REACT ANGULAR VUE TSLINT SCSS LESS HTML FLUTTER EXPRESS AXIOS";
        canvas.drawTextOnPath(text, path, 10, 10, paint);
        iv_canvas.setImageBitmap(baseBitmap);
    }

    public void drawClock() {
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                try {
                    canvasDrawClock();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        timer.schedule(task, 0, 1000);
    }

    public void canvasDrawClock() {
        if (baseBitmap != null) {
            baseBitmap = Bitmap.createBitmap(iv_canvas.getWidth(),
                    iv_canvas.getHeight(), Bitmap.Config.ARGB_8888);
            canvas = new Canvas(baseBitmap);
            canvas.drawColor(Color.GREEN);
        }
        paint.setAntiAlias(true); //抗锯齿
        paint.setStyle(Paint.Style.STROKE); //仅描边
        canvas.translate(canvas.getWidth() / 2, 600); //将位置移动画纸的坐标点:150,150
        canvas.drawCircle(0, 0, 300, paint); //画圆圈
        Paint tmpPaint = new Paint(paint); //小刻度画笔对象
        tmpPaint.setStrokeWidth(4);
        tmpPaint.setTextSize(50);
        float y = 280;
        int count = 60; //总刻度数
        for (int i = 0; i < count; i++) {
            if (i % 5 == 0) {
                canvas.drawLine(0f, y, 0, y + 16f, paint);
                canvas.drawText(i == 0 ? "12" : String.valueOf(i / 5), -25f, -y + 50f, tmpPaint);
            } else {
                canvas.drawLine(0f, y, 0f, y + 10f, tmpPaint);
            }
            canvas.rotate(360 / count, 0f, 0f); //旋转画纸
        }
//绘制指针
        tmpPaint.setColor(Color.GRAY);
        tmpPaint.setStrokeWidth(10);
        canvas.drawCircle(0, 0, 20, tmpPaint);
        tmpPaint.setStyle(Paint.Style.FILL);
        tmpPaint.setColor(Color.YELLOW);
        canvas.drawCircle(0, 0, 18, tmpPaint);
        double basicAngle = Math.PI * 2 / 60;
        now = Calendar.getInstance();
        int currentSecond = now.get(Calendar.SECOND);
        double currentMinute = now.get(Calendar.MINUTE);
        double hourx = now.get(Calendar.HOUR);
        double currentHour = (now.get(Calendar.HOUR_OF_DAY) + currentMinute / 60) * 5;
        //fenzhen
        paint.setStrokeWidth(10);
        canvas.drawLine(0, 0, (float) (200 * Math.sin(basicAngle * currentMinute)), -(float) (200 * Math.cos(basicAngle * currentMinute)), paint);
        //shizhen 3dian
        paint.setStrokeWidth(16);
        canvas.drawLine(0, 0, (float) (160 * Math.sin(basicAngle * currentHour)), -(float) (160 * Math.cos(basicAngle * currentHour)), paint);
        paint.setStrokeWidth(6);
        //miaozhen
        canvas.drawLine(0, 0, (float) (210 * Math.sin(basicAngle * currentSecond)), -(float) (210 * Math.cos(basicAngle * currentSecond)), paint);
        iv_canvas.setImageBitmap(baseBitmap);

    }
}
