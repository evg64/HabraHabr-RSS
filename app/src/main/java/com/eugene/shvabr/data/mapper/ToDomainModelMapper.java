package com.eugene.shvabr.data.mapper;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.eugene.shvabr.data.model.Channel;
import com.eugene.shvabr.data.model.RssFeedData;
import com.eugene.shvabr.data.model.RssItemData;
import com.eugene.shvabr.domain.model.RssFeed;
import com.eugene.shvabr.domain.model.RssItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Eugene on 03.02.2018.
 */

public class ToDomainModelMapper {

    @Nullable
    public RssFeed convert(@Nullable RssFeedData what) {
        if (what == null) {
            return null;
        }
        Channel channel = what.getChannel();
        if (channel == null) {
            return null;
        }
        List<RssItem> converted = convert(channel.getItems());
        if (converted == null) {
            return null;
        }
        return new RssFeed(converted);
    }

    @Nullable
    private List<RssItem> convert(Collection<RssItemData> items) {
        if (items == null) {
            return null;
        }
        List<RssItem> result = new ArrayList<>();
        for (RssItemData item : items) {
            result.add(convert(item));
        }
        return result;
    }

    @NonNull
    private RssItem convert(RssItemData what) {
        return new RssItem(
                what.getTitle(),
                what.getPubDate(),
                what.getCategory(),
                what.getDescription(),
                what.getLink(),
                what.getCreator()
        );
    }
}
