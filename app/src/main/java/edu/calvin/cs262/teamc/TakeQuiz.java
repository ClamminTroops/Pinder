package edu.calvin.cs262.teamc;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jgabrielfreitas.core.BlurImageView;

public class TakeQuiz extends AppCompatActivity {

    BlurImageView myBlurImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_quiz);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Pinder Quiz Main");
        myBlurImage =  (BlurImageView) findViewById(R.id.myBlurImage);
        myBlurImage.setBlur(15);
    }
}
