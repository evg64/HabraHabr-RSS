package com.eugene.shvabr.data.image.parser;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.eugene.shvabr.data.common.network.parser.Parser;

import java.io.InputStream;

/**
 * Created by Eugene on 04.02.2018.
 */

public class ImageDecoder implements Parser<Bitmap> {

    @Override
    public Bitmap parse(InputStream src) throws Exception {
        return BitmapFactory.decodeStream(src, null, null);
    }
}
