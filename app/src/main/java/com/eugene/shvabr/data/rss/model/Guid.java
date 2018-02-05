package com.eugene.shvabr.data.rss.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Text;

/**
 * Используется, чтобы отзеркалить структуру приходящего xml в модели данных.
 */
public class Guid {
    @Text
    private String content;

    @Attribute(name = "isPermaLink", required = false)
    private String isPermaLink;

    public String getContent() {
        return content;
    }

    public String getIsPermaLink() {
        return isPermaLink;
    }

    @Override
    public String toString() {
        return "Guid [content = " + content + ", isPermaLink = " + isPermaLink + "]";
    }
}
