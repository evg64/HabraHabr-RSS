package com.eugene.shvabr.data.rss.cache;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.eugene.shvabr.domain.model.RssFeed;

/**
 Реализация кэша rss, которая держит все в memory.
 */
public class RssFeedInMemoryCache implements RssFeedCache {
    private RssFeed cachedValue;

    @Nullable
    @Override
    public synchronized RssFeed get() {
        return cachedValue;
    }

    @Override
    public synchronized void put(@NonNull RssFeed feed) {
        cachedValue = feed;
    }

    @Override
    public synchronized void reset() {
        cachedValue = null;
    }
}
