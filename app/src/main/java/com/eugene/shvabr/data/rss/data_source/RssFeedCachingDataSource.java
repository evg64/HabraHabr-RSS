package com.eugene.shvabr.data.rss.data_source;

import com.eugene.shvabr.data.rss.cache.RssFeedCache;
import com.eugene.shvabr.data.rss.cache.RssFeedInMemoryCache;
import com.eugene.shvabr.domain.common.BiVariantCallback;
import com.eugene.shvabr.domain.model.RssFeed;

/**
 * Обертка, которая кэширует результат и впоследствии возвращает значение из кэша.
 */
public class RssFeedCachingDataSource implements RssFeedDataSource {

    private final RssFeedDataSource delegate;
    private final RssFeedCache cache;

    public RssFeedCachingDataSource(RssFeedDataSource delegate, RssFeedCache cache) {
        this.delegate = delegate;
        this.cache = cache;
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

    @Override
    public void reset() {
        // сброс кэша происходит на уровне репозитория
        delegate.reset();
    }
}
