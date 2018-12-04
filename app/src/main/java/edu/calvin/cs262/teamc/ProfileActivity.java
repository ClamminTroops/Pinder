package edu.calvin.cs262.teamc;

import android.Manifest;
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


    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.pinderlogov2));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        name = findViewById(R.id.name_profile);
        email = findViewById(R.id.email_profile);
        location= findViewById(R.id.location_profile);
        image = findViewById(R.id.photo_profile);
        loginID = getIntent().getStringExtra("loginID");
        Drawable myDrawable = getResources().getDrawable(R.drawable.person);
        image.setImageDrawable(myDrawable);

        Log.e("loginID",loginID);
        String requestUrl = String.format("https://calvincs262-fall2018-teamc.appspot.com/pinder/v1/person/%s",loginID);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, requestUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(ProfileActivity.this, "Recieved Information!", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject jsonObj = new JSONObject(response);
                    JSONArray arrJson = jsonObj.getJSONArray("items");
                    Log.e("JSON object",arrJson.toString());
                    JSONObject object = arrJson.getJSONObject(0);
                    name.setText(object.getString("name"));
                    email.setText(object.getString("email"));
                    location.setText(object.getString("location"));
                    if (!object.getString("profilePhoto").equals(null))
                    {
                  //  String encodedString = "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";
                    String encodedString = object.getString("profilePhoto");
                    encodedString = encodedString.replace("data:image/jpeg;base64,","");
                    byte[] imageBytes = Base64.decode(encodedString.getBytes(), 0);
                     currentDefault = BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.length);
                    image.setImageBitmap(currentDefault);


                       }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ProfileActivity.this, "Failed", Toast.LENGTH_SHORT).show();

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

                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    File root = new File(Environment.getExternalStorageDirectory(), "Pictures");
                    String time = "profile.jpeg";

                     gpxfile = new File(root, time);

                    Uri uriSavedImage= Uri.parse("file://" + gpxfile.getAbsoluteFile());
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }

                if (gpxfile.length() > 1660000) {

                    Bitmap bitmap = BitmapFactory.decodeFile(gpxfile.getAbsolutePath());
                    image.setImageBitmap(bitmap);

                }

            }

        });


    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new
                        Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }

        }
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


        Bitmap bm= null;
        if (gpxfile != null)
        {
            bm = BitmapFactory.decodeFile(gpxfile.getAbsolutePath());
        } else {
            bm =currentDefault;
        }
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        byte[] byteFormat = stream.toByteArray();
        final String imgString = Base64.encodeToString(byteFormat, Base64.NO_WRAP);
        final String imgStringnew = imgString.substring(imgString.indexOf(",")+1);


        /*
        curl \
         --header "Content-Type: application/json" \
         --request PUT \
        --data '{"profilePhoto":"hello","name":"hellothisjustin","email":"justinbaskaran5@gmail.com","location":"1234567 i dont know this address st."}' \
        https://calvincs262-fall2018-teamc.appspot.com/pinder/v1/player/justinbaskaran

         */
        String requestUrl = String.format("https://calvincs262-fall2018-teamc.appspot.com/pinder/v1/player/%s",loginID);
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, requestUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(ProfileActivity.this, "Successful", Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ProfileActivity.this, "Failed", Toast.LENGTH_SHORT).show();

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

    //TODO: create function for My Matches Button
}
