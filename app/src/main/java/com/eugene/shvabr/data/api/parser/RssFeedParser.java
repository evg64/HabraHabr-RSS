package com.eugene.shvabr.data.api.parser;

import com.eugene.shvabr.data.model.RssFeedData;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.InputStream;

/**
 * Created by Eugene on 03.02.2018.
 */

public class RssFeedParser implements Parser<RssFeedData> {
    private final Serializer serializer = new Persister();

    @Override
    public RssFeedData parse(InputStream src) throws Exception {
        return serializer.read(RssFeedData.class, src, false);
    }
}
