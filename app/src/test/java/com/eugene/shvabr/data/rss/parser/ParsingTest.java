package com.eugene.shvabr.data.rss.parser;

import com.eugene.shvabr.data.rss.model.Guid;
import com.eugene.shvabr.data.rss.model.RssFeedData;
import com.eugene.shvabr.data.rss.model.RssItemData;

import org.junit.Test;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.InputStream;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ParsingTest {

    private final Serializer serializer = new Persister();

    private InputStream inputStreamFor(String resource) {
        return getClass().getResourceAsStream(resource);
    }

    private <T> T tryParse(Class<T> clazz, String resource) throws Exception {
        return serializer.read(clazz, inputStreamFor(resource), false);
    }

    private <T> void tryParseAndPrint(Class<T> clazz, String resource) throws Exception {
        T result = tryParse(clazz, resource);
        System.out.println(String.format("Parsed %s instance: %s", clazz.getSimpleName(), result.toString()));
    }

    @Test
    public void testGuidParsing() throws Exception {
        tryParseAndPrint(Guid.class, "guid.xml");
    }

    @Test
    public void testRssItemDataParsing() throws Exception {
        tryParseAndPrint(RssItemData.class, "rss_item.xml");
    }

    @Test
    public void testRssFeedDataParsing() throws Exception {
        tryParseAndPrint(RssFeedData.class, "rss_feed.xml");
    }
}