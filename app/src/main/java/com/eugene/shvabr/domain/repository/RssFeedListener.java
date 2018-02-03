package com.eugene.shvabr.domain.repository;

import com.eugene.shvabr.domain.model.RssFeed;
import com.eugene.shvabr.domain.use_case.UseCase;

/**
 * Created by Eugene on 03.02.2018.
 */

public interface RssFeedListener extends UseCase.Callback {
    void onSuccess(RssFeed feed);
    void onError(Throwable description);
}
