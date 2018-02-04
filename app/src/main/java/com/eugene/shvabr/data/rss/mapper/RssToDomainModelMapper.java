package com.eugene.shvabr.data.rss.mapper;

import android.support.annotation.Nullable;

import com.eugene.shvabr.data.rss.model.Channel;
import com.eugene.shvabr.data.rss.model.RssFeedData;
import com.eugene.shvabr.data.rss.model.RssItemData;
import com.eugene.shvabr.domain.model.RssFeed;
import com.eugene.shvabr.domain.model.RssItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

/**
 * Created by Eugene on 03.02.2018.
 */

public class RssToDomainModelMapper {

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
            RssItem converted = convert(item);
            if (converted != null) {
                result.add(converted);
            }
        }
        return result;
    }

    private final SimpleDateFormat dateFormat =
            new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.UK);

    @Nullable
    private RssItem convert(RssItemData what) {
        try {
            return new RssItem(
                    what.getTitle(),
                    dateFormat.parse(what.getPubDate()),
                    what.getCategory(),
                    what.getDescription(),
                    what.getLink(),
                    what.getCreator()
            );
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
