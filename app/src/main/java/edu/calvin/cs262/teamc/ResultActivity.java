package edu.calvin.cs262.teamc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/* class for Result Activity
 * shows user the result of the quiz (which is the breed they match
 *      the closest to) and allows the user to begin searching through
 *      dog profiles of that particular breed
 */
public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
    }

    /* function for Adopt One Button
     * takes user to Adopt Pet Activity
     */
    public void onAdoptOneBtnPressed(View view) {
        Intent adopt = new Intent(ResultActivity.this, activity_adoptPet.class);
        startActivity(adopt);
    }
}
