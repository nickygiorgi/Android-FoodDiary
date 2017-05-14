package com.github.nickygiorgi.fooddiary;

import android.os.Environment;

public class Archiver {

    private String error;

    public String getError() {
        return this.error;
    }

    public boolean canArchive() {
        if (!isExternalStorageWritable()) {
            this.error = "External storage is not available at this time - data NOT archived";
            return false;
        }

        return true;
    }

    public boolean tryArchive() { return true; }

    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
}
