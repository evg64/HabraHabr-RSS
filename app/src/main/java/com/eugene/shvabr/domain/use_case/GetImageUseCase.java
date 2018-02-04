package com.eugene.shvabr.domain.use_case;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;

import com.eugene.shvabr.domain.Config;
import com.eugene.shvabr.domain.repository.ImageRepository;

/**
 * Created by Eugene on 04.02.2018.
 */
public class GetImageUseCase implements UseCase {
    private final ImageRepository repository;

    public GetImageUseCase(ImageRepository repository) {
        this.repository = repository;
    }

    @Nullable
    public Drawable getImage(String address) {
        if (Config.LOAD_IMAGES) {
            try {
                Bitmap image = repository.getImage(address);
                final Drawable result;
                if (image == null) {
                    result = null;
                } else {
                    result = new BitmapDrawable(image);
                    result.setBounds(0, 0, result.getIntrinsicWidth(), result.getIntrinsicHeight());
                }
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }
}
