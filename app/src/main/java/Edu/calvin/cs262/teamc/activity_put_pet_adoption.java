package Edu.calvin.cs262.teamc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class activity_put_pet_adoption extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put_pet_adoption);
    }

    public void savepet(View view)
    {
        EditText NameText = findViewById(R.id.NameEdit);
        EditText BreedText = findViewById(R.id.BreedText);
        EditText AgeText = findViewById(R.id.age);

        if( TextUtils.isEmpty(NameText.getText())){
            NameText.setError( "Name is required!" );
        } else if( TextUtils.isEmpty(BreedText.getText())){
            BreedText.setError( "Breed is required!" );
        }else if( TextUtils.isEmpty(AgeText.getText())){
            AgeText.setError( "Age is required!" );
        } else {
            Toast.makeText(getApplicationContext(), "Saved to database!",
                    Toast.LENGTH_LONG).show();
        }

    }
}
