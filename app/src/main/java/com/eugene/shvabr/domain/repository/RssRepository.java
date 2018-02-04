package com.eugene.shvabr.domain.repository;

import com.eugene.shvabr.domain.common.BiVariantCallback;
import com.eugene.shvabr.domain.model.RssFeed;

/**
 * Created by Eugene on 03.02.2018.
 */

public interface RssRepository {
    void getFeed(BiVariantCallback<RssFeed> callback);
}
