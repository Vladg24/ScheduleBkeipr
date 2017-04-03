package com.bkeipr.shedulebkeipr.Fragment;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bkeipr.shedulebkeipr.DB.DatabaseHelper;
import com.bkeipr.shedulebkeipr.LessonsInfFragment;
import com.bkeipr.shedulebkeipr.R;
import com.bkeipr.shedulebkeipr.adapter.DBAdapter;

import java.io.IOException;


public class TuesdayFragment extends Fragment{
    View view;
    DatabaseHelper myDbHelper;
    ListView dataList;
    String str ;
    String nameDay = "Вівторок";
    public TuesdayFragment(String str) {
        this.str=str;

    }    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tuesday_layout, container, false);
        dataList = (ListView) view.findViewById(R.id.dataListTu);

        getDatabase();
        schedule();
        return view;
    }

    private void getDatabase(){
        myDbHelper = new DatabaseHelper(getActivity());
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

    private void schedule(){
        final DBAdapter repository = new DBAdapter(getActivity());
        ArrayAdapter<String[]> adapter = new ArrayAdapter<String[]>(getActivity(), android.R.layout.simple_list_item_2, android.R.id.text1, repository.getTimeTable(str,nameDay)){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                View view = super.getView(position, convertView, parent);
                String[] entry = repository.getTimeTable(str,nameDay).get(position);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);
                text1.setText(entry[0]);
                text2.setText(entry[1]);
                return view;
            }
        };
        dataList.setAdapter(adapter);

        dataList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                String pos = String.valueOf(parent.getPositionForView(view));
                Intent intent = new Intent(view.getContext(),LessonsInfFragment.class);
                intent.putExtra("day", nameDay);
                intent.putExtra("group", str);
                intent.putExtra("position",  pos);
                startActivityForResult(intent, 0);
            }
        });
    }
}