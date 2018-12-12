package edu.calvin.cs262.teamc;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

import java.util.HashMap;
import java.util.Map;

/**
 * class for Main Activity
 *
 * The main screen (or home screen) of the app, which \
 * provides buttons that directs users to take the quiz,
 *      adopt a dog, and/or sell a dog.
 *
 * @author Andrew Thomas and Dana Drosdick
 * @version 3
 * @since 16-11-2018
 */
public class MainActivity extends AppCompatActivity {
    Integer personID=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        // get loginID
        String requestUrl =
                String.format("https://calvincs262-fall2018-teamc.appspot.com/pinder/v1/person/%s",
                        getIntent().getStringExtra("loginID"));

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                requestUrl, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObj = new JSONObject(response);
                    JSONArray arrJson = jsonObj.getJSONArray("items");
                    JSONObject object = arrJson.getJSONObject(0);
                    personID = Integer.parseInt(object.getString("personID"));

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
                return postMap;
            }
        };
        //make the request to your server as indicated in your request url
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);

    }

    /**
     * takes user to Pet For Adoption Activity
     * @param view
     */
    public void onListPethBtnPressed(View view) {
        Intent list = new Intent(MainActivity.this, activity_PetforAdoption.class);
        list.putExtra("personID",personID);
        startActivity(list);
    }

    /**
     * takes user to Adopt Pet Activity
     * @param view
     */
    public void onAdoptPetPressed(View view) {
        Intent adopt = new Intent(MainActivity.this, activity_adoptPet.class);
        adopt.putExtra("personID",personID);
        startActivity(adopt);
    }


    /**
     * takes user to Start Quiz Activity
     * @param view
     */
    public void onStartQuizBtnPressed(View view) {
        Intent match = new Intent(MainActivity.this, StartQuiz.class);
        startActivity(match);
    }

    /**
     * takes user to Profile Activity
     * @param view
     */
    public void onProfilePressed(View view) {
        Intent match = new Intent(MainActivity.this, ProfileActivity.class);
        match.putExtra("loginID",getIntent().getStringExtra("loginID"));
        startActivity(match);
    }

    /**
     * Handler for My Matches Button
     * Launches MyMatchesActivity
     */
    public void onMyMatchesBtnPressed(View view) {
        Intent i = new Intent(MainActivity.this, MyMatchesActivity.class);

        i.putExtra("loginID",getIntent().getStringExtra("loginID"));

        startActivity(i);
    }
}
