package com.eugene.shvabr.data.common.network.http;

import com.eugene.shvabr.data.common.Config;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Eugene on 05.02.2018.
 */

public class HttpGetTest {

    @Test
    public void testWorksFineForCorrectUrl() throws IOException {
        InputStream result = null;
        try {
            result = new HttpGet(Config.RSS_FEED_URL).call();
            Assert.assertNotNull(result);
        } finally {
            if (result != null) {
                result.close();
            }
        }
    }

    @Test
    public void testThrowsExceptionForIncorrectUrl() throws Exception {
        InputStream result = null;
        try {
            result = new HttpGet("Wrong url").call();
            Assert.fail("Exception should be thrown");
        } catch (Exception e) {
            System.out.println("Thrown exception for incorrect url");
        } finally {
            if (result != null) {
                result.close();
            }
        }
    }
}
