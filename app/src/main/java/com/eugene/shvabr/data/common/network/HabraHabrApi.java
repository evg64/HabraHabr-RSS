package com.eugene.shvabr.data.common.network;

import android.graphics.Bitmap;

import com.eugene.shvabr.data.common.Config;
import com.eugene.shvabr.data.common.network.exception.HttpException;
import com.eugene.shvabr.data.common.network.exception.ParseException;
import com.eugene.shvabr.data.common.network.http.HttpGet;
import com.eugene.shvabr.data.rss.parser.RssFeedParser;
import com.eugene.shvabr.data.image.parser.ImageDecoder;
import com.eugene.shvabr.data.rss.mapper.RssToDomainModelMapper;
import com.eugene.shvabr.data.rss.model.RssFeedData;
import com.eugene.shvabr.domain.common.BiVariantCallback;
import com.eugene.shvabr.domain.model.RssFeed;

import java.io.IOException;
import java.io.InputStream;

/**
 * Реализация клиента для бэкэнда.
 */
public class HabraHabrApi implements Backend {

    private RssFeedParser parser = new RssFeedParser();
    private RssToDomainModelMapper mapper = new RssToDomainModelMapper();

    @Override
    public void loadFeed(BiVariantCallback<RssFeed> callback) {
        InputStream is = null;
        try {
            // get over https
            try {
                is = new HttpGet(Config.RSS_FEED_URL).call();
            } catch (IOException e) {
                e.printStackTrace();
                callback.onError(new HttpException(e));
                return;
            }
            // parse
            try {
                RssFeedData data = parser.parse(is);
                RssFeed result = mapper.convert(data);
                callback.onSuccess(result);
            } catch (Exception e) {
                e.printStackTrace();
                callback.onError(new ParseException(e));
            }
        } finally {
            closeInputStream(is);
        }
    }

    @Override
    public Bitmap loadImage(String address) throws HttpException, ParseException {
        InputStream inputStream = null;
        try {
            try {
                inputStream = new HttpGet(address).call();
            } catch (IOException e) {
                e.printStackTrace();
                throw new HttpException(e);
            }
            try {
                return new ImageDecoder().parse(inputStream);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ParseException(e);
            }
        } finally {
            closeInputStream(inputStream);
        }
    }

    private void closeInputStream(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
