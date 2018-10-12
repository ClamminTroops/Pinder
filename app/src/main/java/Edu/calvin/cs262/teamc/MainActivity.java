package Edu.calvin.cs262.teamc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onPetMatchBtnPressed(View view) {
    }

    public void onListPethBtnPressed(View view) {
        Intent list = new Intent(MainActivity.this, activity_PetforAdoption.class);
        startActivity(list);
    }

    public void onAdoptPetPressed(View view) {
        Intent adopt = new Intent(MainActivity.this, activity_adoptPet.class);
        startActivity(adopt);

    }

    public void onTempLoginBtnPressed(View view) {
    }

    public void onMatchBtnPressed (View view) {
        Intent match = new Intent(MainActivity.this,MatchActivity.class);
        startActivity(match);
    }


}
