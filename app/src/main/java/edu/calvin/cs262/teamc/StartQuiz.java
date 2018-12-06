package edu.calvin.cs262.teamc;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jgabrielfreitas.core.BlurImageView;

/**
 * class for Start Quiz Activity
 *
 * confirms that the user wants to take the quiz
 *
 * @author Sam Acharya, Caroline Carlson, and Gavin Martin
 * @version 4
 * @since 04-Dec-2018
 */
public class StartQuiz extends AppCompatActivity {

    public Button yesbutton;
    public Button nobutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_quiz);
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.pinderlogov2));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
    }

    //takes user to Take Quiz Activity on pressing button "Yes"
    public void onYesBtnPressed(View view) {
        Intent toy = new Intent(StartQuiz.this, TakeQuiz.class);
        startActivity(toy);
    }

    //takes user to Main Activity on pressing button "No"
    public void onNoBtnPressed (View view) {
        Intent adopt = new Intent(StartQuiz.this, MainActivity.class);
        startActivity(adopt);
    }
}
