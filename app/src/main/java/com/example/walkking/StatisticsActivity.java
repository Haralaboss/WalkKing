package com.example.walkking;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.Calendar;

public class StatisticsActivity extends AppCompatActivity {

    LineChart statistics_chart;
    int day;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_statistics);

        statistics_chart = findViewById(R.id.statistics_chart);

        LineDataSet lineDataSet = new LineDataSet(statistics(), "Statistics for the past week");
        lineDataSet.setLineWidth(3);
        lineDataSet.setColor(Color.CYAN);

        LineDataSet lineDataGoalSet = new LineDataSet(goalLine(), "Goal");
        lineDataSet.setLineWidth(3);
        float[] orange = {37.55f, 64.76f, 89.02f};
        lineDataGoalSet.setColor(Color.HSVToColor(orange));


        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);
        dataSets.add(lineDataGoalSet);

        LineData data = new LineData(dataSets);

        statistics_chart.getDescription().setText("");


        XAxis xAxis = statistics_chart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(getWeek()));


        statistics_chart.setData(data);
        statistics_chart.getXAxis().setGridLineWidth(2);
        statistics_chart.invalidate();

    }
    //Array for daily steps
    private ArrayList<Entry> statistics() {

        SharedPreferences sp = getSharedPreferences("Info" , Context.MODE_PRIVATE);

        ArrayList<Entry> stats = new ArrayList<Entry>();
        stats.add(new Entry(0,sp.getInt("d0", 0)));
        stats.add(new Entry(1,sp.getInt("d1", 0)));
        stats.add(new Entry(2,sp.getInt("d2", 0)));
        stats.add(new Entry(3,sp.getInt("d3", 0)));
        stats.add(new Entry(4,sp.getInt("d4", 0)));
        stats.add(new Entry(5,sp.getInt("d5", 0)));
        stats.add(new Entry(6,sp.getInt("d6", 0)));

        return stats;

    }

    //Array for user's goal
    private ArrayList<Entry> goalLine() {

        SharedPreferences sp = getSharedPreferences("Info" , Context.MODE_PRIVATE);

        ArrayList<Entry> stats = new ArrayList<Entry>();
        stats.add(new Entry(0,sp.getInt("goal", 0)));
        stats.add(new Entry(1,sp.getInt("goal", 0)));
        stats.add(new Entry(2,sp.getInt("goal", 0)));
        stats.add(new Entry(3,sp.getInt("goal", 0)));
        stats.add(new Entry(4,sp.getInt("goal", 0)));
        stats.add(new Entry(5,sp.getInt("goal", 0)));
        stats.add(new Entry(6,sp.getInt("goal", 0)));

        return stats;

    }

    //Change the days of the week depending on what day it is
    public String[] getWeek(){

        String[] weekdays = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

        Calendar cal = Calendar.getInstance();

        day = cal.get(Calendar.DAY_OF_WEEK);

        switch (day){
            case Calendar.SUNDAY:
                weekdays = new String[] {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
                break;
            case Calendar.MONDAY:
                weekdays = new String[] {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
                break;
            case Calendar.TUESDAY:
                weekdays = new String[] {"Tue", "Wed", "Thu", "Fri", "Sat", "Sun", "Mon"};
                break;
            case Calendar.WEDNESDAY:
                weekdays = new String[] {"Wed", "Thu", "Fri", "Sat", "Sun", "Mon", "Tue"};
                break;
            case Calendar.THURSDAY:
                weekdays = new String[] {"Thu", "Fri", "Sat", "Sun", "Mon", "Tue", "Wed"};
                break;
            case Calendar.FRIDAY:
                weekdays = new String[] {"Fri", "Sat", "Sun", "Mon", "Tue", "Wed", "Thu"};
                break;
            case Calendar.SATURDAY:
                weekdays = new String[] {"Sat", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri"};
                break;
        }

        return weekdays;

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
