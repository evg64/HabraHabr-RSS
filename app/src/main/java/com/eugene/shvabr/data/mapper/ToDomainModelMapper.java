package com.eugene.shvabr.data.mapper;

import android.support.annotation.Nullable;

import com.eugene.shvabr.data.model.RssFeedData;
import com.eugene.shvabr.domain.model.RssFeed;

/**
 * Created by Eugene on 03.02.2018.
 */

public class ToDomainModelMapper {

    @Nullable
    public RssFeed convert(@Nullable RssFeedData from) {
        if (from == null) {
            return null;
        }
        // TODO implement
        return null;
    }
}
