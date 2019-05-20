package com.example.canvas;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mis_item.R;

import java.io.File;
import java.io.FileOutputStream;

public class DrawActivity extends AppCompatActivity {
    private Button btn_save, btn_resume, btn_stroke, btn_black, btn_red, btn_orange, btn_yellow, btn_green, btn_cyan, btn_blue, btn_purple;
    private EditText stroke_input;
    private ImageView iv_canvas;
    private Bitmap baseBitmap;
    private Canvas canvas;
    private Paint paint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);

        // 初始化一个画笔，笔触宽度为5，颜色为红色
        paint = new Paint();
        paint.setStrokeWidth(5);
        paint.setColor(Color.BLACK);

        iv_canvas = (ImageView) findViewById(R.id.iv_canvas);
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_resume = (Button) findViewById(R.id.btn_resume);

        btn_stroke = (Button) findViewById(R.id.btn_stroke);

        btn_red = (Button) findViewById(R.id.btn_red);
        btn_orange = (Button) findViewById(R.id.btn_orange);
        btn_yellow = (Button) findViewById(R.id.btn_yellow);
        btn_green = (Button) findViewById(R.id.btn_green);
        btn_cyan = (Button) findViewById(R.id.btn_cyan);
        btn_blue = (Button) findViewById(R.id.btn_blue);
        btn_purple = (Button) findViewById(R.id.btn_purple);
        btn_black = (Button) findViewById(R.id.btn_black);
        stroke_input = (EditText) findViewById(R.id.stroke_input);

        btn_save.setOnClickListener(click);
        btn_resume.setOnClickListener(click);
        btn_stroke.setOnClickListener(click);
        iv_canvas.setOnTouchListener(touch);

        btn_red.setOnClickListener(click);
        btn_orange.setOnClickListener(click);
        btn_yellow.setOnClickListener(click);
        btn_green.setOnClickListener(click);
        btn_cyan.setOnClickListener(click);
        btn_blue.setOnClickListener(click);
        btn_purple.setOnClickListener(click);
        btn_black.setOnClickListener(click);
    }

    private View.OnTouchListener touch = new View.OnTouchListener() {
        // 定义手指开始触摸的坐标
        float startX;
        float startY;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                // 用户按下动作
                case MotionEvent.ACTION_DOWN:
                    // 第一次绘图初始化内存图片，指定背景为白色
                    if (baseBitmap == null) {
                        baseBitmap = Bitmap.createBitmap(iv_canvas.getWidth(),
                                iv_canvas.getHeight(), Bitmap.Config.ARGB_8888);
                        canvas = new Canvas(baseBitmap);
                        canvas.drawColor(Color.WHITE);
                    }
                    // 记录开始触摸的点的坐标
                    startX = event.getX();
                    startY = event.getY();
                    break;
                // 用户手指在屏幕上移动的动作
                case MotionEvent.ACTION_MOVE:
                    // 记录移动位置的点的坐标
                    float stopX = event.getX();
                    float stopY = event.getY();

                    //根据两点坐标，绘制连线
                    canvas.drawLine(startX, startY, stopX, stopY, paint);

                    // 更新开始点的位置
                    startX = event.getX();
                    startY = event.getY();

                    // 把图片展示到ImageView中
                    iv_canvas.setImageBitmap(baseBitmap);
                    break;
                case MotionEvent.ACTION_UP:

                    break;
                default:
                    break;
            }
            return true;
        }
    };
    private View.OnClickListener click = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.btn_save:
                    saveBitmap();
                    break;
                case R.id.btn_resume:
                    resumeCanvas();
                    break;
                case R.id.btn_stroke:
                    String a = stroke_input.getText().toString();
                    paint.setStrokeWidth(Integer.parseInt(stroke_input.getText().toString()));
                    break;
                case R.id.btn_red:
                    paint.setColor(Color.RED);
                    break;
                case R.id.btn_orange:
                    paint.setColor(0Xfffaa755);
                    break;
                case R.id.btn_yellow:
                    paint.setColor(Color.YELLOW);
                    break;
                case R.id.btn_green:
                    paint.setColor(Color.GREEN);
                    break;
                case R.id.btn_cyan:
                    paint.setColor(Color.CYAN);
                    break;
                case R.id.btn_blue:
                    paint.setColor(Color.BLUE);
                    break;
                case R.id.btn_purple:
                    paint.setColor(Color.MAGENTA);
                    break;
                case R.id.btn_black:
                    paint.setColor(Color.BLACK);
                    break;

                default:
                    break;
            }
        }
    };

    /**
     * 保存图片到SD卡上
     */
    protected void saveBitmap() {
        try {
            // 保存图片到SD卡上
            File file = new File(Environment.getExternalStorageDirectory(),
                    System.currentTimeMillis() + ".png");
            FileOutputStream stream = new FileOutputStream(file);
            baseBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            Toast.makeText(DrawActivity.this, "保存图片成功", Toast.LENGTH_SHORT).show();

            // Android设备Gallery应用只会在启动的时候扫描系统文件夹
            // 这里模拟一个媒体装载的广播，用于使保存的图片可以在Gallery中查看
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_MEDIA_MOUNTED);
            intent.setData(Uri.fromFile(Environment
                    .getExternalStorageDirectory()));
            sendBroadcast(intent);
        } catch (Exception e) {
            Toast.makeText(DrawActivity.this, "保存图片失败", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    /**
     * 清除画板
     */
    protected void resumeCanvas() {
        // 手动清除画板的绘图，重新创建一个画板
        if (baseBitmap != null) {
            baseBitmap = Bitmap.createBitmap(iv_canvas.getWidth(),
                    iv_canvas.getHeight(), Bitmap.Config.ARGB_8888);
            canvas = new Canvas(baseBitmap);
            canvas.drawColor(Color.WHITE);
            iv_canvas.setImageBitmap(baseBitmap);
            Toast.makeText(DrawActivity.this, "清除画板成功，可以重新开始绘图", Toast.LENGTH_SHORT).show();
        }
    }
}
