package com.eugene.shvabr.data.api.http;

import com.eugene.shvabr.data.Config;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;

/**
 * Created by Eugene on 03.02.2018.
 */
abstract class HttpRequest implements Callable<InputStream> {
    URLConnection initializeConnection(final String url) throws IOException {
        URLConnection connection = new URL(url).openConnection();
        connection.setConnectTimeout(Config.TIMEOUT_CONNECT_MILLIS);
        connection.setReadTimeout(Config.TIMEOUT_READ_MILLIS);
        return connection;
    }

}
