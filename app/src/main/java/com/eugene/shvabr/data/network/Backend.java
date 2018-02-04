package com.eugene.shvabr.data.network;

import com.eugene.shvabr.domain.common.BiVariantCallback;
import com.eugene.shvabr.domain.model.RssFeed;

/**
 * Created by Eugene on 03.02.2018.
 */

public interface Backend {
    void loadFeed(BiVariantCallback<RssFeed> callback);
}
