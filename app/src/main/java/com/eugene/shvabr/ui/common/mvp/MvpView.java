package com.eugene.shvabr.ui.common.mvp;

/**
 * Created by Eugene on 03.02.2018.
 */

public interface MvpView {
    void showLoading();
    void hideLoading();
    void showError(String message);
    void showError(int messageResId, Object... formatArgs);
}
