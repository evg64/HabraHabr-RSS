package com.eugene.shvabr.data.image.cache;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eugene on 04.02.2018.
 */

public class ImageInMemoryCache implements ImageCache {

    private final Map<String, Bitmap> cache = new HashMap<>();

    @Nullable
    @Override
    public Bitmap get(String address) {
        return cache.get(address);
    }

    @Override
    public void put(String address, @Nullable Bitmap image) {
        if (image != null) {
            cache.put(address, image);
        }
    }
}
