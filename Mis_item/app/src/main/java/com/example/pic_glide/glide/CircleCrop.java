package com.example.pic_glide.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

public class CircleCrop extends BitmapTransformation {

    public CircleCrop(Context context) {
        super(context);
    }

    public CircleCrop(BitmapPool bitmapPool) {
        super(bitmapPool);
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        int diameter = Math.min(toTransform.getWidth(),toTransform.getHeight());
        //从Bitmao缓存池中尝试获取一个对象来进行重用
        final Bitmap toReuse = pool.get(outWidth,outHeight,Bitmap.Config.ARGB_8888);
        final Bitmap result;
        if(toReuse != null) {
            result = toReuse;
        } else {
            result = Bitmap.createBitmap(diameter,diameter, Bitmap.Config.ARGB_8888);
        }
        //具体进行圆形化变换的部分：算出画布偏移值，并且根据刚才得到的直径算出半径来进行画圆，最后尝试将服用的Bitmap对象重新放回到缓存池当中。
        int dx = (toTransform.getWidth() - diameter) / 2;
        int dy = (toTransform.getHeight() - diameter) / 2;
        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        BitmapShader shader = new BitmapShader(toTransform, BitmapShader.TileMode.CLAMP,BitmapShader.TileMode.CLAMP);
        if(dx != 0 || dy != 0) {
            Matrix matrix = new Matrix();
            matrix.setTranslate(-dx,-dy);
            shader.setLocalMatrix(matrix);
        }
        paint.setShader(shader);
        paint.setAntiAlias(true);
        float radius = diameter / 2f;

        canvas.drawCircle(radius,radius,radius,paint);

        if(toReuse != null && !pool.put(toReuse)) {
            toReuse.recycle();
        }
        return result;
    }

    @Override
    public String getId() {
        return "com.example.glide.CircleCrop";
    }
}
