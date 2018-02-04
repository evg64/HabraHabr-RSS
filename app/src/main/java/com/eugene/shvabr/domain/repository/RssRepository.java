package com.eugene.shvabr.domain.repository;

import com.eugene.shvabr.domain.common.BiVariantCallback;
import com.eugene.shvabr.domain.common.Resettable;
import com.eugene.shvabr.domain.model.RssFeed;

/**
 * Created by Eugene on 03.02.2018.
 */

public interface RssRepository extends Resettable {
    void getFeed(BiVariantCallback<RssFeed> callback);
}
