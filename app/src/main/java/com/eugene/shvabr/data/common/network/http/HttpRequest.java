package com.eugene.shvabr.data.common.network.http;

import com.eugene.shvabr.data.common.Config;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;

/**
 * Исполняемый http-запрос.
 */
abstract class HttpRequest implements Callable<InputStream> {
    URLConnection initializeConnection(final String url) throws IOException {
        URLConnection connection = new URL(url).openConnection();
        connection.setConnectTimeout(Config.TIMEOUT_CONNECT_MILLIS);
        connection.setReadTimeout(Config.TIMEOUT_READ_MILLIS);
        return connection;
    }

}
