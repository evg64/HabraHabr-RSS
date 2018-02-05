package com.eugene.shvabr.domain.repository;

import android.graphics.Bitmap;

import com.eugene.shvabr.data.common.network.exception.HttpException;
import com.eugene.shvabr.data.common.network.exception.ParseException;
import com.eugene.shvabr.domain.common.Resettable;

/**
 * Репозиторий картинок.
 */
public interface ImageRepository extends Resettable {
    /**
     * Загружает синхронно
     * @param address
     * @return
     */
    Bitmap getImage(String address) throws HttpException, ParseException;
}
