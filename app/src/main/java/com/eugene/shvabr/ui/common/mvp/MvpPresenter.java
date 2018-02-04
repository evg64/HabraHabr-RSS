package com.eugene.shvabr.ui.common.mvp;

/**
 * Created by Eugene on 03.02.2018.
 */

public interface MvpPresenter<View extends MvpView> {
    void attach(View view);
    void detach(View view);
}
