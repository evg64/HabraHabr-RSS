package com.eugene.shvabr.ui.rss_feed;

import com.eugene.shvabr.R;
import com.eugene.shvabr.common.ConcurrentUtils;
import com.eugene.shvabr.data.network.exception.HttpException;
import com.eugene.shvabr.data.network.exception.ParseException;
import com.eugene.shvabr.data.repository.RssRepositoryImpl;
import com.eugene.shvabr.domain.common.BiVariantCallback;
import com.eugene.shvabr.domain.model.RssFeed;
import com.eugene.shvabr.domain.repository.RssRepository;
import com.eugene.shvabr.domain.use_case.GetFeedUseCase;
import com.eugene.shvabr.ui.common.mvp.BasePresenter;

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
            public void onSuccess(RssFeed feed) {
                getFeedUseCase = null;
                if (view != null) {
                    view.hideLoading();
                    if (feed == null || feed.getItems() == null) {
                        view.showError(R.string.failed_to_load_rss_empty_beans);
                    } else {
                        view.displayRss(feed.getItems());
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
