package com.eugene.shvabr.data.data_source;

/**
 * Нужна, чтобы скрыть реализации и логику создания {@link RssFeedDataSource} от клиента (репозитория).
 */
public class DataSourceFactory {
    public static RssFeedDataSource createDefaultDataSource() {
        return new RssFeedDataSourceCachingWrapper(new RssFeedWebApiDataSource());
    }
}
