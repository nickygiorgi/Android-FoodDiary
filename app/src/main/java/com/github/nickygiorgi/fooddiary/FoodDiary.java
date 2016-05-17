package com.github.nickygiorgi.fooddiary;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.nickygiorgi.fooddiary.dal.ActiveRecords.Food;
import com.github.nickygiorgi.fooddiary.dal.ActiveRecords.Page;
import com.github.nickygiorgi.fooddiary.dal.FoodDiaryDataSource;
import com.github.nickygiorgi.fooddiary.dal.StaticData.Feeling;
import com.github.nickygiorgi.fooddiary.dal.StaticData.Feelings;

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
        getMenuInflater().inflate(R.menu.menu_food_diary, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_listHistory) {
            Intent listHistory = new Intent(this, ListHistoryActivity.class);
            startActivity(listHistory);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void chooseFeeling(View view) {
        Intent feelingChoice = new Intent(this, FeelingChoiceActivity.class);
        startActivityForResult(feelingChoice, FEELING_CHOICE);
    }

    public void savePage(View view) {
        FoodDiaryDataSource ds = new FoodDiaryDataSource(this.getApplicationContext());
        ds.open();
        final EditText foodEditText = (EditText) findViewById(R.id.foodEditText);
        Food todayFood = ds.insertXFood(foodEditText.getText().toString());
        Page page = ds.insertPage(todayFood.getId(), todayFeeling.Id);
        ds.close();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode == RESULT_OK && requestCode == FEELING_CHOICE) {
            if (data.hasExtra("feeling")) {
                int choosenFeelingId = data.getExtras().getInt("feeling");
                adjustToFeeling(choosenFeelingId);
            }
        }
    }

    public void adjustToFeeling(int feelingId) {
        todayFeeling = Feelings.MapById(feelingId);
        Button feelingChoiceBtn = (Button) findViewById(R.id.feelingsChoiceBtn);
        feelingChoiceBtn.setText(todayFeeling.descriptionAsStaticResource);
        feelingChoiceBtn.setTextColor(getColorFromResourceId(todayFeeling.colorAsStaticResource));
    }

    public int getColorFromResourceId(int resourceId) {
        return ContextCompat.getColor(this.getApplicationContext(), todayFeeling.colorAsStaticResource);
    }
}
