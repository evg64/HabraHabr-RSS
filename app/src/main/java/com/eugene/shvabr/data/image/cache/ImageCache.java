package com.eugene.shvabr.data.image.cache;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;

/**
 * Created by Eugene on 04.02.2018.
 */

public interface ImageCache {
    @Nullable Bitmap get(String address);
    void put(String address, @Nullable Bitmap image);
}
