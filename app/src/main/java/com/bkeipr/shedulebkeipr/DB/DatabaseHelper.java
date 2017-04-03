package com.bkeipr.shedulebkeipr.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class DatabaseHelper extends SQLiteOpenHelper {

    String DB_PATH = null;
    private static String DB_NAME = "shedule";
    private SQLiteDatabase myDataBase;
    private final Context myContext;
    private String group_name="";
    private String day_name="";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 10);
        this.myContext = context;
        this.DB_PATH = "/data/data/com.bkeipr.shedulebkeipr/databases/";
        Log.e("Path 1", DB_PATH);
    }

    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if (dbExist) {
        } else {
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    private void copyDataBase() throws IOException {
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[10];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDataBase() throws SQLException {
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {
        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
            try {
                copyDataBase();
            } catch (IOException e) {
                e.printStackTrace();

            }
    }

    public Cursor getSchedule(String str,String nameDay){
        group_name = str;
        day_name = nameDay;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select distinct lessons.num_lesson, disciplines.name, aud.number, time_lessons.start,time_lessons.end\n" +
                " from lessons, disciplines, groups, teacher, days_of_the_week, aud, time_lessons \n" +
                " where days_of_the_week.IDDays=lessons.IDDays and disciplines.ID=lessons.ID and groups.name='"+group_name +"'" +
                " and  groups.IDGroup=disciplines.IDGroup and aud.IDAud=disciplines.IDAud and time_lessons.IDLessons=lessons.IDLessons\n" +
                " and days_of_the_week.name='"+day_name+"'"+
                " order by lessons.num_lesson ",null);
        return c;
    }

    public Cursor getGroups()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor s = db.rawQuery("Select name from groups",null);
        return s;
    }

    public Cursor getScheduleInfo(String str,String nameDay,int num_less){
        group_name = str;
        day_name = nameDay;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select distinct  teacher.name  from  teacher,lessons, days_of_the_week,groups,disciplines,aud,time_lessons\n" +
                " where days_of_the_week.IDDays=lessons.IDDays and disciplines.ID=lessons.ID and groups.name='"+group_name+"' and teacher.IDTeacher=disciplines.IDTeacher\n" +
                "                and  groups.IDGroup=disciplines.IDGroup and aud.IDAud=disciplines.IDAud and time_lessons.IDLessons=lessons.IDLessons\n" +
                "                 and days_of_the_week.name='"+day_name+"' and lessons.num_lesson="+num_less+"",null);
        return c;
    }

    public Cursor getScheduleInfo1(String str,String nameDay,int num_less){
        group_name = str;
        day_name = nameDay;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select distinct  aud.housing,aud.number \n" +
                " from  teacher,lessons, days_of_the_week,groups,disciplines,aud,time_lessons\n" +
                " where days_of_the_week.IDDays=lessons.IDDays and disciplines.ID=lessons.ID and groups.name='"+group_name+"' and teacher.IDTeacher=disciplines.IDTeacher\n" +
                "                and  groups.IDGroup=disciplines.IDGroup and aud.IDAud=disciplines.IDAud and time_lessons.IDLessons=lessons.IDLessons\n" +
                "                 and days_of_the_week.name='"+day_name+"' and lessons.num_lesson="+num_less+"",null);
        System.out.println(num_less+" fkvj");
        return c;
    }

    public Cursor getScheduleInfo2(String str,String nameDay, int num_less){
        group_name = str;
        day_name = nameDay;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select distinct  time_lessons.start,time_lessons.end\n" +
                " from  teacher,lessons, days_of_the_week,groups,disciplines,aud,time_lessons\n" +
                " where days_of_the_week.IDDays=lessons.IDDays and disciplines.ID=lessons.ID and groups.name='"+group_name+"' and teacher.IDTeacher=disciplines.IDTeacher\n" +
                "                and  groups.IDGroup=disciplines.IDGroup and aud.IDAud=disciplines.IDAud and time_lessons.IDLessons=lessons.IDLessons\n" +
                "                 and days_of_the_week.name='"+day_name+"' and lessons.num_lesson="+num_less+"",null);
        return c;
    }

    public Cursor getDisciplines(String str,String nameDay, int num_less){
        group_name = str;
        day_name = nameDay;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(" select  disciplines.name " +
                " from lessons, disciplines, groups, teacher, days_of_the_week, aud, time_lessons " +
                " where days_of_the_week.IDDays=lessons.IDDays and disciplines.ID=lessons.ID and groups.name='"+group_name+"' " +
                " and  groups.IDGroup=disciplines.IDGroup and aud.IDAud=disciplines.IDAud and time_lessons.IDLessons=lessons.IDLessons " +
                " and days_of_the_week.name='"+day_name+"' and lessons.num_lesson="+num_less+" and teacher.IDTeacher=disciplines.IDTeacher ",null);
        return c;
    }
    public Cursor getRing(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(" select num_lessons,start,end from time_lessons ",null);
        return c;
    }
    public Cursor getTeacher(String str){
        group_name = str;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(" select distinct teacher.name from teacher, groups, disciplines " +
                "where groups.name='"+group_name+"' and groups.IDGroup=disciplines.IDGroup and teacher.IDTeacher=disciplines.IDTeacher",null);
        return c;
    }
}
