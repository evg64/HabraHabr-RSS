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

import java.util.List;

/**
 * RecyclerView адаптер для rss-списка
 */
public class RssAdapter extends RecyclerView.Adapter<RssAdapter.RssItemHolder> {

    private List<RssItemForUI> items;

    public void setItems(List<RssItemForUI> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public boolean hasFeed() {
        return items != null;
    }

    @Override
    public RssItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rss_item, parent, false);
        TextView title = view.findViewById(R.id.title);
        TextView description = view.findViewById(R.id.description);
        title.setMovementMethod(LinkMovementMethod.getInstance());
        description.setMovementMethod(LinkMovementMethod.getInstance());
        return new RssItemHolder(view);
    }

    @Override
    public void onBindViewHolder(RssItemHolder holder, int position) {
        RssItemForUI item = items.get(position);
        holder.binding.setRss(item);
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    static class RssItemHolder extends RecyclerView.ViewHolder {

        private RssItemBinding binding = DataBindingUtil.bind(itemView);

        public RssItemHolder(View itemView) {
            super(itemView);
        }
    }
}
