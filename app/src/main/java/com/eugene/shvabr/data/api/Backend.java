package com.eugene.shvabr.data.api;

import com.eugene.shvabr.domain.repository.RssFeedListener;

/**
 * Created by Eugene on 03.02.2018.
 */

public interface Backend {
    void loadFeed(RssFeedListener callback);
}
