package com.github.nickygiorgi.fooddiary;

import android.Manifest;
import android.app.DialogFragment;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.github.nickygiorgi.fooddiary.activity.extensions.DialogListenerActivity;
import com.github.nickygiorgi.fooddiary.dal.ActiveRecords.Page;
import com.github.nickygiorgi.fooddiary.dal.FoodDiaryDataSource;
import com.github.nickygiorgi.fooddiary.dialogs.ConfirmationDialog;
import com.github.nickygiorgi.fooddiary.dialogs.DialogUtilities;
import com.github.nickygiorgi.fooddiary.dialogs.ErrorDialog;
import com.github.nickygiorgi.fooddiary.dialogs.YesNoDialog;
import com.github.nickygiorgi.fooddiary.ui.adapters.PageListAdapter;

import java.util.List;

public class ListHistoryActivity extends DialogListenerActivity {

    private Archiver archiver = new Archiver();
    private int currentMenuAction = 0;
    private static final int PERMISSIONS_TO_ARCHIVE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ListView listview = (ListView) findViewById(R.id.history_listview);
        final Page[] history = this.getHistory();
        final PageListAdapter adapter = new PageListAdapter(
                this.getApplicationContext(),
                history);
        listview.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_history, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        currentMenuAction = item.getItemId();

        if (currentMenuAction == R.id.action_deleteAll) {
            DialogFragment dialog = new YesNoDialog();
            DialogUtilities.FireDialog(
                    dialog,
                    getFragmentManager(),
                    "Delete Confirmation",
                    "Are you sure you want to delete all records?");
        }

        if (currentMenuAction == R.id.action_archiveAll) {
            this.requestPermissionsToArchive();
        }

        return true;
    }

    private Page[] getHistory() {
        FoodDiaryDataSource ds = new FoodDiaryDataSource(this.getApplicationContext());
        ds.open();
        List<Page> pages = ds.getAllPages();
        ds.close();
        return pages.toArray(new Page[pages.size()]);
    }

    private void requestPermissionsToArchive() {
        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PERMISSIONS_TO_ARCHIVE
            );
        }
        else {
            final boolean writePermissionGranted = true;
            this.tryArchive(writePermissionGranted);
        }
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            String permissions[],
            int[] permissionRequestResults
    ) {
        switch (requestCode) {
            case PERMISSIONS_TO_ARCHIVE: {
                final boolean archivePermissionGranted
                        = this.archivePermissionsGranted(permissionRequestResults);
                this.tryArchive(archivePermissionGranted);

                return;
            }
        }
    }

    private void tryArchive(boolean archivePermissionGranted) {
        boolean archived = false;

        if (archivePermissionGranted) {
            if (archiver.canArchive())
            {
                if (archiver.tryArchive()) {
                    archived = true;
                }
            }
        } else {

            archiver.setError("permission to archive has been denied");
        }

        if (!archived) {
            DialogFragment dialog = new ErrorDialog();
            DialogUtilities.FireDialog(
                    dialog,
                    getFragmentManager(),
                    "Error",
                    archiver.getError());
        }
    }

    private boolean archivePermissionsGranted(int[] permissionRequestResults) {
        return permissionRequestResults.length > 0
                && this.archivePermissionsGranted(permissionRequestResults[0]);
    }

    private boolean archivePermissionsGranted(int writePermission) {
        return writePermission == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onYes() {
        if (currentMenuAction == R.id.action_deleteAll) {
            FoodDiaryDataSource ds = new FoodDiaryDataSource(this.getApplicationContext());
            ds.open();
            boolean success = ds.deleteAllPages();
            ds.close();
            if (success) {
                DialogFragment dialog = new ConfirmationDialog();
                DialogUtilities.FireDialog(
                        dialog,
                        getFragmentManager(),
                        "Success",
                        "All records successfully deleted");
            } else {
                DialogFragment dialog = new ErrorDialog();
                DialogUtilities.FireDialog(
                        dialog,
                        getFragmentManager(),
                        null,
                        "En error occurred while deleting all records");
            }
            this.reload();
        }
    }

    @Override
    public void onNo() { }

    public void reload() {
        finish();
        startActivity(getIntent());
    }
}
