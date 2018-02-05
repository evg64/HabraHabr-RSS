package com.eugene.shvabr.data.rss.data_source;

import com.eugene.shvabr.domain.common.Resettable;
import com.eugene.shvabr.domain.common.BiVariantCallback;
import com.eugene.shvabr.domain.model.RssFeed;

/**
 * Отвечает за получение rss-фида.
 */
public interface RssFeedDataSource extends Resettable {
    void getFeed(BiVariantCallback<RssFeed> callback);
}
