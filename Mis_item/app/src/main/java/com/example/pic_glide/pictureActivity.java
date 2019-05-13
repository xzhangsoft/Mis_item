package com.example.pic_glide;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;
import com.example.mis_item.R;
import com.example.pic_glide.glide.CircleCrop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class pictureActivity extends AppCompatActivity {
    private static final String TAG = "pictureActivity";

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        imageView = findViewById(R.id.mimi);
        // 加载应用资源
         glideSample();
        // 加载本地图片
//        getFilePic();
        //加载Gif
        //getGit();

        // 加载二进制流

        // 加载Uri对象

        //复杂图片
//        complexPic();

        //保存图片到本地
//        savePic();
    }

    private void savePic() {
        imageView.setDrawingCacheEnabled(true);
        Bitmap obmp = Bitmap.createBitmap(imageView.getDrawingCache());
        imageView.setDrawingCacheEnabled(false);

        saveMyBitmap(this,obmp);
    }

    private void saveMyBitmap(Context context, Bitmap bitmap) {
        String sdCardDir = Environment.getExternalStorageDirectory() + "/DCIM/";
        File appDir = new File(sdCardDir, "cats");
        if (!appDir.exists()) {//不存在
            appDir.mkdir();
        }

        String fileName = "cats" + System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        context.sendBroadcast(intent);
        Toast.makeText(context,"图片保存成功",Toast.LENGTH_SHORT).show();

    }

    private void custmoPic() {

    }

    private void complexPic() {
//        MultiTransformation multi = new MultiTransformation(
//                new BlurTransformation(25),
//                new RoundedCornersTransformation(128, 0, RoundedCornersTransformation.CornerType.ALL)
//        );

        String url = "https://www.baidu.com/img/bd_logo1.png";
        Glide.with(this)
                .load(url)

//                .apply(bitmapTransform(new BlurTransformation(25)))
//                .apply(bitmapTransform(new GrayscaleTransformation()))

//                .override(500, 500)
//                .apply(bitmapTransform(multi))
//                .apply(bitmapTransform(new SketchFilterTransformation()))
                .into(imageView);
    }

    private void getComflexPic() {
//        Glide.with(this)
//                .load(R.mipmap.mimi)
//                .apply();
    }

    private void getFilePic() {
        File file = new File(getExternalCacheDir() + "/mimi.jpg");
//        Log.d(TAG, file.toString());
        Glide.with(this).load(file).into(imageView);

    }

    private void getGit() {
//        String url2 = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3396140532,1228025744&fm=23&gp=0.jpg";
//
//        Glide.with(this)
//                .load(url2)
//                .asGif()//只允许加载动态图
//                .placeholder(R.mipmap.ic_launcher)//加载占位图
//                .error(R.mipmap.ic_launcher)//异常占位图
//                .into(imageView);
    }


    private void glideSample() {
        String url = "https://www.baidu.com/img/bd_logo1.png";
//        String gitUrl = "http://p1.pstatp.com/large/166200019850062839d3";
//        String gitUrl1 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1557317466318&di=5028f87f99b3b1ffe3faf9f133a9494e&imgtype=0&src=http%3A%2F%2Fimg.mp.itc.cn%2Fupload%2F20170518%2F8e54925342144841a82058a7d825c07a.jpg";
//
        Glide.with(this)
                .load(R.mipmap.mimi)
                .placeholder(R.mipmap.placeholder)
                .error(R.mipmap.error)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .crossFade(3000)
//                .dontTransform()
//                对原始图片的中心区域进行裁剪后得到的图片
//                .centerCrop()
//                会将图片按照原始的长宽充满全屏
//                .fitCenter()
//                .thumbnail(0.1f)
//                .override(400, 400)
//                遵循图片本身大小
                .transform(new CircleCrop(this))
                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .animate(R.anim.item_alpha_in)
                .into(imageView);

        Log.v(TAG,imageView.getScaleType().toString());

    }


}
