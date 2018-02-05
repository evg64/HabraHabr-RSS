package com.eugene.shvabr.data.image.cache;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;

import com.eugene.shvabr.domain.common.Resettable;

/**
 * Кэш картинок.
 */
public interface ImageCache extends Resettable {
    @Nullable Bitmap get(String address);
    void put(String address, @Nullable Bitmap image);
}
