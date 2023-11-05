package com.example.week2;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;


public class CustomAdapterActivity extends AppCompatActivity {
    final List<Animal> animalList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanteStates){
        super.onCreate(savedInstanteStates);
        setContentView(R.layout.activity_customer_adapter);

        List<Animal> animalList = new ArrayList<>();
        animalList.add(new Animal("Elephant", R.drawable.elephant));
        animalList.add(new Animal("Tiger", R.drawable.tiger));
        animalList.add(new Animal("Giraffe", R.drawable.giraffe));
        animalList.add(new Animal("Lion", R.drawable.lion));

        ListView listView = findViewById(R.id.myListView);
        AnimalAdapter adapter = new AnimalAdapter(this, animalList);
        listView.setAdapter(adapter);
    }


}
