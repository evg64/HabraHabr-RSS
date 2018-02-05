package com.eugene.shvabr.ui.common.mvp;

import android.content.res.Resources;
import android.support.v4.app.Fragment;

import com.eugene.shvabr.ui.common.ErrorDialogFragment;

/**
 * Created by Eugene on 03.02.2018.
 */
public abstract class BaseMvpFragment extends Fragment implements MvpView {

    @Override
    public void showError(String message) {
        ErrorDialogFragment.create(message)
                           .show(getChildFragmentManager(), String.valueOf(message.hashCode()));
    }

    @Override
    public void showError(int messageResId, Object... formatArgs) {
        Resources resources = getResources();
        if (resources != null) {
            showError(resources.getString(messageResId, formatArgs));
        }
    }
}
