package edu.calvin.cs262.teamc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * class for Login Activity
 *
 * It allows users to register a new account, if they are a new user,
 *      or allows users to login, if they already have an account.
 *
 * @author Gavin Martin
 * @version 3
 * @since 16-11-2018
 */
public class activity_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.pinderlogov2));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
    }

    /**
     * method for Sign Up Button
     *
     * It directs the user back to the main screen (Main Activity)
     *
     * TODO: creates a new user account with the inputted username and password
     * TODO: rejects the username if already exists and allows user to re-enter
     *
     * @param view
     */
    public void createAccount(View view) {
        Intent create = new Intent(activity_login.this, MainActivity.class);
        startActivity(create);
    }

    /**
     * method for Sign Up Button
     *
     * It directs the user back to the main screen (Main Activity)
     *
     * TODO: logs in the user with the inputted username and password
     * TODO: rejects the username is the username does not exist
     * TODO: rejects the password if it does not match the username
     *
     * @param view
     */
    public void logIn(View view) {
        Intent logIn = new Intent(activity_login.this, MainActivity.class);
        startActivity(logIn);
    }
}
