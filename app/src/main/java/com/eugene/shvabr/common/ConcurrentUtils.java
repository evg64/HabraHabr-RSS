package com.eugene.shvabr.common;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;

import com.eugene.shvabr.domain.common.BiVariantCallback;

/**
 * Created by Eugene on 04.02.2018.
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
        private final BiVariantCallback<T> wrapped;

        public UIThreadCallback(BiVariantCallback<T> wrapped) {
            this.wrapped = wrapped;
        }

        @Override
        public void onSuccess(final T result) {
            ConcurrentUtils.ensureRunOnUIThread(new Runnable() {
                @Override
                public void run() {
                    wrapped.onSuccess(result);
                }
            });
        }

        @Override
        public void onError(final Throwable description) {
            ConcurrentUtils.ensureRunOnUIThread(new Runnable() {
                @Override
                public void run() {
                    wrapped.onError(description);
                }
            });
        }
    }
}
