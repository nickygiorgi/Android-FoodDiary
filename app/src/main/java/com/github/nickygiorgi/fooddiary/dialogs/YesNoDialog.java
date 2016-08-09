package com.github.nickygiorgi.fooddiary.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.github.nickygiorgi.fooddiary.activity.extensions.DialogListenerActivity;

public class YesNoDialog extends DialogFragment
{
    public YesNoDialog() {}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        Bundle args = getArguments();
        String title = args.getString("title", "");
        String message = args.getString("message", "");

        return new AlertDialog.Builder(getActivity())
                .setTitle(title)
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
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Activity parentActivity = getActivity();
                        if (parentActivity instanceof DialogListenerActivity)
                        {
                            ((DialogListenerActivity) parentActivity).onNo();
                        }
                    }
                })
                .create();
    }
}