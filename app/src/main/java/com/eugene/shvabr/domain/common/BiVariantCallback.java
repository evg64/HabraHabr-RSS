package com.eugene.shvabr.domain.common;

import com.eugene.shvabr.domain.use_case.UseCase;

/**
 * Коллбэк для use-case, состоящий из 2 методов: 1 для успеха и 1 для ошибки.
 */
public interface BiVariantCallback<T> extends UseCase.Callback {
    void onSuccess(T result);

    void onError(Throwable description);
}
