package com.example.week2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.ImageView;

import java.util.List;

public class AnimalAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private List<Animal> animals;

    public AnimalAdapter(Activity activity, List<Animal> animals){
        layoutInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE
        );
        this.animals =animals;
    }
    @Override
    public int getCount() {
        return animals.size();
    }

    @Override
    public Object getItem(int i){
        return animals.get(i);
    }

    @Override
    public long getItemId(int i){
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View rowView = layoutInflater.inflate(R.layout.listview_row,null);
        TextView textView = rowView.findViewById(R.id.textViewRow);
        ImageView imageView = rowView.findViewById(R.id.imageViewRow);

        Animal animal = animals.get(position);
        textView.setText(animal.getType());
        imageView.setImageResource(animal.getPicId());


        return rowView;
    }
}
