package com.github.nickygiorgi.fooddiary;

import android.app.DialogFragment;
import android.os.Bundle;
import android.os.Environment;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ListView listview = (ListView) findViewById(R.id.history_listview);
        final Page[] history = GetHistory();
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
        int id = item.getItemId();

        if (id == R.id.action_deleteAll) {
            DialogFragment dialog = new YesNoDialog();
            DialogUtilities.FireDialog(
                    dialog,
                    getFragmentManager(),
                    "Delete Confirmation",
                    "Are you sure you want to delete all records?");
            return true;
        }

        if (id == R.id.action_archiveAll) {
            if (!archiver.canArchive() || !archiver.tryArchive())
            {
                DialogFragment dialog = new ErrorDialog();
                DialogUtilities.FireDialog(
                        dialog,
                        getFragmentManager(),
                        "Error",
                        archiver.getError()
                );
                return false;
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private Page[] GetHistory() {
        FoodDiaryDataSource ds = new FoodDiaryDataSource(this.getApplicationContext());
        ds.open();
        List<Page> pages = ds.getAllPages();
        ds.close();
        return pages.toArray(new Page[pages.size()]);
    }


    @Override
    public void onYes() {
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
        }
        else {
            DialogFragment dialog = new ErrorDialog();
            DialogUtilities.FireDialog(
                    dialog,
                    getFragmentManager(),
                    null,
                    "En error occurred while deleting all records");
        }
        this.reload();
    }

    @Override
    public void onNo() { }

    public void reload() {
        finish();
        startActivity(getIntent());
    }


}
