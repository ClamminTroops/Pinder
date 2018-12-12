package edu.calvin.cs262.teamc;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
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

/** class for Match Activity
 *
 * allows user to see a potential "match" with a dog they have previously liked
 *      and provides the means to contact the dog's owner/seller
 *
 * @author Dana Drosdick
 * @version 2
 * @since 16-11-2018
 */
public class MatchActivity extends AppCompatActivity {

    ImageView owner;
    ImageView pet;
    String[] email = new String[1];

    String owner_string;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.pinderlogov2));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");

        owner = findViewById(R.id.imageView5);
        pet = findViewById(R.id.imageView2);


        Intent caller = getIntent();
        TextView matchTextTv = findViewById(R.id.matchTextTv);
        TextView contactInfoTv = findViewById(R.id.contactInfoTv);


        matchTextTv.setText("You matched with " + caller.getStringExtra("name") + "!");
        contactInfoTv.setText(caller.getStringExtra("name") + getString(R.string.dogs_contact_info));

        Bundle extras = getIntent().getExtras();

        String encodedString = getIntent().getStringExtra("imgId");
        Log.e("encodedString",extras.getString("imgId"));
        byte[] imageBytes = Base64.decode(encodedString.getBytes(), 0);
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.length);
        pet.setImageBitmap(bitmap);



        String requestUrl =
                String.format("https://calvincs262-fall2018-teamc.appspot.com/pinder/v1/person/%s",
                        getIntent().getStringExtra("loginID"));

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, requestUrl, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObj = new JSONObject(response);
                    JSONArray arrJson = jsonObj.getJSONArray("items");
                    Log.e("JSON object",arrJson.toString());
                    JSONObject object = arrJson.getJSONObject(0);
                    email[0] = object.getString("email");
                    owner_string = object.getString("profilePhoto");

                    String encodedString = owner_string;
                    byte[] imageBytes = Base64.decode(encodedString.getBytes(), 0);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.length);
                    owner.setImageBitmap(bitmap);

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
     * function for Action Send Button
     *
     * pulls up email on user's phone so user can email dog owner/seller
     *
     * @param view
     */
    public void ACTION_SEND(View view) {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");

        intent.putExtra(Intent.EXTRA_EMAIL, new String[] { email[0] });
        intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
        intent.putExtra(Intent.EXTRA_TEXT, "mail body");

        startActivity(Intent.createChooser(intent, ""));

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
        Intent home = new Intent(MatchActivity.this, MainActivity.class);
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
        Intent home = new Intent(MatchActivity.this, Help.class);
        startActivity(home);
    }


}
