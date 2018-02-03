package com.eugene.shvabr.domain.model;

import java.util.Collections;
import java.util.List;

/**
 * Created by Eugene on 03.02.2018.
 */

public class RssItem {

    private final String title;

    private final String publishDate;

    private final List<String> categories;

    private final String description;

    private final String link;

    private final String creator;

    public RssItem(String title, String publishDate, List<String> categories, String description, String link, String creator) {
        this.title = title;
        this.publishDate = publishDate;
        this.categories = categories == null ? null : Collections.unmodifiableList(categories);
        this.description = description;
        this.link = link;
        this.creator = creator;
    }

    public String getTitle() {
        return title;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public List<String> getCategories() {
        return categories;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public String getCreator() {
        return creator;
    }
}
