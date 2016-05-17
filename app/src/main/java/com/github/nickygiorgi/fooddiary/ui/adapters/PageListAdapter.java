package com.github.nickygiorgi.fooddiary.ui.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.github.nickygiorgi.fooddiary.R;
import com.github.nickygiorgi.fooddiary.dal.ActiveRecords.Page;
import com.github.nickygiorgi.fooddiary.dal.StaticData.Feeling;

public class PageListAdapter extends ArrayAdapter<Page> {
    private final Context context;
    private final Page[] pages;

    public PageListAdapter(Context context, Page[] pages) {
        super(context, -1, pages);
        this.context = context;
        this.pages = pages;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_list_history, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.listHistoryRecordText);
        Page currentRecord = this.pages[position];
        Feeling currentRecordFeeling = currentRecord.getFeeling();
        textView.setText(currentRecord.toString());
        textView.setTextColor(ContextCompat.getColor(this.context, currentRecordFeeling.colorAsStaticResource));
        return rowView;
    }
}
