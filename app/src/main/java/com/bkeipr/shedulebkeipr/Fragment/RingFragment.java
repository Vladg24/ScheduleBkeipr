package com.bkeipr.shedulebkeipr.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bkeipr.shedulebkeipr.R;
import com.bkeipr.shedulebkeipr.adapter.DBAdapter;

public class RingFragment extends Fragment {

    View view;
    ListView dataList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.ring, container, false);
        dataList = (ListView) view.findViewById(R.id.ringList);

        ring();
        return view;
    }

    private void ring(){
        final DBAdapter repository = new DBAdapter(getActivity());
        ArrayAdapter<String[]> adapter = new ArrayAdapter<String[]>(getActivity(), android.R.layout.simple_list_item_2, android.R.id.text1, repository.getRing()){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                View view = super.getView(position, convertView, parent);
                String[] entry = repository.getRing().get(position);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                text1.setText(entry[0]);

                return view;
            }
        };
        dataList.setAdapter(adapter);
    }
}
