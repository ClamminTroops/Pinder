package edu.calvin.cs262.teamc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/* class Main Activity
 * for main screen (or home screen) of the app
 * provides buttons that directs users to take the quiz,
 *      adopt a dog, and/or sell a dog.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //takes user to Pet For Adoption Activity
    public void onListPethBtnPressed(View view) {
        Intent list = new Intent(MainActivity.this, activity_PetforAdoption.class);
        startActivity(list);
    }

    //takes user to Adopt Pet Activity
    public void onAdoptPetPressed(View view) {
        Intent adopt = new Intent(MainActivity.this, activity_adoptPet.class);
        startActivity(adopt);
    }


    //takes user to Start Quiz Activity
    public void onStartQuizBtnPressed(View view) {
        Intent match = new Intent(MainActivity.this, StartQuiz.class);
        startActivity(match);
    }

    //takes user to Profile Activity
    public void onProfilePressed(View view) {
        Intent match = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(match);
    }
}
