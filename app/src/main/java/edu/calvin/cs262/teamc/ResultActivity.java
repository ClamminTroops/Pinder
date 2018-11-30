package edu.calvin.cs262.teamc;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.pinderlogov2));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");

        //set textview to the result from the quiz
        textView = findViewById(R.id.breedName);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            String result = bundle.getString("Breed");
            textView.setText(result);
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
