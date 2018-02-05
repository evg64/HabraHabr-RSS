package com.eugene.shvabr.common;

import android.content.res.Resources;

/**
 * Helper-класс для работы с ui.
 */
public class UIHelper {
    static void initialize(Resources resources) {
        UIHelper.resources = resources;
    }

    private static Resources resources;

    public static Resources getResources() {
        return resources;
    }
}
