package com.eugene.shvabr.ui.rss_feed;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eugene.shvabr.R;
import com.eugene.shvabr.ui.common.mvp.BaseMvpFragment;
import com.eugene.shvabr.ui.rss_feed.model.RssItemForUI;

import java.util.List;

/**
 * Фрагмент с rss-списком
 */
public class RssFeedFragment extends BaseMvpFragment implements RssMvp.View {

    private RssMvp.Presenter presenter = new RssPresenter();

    @Override
    public void showLoading() {
        loadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingView.setVisibility(View.GONE);
    }

    private RecyclerView recycler;
    private RssAdapter adapter = new RssAdapter();
    private View loadingView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rss_feed_fragment, container, false);
        recycler = view.findViewById(R.id.recycler);
        loadingView = view.findViewById(R.id.loading_view);

        recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recycler.setAdapter(adapter);
        return view;
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
    public void displayRss(List<RssItemForUI> items) {
        adapter.setItems(items);
    }

    @Override
    public boolean hasFeed() {
        return adapter.hasFeed();
    }
}
