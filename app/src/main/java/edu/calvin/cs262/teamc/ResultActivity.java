package edu.calvin.cs262.teamc;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * class for Result Activity
 *
 * shows user the result of the quiz (which is the breed they match
 *      the closest to) and allows the user to begin searching through
 *      dog profiles of that particular breed
 *
 * @author Caroline Carlson
 * @version 2
 * @since 16-11-2018
 */
public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.pinderlogov2));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null){

            /**
             * set results for first breed
             */
            TextView breed1 = findViewById(R.id.firstBreed);
            String breed = bundle.getString("gold");
            breed1.setText(breed);

            ProgressBar progressBar1 = findViewById(R.id.firstProgressBar);
            Integer percent = bundle.getInt("goldScore");
            progressBar1.setProgress(percent);

            TextView percent1 = findViewById(R.id.firstPercent);
            String score = Integer.toString(percent) + "%";
            percent1.setText(score);

            ImageView image1 = findViewById(R.id.firstImage);
            if (breed.equals("Spaniel")) {
                image1.setImageResource(R.drawable.spaniel);
            } else if (breed.equals("Poodle")) {
                image1.setImageResource(R.drawable.poodle);
            } else if (breed.equals("Pomeranian")) {
                image1.setImageResource(R.drawable.pom);
            } else if (breed.equals("Labrador")) {
                image1.setImageResource(R.drawable.lab);
            } else if (breed.equals("Husky")) {
                image1.setImageResource(R.drawable.husky);
            } else if (breed.equals("Golden Retriever")) {
                image1.setImageResource(R.drawable.golden);
            } else if (breed.equals("German Shepherd")) {
                image1.setImageResource(R.drawable.german);
            } else if (breed.equals("Collie")) {
                image1.setImageResource(R.drawable.collie);
            } else if (breed.equals("Beagle")) {
                image1.setImageResource(R.drawable.beagle2);
            } else if (breed.equals("Bulldog")) {
                image1.setImageResource(R.drawable.bulldog);
            }

            /**
             * set results for second breed
             */
            TextView breed2 = findViewById(R.id.secondBreed);
            breed = bundle.getString("silver");
            breed2.setText(breed);

            ProgressBar progressBar2 = findViewById(R.id.secondProgressBar);
            percent = bundle.getInt("silverScore");
            progressBar2.setProgress(percent);

            TextView percent2 = findViewById(R.id.secondPercent);
            score = Integer.toString(percent) + "%";
            percent2.setText(score);

            ImageView image2 = findViewById(R.id.secondImage);
            if (breed.equals("Spaniel")) {
                image2.setImageResource(R.drawable.spaniel);
            } else if (breed.equals("Poodle")) {
                image2.setImageResource(R.drawable.poodle);
            } else if (breed.equals("Pomeranian")) {
                image2.setImageResource(R.drawable.pom);
            } else if (breed.equals("Labrador")) {
                image2.setImageResource(R.drawable.lab);
            } else if (breed.equals("Husky")) {
                image2.setImageResource(R.drawable.husky);
            } else if (breed.equals("Golden Retriever")) {
                image2.setImageResource(R.drawable.golden);
            } else if (breed.equals("German Shepherd")) {
                image2.setImageResource(R.drawable.german);
            } else if (breed.equals("Collie")) {
                image2.setImageResource(R.drawable.collie);
            } else if (breed.equals("Beagle")) {
                image2.setImageResource(R.drawable.beagle);
            } else if (breed.equals("Bulldog")) {
                image2.setImageResource(R.drawable.bulldog);
            }

            /**
             * set results for third breed
             */
            TextView breed3 = findViewById(R.id.thirdBreed);
            breed = bundle.getString("bronze");
            breed3.setText(breed);

            ProgressBar progressBar3 = findViewById(R.id.thirdProgressBar);
            percent = bundle.getInt("bronzeScore");
            progressBar3.setProgress(percent);

            TextView percent3 = findViewById(R.id.thirdPercent);
            score = Integer.toString(percent) + "%";
            percent3.setText(score);

            ImageView image3 = findViewById(R.id.thirdImage);
            if (breed.equals("Spaniel")) {
                image3.setImageResource(R.drawable.spaniel);
            } else if (breed.equals("Poodle")) {
                image3.setImageResource(R.drawable.poodle);
            } else if (breed.equals("Pomeranian")) {
                image3.setImageResource(R.drawable.pom);
            } else if (breed.equals("Labrador")) {
                image3.setImageResource(R.drawable.lab);
            } else if (breed.equals("Husky")) {
                image3.setImageResource(R.drawable.husky);
            } else if (breed.equals("Golden Retriever")) {
                image3.setImageResource(R.drawable.golden);
            } else if (breed.equals("German Shepherd")) {
                image3.setImageResource(R.drawable.german);
            } else if (breed.equals("Collie")) {
                image3.setImageResource(R.drawable.collie);
            } else if (breed.equals("Beagle")) {
                image3.setImageResource(R.drawable.beagle);
            } else if (breed.equals("Bulldog")) {
                image3.setImageResource(R.drawable.bulldog);
            }
        }
    }

    /**
     * function for Adopt One Button
     *
     * takes user to Adopt Pet Activity
     *
     * @param view
     */
    public void onAdoptOneBtnPressed(View view) {
        Intent adopt = new Intent(ResultActivity.this, activity_adoptPet.class);
        startActivity(adopt);
    }
}
