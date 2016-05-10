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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListHistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_history);

        final ListView listview = (ListView) findViewById(R.id.listview);

        final ArrayList<String> list = GetFormattedList();
        final StableArrayAdapter adapter = new StableArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                list);
        listview.setAdapter(adapter);
    }

    private ArrayList<String> GetFormattedList() {
        ArrayList<String> records = new ArrayList<>();
        FoodDiaryDataSource ds = new FoodDiaryDataSource(this.getApplicationContext());
        ds.open();
        List<Page> pages = ds.getAllPages();
        ds.close();
        for (Page page : pages) {
            records.add(page.getDate().toString() + " - " + page.getFood() + " - " + page.getFeeling_id());
        }
        return records;
    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }


}
