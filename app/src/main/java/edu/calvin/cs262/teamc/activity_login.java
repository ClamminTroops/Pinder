package edu.calvin.cs262.teamc;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/* class for Login Activity
 * allows users to register a new account, if they are a new user,
 *      or allows users to login, if they already have an account
 */
public class activity_login extends AppCompatActivity {


    List<String> playerList = new ArrayList<>();
    EditText usernameL;
    EditText passwordL;

    EditText usernameC;
    EditText passwordC;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameL = findViewById(R.id.loginUsername);
        passwordL = findViewById(R.id.loginPassword);

        usernameC = findViewById(R.id.SignUpUsername);
        passwordC = findViewById(R.id.SignUpPassword);

        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.pinderlogov2));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");

    }

    /* method for Sign Up Button
     * TODO: creates a new user account with the inputted username and password
     * TODO: rejects the username if already exists and allows user to re-enter
     * directs the user back to the main screen (Main Activity)
     */
    public void createAccount(View view) {
        if (usernameC.getText().toString().length() <= 4  || passwordC.getText().toString().length() <= 4 )
        {
            Toast.makeText(activity_login.this, "Too short! Password and Username must be 4 characters in length", Toast.LENGTH_SHORT).show();
        } else {
            String requestUrl = "https://calvincs262-fall2018-teamc.appspot.com/pinder/v1/player";
            StringRequest stringRequest = new StringRequest(Request.Method.POST, requestUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(activity_login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent createAccount = new Intent(activity_login.this, MainActivity.class);
                    createAccount.putExtra("loginID", usernameC.getText().toString());
                    startActivity(createAccount);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> postMap = new HashMap<>();
                    postMap.put("Content-Type", "application/json");
                    postMap.put("loginID", usernameC.getText().toString());
                    postMap.put("password", passwordC.getText().toString());
                    return postMap;
                }
            };
            //make the request to your server as indicated in your request url
            Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
        }
    }

    /* method for Sign Up Button
     * TODO: logs in the user with the inputted username and password
     * TODO: rejects the username is the username does not exist
     * TODO: rejects the password if it does not match the username
     * directs the user back to the main screen (Main Activity)
     */
    public void logIn(View view) {

        Intent logIn = new Intent(activity_login.this, MainActivity.class);

        String requestUrl = String.format("https://calvincs262-fall2018-teamc.appspot.com/pinder/v1/login/%s/%s",usernameL.getText().toString(),passwordL.getText().toString());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, requestUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Intent logIn = new Intent(activity_login.this, MainActivity.class);
                Log.e("onResponse",response);
                JSONObject jsonObj = null;
                try {
                    jsonObj = new JSONObject(response);
                    JSONArray arrJson = jsonObj.getJSONArray("items");
                    String responses = String.valueOf(arrJson);
                    responses = responses.replace("[", "").replace("\"", "").replace("]", "");
                    Log.e("loginJSON object", responses);
                    if (responses.equals("valid"))
                    {
                        logIn.putExtra("loginID",usernameL.getText().toString());
                        startActivity(logIn);
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
                return postMap;
            }
        };
        //make the request to your server as indicated in your request url
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);

    }
}
