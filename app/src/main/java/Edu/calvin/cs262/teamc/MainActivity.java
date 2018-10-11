package Edu.calvin.cs262.teamc;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public Button petMatchBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Pinder");
        onPetMatchBtnPressed();
    }

    public void onPetMatchBtnPressed() {
        petMatchBtn = (Button)findViewById(R.id.petMatchBtn);

        petMatchBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent petMatch = new Intent(MainActivity.this,Main1Activity.class);
                startActivity(petMatch);

            }
        });
    }


    public void onListPethBtnPressed(View view) {
    }

    public void onAdoptPetPressed(View view) {
    }

    public void onTempLoginBtnPressed(View view) {
    }
}
