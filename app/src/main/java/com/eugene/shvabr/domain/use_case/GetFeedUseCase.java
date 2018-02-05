package com.eugene.shvabr.domain.use_case;

import com.eugene.shvabr.domain.common.BiVariantCallback;
import com.eugene.shvabr.domain.model.RssFeed;
import com.eugene.shvabr.domain.repository.RssRepository;

/**
 * Интерактор для получения rss-фида.
 */
public class GetFeedUseCase extends BiVariantUseCase<RssFeed> {

    private final RssRepository repository;

    /**
     * @param repository - поскольку нельзя светить реализации репозитория на уровне use-case, а
     *                   возможности использовать di нет, я решил передавать эти реализации из классов-клиентов.
     */
    public GetFeedUseCase(RssRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeInternal(final BiVariantCallback<RssFeed> callback) {
        repository.getFeed(callback);
    }
}
