package com.eugene.shvabr.common;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;

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
}
