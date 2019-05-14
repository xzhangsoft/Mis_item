package com.example.anim;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mis_item.R;

public class DrawableActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_start, btn_stop;
    ImageView img_show;
    private AnimationDrawable anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable);
        bindViews();
        anim = (AnimationDrawable) img_show.getBackground();
    }


    private void bindViews() {
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_stop = (Button) findViewById(R.id.btn_stop);
        img_show = (ImageView) findViewById(R.id.img_show);
        btn_start.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                anim.start();
                break;
            case R.id.btn_stop:
                anim.stop();
                break;
        }
    }

//    protected void start() {
//        if (anim != null && !anim.isRunning()) {
//            anim.start();
//            Toast.makeText(DrawableActivity.this, "开始播放", Toast.LENGTH_SHORT).show();
//            Log.v("DrawableActivity", "index 为5的帧持续时间为：" + anim.getDuration(5) + "毫秒");
//            Log.v("DrawableActivity", "当前AnimationDrawable一共有" + anim.getNumberOfFrames() + "帧");
//        }
//    }
}
