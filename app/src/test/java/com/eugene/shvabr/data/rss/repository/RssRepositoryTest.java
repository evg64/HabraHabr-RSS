package com.eugene.shvabr.data.rss.repository;

import com.eugene.shvabr.domain.common.BiVariantCallback;
import com.eugene.shvabr.domain.model.RssFeed;
import com.eugene.shvabr.domain.repository.RssRepository;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Eugene on 05.02.2018.
 */

public class RssRepositoryTest {

    private RssRepository repository = RssRepositoryImpl.getInstance();

    @Test
    public void testRssRepository() throws Exception {
        RssRepositoryImpl.getInstance()
                         .getFeed(new BiVariantCallback<RssFeed>() {
                             @Override
                             public void onSuccess(RssFeed result) {
                                 Assert.assertNotNull(result);
                                 Assert.assertNotNull(result.getItems());
                                 Assert.assertFalse(result.getItems()
                                                          .isEmpty());
                             }

                             @Override
                             public void onError(Throwable description) {
                                 Assert.fail(description.getMessage());
                             }
                         });
    }

    @Test
    public void testRssRepositoryResetWorks() throws Exception {
        repository.getFeed(new BiVariantCallback<RssFeed>() {
                 @Override
                 public void onSuccess(RssFeed result) {
                     repository.reset();
                     repository
                          .getFeed(new BiVariantCallback<RssFeed>() {
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

                 @Override
                 public void onError(Throwable description) {
                     Assert.fail(description.getMessage());
                 }
             });
    }
}
