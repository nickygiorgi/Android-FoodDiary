package com.github.nickygiorgi.fooddiary;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.github.nickygiorgi.fooddiary.dal.ActiveRecords.Page;
import com.github.nickygiorgi.fooddiary.dal.FoodDiaryDataSource;
import com.github.nickygiorgi.fooddiary.ui.adapters.PageListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListHistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ListView listview = (ListView) findViewById(R.id.listview);
        final Page[] history = GetHistory();
        final PageListAdapter adapter = new PageListAdapter(
                this.getApplicationContext(),
                history);
        listview.setAdapter(adapter);
    }

    private Page[] GetHistory() {
        ArrayList<String> records = new ArrayList<>();
        FoodDiaryDataSource ds = new FoodDiaryDataSource(this.getApplicationContext());
        ds.open();
        List<Page> pages = ds.getAllPages();
        ds.close();
        return pages.toArray(new Page[pages.size()]);
    }


}
