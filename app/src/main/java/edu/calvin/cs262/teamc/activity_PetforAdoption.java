package edu.calvin.cs262.teamc;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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

    public Button yesbutton;
    File imageURL = null ;
    int exampleString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put_pet_adoption);
        Bundle extras = getIntent().getExtras();
        exampleString = extras.getInt("personID");

        getSupportActionBar().hide();


        ToggleButton toggleButton = findViewById(R.id.toggleButton4);
        toggleButton.setText("Female");
        toggleButton.setTextOff("Female");
        toggleButton.setTextOn("Male");



        FileChooser fileChooser = null;
        if (isStoragePermissionGranted())
        {
            fileChooser = new FileChooser(activity_PetforAdoption.this);
        }



        final TextView ImageURL = findViewById(R.id.chooseFile);

        final FileChooser finalFileChooser = fileChooser;
        ImageURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalFileChooser.setFileListener(new FileChooser.FileSelectedListener() {
                    @Override
                    public void fileSelected(final File file) {
                    // Here, thisActivity is the current activity
                    imageURL = file;
                    String filename = file.getAbsolutePath();
                    ImageURL.setText(imageURL.getAbsolutePath());
                    Log.i("File Name", filename);
                    // then actually do something in another module
                    }
                });
                // Set up and filter my extension I am looking for
                //fileChooser.setExtension("pdf");
                finalFileChooser.showDialog();
            }
        });
    }

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }


    /**
     * method for returning home
     *
     * This takes the user to the home screen activity
     *
     *
     * @param view
     */
    public void goHome(View view) {
        Intent home = new Intent(activity_PetforAdoption.this, MainActivity.class);
        home.putExtra("personID", exampleString);
        startActivity(home);
    }


    /**
     * method for viewing help screen
     *
     * This takes the user to the Help screen activity
     *
     *
     * @param view
     */
    public void getHelp(View view) {
        Intent home = new Intent(activity_PetforAdoption.this, Help.class);
        home.putExtra("personID", exampleString);
        startActivity(home);
    }



    public void saveNewPetSwipe (View view) {
        EditText NameText = findViewById(R.id.NameEdit);
        ToggleButton GenderText = findViewById(R.id.toggleButton4);
        Spinner BreedText = findViewById(R.id.chooseBreed);
        TextView ImageURL = findViewById(R.id.chooseFile);

        if (TextUtils.isEmpty(NameText.getText())) {

            NameText.setError(getString(R.string.Name_Required));

        } else {

            final String Name = NameText.getText().toString();
            final String Gender = GenderText.getText().toString();
            final String Breed = BreedText.getSelectedItem().toString();
            String ImageUrl = ImageURL.getText().toString();

            Bitmap bm = BitmapFactory.decodeFile(imageURL.getAbsolutePath());
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 80, stream);
            byte[] byteFormat = stream.toByteArray();
            final String imgString = Base64.encodeToString(byteFormat, Base64.NO_WRAP);

            if (TextUtils.isEmpty(NameText.getText()))
            {
                NameText.setError(getString(R.string.Name_Required));
            }
            else if (ImageURL.getText().toString().equals(""))
            {

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



