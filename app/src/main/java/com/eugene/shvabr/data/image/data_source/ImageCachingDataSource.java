package com.eugene.shvabr.data.image.data_source;

import android.graphics.Bitmap;

import com.eugene.shvabr.data.common.network.exception.HttpException;
import com.eugene.shvabr.data.common.network.exception.ParseException;
import com.eugene.shvabr.data.image.cache.ImageCache;

/**
 * Обертка, которая кэширует результат и впоследствии возвращает значение из кэша.
 */
public class ImageCachingDataSource implements ImageDataSource {
    private ImageCache cache;
    private final ImageDataSource delegate;

    public ImageCachingDataSource(ImageDataSource delegate, ImageCache cache) {
        this.delegate = delegate;
        this.cache = cache;
    }

    @Override
    public Bitmap getImage(String address) throws HttpException, ParseException {
        Bitmap result = cache.get(address);
        if (result == null) {
            result = delegate.getImage(address);
            cache.put(address, result);
        }
        return result;
    }
}
