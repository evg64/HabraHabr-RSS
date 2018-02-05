package com.eugene.shvabr.data.rss.mapper;

import com.eugene.shvabr.data.rss.model.Channel;
import com.eugene.shvabr.data.rss.model.RssFeedData;
import com.eugene.shvabr.data.rss.model.RssItemData;
import com.eugene.shvabr.domain.model.RssFeed;
import com.eugene.shvabr.domain.model.RssItem;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Eugene on 05.02.2018.
 */

public class MapperTest {
    private RssFeedData stub;
    private String description = "С 1 марта 2018 года <a href=\"https://cabforum.org/\">Certification Authority/Browser Forum (CA/B Forum)</a> вводит новые требования для всех Удостоверяющих Центров:";

    private final String title = "[Перевод] Leakpocalypse: Rust может неприятно удивить";
    private final String date = "Mon, 05 Feb 2018 15:51:27 GMT";
    private final List<String> categories = Arrays.asList("c1", "c2", "c3", "c4");
    private final String link = "https://habrahabr.ru/post/348346/?utm_campaign=348346";
    private String creator = "GlobalSign";

    @Before
    public void init() throws NoSuchFieldException, IllegalAccessException {
        RssItemData item = new RssItemData();
        Field fTitle = item.getClass().getDeclaredField("title");
        Field fPubDate = item.getClass().getDeclaredField("pubDate");
        Field fCategory = item.getClass().getDeclaredField("category");
        Field fDescription = item.getClass().getDeclaredField("description");
        Field fLink = item.getClass().getDeclaredField("link");
        Field fCreator = item.getClass().getDeclaredField("creator");

        fTitle.setAccessible(true);
        fPubDate.setAccessible(true);
        fCategory.setAccessible(true);
        fDescription.setAccessible(true);
        fLink.setAccessible(true);
        fCreator.setAccessible(true);

        fTitle.set(item, title);
        fPubDate.set(item, date);
        fCategory.set(item, categories);
        fDescription.set(item, description);
        fLink.set(item, link);
        fCreator.set(item, creator);

        // list
        List<RssItemData> list = new ArrayList<>();
        list.add(item);

        // channel
        Channel channel = new Channel();
        Field fieldItems = channel.getClass().getDeclaredField("items");
        fieldItems.setAccessible(true);
        fieldItems.set(channel, list);

        // feed
        stub = new RssFeedData();
        Field fieldChannel = stub.getClass().getDeclaredField("channel");
        fieldChannel.setAccessible(true);
        fieldChannel.set(stub, channel);
    }

    @Test
    public void testThatMappingIsDoneCorrectrly() {
        RssToDomainModelMapper mapper = new RssToDomainModelMapper();
        RssFeed domainModel = mapper.convert(stub);
        Assert.assertNotNull(domainModel);
        List<RssItem> items = domainModel.getItems();
        Assert.assertNotNull(items);
        Assert.assertTrue(items.size() == 1);
        RssItem item = items.get(0);
        Assert.assertTrue(item.getTitle().equals(title));
        Assert.assertTrue(item.getPublishDate().getTime() == 1517845887000L);
        Assert.assertTrue(item.getCategories().equals(categories));
        Assert.assertTrue(item.getDescription().equals(description));
        Assert.assertTrue(item.getLink().equals(link));
        Assert.assertTrue(item.getCreator().equals(creator));
    }
}
