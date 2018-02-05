package com.eugene.shvabr.ui.rss_feed.model;

import android.text.Spanned;

/**
 * Модель элемента rss-фида для слоя представления.
 */
public class RssItemForUI {

    private final Spanned title;
    private final String publishDate;
    private final String categories;
    private final Spanned description;
    private final String creator;

    public RssItemForUI(Spanned title, String publishDate, String categories, Spanned description, String creator) {
        this.title = title;
        this.publishDate = publishDate;
        this.categories = categories;
        this.description = description;
        this.creator = creator;
    }

    public Spanned getTitle() {
        return title;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public String getCategories() {
        return categories;
    }

    public Spanned getDescription() {
        return description;
    }

    public String getCreator() {
        return creator;
    }

}
