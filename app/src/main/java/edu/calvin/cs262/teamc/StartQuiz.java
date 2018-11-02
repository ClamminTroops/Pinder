package edu.calvin.cs262.teamc;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jgabrielfreitas.core.BlurImageView;

public class StartQuiz extends AppCompatActivity {

    BlurImageView myBlurImage;
    public Button yesbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_quiz);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Pinder Quiz");
        init();
        myBlurImage =  (BlurImageView) findViewById(R.id.myBlurImage);
        myBlurImage.setBlur(15);
    }

    public void init(){
        yesbutton = (Button)findViewById(R.id.yesbutton);
        yesbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent toy = new Intent(getBaseContext(), TakeQuiz.class);
                startActivity(toy);
            }
        });
    }
}
