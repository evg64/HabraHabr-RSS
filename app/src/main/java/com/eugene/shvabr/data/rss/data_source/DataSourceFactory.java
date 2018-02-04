package com.eugene.shvabr.data.rss.data_source;

/**
 * Нужна, чтобы скрыть реализации и логику создания {@link RssFeedDataSource} от клиента (репозитория).
 */
public class DataSourceFactory {
    public static RssFeedDataSource createDefaultDataSource() {
        RssFeedDataSource onions = new RssFeedNetworkDataSource();
        onions = new RssFeedCachingDataSource(onions);
        onions = new SingleTaskDataSource(onions);
        return onions;
    }
}
