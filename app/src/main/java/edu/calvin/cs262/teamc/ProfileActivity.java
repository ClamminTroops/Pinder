package edu.calvin.cs262.teamc;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static edu.calvin.cs262.teamc.R.drawable.person;

/** class for Profile Activity
 *
 * allows user to make and save changes to their profile
 *      and to access their matches (taking them to Match Activity)
 *
 * @author Gavin Martin
 * @version 1
 * @since 16-11-2018
 */
public class ProfileActivity extends AppCompatActivity {
    String loginID;
    EditText name;
    EditText email;
    EditText location;
    ImageView image;
    File gpxfile=null;
    Bitmap currentDefault;
    Uri uriSavedImage;

    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_profile);
//        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.pinderlogov2));
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setTitle("");
        getSupportActionBar().hide();



        name = findViewById(R.id.name_profile);
        email = findViewById(R.id.email_profile);
        location= findViewById(R.id.location_profile);
        image = findViewById(R.id.photo_profile);
        loginID = getIntent().getStringExtra("loginID");

        Drawable myDrawable = getResources().getDrawable(R.drawable.person);
        image.setImageDrawable(myDrawable);

        String requestUrl = String.format(
                "https://calvincs262-fall2018-teamc.appspot.com/pinder/v1/person/%s",loginID);
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, requestUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObj = new JSONObject(response);
                    JSONArray arrJson = jsonObj.getJSONArray("items");
                    Log.e("JSON object",arrJson.toString());
                    JSONObject object = arrJson.getJSONObject(0);
                    name.setText(object.getString("name"));
                    email.setText(object.getString("email"));
                    location.setText(object.getString("location"));
                    if (!object.getString("profilePhoto").equals(null)) {

                        String encodedString = object.getString("profilePhoto");
                        encodedString = encodedString
                                .replace("data:image/jpeg;base64,", "");
                        byte[] imageBytes = Base64.decode(encodedString.getBytes(), 0);
                        currentDefault = BitmapFactory.decodeByteArray(
                                imageBytes, 0, imageBytes.length);
                        image.setImageBitmap(currentDefault);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> postMap = new HashMap<>();
                postMap.put("Content-Type","application/json");
                //..... Add as many key value pairs in the map as necessary for your request
                return postMap;
            }
        };
        //make the request to your server as indicated in your request url
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);

        image.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                gpxfile = null;

                if (checkSelfPermission(Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA},
                            MY_CAMERA_PERMISSION_CODE);
                } else {
                    StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                    StrictMode.setVmPolicy(builder.build());

                    Intent cameraIntent = new Intent(
                            android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    File root = new File(
                            Environment.getExternalStorageDirectory(), "Pictures");
                    String time = "profile.jpeg";

                     gpxfile = new File(root, time);

                     uriSavedImage= Uri.parse("file://" + gpxfile.getAbsoluteFile());
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }

            }

        });


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ContentResolver cr = this.getContentResolver();

        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = null;
            try {
                photo = MediaStore.Images.Media.getBitmap(cr, uriSavedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
            image.setImageBitmap(photo);
        }
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent cameraIntent = new
                        Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        }
    }

    /* Handler for My Matches Button
     * Launches MyMatchesActivity
     */
    public void onMyMatchesBtnPressed(View view) {
        Integer personID=null;

        Intent i = new Intent(ProfileActivity.this, MyMatchesActivity.class);
        i.putExtra("loginID",getIntent().getStringExtra("loginID"));
        i.putExtra("personID",getIntent().getIntExtra("personID", 0));
        startActivity(i);
    }

    /**
     * function for Save Changes Button
     *
     * TODO: saves changes made to profile
     * and returns to Main Activity
     *
     * @param view
     */
    public void saveChanges(View view) {
        final String names = name.getText().toString();
        final String emails = email.getText().toString();
        final String locations = location.getText().toString();

        image.buildDrawingCache();
        Bitmap bm = image.getDrawingCache();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        byte[] byteFormat = stream.toByteArray();
        final String imgString = Base64.encodeToString(byteFormat, Base64.NO_WRAP);
        final String imgStringnew = imgString.substring(imgString.indexOf(",")+1);

        String requestUrl = String.format(
                "https://calvincs262-fall2018-teamc.appspot.com/pinder/v1/player/%s",loginID);
        StringRequest stringRequest = new StringRequest(
                Request.Method.PUT, requestUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> postMap = new HashMap<>();
                postMap.put("Content-Type","application/json");
                postMap.put("name", names);
                postMap.put("email",emails);
                postMap.put("location",locations);
                postMap.put("profilePhoto",imgStringnew);

                //..... Add as many key value pairs in the map as necessary for your request
                return postMap;
            }
        };
        //make the request to your server as indicated in your request url
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
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
        Intent home = new Intent(ProfileActivity.this, MainActivity.class);
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
        Intent home = new Intent(ProfileActivity.this, Help.class);
        startActivity(home);
    }

}
