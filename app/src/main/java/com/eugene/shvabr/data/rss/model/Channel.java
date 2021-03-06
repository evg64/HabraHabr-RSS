package com.eugene.shvabr.data.rss.model;

import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * Используется, чтобы отзеркалить структуру приходящего xml в модели данных.
 */
public class Channel {

    @ElementList(entry = "item", inline = true)
    private List<RssItemData> items;

    public List<RssItemData> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "itemCount=" + items.size() +
                '}';
    }

//    private String pubDate;
//
//    private String title;
//
//    private String managingEditor;
//
//    private String description;
//
//    private String link;
//
//    private String lastBuildDate;
    //    private String generator;
//
//    private Image image;
//
//    private String language;

}
