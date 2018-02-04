package com.eugene.shvabr.data;

import com.eugene.shvabr.data.network.HabraHabrRss;
import com.eugene.shvabr.data.network.Backend;
import com.eugene.shvabr.domain.common.BiVariantCallback;
import com.eugene.shvabr.domain.model.RssFeed;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Eugene on 03.02.2018.
 */

public class BackendUnitTest {
    @Test
    public void testRssIsLoadedWell() throws Exception {
        Backend backend = new HabraHabrRss();
        backend.loadFeed(new BiVariantCallback<RssFeed>() {
            @Override
            public void onSuccess(RssFeed feed) {
                System.out.println("Received: " + feed);
                Assert.assertNotNull(feed);
                Assert.assertNotNull(feed.getItems());
                Assert.assertFalse(feed.getItems().isEmpty());
            }

            @Override
            public void onError(Throwable description) {
                Assert.fail();
            }
        });
    }
}
