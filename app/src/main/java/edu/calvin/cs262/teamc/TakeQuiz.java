package edu.calvin.cs262.teamc;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jgabrielfreitas.core.BlurImageView;

/* class for Take Quiz Activity
 * prompts user to answers questions for the quiz
 * TODO: Update questions for the quiz and add new quiz activities as necessary
 * TODO: keep track of user answers to the questions
 */
public class TakeQuiz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_quiz);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Pinder Quiz Main");
    }

    public void onDoneBtnPressed (View view) {
        Intent adopt = new Intent(TakeQuiz.this, ResultActivity.class);
        startActivity(adopt);
        actionBar.setTitle("");
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.pinderlogov2));
        myBlurImage =  (BlurImageView) findViewById(R.id.myBlurImage);
        myBlurImage.setBlur(15);
    }
}
