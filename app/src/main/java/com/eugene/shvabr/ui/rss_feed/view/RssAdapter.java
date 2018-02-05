package com.eugene.shvabr.ui.rss_feed.view;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eugene.shvabr.R;
import com.eugene.shvabr.databinding.RssItemBinding;
import com.eugene.shvabr.ui.rss_feed.model.RssItemForUI;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView адаптер для rss-списка
 */
public class RssAdapter extends RecyclerView.Adapter<RssAdapter.Holder> {

    private List<RssItemForUI> items;
    /**
     * если false и {@link #items} != null, показывает крутилку в конце списка (чтобы при поэлементной загрузке понимать, догружаем мы еще что-то или нет)
     */
    private boolean allItemsLoaded;

    private static final int ITEM_VIEW_TYPE_RSS = 0;
    private static final int ITEM_VIEW_TYPE_PROGRESS = 1;

    public void resetItems() {
        allItemsLoaded = false;
        this.items = null;
        notifyDataSetChanged();
    }

    public boolean hasFeed() {
        return items != null;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case ITEM_VIEW_TYPE_RSS: {
                View view = inflater.inflate(R.layout.rss_item, parent, false);
                TextView title = view.findViewById(R.id.title);
                TextView description = view.findViewById(R.id.description);
                title.setMovementMethod(LinkMovementMethod.getInstance());
                description.setMovementMethod(LinkMovementMethod.getInstance());
                return new RssItemHolder(view);
            }
            case ITEM_VIEW_TYPE_PROGRESS: {
                View view = inflater.inflate(R.layout.loading_item, parent, false);
                return new LoadingHolder(view);
            }
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        if (holder instanceof RssItemHolder) {
            RssItemForUI item = items.get(position);
            ((RssItemHolder) holder).binding.setRss(item);
        }
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size() + (allItemsLoaded ? 0 : 1);
    }

    @Override
    public int getItemViewType(int position) {
        if (allItemsLoaded) {
            return ITEM_VIEW_TYPE_RSS;
        } else {
            return position == getItemCount() - 1 ? ITEM_VIEW_TYPE_PROGRESS : ITEM_VIEW_TYPE_RSS;
        }
    }

    public void addItem(RssItemForUI item) {
        if (items == null) {
            items = new ArrayList<>();
        }
        items.add(item);
        notifyDataSetChanged();
    }

    public void setAllItemsLoaded(boolean allItemsLoaded) {
        this.allItemsLoaded = allItemsLoaded;
        notifyDataSetChanged();
    }

    static class Holder extends RecyclerView.ViewHolder {

        Holder(View itemView) {
            super(itemView);
        }
    }

    static class RssItemHolder extends Holder {

        private RssItemBinding binding = DataBindingUtil.bind(itemView);

        RssItemHolder(View itemView) {
            super(itemView);
        }
    }

    static class LoadingHolder extends Holder {

        LoadingHolder(View itemView) {
            super(itemView);
        }
    }
}
