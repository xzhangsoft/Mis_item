package com.example.anim;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.mis_item.R;

public class AnimationMainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "AnimationMainActivity";
    Button anim_tween, anim_drawable, anim_value, anim_object,anim_layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_main);

        Log.v(TAG, "AnimationMainActivity");

        anim_tween = findViewById(R.id.anim_tween);
        anim_drawable = findViewById(R.id.anim_drawable);
        anim_value = findViewById(R.id.anim_value_anim);
        anim_object = findViewById(R.id.anim_object_anim);

        anim_layout = findViewById(R.id.anim_layout);

        anim_tween.setOnClickListener(this);
        anim_drawable.setOnClickListener(this);
        anim_value.setOnClickListener(this);
        anim_object.setOnClickListener(this);
        anim_layout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.anim_tween:
                Intent intent_pic = new Intent(AnimationMainActivity.this, TweenActivity.class);
                startActivity(intent_pic);
                break;
            case R.id.anim_drawable:
                startActivity(new Intent(AnimationMainActivity.this, DrawableActivity.class));
                break;

            case R.id.anim_value_anim:
                startActivity(new Intent(AnimationMainActivity.this, ValueMainActivity.class));
                break;

            case R.id.anim_object_anim:
                startActivity(new Intent(AnimationMainActivity.this, ObjectAnimatorActivity.class));
                break;

            case R.id.anim_layout:
                startActivity(new Intent(AnimationMainActivity.this, LayoutAnimActivity.class));
                break;
        }

    }
}
