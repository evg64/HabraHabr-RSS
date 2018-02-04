package com.eugene.shvabr.ui.common;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.eugene.shvabr.R;

/**
 * Created by Eugene on 03.02.2018.
 */

public class ErrorDialogFragment extends DialogFragment {
    public static ErrorDialogFragment create(String message) {
        ErrorDialogFragment result = new ErrorDialogFragment();
        Bundle args = new Bundle();
        args.putString(Extras.MESSAGE, message);
        result.setArguments(args);
        return result;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String message = getArguments().getString(Extras.MESSAGE);
        return new AlertDialog.Builder(getContext()).setTitle(R.string.error)
                                                    .setMessage(message)
                                                    .create();
    }
}
