package com.eugene.shvabr.data.image.data_source;

import android.graphics.Bitmap;

import com.eugene.shvabr.data.common.network.exception.HttpException;
import com.eugene.shvabr.data.common.network.exception.ParseException;
import com.eugene.shvabr.data.image.cache.ImageCache;
import com.eugene.shvabr.data.image.cache.ImageInMemoryCache;

/**
 * Created by Eugene on 04.02.2018.
 */

public class ImageCachingDataSource implements ImageDataSource {
    private ImageCache cache = new ImageInMemoryCache();
    private final ImageDataSource wrapped;

    public ImageCachingDataSource(ImageDataSource wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public Bitmap getImage(String address) throws HttpException, ParseException {
        Bitmap result = cache.get(address);
        if (result == null) {
            result = wrapped.getImage(address);
            cache.put(address, result);
        }
        return result;
    }
}
