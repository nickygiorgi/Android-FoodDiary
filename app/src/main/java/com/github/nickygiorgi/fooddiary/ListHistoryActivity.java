package com.github.nickygiorgi.fooddiary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.github.nickygiorgi.fooddiary.dal.ActiveRecords.Page;
import com.github.nickygiorgi.fooddiary.dal.FoodDiaryDataSource;
import com.github.nickygiorgi.fooddiary.ui.adapters.PageListAdapter;

import java.util.List;

public class ListHistoryActivity extends AppCompatActivity {

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


}
