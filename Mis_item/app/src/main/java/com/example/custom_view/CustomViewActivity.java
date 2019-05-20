package com.example.custom_view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.canvas.BallActivity;
import com.example.canvas.Canvas2Activity;
import com.example.canvas.CanvasActivity;
import com.example.canvas.CanvasIndexActivity;
import com.example.canvas.YellowManActivity;
import com.example.mis_item.R;

public class CustomViewActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_circle;
    private Button btn_list;
    private Button btn_yellow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);

        bindViews();
    }

    private void bindViews() {
        btn_circle = (Button) findViewById(R.id.btn_circle);
        btn_list = (Button) findViewById(R.id.btn_list);
        btn_yellow = (Button) findViewById(R.id.btn_yellow);

        btn_circle.setOnClickListener(this);
        btn_list.setOnClickListener(this);
        btn_yellow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_circle:
                goActivity(BasicActivity.class);
                break;
            case R.id.btn_list:
                goActivity(ListActivity.class);
                break;
            case R.id.btn_yellow:
                goActivity(CanvasActivity.class);
                break;
        }
    }

    public void goActivity(Class clazz) {
        Intent intent = new Intent(CustomViewActivity.this, clazz);
        startActivity(intent);
    }
}
