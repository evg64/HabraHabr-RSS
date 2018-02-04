package com.eugene.shvabr.data.data_source;

import com.eugene.shvabr.domain.common.BiVariantCallback;
import com.eugene.shvabr.domain.model.RssFeed;

/**
 * Created by Eugene on 03.02.2018.
 */

public interface RssFeedDataSource {
    void getFeed(BiVariantCallback<RssFeed> callback);
}
