package com.github.nickygiorgi.fooddiary.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.github.nickygiorgi.fooddiary.R;
import com.github.nickygiorgi.fooddiary.activity.extensions.DialogListenerActivity;

public class ErrorDialog extends DialogFragment
{
    public ErrorDialog() {}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        Bundle args = getArguments();
        String message = args.getString("message", "");

        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.error_dialog_title)
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Activity parentActivity = getActivity();
                        if (parentActivity instanceof DialogListenerActivity)
                        {
                            ((DialogListenerActivity) parentActivity).onYes();
                        }
                    }
                })
                .create();
    }
}
