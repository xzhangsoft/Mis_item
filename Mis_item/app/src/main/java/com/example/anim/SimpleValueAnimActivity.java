package com.example.anim;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mis_item.R;

import static android.animation.ValueAnimator.REVERSE;

public class SimpleValueAnimActivity extends AppCompatActivity {
    private static final String TAG="SimpleValueAnimActivity";

    private TextView tv;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_value_anim);
        valueAnimationInit();
    }

    private void valueAnimationInit() {
/*       在这里我们利用 ValueAnimator.ofInt 创建了一个值从 0 到 400 的动画，动画时长是 1s，然后让动画开始。
        从这段代码中可以看出，ValueAnimator 没有跟任何的控件相关联，那也正好说明 ValueAnimator 只是对值做动画运算，
        而不是针对控件的，我们需要监听 ValueAnimator 的动画过程来自己对控件做操作。
 */

//        ValueAnimator animator = ValueAnimator.ofInt(0,400);
//        animator.setDuration(1000);
//        animator.start();

        tv = (TextView) findViewById(R.id.tv);

        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doAnimation();
            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SimpleValueAnimActivity.this, "clicked me", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void doAnimation(){
//        ValueAnimator animator = ValueAnimator.ofInt(0,400,200,500,100,900);
        ValueAnimator animator = ValueAnimator.ofFloat(0f,400f,500f,300f);
        animator.setDuration(2000);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float curValueFloat = (Float)animation.getAnimatedValue();
                int curValue = curValueFloat.intValue();
//                int curValue = (int)animation.getAnimatedValue();
                tv.layout(curValue,curValue,curValue+tv.getWidth(),curValue+tv.getHeight());

            }
        });
        animator.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {
                Log.v(TAG,"onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.v(TAG,"onAnimationEnd");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Log.v(TAG,"onAnimationCancel");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.v(TAG,"onAnimationRepeat");
            }
        });
        animator.start();
        animator.setRepeatCount(2);
        animator.setRepeatMode(REVERSE);


    }
}
