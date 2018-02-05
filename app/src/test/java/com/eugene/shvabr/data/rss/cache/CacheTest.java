package com.eugene.shvabr.data.rss.cache;

import com.eugene.shvabr.domain.model.RssFeed;
import com.eugene.shvabr.domain.model.RssItem;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Eugene on 05.02.2018.
 */

public class CacheTest {

    private RssFeed stubFeed = new RssFeed(new ArrayList<RssItem>());

    @Test
    public void testThatInMemoryCachingWorks() {
        RssFeedInMemoryCache cache = new RssFeedInMemoryCache();
        cache.put(stubFeed);
        Assert.assertTrue(cache.get() == stubFeed);
    }

    @Test
    public void testThatResetWorks() {
        RssFeedInMemoryCache cache = new RssFeedInMemoryCache();
        cache.put(stubFeed);
        cache.reset();
        Assert.assertTrue(cache.get() == null);
    }
}
