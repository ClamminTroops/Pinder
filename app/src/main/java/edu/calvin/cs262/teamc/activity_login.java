package edu.calvin.cs262.teamc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class activity_login extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    public void createAccount(View view) {
        Intent create = new Intent(activity_login.this, MainActivity.class);
        startActivity(create);
    }

    public void logIn(View view) {
        Intent logIn = new Intent(activity_login.this, MainActivity.class);
        startActivity(logIn);
    }
}
