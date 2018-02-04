package com.eugene.shvabr.data.rss.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;

import java.util.List;

/**
 * Created by Eugene on 03.02.2018.
 */
public class RssItemData {
    @Element
    private Guid guid;

    @Element
    private String pubDate;

    @Element(data = true)
    private String title;

    @ElementList(entry = "category", inline = true)
    private List<String> category;

    @Element
    private String description;

    @Element
    private String link;

    @Element
    @Namespace(reference="http://purl.org/dc/elements/1.1/", prefix="dc")
    private String creator;

    public String getCreator() {
        return creator;
    }

    public Guid getGuid() {
        return guid;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    @Override
    public String toString() {
        return "RssItemData [ pubDate = " + pubDate + ", title = " + title + "]";
    }
}
