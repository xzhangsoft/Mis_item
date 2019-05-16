package com.example.canvas;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mis_item.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class BallActivity extends AppCompatActivity implements View.OnTouchListener {
    private ImageView iv_canvas;
    private Bitmap baseBitmap;
    private Canvas canvas;
    private Paint mPaint;
    float vpx;//圆心横坐标
    float vpy;//圆心纵坐标
    int Radius = 350;
    int layerBallNUm = 360 / 20;
    int layerIntervalUp = 360 / 20;
    double angleX = Math.PI / 100;
    double angleY = Math.PI / 100;
    boolean isrunning = false;
    ArrayList<Ball> balls = new ArrayList<Ball>();
    private Timer timer;
    private TimerTask task;
    private Button btn_start;
    private Button btn_stop;
    Animation anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ball);
        bindViews();
        mPaint = new Paint();
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    public void onWindowFocusChanged(boolean focus) {
        super.onWindowFocusChanged(focus);
        // get the imageviews width and height here

        if (baseBitmap == null) {
            createNewCanvas();
        }
        anim = new Animation();
        anim.init();
//        anim.start();
    }

    public void createNewCanvas() {
        baseBitmap = Bitmap.createBitmap(iv_canvas.getWidth(),
                iv_canvas.getHeight(), Bitmap.Config.ARGB_8888);
        vpx = iv_canvas.getWidth() / 2;
        vpy = iv_canvas.getHeight() / 2;
        canvas = new Canvas(baseBitmap);
        canvas.drawColor(Color.WHITE);
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                switch (v.getId()) {
                    case R.id.btn_start:
                        anim.start();
                        break;
                    case R.id.btn_stop:
                        anim.stop();
                        break;
                    case R.id.iv_ball:
                        double x = event.getX() - vpx - v.getLeft();
                        double y = event.getY() - vpy- v.getTop();
                        angleX = -y*0.0001;
                        angleY = -x*0.0001;
                        break;
                }
                break;
        }

        return true;
    }

    private void bindViews() {
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_stop = (Button) findViewById(R.id.btn_stop);
        iv_canvas = (ImageView) findViewById(R.id.iv_ball);

        btn_start.setOnTouchListener(this);
        btn_stop.setOnTouchListener(this);
        iv_canvas.setOnTouchListener(this);
    }

    class Ball {
        public double x;
        public double y;
        public double z;
        public double r;

        public Ball(double x, double y, double z, int r) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.r = r;
        }

        public void paint() {
            float fl = 420;
            canvas.save();
            double scale = fl / (fl - this.z);
            double alpha = (this.z + Radius) / (2 * Radius);
            mPaint.setStyle(Paint.Style.FILL);
            int c1 = (int) (Math.random() * 255);
            int c2 = (int) (Math.random() * 255);
            int c3 = (int) (Math.random() * 255);
            mPaint.setColor(Color.argb((float) (255 * alpha), c1, c2, c3));
            canvas.drawCircle((float) (vpx + this.x), (float) (vpy + this.y), (float) (this.r * scale), mPaint);
            canvas.restore();
        }
    }

    class Layer {
        public double radius;
        public float x = 0;
        public float y = 0;
        public float up;

        public Layer(int num, int up) {
            this.radius = Math.sqrt(Math.pow(Radius, 2) - Math.pow(Radius * Math.cos(num * Math.PI * 2 / layerBallNUm), 2));
            this.up = up;
        }

        public void setBalls() {
            for (int i = 0; i < layerBallNUm; i++) {
                double thisAngle = 2 * Math.PI / layerBallNUm * i;
                Ball b = new Ball(this.radius * Math.cos(thisAngle), this.radius * Math.sin(thisAngle),
                        this.up * Math.sqrt(Math.pow(Radius, 2) - Math.pow(this.radius, 2)), 3);
                b.paint();
                balls.add(b);
            }
        }

        public void draw() {
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(Color.WHITE);
            this.setBalls();
//            canvas.drawCircle((float) vpx , (float) vpy , (float)(this.radius), mPaint);
        }
    }

    public void rotateX() {
        double cos = Math.cos(angleX);
        double sin = Math.sin(angleX);
        for (int i = 0; i < balls.size(); i++) {
            double y1 = balls.get(i).y * cos - balls.get(i).z * sin;
            double z1 = balls.get(i).z * cos + balls.get(i).y * sin;
            balls.get(i).y = y1;
            balls.get(i).z = z1;
        }
    }

    public void animate() {
        canvas.drawColor(Color.WHITE);
        rotateX();
        rotateY();
        rotateZ();
        for (int i = 0; i < balls.size(); i++) {
            balls.get(i).paint();
        }
        iv_canvas.setImageBitmap(baseBitmap);
    }

    public void rotateY() {
        double cos = Math.cos(angleY);
        double sin = Math.sin(angleY);
        for (int i = 0; i < balls.size(); i++) {
            double x1 = balls.get(i).x * cos - balls.get(i).z * sin;
            double z1 = balls.get(i).z * cos + balls.get(i).x * sin;
            balls.get(i).x = x1;
            balls.get(i).z = z1;
        }
    }

    public void rotateZ() {
        double cos = Math.cos(angleY);
        double sin = Math.sin(angleY);
        for (int i = 0; i < balls.size(); i++) {
            double x1 = balls.get(i).x * cos - balls.get(i).y * sin;
            double y1 = balls.get(i).y * cos + balls.get(i).x * sin;
            balls.get(i).x = x1;
            balls.get(i).y = y1;
        }
    }

    public void runAnimation() {
        if (isrunning) {
            timer = new Timer();
            task = new TimerTask() {
                @Override
                public void run() {
                    try {
                        animate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            timer.schedule(task, 0, 100);
        } else {
            return;
        }
    }

    public void stopAnimation() {
        if (!isrunning) {
            timer.cancel();
            task.cancel();
        } else {
            return;
        }
    }

    class Animation {
        public void init() {
            double num = layerIntervalUp / 2;
            for (int i = 0; i <= num; i++) {
                Layer layerUp = new Layer(i, 1);
                layerUp.draw();
                Layer layerDown = new Layer(i, -1);
                layerDown.draw();
            }
            iv_canvas.setImageBitmap(baseBitmap);
        }

        public void start() {
            isrunning = true;
            runAnimation();
        }

        public void stop() {
            isrunning = false;
            stopAnimation();
        }
    }
}


