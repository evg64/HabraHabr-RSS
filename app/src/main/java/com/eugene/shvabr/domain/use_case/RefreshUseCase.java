package com.eugene.shvabr.domain.use_case;

import com.eugene.shvabr.domain.common.BiVariantCallback;
import com.eugene.shvabr.domain.model.RssFeed;
import com.eugene.shvabr.domain.repository.ImageRepository;
import com.eugene.shvabr.domain.repository.RssRepository;

/**
 * Реализует функционал pull-to-refresh.
 */
public class RefreshUseCase extends BiVariantUseCase<RssFeed> {
    private final RssRepository rssRepository;
    private final ImageRepository imageRepository;

    public RefreshUseCase(RssRepository rssRepository, ImageRepository imageRepository) {
        this.rssRepository = rssRepository;
        this.imageRepository = imageRepository;
    }

    @Override
    protected void executeInternal(BiVariantCallback<RssFeed> callback) {
        rssRepository.reset();
        imageRepository.reset();
        rssRepository.getFeed(callback);
    }
}
