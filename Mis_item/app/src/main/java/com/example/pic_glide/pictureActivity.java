package com.example.pic_glide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.mis_item.R;
public class pictureActivity extends AppCompatActivity {

    private String url = "https://www.baidu.com/img/bd_logo1.png";

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        glideSample();
    }



    private void glideSample() {
        imageView = findViewById(R.id.mimi);

        Glide.with(this)
                .load("")
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .crossFade(3000)
                .dontAnimate()
//                .override(800,100)
                .animate(R.anim.item_alpha_in)
                .into(imageView);

    }




}
