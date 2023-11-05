package com.example.week3;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PostAdapter extends BaseAdapter {
    List<Post> postList;
    LayoutInflater layoutInflater;

    public PostAdapter(Activity activity, List<Post> postList) {
        this.postList = postList;
        layoutInflater = activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return postList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView;
        rowView = layoutInflater.inflate(R.layout.row,null);
        EditText txtMessage= rowView.findViewById(R.id.txtMessage);
        TextView txtLocation = rowView.findViewById(R.id.txtLocation);
        ImageView imageView = rowView.findViewById(R.id.imageView);
        Post post = postList.get(position);
        txtMessage.setText(post.getMessage());
        txtLocation.setText(post.getLocation().getLatitude()+" "+post.getLocation().getLongitude());
        imageView.setImageBitmap(post.getImage(post.getImage(post.getImage())));
        return rowView;
    }


}
