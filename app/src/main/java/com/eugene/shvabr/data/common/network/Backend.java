package com.eugene.shvabr.data.common.network;

import android.graphics.Bitmap;

import com.eugene.shvabr.data.common.network.exception.HttpException;
import com.eugene.shvabr.data.common.network.exception.ParseException;
import com.eugene.shvabr.domain.common.BiVariantCallback;
import com.eugene.shvabr.domain.model.RssFeed;

/**
 * Интерфейс объявляет методы, которые предоставляет нам api бэка.
 */
public interface Backend {
    void loadFeed(BiVariantCallback<RssFeed> callback);

    Bitmap loadImage(String address) throws HttpException, ParseException;
}
