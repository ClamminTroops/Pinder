package edu.calvin.cs262.teamc;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.view.WindowManager.LayoutParams;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jgabrielfreitas.core.BlurImageView;
import com.obsez.android.lib.filechooser.ChooserDialog;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

//TODO: delete commented-out code if code is no longer needed

/**
 * class for Pet For Adoption Activity
 *
 * It allows users to add information about their dog and to put their
 *      dog up for adoption
 *
 * @author Justin Baskaran
 * @version 1
 * @since 16-11-2018
 */
public class activity_PetforAdoption extends AppCompatActivity {

    BlurImageView myBlurImage;
    public Button yesbutton;
    File imageURL = null ;
    Integer exampleString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put_pet_adoption);
        Bundle extras = getIntent().getExtras();
         exampleString = extras.getInt("personID");


        Log.e("personID", String.valueOf(exampleString));

        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.pinderlogov2));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");


        ToggleButton toggleButton = findViewById(R.id.toggleButton4);
        toggleButton.setText("Female");
        toggleButton.setTextOff("Female");
        toggleButton.setTextOn("Male");

        //set default values to ToggleButton "houseTrained"
        ToggleButton tg1 = findViewById(R.id.houseTrained);
        tg1.setText("No");
        tg1.setTextOff("Yes");
        tg1.setTextOn("No");

        final FileChooser fileChooser = new FileChooser(activity_PetforAdoption.this);


        final TextView ImageURL = findViewById(R.id.chooseFile);
        ImageURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileChooser.setFileListener(new FileChooser.FileSelectedListener() {
                    @Override
                    public void fileSelected(final File file) {


                        imageURL = file;
                        String filename = file.getAbsolutePath();
                        ImageURL.setText(imageURL.getAbsolutePath());
                        Log.i("File Name", filename);
                        // then actually do something in another module

                    }
                });
                // Set up and filter my extension I am looking for
                //fileChooser.setExtension("pdf");
                fileChooser.showDialog();

            }
        });



    }

    public void saveNewPetSwipe (View view) {
        EditText NameText = findViewById(R.id.NameEdit);
        ToggleButton GenderText = findViewById(R.id.toggleButton4);
        EditText BreedText = findViewById(R.id.chooseBreed);
        Spinner EnergyLevel = findViewById(R.id.energylvl);
        ToggleButton HouseTrained = findViewById(R.id.houseTrained);
        Spinner size = findViewById(R.id.size);
        TextView ImageURL = findViewById(R.id.chooseFile);

        if (TextUtils.isEmpty(NameText.getText())) {
            NameText.setError(getString(R.string.Name_Required));
        } else if (TextUtils.isEmpty(BreedText.getText())) {
            BreedText.setError(getString(R.string.Age_Required));
        } else {
            final String Name = NameText.getText().toString();
            final String Gender = GenderText.getText().toString();
            final String Breed = BreedText.getText().toString();
            final String Energy = EnergyLevel.getSelectedItem().toString();
            final String House = HouseTrained.getText().toString();
            final String Size = size.getSelectedItem().toString();
            String ImageUrl = ImageURL.getText().toString();



            Bitmap bm = BitmapFactory.decodeFile(imageURL.getAbsolutePath());
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 80, stream);
            byte[] byteFormat = stream.toByteArray();
            final String imgString = Base64.encodeToString(byteFormat, Base64.NO_WRAP);


            Log.e("saveNewPetSwipe-Name",Name);
            Log.e("saveNewPetSwipe-Gender",Gender);
            Log.e("saveNewPetSwipe-Breed",Breed);
            Log.e("saveNewPetSwipe-Energy",Energy);
            Log.e("saveNewPetSwipe-House",House);
            Log.e("saveNewPetSwipe-Size",Size);
            Log.e("saveNewPetSwipe-imgString",imgString);


            if (TextUtils.isEmpty(NameText.getText()))
            {
                NameText.setError(getString(R.string.Name_Required));
            } else if (TextUtils.isEmpty(BreedText.getText()))
            {
                BreedText.setError(getString(R.string.Age_Required));
            }
            else if (ImageURL.getText().toString().equals(""))
            {
                BreedText.setError("No Picture!");
            }
            else {


                String requestUrl = "https://calvincs262-fall2018-teamc.appspot.com/pinder/v1/listpet";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, requestUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(activity_PetforAdoption.this, "Listed Pet!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //    Toast.makeText(activity_PetforAdoption.this, "Failed", Toast.LENGTH_SHORT).show();

                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> postMap = new HashMap<>();
                        postMap.put("Content-Type", "application/json");
                        postMap.put("name", Name);
                        postMap.put("gender", Gender);
                        postMap.put("breed", Breed);
                        postMap.put("energylevel", Energy);
                        postMap.put("housetrained", House);
                        postMap.put("size", Size);
                        postMap.put("personidAndr", String.valueOf(exampleString));
                        postMap.put("photo", imgString);
                        //..... Add as many key value pairs in the map as necessary for your request
                        return postMap;
                    }
                };
                //make the request to your server as indicated in your request url
                Volley.newRequestQueue(getApplicationContext()).add(stringRequest);


            }
        }

    }


}



