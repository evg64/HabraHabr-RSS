package com.eugene.shvabr.ui.rss_feed;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;

import com.eugene.shvabr.data.network.http.HttpGet;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eugene on 04.02.2018.
 */
class RssImageGetter implements Html.ImageGetter {
    private Map<String, Drawable> cache = new HashMap<>();

    @Override
    public Drawable getDrawable(String source) {
        if (cache.containsKey(source)) {
            return cache.get(source);
        }
        HttpGet get = new HttpGet(source);
        try {
            InputStream inputStream = get.call();
            Bitmap bmp = BitmapFactory.decodeStream(inputStream, new Rect(100, 100, 100, 100), null);
            if (bmp == null) {
                return null;
            } else {
                BitmapDrawable result = new BitmapDrawable(bmp);
                result.setBounds(0, 0, result.getIntrinsicWidth(), result.getIntrinsicHeight());
                cache.put(source, result);
                return result;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
