package com.github.nickygiorgi.fooddiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.nickygiorgi.fooddiary.dal.StaticData.Feeling;
import com.github.nickygiorgi.fooddiary.dal.StaticData.Feelings;
import com.github.nickygiorgi.fooddiary.db.contract;

public class FeelingChoiceActivity extends AppCompatActivity {

    private Feeling todayFeeling = Feelings.BAD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeling_choice);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void setToGood(View view) {
        todayFeeling = Feelings.GOOD;
        finish();
    }

    public void setToOK(View view) {
        todayFeeling = Feelings.OK;
        finish();
    }

    public void setToBad(View view) {
        todayFeeling = Feelings.BAD;
        finish();
    }

    @Override
    public void finish() {
        Intent data = new Intent();
        data.putExtra("feeling", todayFeeling.Id);
        setResult(RESULT_OK, data);
        super.finish();
    }

}
