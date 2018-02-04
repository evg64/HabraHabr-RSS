package com.eugene.shvabr.data.data_source;

import com.eugene.shvabr.data.cache.RssFeedCache;
import com.eugene.shvabr.data.cache.RssFeedInMemoryCache;
import com.eugene.shvabr.domain.common.BiVariantCallback;
import com.eugene.shvabr.domain.model.RssFeed;

/**
 * Created by Eugene on 03.02.2018.
 */

public class RssFeedCachingDataSource implements RssFeedDataSource {

    private final RssFeedDataSource delegate;
    private final RssFeedCache cache = new RssFeedInMemoryCache();

    public RssFeedCachingDataSource(RssFeedDataSource delegate) {
        this.delegate = delegate;
    }

    @Override
    public void getFeed(final BiVariantCallback<RssFeed> callback) {
        RssFeed cached = cache.get();
        if (cached == null) {
            delegate.getFeed(new BiVariantCallback<RssFeed>() {
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
        } else {
            callback.onSuccess(cached);
        }
    }
}
