package com.eugene.shvabr.data.repository;

import com.eugene.shvabr.data.data_source.DataSourceFactory;
import com.eugene.shvabr.data.data_source.RssFeedDataSource;
import com.eugene.shvabr.data.mapper.ToDomainModelMapper;
import com.eugene.shvabr.domain.repository.RssFeedListener;
import com.eugene.shvabr.domain.repository.RssRepository;

/**
 * Created by Eugene on 03.02.2018.
 */
public class RssRepositoryImpl implements RssRepository {

    private final RssFeedDataSource source = DataSourceFactory.createDefaultDataSource();
    private final ToDomainModelMapper mapper = new ToDomainModelMapper();

    @Override
    public void getFeed(RssFeedListener callback) {
        source.getFeed(callback);
    }
}
