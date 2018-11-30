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
 * @author Sam Acharya and Caroline Carlson
 * @version 2
 * @since 16-11-2018
 */
public class StartQuiz extends AppCompatActivity {

    BlurImageView myBlurImage;
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
        //actionBar.setTitle("");
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.pinderlogov2));
        init();
        //myBlurImage =  (BlurImageView) findViewById(R.id.myBlurImage);
        //myBlurImage.setBlur(15);
    }

    /**
     * takes user to Take Quiz Activity on pressing button "Yes"
     */
    public void init(){
        yesbutton = (Button)findViewById(R.id.yesbutton);
        yesbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent toy = new Intent(getBaseContext(), TakeQuiz.class);
                startActivity(toy);
            }
        });
        nobutton = (Button)findViewById(R.id.nobutton);
        nobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(getBaseContext(), TakeQuiz.class);
                startActivity(toy);
            }
        });
    }
}
