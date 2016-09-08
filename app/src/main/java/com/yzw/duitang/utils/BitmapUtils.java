package com.yzw.duitang.utils;

import android.content.Context;
import android.graphics.BitmapFactory;

/**
 * Created by yzw on 2016/9/6.
 */
public class BitmapUtils {
    public static float getBitmapRatio(Context context, int resourceId) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), resourceId, options);
        return options.outHeight/(float)options.outWidth;
    }
}
