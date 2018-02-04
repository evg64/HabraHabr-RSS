package com.eugene.shvabr.ui.common;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
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

    public interface Retryable {
        void retry();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String message = getArguments().getString(Extras.MESSAGE);
        return new AlertDialog.Builder(getContext())
                .setTitle(R.string.error)
                .setMessage(message)
                .setPositiveButton(R.string.reload, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Fragment parent = getParentFragment();
                        if (parent != null && parent instanceof Retryable) {
                            ((Retryable) parent).retry();
                        }
                    }
                })
                .create();
    }
}
