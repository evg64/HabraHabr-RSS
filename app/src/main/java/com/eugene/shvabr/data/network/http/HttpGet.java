package com.eugene.shvabr.data.network.http;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Eugene on 03.02.2018.
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
