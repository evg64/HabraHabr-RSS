# HabraHabr-RSS
Rss-feed for habrahabr.ru. Written in pure java and android sdk/support library with no 3rd party libraries but Simple XML.

Contains 2 branches:
1) master: the main one,
2) with_images_experimental: experimental branch where images (img tags inside description html) are displayed. This adds lags to scrolling so this branch requires further development. As possible solutions we may try to stub description during scrolling or even try to use webview instead of textview (the former should be slower, however, that is not for sure yet).
