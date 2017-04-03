package com.bkeipr.shedulebkeipr.adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bkeipr.shedulebkeipr.DB.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class DBAdapter {

        private SQLiteDatabase db;
        private Context cont;
        Cursor cursor;

    DatabaseHelper databaseHelper;
        public DBAdapter(Context context) {
            db = new DatabaseHelper(context).getWritableDatabase();
            databaseHelper = new DatabaseHelper(context);
            cont=context;
        }

    public List<String[]> getTimeTable(String str,String nameDay)
    {
       cursor = databaseHelper.getSchedule(str,nameDay);
        return getData();
    }
    public List<String[]> getRing()
    {
       cursor = databaseHelper.getRing();
        return getRingShedule();
    }
    public List<String[]> getTeacher(String str)
    {
       cursor = databaseHelper.getTeacher(str);
        return getTeacherShedule();
    }


    public ArrayList<String[]> getData()
    {
        Enum_Fields.FieldShedule field = Enum_Fields.FieldShedule.ID;
        Enum_Fields.FieldShedule field1 = Enum_Fields.FieldShedule.name;
        Enum_Fields.FieldShedule field2 = Enum_Fields.FieldShedule.num;
        Enum_Fields.FieldShedule field3 = Enum_Fields.FieldShedule.start;
        Enum_Fields.FieldShedule field4 = Enum_Fields.FieldShedule.end;
        ArrayList<String[]> list = new ArrayList<String[]>();
        if ((cursor != null) && (cursor.getCount() > 0)) {
            cursor.moveToFirst();
            do {
                list.add(new String[]{cursor.getString(field.getFieldCode())+"  "+cursor.getString(field1.getFieldCode()),"                                                                 " +
                        ""+cursor.getString(field2.getFieldCode())+"   "+ cursor.getString(field3.getFieldCode())+"-"+
                        cursor.getString(field4.getFieldCode())});

            } while (cursor.moveToNext());
        }
        return list;
    }

    public ArrayList<String[]> getRingShedule()
    {

        Enum_Fields.FieldShedule field2 = Enum_Fields.FieldShedule.ID;
        Enum_Fields.FieldShedule field3 = Enum_Fields.FieldShedule.name;
        Enum_Fields.FieldShedule field4 = Enum_Fields.FieldShedule.num;
        ArrayList<String[]> list = new ArrayList<String[]>();
        if ((cursor != null) && (cursor.getCount() > 0)) {
            cursor.moveToFirst();
            do {
                list.add(new String[]{"  "+cursor.getString(field2.getFieldCode())+"                    " +
                        " "+ cursor.getString(field3.getFieldCode())+"-"+
                        cursor.getString(field4.getFieldCode())});

            } while (cursor.moveToNext());
        }
        return list;
    }

    public ArrayList<String[]> getTeacherShedule()
    {

        Enum_Fields.FieldShedule field2 = Enum_Fields.FieldShedule.ID;
        ArrayList<String[]> list = new ArrayList<String[]>();
        if ((cursor != null) && (cursor.getCount() > 0)) {
            cursor.moveToFirst();
            do {
                list.add(new String[]{"  "+cursor.getString(field2.getFieldCode())});

            } while (cursor.moveToNext());
        }
        return list;
    }
}