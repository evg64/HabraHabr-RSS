package com.eugene.shvabr.data.image.repository;

import android.graphics.Bitmap;

import com.eugene.shvabr.data.common.network.exception.HttpException;
import com.eugene.shvabr.data.common.network.exception.ParseException;
import com.eugene.shvabr.data.image.data_source.ImageCachingDataSource;
import com.eugene.shvabr.data.image.data_source.ImageDataSource;
import com.eugene.shvabr.data.image.data_source.ImageNetworkDataSource;
import com.eugene.shvabr.domain.repository.ImageRepository;

/**
 * Created by Eugene on 04.02.2018.
 */

public class ImageRepositoryImpl implements ImageRepository {

    private static final class Singletone {
        private static final ImageRepositoryImpl INSTANCE = new ImageRepositoryImpl();
    }

    private ImageRepositoryImpl() {
    }

    public static ImageRepositoryImpl getInstance() {
        return Singletone.INSTANCE;
    }

    private final ImageDataSource source = new ImageCachingDataSource(new ImageNetworkDataSource());

    @Override
    public Bitmap getImage(String address) throws HttpException, ParseException {
        return source.getImage(address);
    }
}
