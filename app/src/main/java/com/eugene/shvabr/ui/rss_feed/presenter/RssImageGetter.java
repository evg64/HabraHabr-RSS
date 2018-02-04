package com.eugene.shvabr.ui.rss_feed.presenter;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.text.Html;

import com.eugene.shvabr.R;
import com.eugene.shvabr.common.UIHelper;
import com.eugene.shvabr.domain.repository.ImageRepository;
import com.eugene.shvabr.domain.use_case.GetImageUseCase;

/**
 * Created by Eugene on 04.02.2018.
 */
class RssImageGetter implements Html.ImageGetter {
    private final ImageRepository repository;

    private Drawable emptyImage;

    public RssImageGetter(ImageRepository repository) {
        this.repository = repository;
    }

    @Override
    public Drawable getDrawable(String source) {
        Drawable image = new GetImageUseCase(repository).getImage(source);
        if (image == null) {
            image = getEmptyImage();
        }
        return image;
    }

    @NonNull
    private Drawable getEmptyImage() {
        if (emptyImage == null) {
            emptyImage = UIHelper.getResources().getDrawable(R.drawable.transparent);
        }
        return emptyImage;
    }
}
