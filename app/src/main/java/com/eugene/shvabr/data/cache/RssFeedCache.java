package com.eugene.shvabr.data.cache;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.eugene.shvabr.domain.model.RssFeed;

/**
 * Created by Eugene on 03.02.2018.
 */

public interface RssFeedCache {
    @Nullable
    RssFeed get();
    void put(@NonNull RssFeed feed);
}