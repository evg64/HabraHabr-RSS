package com.eugene.shvabr.data.data_source;

import com.eugene.shvabr.domain.common.BiVariantCallback;
import com.eugene.shvabr.domain.model.RssFeed;

import java.util.HashSet;
import java.util.Set;

/**
 * Вместо создания 2го (дублирующего) запроса уведомит клиента по окончании 1го.
 */
public class SingleTaskDataSource implements RssFeedDataSource {
    private final RssFeedDataSource wrapped;
    private OneToManyCallback oneToMany;

    public SingleTaskDataSource(RssFeedDataSource wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public synchronized void getFeed(BiVariantCallback<RssFeed> callback) {
        if (isLoading()) {
            oneToMany.addListener(callback);
        } else {
            oneToMany = new OneToManyCallback();
            oneToMany.addListener(callback);
            wrapped.getFeed(oneToMany);
        }
    }

    private boolean isLoading() {
        return oneToMany != null;
    }

    private class OneToManyCallback implements BiVariantCallback<RssFeed> {

        private Set<BiVariantCallback<RssFeed>> listeners = new HashSet<>();

        private void addListener(BiVariantCallback<RssFeed> listener) {
            listeners.add(listener);
        }

        @Override
        public void onSuccess(RssFeed result) {
            synchronized (SingleTaskDataSource.this) {
                for (BiVariantCallback<RssFeed> listener : listeners) {
                    listener.onSuccess(result);
                }
                oneToMany = null;
            }
        }

        @Override
        public void onError(Throwable description) {
            synchronized (SingleTaskDataSource.this) {
                for (BiVariantCallback<RssFeed> listener : listeners) {
                    listener.onError(description);
                }
                oneToMany = null;
            }
        }
    }
}
