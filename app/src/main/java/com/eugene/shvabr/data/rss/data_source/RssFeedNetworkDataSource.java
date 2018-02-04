package com.eugene.shvabr.data.rss.data_source;

import com.eugene.shvabr.data.common.network.Backend;
import com.eugene.shvabr.data.common.network.HabraHabrApi;
import com.eugene.shvabr.domain.common.BiVariantCallback;
import com.eugene.shvabr.domain.model.RssFeed;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Ходит в апи, загружает rss, парсит.
 */
public class RssFeedNetworkDataSource implements RssFeedDataSource {
    private Executor executor = Executors.newSingleThreadExecutor();
    private final Backend backend = new HabraHabrApi();

    private final List<ExecutorJob> tasksInProgress = new ArrayList<>();

    @Override
    public void getFeed(final BiVariantCallback<RssFeed> callback) {
        executor.execute(new ExecutorJob(callback));
    }

    @Override
    public synchronized void reset() {
        for (ExecutorJob task : tasksInProgress) {
            task.cancel();
        }
    }

    private class ExecutorJob implements Runnable, BiVariantCallback<RssFeed> {
        private final BiVariantCallback<RssFeed> delegate;
        private AtomicBoolean isCancelled = new AtomicBoolean(false);

        private ExecutorJob(BiVariantCallback<RssFeed> delegate) {
            this.delegate = delegate;
        }

        @Override
        public synchronized void run() {
            RssFeedNetworkDataSource.this.tasksInProgress.add(this);
            backend.loadFeed(this);
        }

        @Override
        public void onSuccess(RssFeed result) {
            synchronized (RssFeedNetworkDataSource.this) {
                RssFeedNetworkDataSource.this.tasksInProgress.remove(this);
                if (!isCancelled.get()) {
                    delegate.onSuccess(result);
                }
            }
        }

        @Override
        public void onError(Throwable description) {
            synchronized (RssFeedNetworkDataSource.this) {
                RssFeedNetworkDataSource.this.tasksInProgress.remove(this);
                if (!isCancelled.get()) {
                    delegate.onError(description);
                }
            }
        }

        void cancel() {
            synchronized (RssFeedNetworkDataSource.this) {
                isCancelled.set(true);
            }
        }
    }
}
