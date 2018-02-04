package com.eugene.shvabr.data.image.data_source;

import android.graphics.Bitmap;

import com.eugene.shvabr.data.common.network.exception.HttpException;
import com.eugene.shvabr.data.common.network.exception.ParseException;

/**
 * Created by Eugene on 04.02.2018.
 */
public interface ImageDataSource {
    Bitmap getImage(String address) throws HttpException, ParseException;
}
