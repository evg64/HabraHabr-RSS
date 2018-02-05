package com.eugene.shvabr.data.common.network.http;

import java.io.IOException;
import java.io.InputStream;

/**
 * Исполняемый http get-запрос.
 */
public class HttpGet extends HttpRequest {
    private final String url;

    public HttpGet(String url) {
        this.url = url;
    }

    @Override
    public InputStream call() throws IOException {
        return initializeConnection(url).getInputStream();
    }
}
