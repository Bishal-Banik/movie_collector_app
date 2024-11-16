package com.rkcorner.assignment4;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> genres; // List of genre names
    HashMap<String, List<String>> movieDetails; // Maps genre to movie list

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the genre and movie details lists
        genres = new ArrayList<>();
        movieDetails = new HashMap<>();

        // Populate the genres and movie details
        prepareListData();

        // Set the ExpandableListView and adapter
        expandableListView = findViewById(R.id.expandableListView);
        expandableListAdapter = new GenreExpandableListAdapter(this, genres, movieDetails);
        expandableListView.setAdapter(expandableListAdapter);

        // Click listener for genre items
        expandableListView.setOnGroupClickListener((parent, v, groupPosition, id) -> {
            String genre = genres.get(groupPosition);
            Toast.makeText(getApplicationContext(), "Genre: " + genre, Toast.LENGTH_SHORT).show();
            return false; // Allow the default behavior (expand the genre)
        });

        // Click listener for movie items
        expandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            String genre = genres.get(groupPosition);
            String movie = movieDetails.get(genre).get(childPosition);
            showMovieDetails(movie); // Display movie details (like release year)
            return true;
        });
    }

    private void prepareListData() {
        // Adding genres
        genres.add("Action");
        genres.add("Sci-Fi");
        genres.add("Drama");

        // Adding movies for each genre
        List<String> actionMovies = new ArrayList<>();
        actionMovies.add("The Dark Knight");
        actionMovies.add("Mad Max: Fury Road");

        List<String> sciFiMovies = new ArrayList<>();
        sciFiMovies.add("Inception");
        sciFiMovies.add("Interstellar");

        List<String> dramaMovies = new ArrayList<>();
        dramaMovies.add("The Pursuit of Happyness");
        dramaMovies.add("The Shawshank Redemption");

        // Mapping genre to movie list
        movieDetails.put(genres.get(0), actionMovies); // Action
        movieDetails.put(genres.get(1), sciFiMovies);  // Sci-Fi
        movieDetails.put(genres.get(2), dramaMovies);  // Drama
    }

    // Show movie details (e.g., release year)
    private void showMovieDetails(String movie) {
        String releaseYear = "";
        switch (movie) {
            case "The Dark Knight":
                releaseYear = "2008";
                break;
            case "Mad Max: Fury Road":
                releaseYear = "2015";
                break;
            case "Inception":
                releaseYear = "2010";
                break;
            case "Interstellar":
                releaseYear = "2014";
                break;
            case "The Pursuit of Happyness":
                releaseYear = "2006";
                break;
            case "The Shawshank Redemption":
                releaseYear = "1994";
                break;
        }
        // Show the release year of the movie
        Toast.makeText(getApplicationContext(), movie + " - Released in " + releaseYear, Toast.LENGTH_SHORT).show();
    }
}