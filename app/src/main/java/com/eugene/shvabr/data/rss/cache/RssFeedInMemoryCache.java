package com.eugene.shvabr.data.rss.cache;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.eugene.shvabr.domain.model.RssFeed;

/**
 * Created by Eugene on 03.02.2018.
 */

public class RssFeedInMemoryCache implements RssFeedCache {
    private RssFeed cachedValue;

    @Nullable
    @Override
    public RssFeed get() {
        return cachedValue;
    }

    @Override
    public void put(@NonNull RssFeed feed) {
        cachedValue = feed;
    }
}
