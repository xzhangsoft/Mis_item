package com.example.pic_glide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.mis_item.R;

import jp.wasabeef.glide.transformations.gpu.VignetteFilterTransformation;


public class pictureActivity extends AppCompatActivity {
    private static final String TAG = "pictureActivity";


    ImageView imageView0, imageView1, imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        new GlideConfiguration();
        imageView0 = findViewById(R.id.pic0);
        imageView1 = findViewById(R.id.pic1);
        imageView2 = findViewById(R.id.pic2);
        loadDiffResPic();
    }

    private void loadDiffResPic() {
//        loadPicGlide();
//        loadPic1Glide();
//        loadGifGlide();

//        glideInnerProper();
        glideTransform();
    }

    private void glideTransform() {

        MultiTransformation multi = new MultiTransformation(
//                new BlurTransformation(25)
//                new RoundedCornersTransformation(128, 0, RoundedCornersTransformation.CornerType.ALL)
//                new SwirlFilterTransformation()
//                new ContrastFilterTransformation(11)
                new VignetteFilterTransformation()
        );

        RequestOptions options = new RequestOptions()
                .skipMemoryCache(true)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .bitmapTransform(multi);

        Glide.with(this)
                .load(R.mipmap.girl)
                .apply(options)
                .into(imageView1);
    }

    private void glideInnerProper() {

        RequestOptions options = new RequestOptions()
                .centerCrop()
//                .fitCenter()
//                .dontTransform()
//                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
//                .override(200,200)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(R.mipmap.placeholder)
                .error(R.mipmap.error);

        Glide.with(this)
                .load(R.mipmap.girl)
                .apply(options)
                .into(imageView0);

        Log.v(TAG + "image", imageView0.getScaleType().toString());

    }

    private void loadPic1Glide() {

        RequestOptions options = new RequestOptions().
                circleCropTransform()
                .override(400, 400);

        Glide.with(this)
                .load(R.mipmap.girl)
                .apply(options)
                .into(imageView2);
    }

    private void loadGifGlide() {

        RequestOptions options = new RequestOptions();
        Glide.with(this)
                .asBitmap()
                .load(R.mipmap.ironman)
                .into(imageView1);
    }

}
