package com.github.nickygiorgi.fooddiary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.NumberPicker;

public class LooActivity extends AppCompatActivity {

    public int looEpisodes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loo);

        NumberPicker times = (NumberPicker) findViewById(R.id.looTimes);
        times.setMinValue(0);
        times.setMaxValue(100);

        times.setWrapSelectorWheel(false);

        times.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                looEpisodes = newVal;
            }
        });
    }
}
