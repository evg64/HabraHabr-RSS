package com.eugene.shvabr.common;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;

import com.eugene.shvabr.domain.common.BiVariantCallback;

/**
 * Helper-класс для работы с многопоточностью.
 */
public class ConcurrentUtils {
    public static boolean isUIThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    private static Handler mainThreadHandler = new Handler(Looper.getMainLooper());

    public static void runOnUIThread(@Nullable Runnable runnable) {
        if (runnable != null) {
            mainThreadHandler.post(runnable);
        }
    }

    public static void ensureRunOnUIThread(@Nullable Runnable runnable) {
        if (runnable == null) {
            return;
        }
        if (isUIThread()) {
            runnable.run();
        } else {
            runOnUIThread(runnable);
        }
    }

    /**
     * Прокидывает все вызовы в главный поток
     */
    public static class UIThreadCallback<T> implements BiVariantCallback<T> {
        private final BiVariantCallback<T> delegate;

        public UIThreadCallback(BiVariantCallback<T> delegate) {
            this.delegate = delegate;
        }

        @Override
        public void onSuccess(final T result) {
            ConcurrentUtils.ensureRunOnUIThread(new Runnable() {
                @Override
                public void run() {
                    delegate.onSuccess(result);
                }
            });
        }

        @Override
        public void onError(final Throwable description) {
            ConcurrentUtils.ensureRunOnUIThread(new Runnable() {
                @Override
                public void run() {
                    delegate.onError(description);
                }
            });
        }
    }
}
