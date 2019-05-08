package com.example.pic_glide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.mis_item.R;

import java.io.File;

public class pictureActivity extends AppCompatActivity {
    private static final String TAG = "pictureActivity";

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        imageView = findViewById(R.id.mimi);

        glideSample();
//        getFilePic();
//        getGit();
   }

   private void custmoPic() {

   }

   private void getComflexPic() {
//        Glide.with(this)
//                .load(R.mipmap.mimi)
//                .apply();
   }

    private void getFilePic() {
        File file = new File(getExternalCacheDir() + "/mimi.jpg");
        Log.d(TAG, file.toString());
        Glide.with(this).load(file).into(imageView);

    }

    private void getGit() {
        String url2 = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3396140532,1228025744&fm=23&gp=0.jpg";

        Glide.with(this)
                .load(url2)
                .asGif()//只允许加载动态图
                .placeholder(R.mipmap.ic_launcher)//加载占位图
                .error(R.mipmap.ic_launcher)//异常占位图
                .into(imageView);
    }



    private void glideSample() {
        String url = "https://www.baidu.com/img/bd_logo1.png";
        String gitUrl = "http://p1.pstatp.com/large/166200019850062839d3";
        String gitUrl1 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1557316928275&di=457da777f376d151d45e2945bfcc7395&imgtype=0&src=http%3A%2F%2Fhiphotos.baidu.com%2Fdoc%2Fpic%2Fitem%2F9825bc315c6034a86deaed63c2134954082376c3.jpg";

        Glide.with(this)
                .load(gitUrl1)
                .asBitmap()
                .placeholder(R.mipmap.placeholder)
                .error(R.mipmap.error)
                .diskCacheStrategy(DiskCacheStrategy.NONE)

//                .crossFade(3000)
//                .dontAnimate()
                //对原始图片的中心区域进行裁剪后得到的图片
//                .centerCrop()
                //会将图片按照原始的长宽充满全屏
//                .fitCenter()
//                .transform(new )
//                .bitmapTransform(new BlurTransformation())
//                .thumbnail(0.1f)
                .override(400,400)
                //遵循图片本身大小
//                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
//                .animate(R.anim.item_alpha_in)
                .into(imageView);

    }




}
