package com.github.nickygiorgi.fooddiary;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Archiver {

    private String error;

    public String getError() {
        return this.error;
    }

    public void setError(String error) { this.error = error; }

    public boolean canArchive() {
        if (!isExternalStorageWritable()) {
            this.error = "External storage is not available at this time - data NOT archived";
            return false;
        }

        return true;
    }

    public boolean tryArchive() {

        try {
            File archiveDir = getArchiveStorageDir();
            tryWriteToFile(archiveDir);
        }
        catch (Exception ex) {
            this.error = ex.getMessage();
            return false;
        }

        return true;
    }

    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    private File getArchiveStorageDir() throws Exception {
        return Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
    }

    private void tryWriteToFile(File archiveDir) throws IOException {

        String content = "food diary data archive";
        File file;
        FileOutputStream outputStream;
        file = new File(archiveDir, "FoodDiaryArchive_" + getCurrentTimeStamp() + ".txt");

        outputStream = new FileOutputStream(file);
        outputStream.write(content.getBytes());
        outputStream.close();
    }

    // TODO move this to utilities
    public String getCurrentTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
    }
}
