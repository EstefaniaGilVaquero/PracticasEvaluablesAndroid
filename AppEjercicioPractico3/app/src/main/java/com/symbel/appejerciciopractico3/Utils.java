package com.symbel.appejerciciopractico3;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * Created by estefi on 29/07/2016.
 */
public class Utils {

    public static Bitmap scaleDownBitmap(Bitmap photo, int newHeight, Context context) {

        final float densityMultiplier = context.getResources().getDisplayMetrics().density;

        int h= (int) (newHeight*densityMultiplier);
        int w= (int) (h * photo.getWidth()/((double) photo.getHeight()));

        photo= Bitmap.createScaledBitmap(photo, w, h, true);

        return photo;
    }
}
