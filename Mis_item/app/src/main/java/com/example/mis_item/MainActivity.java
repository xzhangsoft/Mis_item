package com.example.mis_item;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.anim.AnimationMainActivity;
import com.example.pic_glide.pictureActivity;
import com.example.webview.LoginActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_pic = (Button) findViewById(R.id.pic_glide);
        Button btn_anmi = (Button) findViewById(R.id.animation);
        Button btn_webview = (Button) findViewById(R.id.webview);

        btn_pic.setOnClickListener(this);
        btn_anmi.setOnClickListener(this);
        btn_webview.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pic_glide:
                Intent intent_pic = new Intent(MainActivity.this, pictureActivity.class);
                startActivity(intent_pic);
                break;
            case R.id.animation:
                Intent intent_anmi = new Intent(MainActivity.this, AnimationMainActivity.class);
                startActivity(intent_anmi);
                break;
            case R.id.webview:
                Intent intent_webview = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent_webview);
                break;

        }
    }
}
