package com.eugene.shvabr.data.repository;

import com.eugene.shvabr.data.data_source.DataSourceFactory;
import com.eugene.shvabr.data.data_source.RssFeedDataSource;
import com.eugene.shvabr.domain.common.BiVariantCallback;
import com.eugene.shvabr.domain.model.RssFeed;
import com.eugene.shvabr.domain.repository.RssRepository;

/**
 * Created by Eugene on 03.02.2018.
 */
public class RssRepositoryImpl implements RssRepository {

    private final RssFeedDataSource source = DataSourceFactory.createDefaultDataSource();

    private RssRepositoryImpl() {

    }

    private static class Singletone {
        private static final RssRepositoryImpl instance = new RssRepositoryImpl();
    }

    @Override
    public void getFeed(BiVariantCallback<RssFeed> callback) {
        source.getFeed(callback);
    }

    public static RssRepository getInstance() {
        return Singletone.instance;
    }
}
