package com.eugene.shvabr.common;

import android.content.res.Resources;

/**
 * Created by Eugene on 04.02.2018.
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
