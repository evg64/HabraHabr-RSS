package com.eugene.shvabr.ui.rss_feed;

import com.eugene.shvabr.domain.model.RssFeed;
import com.eugene.shvabr.domain.model.RssItem;
import com.eugene.shvabr.ui.common.mvp.MvpPresenter;
import com.eugene.shvabr.ui.common.mvp.MvpView;

import java.util.List;

/**
 * Created by Eugene on 03.02.2018.
 */

public interface RssMvp {
    interface View extends MvpView {
        void displayRss(List<RssItem> items);
        boolean hasFeed();
    }

    interface Presenter extends MvpPresenter<View> {
    }
}
