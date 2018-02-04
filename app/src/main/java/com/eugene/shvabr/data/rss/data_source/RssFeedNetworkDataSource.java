package com.eugene.shvabr.data.rss.data_source;

import com.eugene.shvabr.data.common.network.HabraHabrApi;
import com.eugene.shvabr.data.common.network.Backend;
import com.eugene.shvabr.domain.common.BiVariantCallback;
import com.eugene.shvabr.domain.model.RssFeed;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Ходит в апи, загружает rss, парсит.
 */
public class RssFeedNetworkDataSource implements RssFeedDataSource {
    private Executor executor = Executors.newSingleThreadExecutor();
    private final Backend backend = new HabraHabrApi();

    @Override
    public void getFeed(final BiVariantCallback<RssFeed> callback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                backend.loadFeed(callback);
            }
        });
    }
}
