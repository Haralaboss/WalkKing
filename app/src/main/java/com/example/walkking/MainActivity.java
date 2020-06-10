package com.example.walkking;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements SensorEventListener {


    TextView tv_steps;
    TextView tv_kms;
    TextView tv_calories;

    ProgressBar steps_progress;

    SensorManager sensorManager;

    boolean running;

    int percentage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_steps = findViewById(R.id.tv_steps);
        tv_kms = findViewById(R.id.tv_kms);
        tv_calories = findViewById(R.id.tv_calories);
        steps_progress = findViewById(R.id.stepsProgressBar);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

    }

    @Override
    protected void onResume() {
        super.onResume();
        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        if (countSensor != null){
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);

        }
        else{
            Toast.makeText(this,"Sensor not found!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();


        running = false;
        //sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (running){
            tv_steps.setText(String.valueOf(event.values[0]));
            tv_kms.setText(String.valueOf(getKilometers(event)));
            tv_calories.setText(String.valueOf(getCalories(getKilometers(event))));

            float steps = event.values[0];
            int goal = 1000;

            if(steps > goal){
                steps_progress.setProgress(100);
            }
            else{
                percentage = (int) (steps*100/goal);

                steps_progress.setProgress(percentage);
            }



        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }



    //Method to get and round up the Kilometers.
    public double getKilometers(SensorEvent event){

        float steps = event.values[0];

        double kilometers = (double) (steps  * 0.762 / 1000);


        double roundedKilometers = Math.round(kilometers * 100.0) / 100.0;


        return roundedKilometers;
    }

    //Method to get Calories
    public int getCalories (double kilometers){
        SharedPreferences sp = getSharedPreferences("Info", Context.MODE_PRIVATE);

        String gender = sp.getString("gender","");
        int age = sp.getInt("age", 0);
        int weight = sp.getInt("weight", 0);
        int height = sp.getInt("height", 0);

        double calories = 0;
        double bmr = 0;

        if (sp.getString("gender", "").equals("Male")) {
            bmr = 5 + (10 * weight) + (6.25 * height) - (5 * age);
        } else if (sp.getString("gender", "").equals("Female")) {
            bmr = -161 + (10 * weight) + (6.25 * height) - (5 * age);
        }


        if (gender.equals("Male")){
            if(age < 60){
                calories = (bmr * 3.5 / 24) * (kilometers / 4.8);
            }
            else{
                calories = (bmr * 3 / 24) * (kilometers / 3.8);
            }
        }
        else if (gender.equals("Female")) {
            if (age < 60) {
                calories = (bmr * 3.5 / 24) * (kilometers / 4.3);
            }
            else{
                calories = (bmr * 3.0 / 24) * (kilometers / 3.5);
            }
        }

        int intCalories = (int) calories;



        return intCalories;

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