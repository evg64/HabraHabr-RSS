package com.eugene.shvabr.data.image.data_source;

import android.graphics.Bitmap;

import com.eugene.shvabr.data.common.network.Backend;
import com.eugene.shvabr.data.common.network.HabraHabrApi;
import com.eugene.shvabr.data.common.network.exception.HttpException;
import com.eugene.shvabr.data.common.network.exception.ParseException;

/**
 * Created by Eugene on 04.02.2018.
 */

public class ImageNetworkDataSource implements ImageDataSource {
    private final Backend backend = new HabraHabrApi();

    @Override
    public Bitmap getImage(String address) throws HttpException, ParseException {
        return backend.loadImage(address);
    }
}
