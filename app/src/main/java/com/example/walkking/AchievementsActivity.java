package com.example.walkking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;


/* Den ylopoiithike akomi */
public class AchievementsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);
    }





    //MENU
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.homepage:
                Intent intent1 = new Intent (this, MainActivity.class);
                startActivity(intent1);
                return true;
            case R.id.statspage:
                Intent intent2 = new Intent (this, StatisticsActivity.class);
                startActivity(intent2);
                return true;
            case R.id.settings:
                Intent intent3 = new Intent (this, SettingsActivity.class);
                startActivity(intent3);
                return true;
            case R.id.achievpage:
                Intent intent4 = new Intent (this, AchievementsActivity.class);
                startActivity(intent4);
        }
        return true;
    }
}
