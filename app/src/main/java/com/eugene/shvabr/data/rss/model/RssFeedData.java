package com.eugene.shvabr.data.rss.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Модель в data-слое нужна, главным образом, для того, чтобы не засорять доменную модель аннотациями от SimpleXML.<br>
 * + там по структуре небольшие различия.
 */
@Root
public class RssFeedData {
    @Element
    private Channel channel;

    @Attribute
    private String version;

    public Channel getChannel() {
        return channel;
    }

    public String getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return "RssFeedData{" +
                "channel=" + channel +
                ", version='" + version + '\'' +
                '}';
    }
}
