package com.example.week2;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayAdapterActivity extends ListActivity {

//    static final String[] Animals = new String[] {"Cat","Dog","Bird","Bear"};
    static final List Animals = Arrays.asList(new String[]{"lion", "elephant", "tiger", "giraffe"});

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_array_adapter,Animals));
        ListView listView =getListView();
        listView.setTextFilterEnabled(true);
    }
}
