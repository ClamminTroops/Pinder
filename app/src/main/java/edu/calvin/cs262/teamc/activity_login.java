package edu.calvin.cs262.teamc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/* class for Login Activity
 * allows users to register a new account, if they are a new user,
 *      or allows users to login, if they already have an account
 */
public class activity_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    /* method for Sign Up Button
     * TODO: creates a new user account with the inputted username and password
     * TODO: rejects the username if already exists and allows user to re-enter
     * directs the user back to the main screen (Main Activity)
     */
    public void createAccount(View view) {
        Intent create = new Intent(activity_login.this, MainActivity.class);
        startActivity(create);
    }

    /* method for Sign Up Button
     * TODO: logs in the user with the inputted username and password
     * TODO: rejects the username is the username does not exist
     * TODO: rejects the password if it does not match the username
     * directs the user back to the main screen (Main Activity)
     */
    public void logIn(View view) {
        Intent logIn = new Intent(activity_login.this, MainActivity.class);
        startActivity(logIn);
    }
}
