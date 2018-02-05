package com.eugene.shvabr.ui.rss_feed.presenter;

import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.Spanned;

import com.eugene.shvabr.R;
import com.eugene.shvabr.common.UIHelper;
import com.eugene.shvabr.domain.model.RssItem;
import com.eugene.shvabr.domain.repository.ImageRepository;
import com.eugene.shvabr.ui.rss_feed.model.RssItemForUI;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Конвертирует доменную модель элементов rss списка в модель представления.
 */
public class RssToUIModelMapper {
    private final Resources resources = UIHelper.getResources();
    private final ImageRepository repository;

    public RssToUIModelMapper(ImageRepository repository) {
        this.repository = repository;
    }

    @Nullable
    public List<RssItemForUI> convert(@Nullable List<RssItem> what) {
        if (what == null) {
            return null;
        }
        List<RssItemForUI> result = new ArrayList<>();
        for (RssItem item : what) {
            RssItemForUI converted = convert(item);
            if (converted != null) {
                result.add(converted);
            }
        }
        return result;
    }

    private SimpleDateFormat publishDateFormatter = new SimpleDateFormat("dd.MM.yyyy, HH:mm");

    @Nullable
    public RssItemForUI convert(RssItem what) {
        if (what == null) {
            return null;
        }
        final Spanned title = convertTitle(what.getTitle(), what.getLink());
        String publishDate = publishDateFormatter.format(what.getPublishDate());
        String categories = convertCategories(what.getCategories());
        Spanned description = toHtml(what.getDescription());
        String creator = resources.getString(R.string.published_format, what.getCreator());
        return new RssItemForUI(title, publishDate, categories, description, creator);
    }

    @NonNull
    private String convertCategories(@Nullable List<String> categories) {
        if (categories == null) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (String category : categories) {
            if (builder.length() > 0) {
                builder.append(", ");
            }
            builder.append(category);
        }
        return resources.getString(R.string.categories_format, builder.toString());
    }

    private Spanned convertTitle(String title, String link) {
        return toHtml("<a href='" + link + "'>" + title + "</a>");
    }

    private Spanned toHtml(String htmlString) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            int flags = 0;
            return Html.fromHtml(htmlString, flags);
        } else {
            return Html.fromHtml(htmlString, new RssImageGetter(repository), null);
        }
    }

}
