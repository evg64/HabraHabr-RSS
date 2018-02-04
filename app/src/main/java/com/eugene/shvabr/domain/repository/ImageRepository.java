package com.eugene.shvabr.domain.repository;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.eugene.shvabr.data.common.network.exception.HttpException;
import com.eugene.shvabr.data.common.network.exception.ParseException;

/**
 * Created by Eugene on 04.02.2018.
 */
public interface ImageRepository {
    /**
     * Загружает синхронно
     * @param address
     * @return
     */
    Bitmap getImage(String address) throws HttpException, ParseException;
}
