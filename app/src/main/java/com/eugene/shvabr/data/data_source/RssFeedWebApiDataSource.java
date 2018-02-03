package com.eugene.shvabr.data.data_source;

import com.eugene.shvabr.data.api.HabraHabrRss;
import com.eugene.shvabr.data.api.Backend;
import com.eugene.shvabr.domain.repository.RssFeedListener;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Ходит в апи, загружает rss, парсит.
 */
public class RssFeedWebApiDataSource implements RssFeedDataSource {
    private Executor executor = Executors.newSingleThreadExecutor();
    private final Backend backend = new HabraHabrRss();

    @Override
    public void getFeed(final RssFeedListener callback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                backend.loadFeed(callback);
            }
        });
    }
}
