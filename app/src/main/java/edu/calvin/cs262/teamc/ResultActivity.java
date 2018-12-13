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

            //set results for first breed
            TextView breed1 = findViewById(R.id.firstBreed);
            String breed = bundle.getString("gold");
            breed1.setText(breed);

            ProgressBar progressBar1 = findViewById(R.id.firstProgressBar);
            int percent = bundle.getInt("goldScore");
            progressBar1.setProgress(percent);

            TextView percent1 = findViewById(R.id.firstPercent);
            String score = Integer.toString(percent) + "%";
            percent1.setText(score);

            ImageView image1 = findViewById(R.id.firstImage);
            assert breed != null;
            switch (breed) {
                case "Spaniel":
                    image1.setImageResource(R.drawable.spaniel);
                    break;
                case "Poodle":
                    image1.setImageResource(R.drawable.poodle);
                    break;
                case "Pomeranian":
                    image1.setImageResource(R.drawable.pom);
                    break;
                case "Labrador":
                    image1.setImageResource(R.drawable.lab);
                    break;
                case "Husky":
                    image1.setImageResource(R.drawable.husky);
                    break;
                case "Golden Retriever":
                    image1.setImageResource(R.drawable.golden);
                    break;
                case "German Shepherd":
                    image1.setImageResource(R.drawable.german);
                    break;
                case "Collie":
                    image1.setImageResource(R.drawable.collie);
                    break;
                case "Beagle":
                    image1.setImageResource(R.drawable.beagle2);
                    break;
                case "Bulldog":
                    image1.setImageResource(R.drawable.bulldog);
                    break;
            }

            //set results for second breed
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
            assert breed != null;
            switch (breed) {
                case "Spaniel":
                    image2.setImageResource(R.drawable.spaniel);
                    break;
                case "Poodle":
                    image2.setImageResource(R.drawable.poodle);
                    break;
                case "Pomeranian":
                    image2.setImageResource(R.drawable.pom);
                    break;
                case "Labrador":
                    image2.setImageResource(R.drawable.lab);
                    break;
                case "Husky":
                    image2.setImageResource(R.drawable.husky);
                    break;
                case "Golden Retriever":
                    image2.setImageResource(R.drawable.golden);
                    break;
                case "German Shepherd":
                    image2.setImageResource(R.drawable.german);
                    break;
                case "Collie":
                    image2.setImageResource(R.drawable.collie);
                    break;
                case "Beagle":
                    image2.setImageResource(R.drawable.beagle2);
                    break;
                case "Bulldog":
                    image2.setImageResource(R.drawable.bulldog);
                    break;
            }

            //set results for third breed
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
            assert breed != null;
            switch (breed) {
                case "Spaniel":
                    image3.setImageResource(R.drawable.spaniel);
                    break;
                case "Poodle":
                    image3.setImageResource(R.drawable.poodle);
                    break;
                case "Pomeranian":
                    image3.setImageResource(R.drawable.pom);
                    break;
                case "Labrador":
                    image3.setImageResource(R.drawable.lab);
                    break;
                case "Husky":
                    image3.setImageResource(R.drawable.husky);
                    break;
                case "Golden Retriever":
                    image3.setImageResource(R.drawable.golden);
                    break;
                case "German Shepherd":
                    image3.setImageResource(R.drawable.german);
                    break;
                case "Collie":
                    image3.setImageResource(R.drawable.collie);
                    break;
                case "Beagle":
                    image3.setImageResource(R.drawable.beagle2);
                    break;
                case "Bulldog":
                    image3.setImageResource(R.drawable.bulldog);
                    break;
            }
        }
    }

    /**
     * method for returning home
     *
     * This takes the user to the home screen activity
     *
     *
     * @param view
     */
    public void goHome(View view) {
        Intent home = new Intent(ResultActivity.this, MainActivity.class);
        startActivity(home);
    }


    /**
     * method for viewing help screen
     *
     * This takes the user to the Help screen activity
     *
     *
     * @param view
     */
    public void getHelp(View view) {
        Intent home = new Intent(ResultActivity.this, Help.class);
        startActivity(home);
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
