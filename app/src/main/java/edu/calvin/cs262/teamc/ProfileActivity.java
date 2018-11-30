package edu.calvin.cs262.teamc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/** class for Profile Activity
 *
 * allows user to make and save changes to their profile
 *      and to access their matches (taking them to Match Activity)
 *
 * @author Gavin Martin
 * @version 1
 * @since 16-11-2018
 */
public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.pinderlogov2));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
    }

    /**
     * function for Save Changes Button
     *
     * TODO: saves changes made to profile
     * and returns to Main Activity
     *
     * @param view
     */
    public void saveChanges(View view) {
        Intent logIn = new Intent(ProfileActivity.this, MainActivity.class);
        startActivity(logIn);
    }

    //TODO: create function for My Matches Button
}
