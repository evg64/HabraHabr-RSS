package com.eugene.shvabr.domain.use_case;

import com.eugene.shvabr.domain.repository.RssFeedListener;
import com.eugene.shvabr.domain.repository.RssRepository;

/**
 * Created by Eugene on 03.02.2018.
 */

public class GetFeedUseCase implements UseCase<RssFeedListener> {

    private final RssRepository repository;

    /**
     * @param repository - поскольку нельзя светить реализации репозитория на уровне use-case, а
     *                   возможности использовать di нет, я решил передавать эти реализации из классов-клиентов.
     */
    public GetFeedUseCase(RssRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(RssFeedListener callback) {

    }
}
