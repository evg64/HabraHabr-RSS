package com.eugene.shvabr.data.network;

import com.eugene.shvabr.data.Config;
import com.eugene.shvabr.data.network.exception.HttpException;
import com.eugene.shvabr.data.network.exception.ParseException;
import com.eugene.shvabr.data.network.http.HttpGet;
import com.eugene.shvabr.data.network.parser.RssFeedParser;
import com.eugene.shvabr.data.mapper.RssToDomainModelMapper;
import com.eugene.shvabr.data.model.RssFeedData;
import com.eugene.shvabr.domain.common.BiVariantCallback;
import com.eugene.shvabr.domain.model.RssFeed;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Eugene on 03.02.2018.
 */

public class HabraHabrRss implements Backend {

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
