package com.example.walkking;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity implements DialogGender.DialogGenderListener, DialogAge.DialogAgeListener, DialogHeight.DialogHeightListener, DialogWeight.DialogWeightListener, DialogGoal.DialogGoalListener {

    TextView tv_bmr;

    private Button gender_button, age_button, height_button, weight_button, goal_button;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sp = getSharedPreferences("Info", Context.MODE_PRIVATE);



        setContentView(R.layout.activity_settings);

        tv_bmr = findViewById(R.id.tv_bmr);
        tv_bmr.setText("Your BMR is " + getBMR());

        gender_button = findViewById(R.id.gender_button);
        age_button = findViewById(R.id.age_button);
        height_button = findViewById(R.id.height_button);
        weight_button = findViewById(R.id.weight_button);

        gender_button.setText(sp.getString("gender",""));

        age_button.setText(String.valueOf(sp.getInt("age", 0)));
        height_button.setText(String.valueOf(sp.getInt("height", 0)));
        weight_button.setText(String.valueOf(sp.getInt("weight", 0)));

        gender_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGenderDialog();
            }
        });

        age_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAgeDialog();
            }
        });

        height_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHeightDialog();
            }
        });

        weight_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWeightDialog();
            }
        });


        goal_button = findViewById(R.id.goal_button);
        goal_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGoalDialog();
            }
        });
    }


    public double getBMR(){

        SharedPreferences sp = getSharedPreferences("Info", Context.MODE_PRIVATE);

        double bmr = 0;

            int age = sp.getInt("age", 0);
            int weight = sp.getInt("weight", 0);
            int height = sp.getInt("height", 0);


            if (sp.getString("gender", "").equals("Male")) {
                bmr = 5 + (10 * weight) + (6.25 * height) - (5 * age);
            } else if (sp.getString("gender", "").equals("Female")) {
                bmr = -161 + (10 * weight) + (6.25 * height) - (5 * age);
            }

        return bmr;

    }



    public void openGenderDialog(){
        DialogGender dialogGender = new DialogGender();
        dialogGender.show(getSupportFragmentManager(), "gender dialog");
    }

    public void openAgeDialog(){
        DialogAge dialogAge = new DialogAge();
        dialogAge.show(getSupportFragmentManager(), "age dialog");
    }


    public void openHeightDialog(){
        DialogHeight dialogHeight = new DialogHeight();
        dialogHeight.show(getSupportFragmentManager(), "height dialog");
    }

    public void openWeightDialog(){
        DialogWeight dialogWeight = new DialogWeight();
        dialogWeight.show(getSupportFragmentManager(), "weight dialog");
    }

    public void openGoalDialog(){
        DialogGoal dialogGoal = new DialogGoal();
        dialogGoal.show(getSupportFragmentManager(), "goal dialog");

    }



    @Override
    public void applyGender(int gender) {
        String gender1 = "null";
        //Log.d("Babis", "Value: " + gender);
        if (gender == 0){
            gender_button.setText("Male");
             gender1 = "Male";
        }
        else {
            gender_button.setText("Female");
            gender1 = "Female";
        }


        saveInfo(0, 0, 0, gender1, 0);
        tv_bmr.setText("Your BMR is " + getBMR());
    }

    @Override
    public void applyAge(int ageA) {
        age_button.setText(String.valueOf(ageA));

        saveInfo(ageA, 0, 0, "null", 0);
        tv_bmr.setText("Your BMR is " + getBMR());

    }

    @Override
    public void applyHeight(int heightA) {
        height_button.setText(String.valueOf(heightA));

        saveInfo(0, 0, heightA, "null", 0);
        tv_bmr.setText("Your BMR is " + getBMR());
    }

    @Override
    public void applyWeight(int weightA) {
        weight_button.setText(String.valueOf(weightA));

        saveInfo(0, weightA, 0, "null", 0);
        tv_bmr.setText("Your BMR is " + getBMR());
    }

    @Override
    public void applyGoal(int goal) {
        saveInfo(0, 0, 0, "null", goal);


    }



    public void saveInfo(int ageS, int weightS,int heightS, String genderS, int goalS){
        SharedPreferences sp = getSharedPreferences("Info", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();

        if(ageS!=0){
            edit.putInt("age", ageS);
        }
        if(weightS!=0){
            edit.putInt("weight", weightS);
        }
        if(heightS!=0){
            edit.putInt("height", heightS);
        }
        if(!genderS.equals("null")){
            edit.putString("gender", genderS);
        }
        if(goalS!=0){
            edit.putInt("goal",goalS);
        }
        edit.apply();

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
