package com.rkcorner.assignment4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class MovieListAdapter extends ArrayAdapter<Movie> {

    public MovieListAdapter(Context context, ArrayList<Movie> movies) {
        super(context, 0, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_item, parent, false);
        }

        Movie movie = getItem(position);

        TextView movieName = convertView.findViewById(R.id.movie_name);
        TextView movieYear = convertView.findViewById(R.id.movie_year);

        movieName.setText(movie.getName());
        movieYear.setText(movie.getYear());

        return convertView;
    }
}
