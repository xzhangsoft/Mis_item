package com.example.canvas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mis_item.MainActivity;
import com.example.mis_item.R;
import com.example.pic_glide.pictureActivity;

public class CanvasIndexActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_basic;
    private Button btn_ball;
    private Button btn_drawYellow;
    private Button btn_yellow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_index);

        bindViews();
    }

    private void bindViews() {
        btn_basic = (Button) findViewById(R.id.btn_basic);
        btn_ball = (Button) findViewById(R.id.btn_ball);
        btn_drawYellow = (Button) findViewById(R.id.btn_drawYellow);
        btn_yellow = (Button) findViewById(R.id.btn_yellow);

        btn_basic.setOnClickListener(this);
        btn_ball.setOnClickListener(this);
        btn_drawYellow.setOnClickListener(this);
        btn_yellow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_basic:
                goActivity(Canvas2Activity.class);
                break;
            case R.id.btn_ball:
                goActivity(BallActivity.class);
                break;
            case R.id.btn_drawYellow:
                goActivity(YellowManActivity.class);
                break;
            case R.id.btn_yellow:
                goActivity(CanvasActivity.class);
                break;
        }
    }

    public void goActivity(Class clazz) {
        Intent intent = new Intent(CanvasIndexActivity.this, clazz);
        startActivity(intent);
    }
}
