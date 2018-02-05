package com.eugene.shvabr.ui.rss_feed;

import com.eugene.shvabr.ui.common.mvp.MvpPresenter;
import com.eugene.shvabr.ui.common.mvp.MvpView;
import com.eugene.shvabr.ui.rss_feed.model.RssItemForUI;

/**
 * Mvp-контракт для rss-фида.
 */
public interface RssMvp {

    interface View extends MvpView {
        boolean hasFeed();

        void addRssItem(RssItemForUI item);

        void resetItems();

        void notifyAllItemsLoaded();
    }

    interface Presenter extends MvpPresenter<View> {
        void refresh();
    }
}
