package com.eugene.shvabr.data.rss.repository;

import com.eugene.shvabr.data.rss.cache.RssFeedCache;
import com.eugene.shvabr.data.rss.cache.RssFeedInMemoryCache;
import com.eugene.shvabr.data.rss.data_source.RssFeedCachingDataSource;
import com.eugene.shvabr.data.rss.data_source.RssFeedDataSource;
import com.eugene.shvabr.data.rss.data_source.RssFeedNetworkDataSource;
import com.eugene.shvabr.data.rss.data_source.SingleTaskDataSource;
import com.eugene.shvabr.domain.common.BiVariantCallback;
import com.eugene.shvabr.domain.model.RssFeed;
import com.eugene.shvabr.domain.repository.RssRepository;

/**
 * Created by Eugene on 03.02.2018.
 */
public class RssRepositoryImpl implements RssRepository {

    private final RssFeedCache cache = new RssFeedInMemoryCache();

    private final RssFeedDataSource source = createDataSource();

    private RssFeedDataSource createDataSource() {
        RssFeedDataSource onions = new RssFeedNetworkDataSource();
        onions = new RssFeedCachingDataSource(onions, cache);
        onions = new SingleTaskDataSource(onions);
        return onions;
    }

    private RssRepositoryImpl() {

    }

    private static class Singletone {
        private static final RssRepositoryImpl instance = new RssRepositoryImpl();
    }

    @Override
    public void getFeed(BiVariantCallback<RssFeed> callback) {
        source.getFeed(callback);
    }

    @Override
    public void reset() {
        cache.reset();
        source.reset();
    }

    public static RssRepository getInstance() {
        return Singletone.instance;
    }
}
