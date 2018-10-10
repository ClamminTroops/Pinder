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
        Intent adopt = new Intent(MainActivity.this, activity_put_pet_adoption.class);
        startActivity(adopt);
    }

    public void onAdoptPetPressed(View view) {
        Intent adopt = new Intent(MainActivity.this, adopt_activity.class);
        startActivity(adopt);

    }

    public void onTempLoginBtnPressed(View view) {
    }
}
