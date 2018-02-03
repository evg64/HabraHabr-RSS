package com.eugene.shvabr.data.data_source;

import com.eugene.shvabr.domain.repository.RssFeedListener;

/**
 * Created by Eugene on 03.02.2018.
 */

public interface RssFeedDataSource {
    void getFeed(RssFeedListener callback);
}
