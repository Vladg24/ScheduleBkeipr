package com.bkeipr.shedulebkeipr;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.widget.TextView;

import com.bkeipr.shedulebkeipr.DB.DatabaseHelper;

import java.io.IOException;

public class LessonsInfFragment extends Activity {

    private static final int LAYOUT = R.layout.less_inf;

    DatabaseHelper myDbHelper;
    TextView textTeacher;
    TextView textTime;
    TextView textLocation;
    TextView head;
    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
        textTeacher = (TextView) findViewById(R.id.textTeacher);
        textLocation = (TextView) findViewById(R.id.textLocation);
        textTime = (TextView) findViewById(R.id.textTime);
        head = (TextView) findViewById(R.id.headText);

        getDatabase();
        viewTeacher();
        viewLocation();
        viewTime();
        getHead();
    }

    public void viewTeacher(){
        Intent intent = getIntent();

        String day = intent.getStringExtra("day");
        String group = intent.getStringExtra("group");
        int position = Integer.parseInt(intent.getStringExtra("position"));
        c = myDbHelper.getScheduleInfo(group,day,(position+1));
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()){
            buffer.append(c.getString(0)+"   ");
            viewTextTeacher(buffer.toString());
        }
    }

    public void viewTextTeacher(String text){
        textTeacher.setText(text);
    }

    public void viewLocation(){
        Intent intent = getIntent();

        String day = intent.getStringExtra("day");
        String group = intent.getStringExtra("group");
        int position = Integer.parseInt(intent.getStringExtra("position"));
        c = myDbHelper.getScheduleInfo1(group,day,(position+1));
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()){
            buffer.append("Корпус: "+c.getString(0)+", ");
            buffer.append("аудиторія: "+c.getString(1));
            viewTextLocation(buffer.toString());
        }
    }

    public void viewTextLocation(String text){
        textLocation.setText(text);
    }

    public void viewTime(){
        Intent intent = getIntent();

        String day = intent.getStringExtra("day");
        String group = intent.getStringExtra("group");
        int position = Integer.parseInt(intent.getStringExtra("position"));
        c = myDbHelper.getScheduleInfo2(group,day,(position+1));
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()){
            buffer.append(c.getString(0)+"-");
            buffer.append(c.getString(1));
            viewTextTime(buffer.toString());
        }
    }

    public void viewTextTime(String text){
        textTime.setText(text);
    }

    public void getHead(){
        Intent intent = getIntent();

        String day = intent.getStringExtra("day");
        String group = intent.getStringExtra("group");
        int position = Integer.parseInt(intent.getStringExtra("position"));
        c = myDbHelper.getDisciplines(group,day,(position+1));
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()){
            buffer.append(c.getString(0));
            viewHead(buffer.toString());
        }
    }

    public void viewHead(String text){
        head.setText(text);
    }

    private void getDatabase(){
        myDbHelper = new DatabaseHelper(this);
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            myDbHelper.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;
        }
    }
}