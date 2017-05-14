package com.github.nickygiorgi.fooddiary;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS), "FoodDiary_archive");

        if (!file.exists()) {
            boolean newDirCreated = file.mkdirs();
            if (!newDirCreated) {
                throw new Exception("Could not create directory for archive");
            }
        }

        return file;
    }

    private void tryWriteToFile(File archiveDir) throws IOException {
        File file = new File(archiveDir.getPath() + File.separator + getCurrentTimeStamp());
        file.createNewFile();
        FileWriter writer = new FileWriter(file);
        //TODO archive real data
        writer.write("Food diary data archive");
        writer.flush();
        writer.close();
    }

    // TODO move this to utilities
    public String getCurrentTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
    }
}
