package com.rkcorner.assignment4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class GenreExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> genres;
    private HashMap<String, List<String>> movieDetails;

    public GenreExpandableListAdapter(Context context, List<String> genres, HashMap<String, List<String>> movieDetails) {
        this.context = context;
        this.genres = genres;
        this.movieDetails = movieDetails;
    }

    @Override
    public int getGroupCount() {
        return genres.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return movieDetails.get(genres.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return genres.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return movieDetails.get(genres.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(android.R.layout.simple_expandable_list_item_1, null); // Default layout
        }
        TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
        textView.setText(genres.get(groupPosition)); // Set genre name
        textView.setTextSize(24); // Set larger text size
        textView.setTextColor(context.getResources().getColor(android.R.color.white)); // Optional: Set text color
        textView.setPadding(32, 16, 32, 16); // Optional: Add padding for better spacing
        textView.setTypeface(null, android.graphics.Typeface.BOLD); // Optional: Set bold text
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(android.R.layout.simple_list_item_1, null);
        }
        TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
        textView.setText(movieDetails.get(genres.get(groupPosition)).get(childPosition));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
