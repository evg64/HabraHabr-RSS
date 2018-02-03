package com.eugene.shvabr.data.data_source;

import com.eugene.shvabr.data.cache.RssFeedCache;
import com.eugene.shvabr.data.cache.RssFeedInMemoryCache;
import com.eugene.shvabr.domain.model.RssFeed;
import com.eugene.shvabr.domain.repository.RssFeedListener;

/**
 * Created by Eugene on 03.02.2018.
 */

public class RssFeedDataSourceCachingWrapper implements RssFeedDataSource {

    private final RssFeedDataSource delegate;
    private final RssFeedCache cache = new RssFeedInMemoryCache();

    public RssFeedDataSourceCachingWrapper(RssFeedDataSource delegate) {
        this.delegate = delegate;
    }

    @Override
    public void getFeed(final RssFeedListener callback) {
        delegate.getFeed(new RssFeedListener() {
            @Override
            public void onSuccess(RssFeed feed) {
                cache.put(feed);
                callback.onSuccess(feed);
            }

            @Override
            public void onError(Throwable description) {
                callback.onError(description);
            }
        });
    }
}
