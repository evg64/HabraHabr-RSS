package com.eugene.shvabr.ui.rss_feed.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.eugene.shvabr.R;
import com.eugene.shvabr.ui.common.ErrorDialogFragment;
import com.eugene.shvabr.ui.common.mvp.BaseMvpFragment;
import com.eugene.shvabr.ui.rss_feed.RssMvp;
import com.eugene.shvabr.ui.rss_feed.model.RssItemForUI;
import com.eugene.shvabr.ui.rss_feed.presenter.RssPresenter;

/**
 * Фрагмент с rss-списком
 */
public class RssFeedFragment extends BaseMvpFragment implements RssMvp.View, ErrorDialogFragment.Retryable {

    private RssMvp.Presenter presenter = new RssPresenter();
    private SwipeRefreshLayout refreshLayout;

    @Override
    public void showLoading() {
        refreshLayout.setRefreshing(true);
        recycler.setVisibility(View.GONE);
//        loadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        refreshLayout.setRefreshing(false);
        recycler.setVisibility(View.VISIBLE);
//        loadingView.setVisibility(View.GONE);
    }

    private RecyclerView recycler;
    private RssAdapter adapter = new RssAdapter();
//    private View loadingView;

    public RssFeedFragment() {
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rss_feed_fragment, container, false);
        recycler = view.findViewById(R.id.recycler);
//        loadingView = view.findViewById(R.id.loading_view);

        recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recycler.setAdapter(adapter);

        refreshLayout = view.findViewById(R.id.swipe_to_refresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.refresh();
            }
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.rss_options_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                presenter.refresh();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.attach(this);
    }

    @Override
    public void onDestroy() {
        presenter.detach(this);
        super.onDestroy();
    }

    @Override
    public boolean hasFeed() {
        return adapter.hasFeed();
    }

    @Override
    public void addRssItem(RssItemForUI item) {
        adapter.addItem(item);
    }

    @Override
    public void resetItems() {
        adapter.resetItems();
    }

    @Override
    public void notifyAllItemsLoaded() {
        adapter.setAllItemsLoaded(true);
    }

    @Override
    public void retry() {
        presenter.refresh();
    }
}
