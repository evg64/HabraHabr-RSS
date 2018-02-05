package com.eugene.shvabr.ui.rss_feed.presenter;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.text.Html;

import com.eugene.shvabr.R;
import com.eugene.shvabr.common.UIHelper;
import com.eugene.shvabr.domain.repository.ImageRepository;
import com.eugene.shvabr.domain.use_case.GetImageUseCase;

/**
 * Отвечает за то, чтобы вернуть картинку в класс {@link Html} при конвертации html с описанием поста из строкового представления в {@link android.text.Spannable}.
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
