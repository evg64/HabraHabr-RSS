package com.eugene.shvabr.domain.repository;

/**
 * Created by Eugene on 03.02.2018.
 */

public interface RssRepository {
    void getFeed(RssFeedListener callback);
}
