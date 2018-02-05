package com.eugene.shvabr.domain.use_case;

import android.support.annotation.Nullable;
import android.util.Log;

import com.eugene.shvabr.BuildConfig;
import com.eugene.shvabr.domain.common.BiVariantCallback;

/**
 * Работает с коллбэком {@link BiVariantCallback}
 */
public abstract class BiVariantUseCase<ResultType>
        implements AsyncUseCase<BiVariantCallback<ResultType>> {

    @Override
    public final void execute(BiVariantCallback<ResultType> callback) {
        callback = new UnsubscribingCallback(callback);
        try {
            executeInternal(callback);
        } catch (Exception e) {
            callback.onError(e);
        }
    }

    /**
     * Сюда передается {@link UnsubscribingCallback}, т.е. мы гарантируем, что клиент не получит
     * более 1 обратного вызова
     */
    protected abstract void executeInternal(BiVariantCallback<ResultType> callback);

    protected volatile boolean cancelledSubscription;

    @Override
    public synchronized void unsubscribe() {
        // придется перед каждым вызовом метода на коллбэке синхронизироваться по this и проверять флажок
        cancelledSubscription = true;
    }

    /**
     * Поскольку клиент должен получить не более 1 обратного вызова,
     * надо вручную отписываться от уведомлений клиента перед каждым вызовом коллбэка,
     * т.к. без rx мы не можем гарантировать, что onError не вызовется дважды
     * и даже то, что при вызове onSuccess не вылетит Exception, и управление не приедет в onError
     */
    private class UnsubscribingCallback implements BiVariantCallback<ResultType> {

        private final BiVariantCallback<ResultType> delegate;

        private UnsubscribingCallback(BiVariantCallback<ResultType> delegate) {
            this.delegate = delegate;
        }

        @Override
        public void onSuccess(ResultType result) {
            synchronized (BiVariantUseCase.this) {
                if (!cancelledSubscription) {
                    unsubscribe();
                    delegate.onSuccess(result);
                } else {
                    logDuplicateCall(null);
                }
            }
        }

        @Override
        public void onError(Throwable description) {
            synchronized (BiVariantUseCase.this) {
                if (!cancelledSubscription) {
                    unsubscribe();
                    delegate.onError(description);
                } else {
                    logDuplicateCall(description);
                }
            }
        }

        private void logDuplicateCall(@Nullable Throwable cause) {
            if (BuildConfig.DEBUG) {
                Log.w("Shvabr", "Попытка 2х или более обратных вызовов",
                        cause == null ? null : new Throwable(cause));
            }
        }
    }

}
