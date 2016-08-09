package com.github.nickygiorgi.fooddiary.dialogs;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;

public final class DialogUtilities {

    public static final void FireDialog(DialogFragment dialog, FragmentManager manager, String title, String message) {
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("message", message);
        dialog.setArguments(args);
        dialog.show(manager, "tag");
    }

}
