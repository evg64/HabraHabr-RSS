package com.eugene.shvabr.ui.common.mvp;

/**
 * Created by Eugene on 03.02.2018.
 */
public class BasePresenter<View extends MvpView> implements MvpPresenter<View> {
    protected View view;

    @Override
    public void attach(View view) {
        this.view = view;
    }

    @Override
    public void detach(View view) {
        if (this.view == view) {
            this.view = null;
        }
    }
}
