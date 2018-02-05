package com.eugene.shvabr.ui.rss_feed.presenter;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.eugene.shvabr.R;
import com.eugene.shvabr.common.ConcurrentUtils;
import com.eugene.shvabr.data.common.network.exception.HttpException;
import com.eugene.shvabr.data.common.network.exception.ParseException;
import com.eugene.shvabr.data.image.repository.ImageRepositoryImpl;
import com.eugene.shvabr.data.rss.repository.RssRepositoryImpl;
import com.eugene.shvabr.domain.common.BiVariantCallback;
import com.eugene.shvabr.domain.model.RssFeed;
import com.eugene.shvabr.domain.model.RssItem;
import com.eugene.shvabr.domain.repository.ImageRepository;
import com.eugene.shvabr.domain.repository.RssRepository;
import com.eugene.shvabr.domain.use_case.GetFeedUseCase;
import com.eugene.shvabr.domain.use_case.RefreshUseCase;
import com.eugene.shvabr.ui.common.mvp.BasePresenter;
import com.eugene.shvabr.ui.rss_feed.RssMvp;
import com.eugene.shvabr.ui.rss_feed.model.RssItemForUI;

import java.util.ArrayList;
import java.util.List;

/**
 * Presenter для rss фида.
 */
public class RssPresenter extends BasePresenter<RssMvp.View> implements RssMvp.Presenter {

    private final RssRepository rssRepository = RssRepositoryImpl.getInstance();
    private final ImageRepository imageRepository = ImageRepositoryImpl.getInstance();
    private GetFeedUseCase getFeedUseCase;
    private ConvertAndDisplayTask convertAndDisplayTask;

    @Override
    public void attach(RssMvp.View view) {
        super.attach(view);
        if (needsFeed()) {
            loadRssFeed();
        }
    }

    @Override
    public void detach(RssMvp.View view) {
        if (getFeedUseCase != null) {
            getFeedUseCase.unsubscribe();
        }
        super.detach(view);
    }

    private boolean needsFeed() {
        return getFeedUseCase == null && view != null && !view.hasFeed();
    }

    private void loadRssFeed() {
        BiVariantCallback<RssFeed> callback = new BiVariantCallback<RssFeed>() {
            @Override
            public void onSuccess(final RssFeed feed) {
                getFeedUseCase = null;
                onRssFeedReceived(feed);
            }

            @Override
            public void onError(Throwable description) {
                getFeedUseCase = null;
                onFailedToReceiveRssFeed(description);
            }
        };
        view.showLoading();
        getFeedUseCase = new GetFeedUseCase(rssRepository);
        getFeedUseCase.execute(new ConcurrentUtils.UIThreadCallback<>(callback));
    }

    private void onRssFeedReceived(RssFeed feed) {
        if (view != null) {
            if (feed == null || feed.getItems() == null) {
                view.hideLoading();
                view.showError(R.string.failed_to_load_rss_empty_beans);
            } else {
                convertAndDisplayTask = new ConvertAndDisplayTask(feed);
                convertAndDisplayTask.execute();
            }
        }
    }

    private void onFailedToReceiveRssFeed(Throwable description) {
        if (view != null) {
            view.hideLoading();
            final int errMsgResId;
            if (description instanceof HttpException) {
                errMsgResId = R.string.failed_to_load_rss_network_error;
            } else if (description instanceof ParseException) {
                errMsgResId = R.string.failed_to_load_rss_parsing_error;
            } else {
                errMsgResId = R.string.failed_to_load_rss_unknown_error;
            }
            view.showError(errMsgResId);
        }
    }

    @Override
    public void refresh() {
        view.showLoading();
        if(convertAndDisplayTask != null) {
            convertAndDisplayTask.cancel(true);
        }
        BiVariantCallback<RssFeed> callback = new BiVariantCallback<RssFeed>() {
            @Override
            public void onSuccess(RssFeed result) {
                onRssFeedReceived(result);
            }

            @Override
            public void onError(Throwable description) {
                onFailedToReceiveRssFeed(description);
            }
        };
        callback = new ConcurrentUtils.UIThreadCallback<>(callback);
        new RefreshUseCase(rssRepository, imageRepository).execute(callback);
    }

    /**
     * Приводит ответ от апи к виду, пригодному для представления.<br>
     * Делать это надо в бэкграунде, т.к. по ходу мы будем загружать картинки.
     */
    private class ConvertAndDisplayTask extends AsyncTask<Void, RssItemForUI, List<RssItemForUI>> {

        private final RssFeed feed;
        private final RssToUIModelMapper mapper = new RssToUIModelMapper(imageRepository);

        private ConvertAndDisplayTask(@NonNull RssFeed feed) {
            this.feed = feed;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            view.resetItems();
        }

        @Override
        protected List<RssItemForUI> doInBackground(Void... voids) {
            if (feed.getItems() == null) {
                return null;
            }
            List<RssItemForUI> result = new ArrayList<>();
            for (RssItem item : feed.getItems()) {
                if (isCancelled()) {
                    break;
                }
                RssItemForUI converted = mapper.convert(item);
                if (converted != null) {
                    result.add(converted);
                    publishProgress(converted);
                }
            }
            return result;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            convertAndDisplayTask = null;
        }

        @Override
        protected void onProgressUpdate(RssItemForUI... values) {
            super.onProgressUpdate(values);
            if (view != null && !isCancelled()) {
                view.hideLoading();
                view.addRssItem(values[0]);
            }
        }

        @Override
        protected void onPostExecute(List<RssItemForUI> converted) {
            super.onPostExecute(converted);
            convertAndDisplayTask = null;
            if (view != null) {
                view.hideLoading();
                if (converted == null) {
                    view.showError(R.string.failed_to_display_rss_feed);
                } else {
                    view.notifyAllItemsLoaded();
                }
            }
        }
    }
}
