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
import com.eugene.shvabr.domain.repository.RssRepository;
import com.eugene.shvabr.domain.use_case.GetFeedUseCase;
import com.eugene.shvabr.ui.common.mvp.BasePresenter;
import com.eugene.shvabr.ui.rss_feed.RssMvp;
import com.eugene.shvabr.ui.rss_feed.model.RssItemForUI;

import java.util.List;

/**
 * Created by Eugene on 03.02.2018.
 */
public class RssPresenter extends BasePresenter<RssMvp.View> implements RssMvp.Presenter {

    private final RssRepository repository = RssRepositoryImpl.getInstance();
    private GetFeedUseCase getFeedUseCase;

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
                if (view != null) {
                    if (feed == null || feed.getItems() == null) {
                        view.hideLoading();
                        view.showError(R.string.failed_to_load_rss_empty_beans);
                    } else {
                        new ConvertAndDisplayTask(feed).execute();
                    }
                }
            }

            @Override
            public void onError(Throwable description) {
                getFeedUseCase = null;
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
        };
        view.showLoading();
        getFeedUseCase = new GetFeedUseCase(repository);
        getFeedUseCase.execute(new UIThreadCallback(callback));
    }

    /**
     * Приводит ответ от апи к виду, пригодному для представления.<br>
     * Делать это надо в бэкграунде, т.к. по ходу мы будем загружать картинки.
     */
    private class ConvertAndDisplayTask extends AsyncTask<Void, Void, List<RssItemForUI>> {

        private final RssFeed feed;
        private final RssToUIModelMapper mapper = new RssToUIModelMapper(ImageRepositoryImpl.getInstance());

        private ConvertAndDisplayTask(@NonNull RssFeed feed) {
            this.feed = feed;
        }

        @Override
        protected List<RssItemForUI> doInBackground(Void... voids) {
            return mapper.convert(feed.getItems());
        }

        @Override
        protected void onPostExecute(List<RssItemForUI> converted) {
            super.onPostExecute(converted);
            if (view != null) {
                view.hideLoading();
                if (converted == null) {
                    view.showError(R.string.failed_to_display_rss_feed);
                } else {
                    view.displayRss(converted);
                }
            }
        }
    }

    /**
     * Прокидывает все вызовы в главный поток
     */
    private static class UIThreadCallback implements BiVariantCallback<RssFeed> {
        private final BiVariantCallback<RssFeed> wrapped;

        private UIThreadCallback(BiVariantCallback<RssFeed> wrapped) {
            this.wrapped = wrapped;
        }

        @Override
        public void onSuccess(final RssFeed result) {
            ConcurrentUtils.ensureRunOnUIThread(new Runnable() {
                @Override
                public void run() {
                    wrapped.onSuccess(result);
                }
            });
        }

        @Override
        public void onError(final Throwable description) {
            ConcurrentUtils.ensureRunOnUIThread(new Runnable() {
                @Override
                public void run() {
                    wrapped.onError(description);
                }
            });
        }
    }
}
