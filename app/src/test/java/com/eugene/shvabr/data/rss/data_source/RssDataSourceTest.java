package com.eugene.shvabr.data.rss.data_source;

import com.eugene.shvabr.data.rss.cache.RssFeedCache;
import com.eugene.shvabr.data.rss.cache.RssFeedInMemoryCache;
import com.eugene.shvabr.domain.common.BiVariantCallback;
import com.eugene.shvabr.domain.model.RssFeed;
import com.eugene.shvabr.domain.model.RssItem;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Eugene on 05.02.2018.
 */

public class RssDataSourceTest {

    private MockDataSource mockDataSource = new MockDataSource();
    private RssFeedCache cache = new RssFeedInMemoryCache();
    private RssFeedCachingDataSource cachingDataSource = new RssFeedCachingDataSource(mockDataSource, cache);
    private RssFeedNetworkDataSource networkDataSource = new RssFeedNetworkDataSource();
    private SingleTaskDataSource singleTaskDataSource = new SingleTaskDataSource(mockDataSource);

    @Test
    public void testThatCachingDataSourceReturnsCachedInstance() throws Exception {
        cachingDataSource.getFeed(new BiVariantCallback<RssFeed>() {
            @Override
            public void onSuccess(final RssFeed result) {
                cachingDataSource.getFeed(new BiVariantCallback<RssFeed>() {
                    @Override
                    public void onSuccess(RssFeed result2) {
                        Assert.assertEquals(result, result2);
                    }

                    @Override
                    public void onError(Throwable description) {
                        Assert.fail(description.getMessage());
                    }
                });
            }

            @Override
            public void onError(Throwable description) {
                Assert.fail(description.getMessage());
            }
        });
    }

    @Test
    public void testThatCachingDataSourceProperlyImlementsReset() throws Exception {
        cachingDataSource.getFeed(new BiVariantCallback<RssFeed>() {
            @Override
            public void onSuccess(final RssFeed result) {
                cache.reset();
                cachingDataSource.getFeed(new BiVariantCallback<RssFeed>() {
                    @Override
                    public void onSuccess(RssFeed result2) {
                        Assert.assertNotEquals(result, result2);
                    }

                    @Override
                    public void onError(Throwable description) {
                        Assert.fail(description.getMessage());
                    }
                });
            }

            @Override
            public void onError(Throwable description) {
                Assert.fail(description.getMessage());
            }
        });
    }

    @Test
    public void testThatNetworkDataSourceReturnsValue() throws Exception {
        networkDataSource.getFeed(new BiVariantCallback<RssFeed>() {
            @Override
            public void onSuccess(RssFeed result) {
                Assert.assertNotNull(result);
                Assert.assertNotNull(result.getItems());
                Assert.assertFalse(result.getItems().isEmpty());
            }

            @Override
            public void onError(Throwable description) {
                Assert.fail(description.getMessage());
            }
        });
    }

    @Test
    public void testThatSingleTaskDataSourceDoesntCallDelegateTwice() throws Exception {
        // requires mockito
    }

    private static final class MockDataSource implements RssFeedDataSource {

        @Override
        public void reset() {

        }

        @Override
        public void getFeed(BiVariantCallback<RssFeed> callback) {
            try {
                Thread.sleep(1000);
                callback.onSuccess(new RssFeed(new ArrayList<RssItem>()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
