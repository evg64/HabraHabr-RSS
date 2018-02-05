package com.eugene.shvabr.domain.model;

import android.support.annotation.NonNull;

import java.util.Collections;
import java.util.List;

/**
 * Доменная модель rss-фида.
 */
public class RssFeed {
    private final List<RssItem> items;

    public RssFeed(@NonNull List<RssItem> items) {
        this.items = Collections.unmodifiableList(items);
    }

    public List<RssItem> getItems() {
        return items;
    }
}
