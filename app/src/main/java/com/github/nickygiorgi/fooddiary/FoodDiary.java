package com.github.nickygiorgi.fooddiary;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.github.nickygiorgi.fooddiary.dal.StaticData.Feeling;
import com.github.nickygiorgi.fooddiary.dal.StaticData.Feelings;
import com.github.nickygiorgi.fooddiary.db.contract;

public class FoodDiary extends AppCompatActivity {

    public final int FEELING_CHOICE = 1;

    private Feeling todayFeeling = Feelings.BAD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_diary);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_food_diary, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void chooseFeeling(View view) {
        Intent feelingChoice = new Intent(this, FeelingChoiceActivity.class);
        startActivityForResult(feelingChoice, FEELING_CHOICE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode == RESULT_OK && requestCode == FEELING_CHOICE) {
            if (data.hasExtra("feeling")) {
                int choosenFeelingId = data.getExtras().getInt("feeling");
                todayFeeling = Feelings.MapById(choosenFeelingId);
                Button feelingChoiceBtn = (Button) findViewById(R.id.feelingsChoiceBtn);
                feelingChoiceBtn.setText("and it felt " + todayFeeling.Desc);
            }
        }
    }
}
