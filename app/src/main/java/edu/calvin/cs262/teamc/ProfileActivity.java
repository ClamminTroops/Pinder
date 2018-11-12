package edu.calvin.cs262.teamc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/* class for Profile Activity
 * allows user to make and save changes to their profile
 *      and to access their matches (taking them to Match Activity)
 */
public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
    }

    /* function for Save Changes Button
     * TODO: saves changes made to profile
     * returns to Main Activity when pressed
     */
    public void saveChanges(View view) {
        Intent logIn = new Intent(ProfileActivity.this, MainActivity.class);
        startActivity(logIn);
    }

    //TODO: create function for My Matches Button
}
