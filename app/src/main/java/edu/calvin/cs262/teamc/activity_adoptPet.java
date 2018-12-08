package edu.calvin.cs262.teamc;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** class for Adopt Pet Activity
 *
 * It allows users to see profiles of dogs they can adopt
 *      and like/dislike a dog
 *
 * @author Justin Baskaran
 * @version 2
 * @since 16-11-2018
 */
public class activity_adoptPet extends AppCompatActivity {

    ImageAdapter adapter;
    ViewPager viewPager;
    List<Dogs> dogs = new ArrayList<Dogs>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adopt_activity);
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.pinderlogov2));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");


        String requestUrl = "https://calvincs262-fall2018-teamc.appspot.com/pinder/v1/adoptaPet";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, requestUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               // Toast.makeText(activity_adoptPet.this, "Recieved Information!", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject jsonObj = new JSONObject(response);
                    JSONArray arrJson = jsonObj.getJSONArray("items");
                    //Log.e("JSON object",arrJson.toString());
                    for (int i=0; i<arrJson.length(); i++)
                    {
                        JSONObject object = arrJson.getJSONObject(i);
                        Log.e("GetDogs-Name",object.getString("photo"));
                        String photo =object.getString("photo");
                        Log.e("GetDogs-Breed",object.getString("name"));
                        String name =object.getString("name");
                        Log.e("GetDogs-ProfilePhoto",object.getString("breed"));
                        String breed =object.getString("breed");
                        Log.e("GetDogs-ProfilePhoto",object.getString("gender"));
                        String gender =object.getString("gender");


                        Dogs dogsitem = new Dogs();
                        dogsitem.dogid = Integer.parseInt(object.getString("dogID"));
                        dogsitem.photo_string = photo;
                        dogsitem.name_string = name ;
                        dogsitem.breed_string = breed;
                        dogsitem.gender_string = gender;

                        dogs.add(dogsitem);
                    }


                    viewPager = (ViewPager) findViewById(R.id.pictureWindow);
                    adapter = new ImageAdapter(activity_adoptPet.this,dogs);
                    viewPager.setAdapter(adapter);




                    Log.e("onResponse-Length", String.valueOf(dogs.size()));



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // Toast.makeText(activity_adoptPet.this, "Failed", Toast.LENGTH_SHORT).show();

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

    }

    /**
     * method for Like Button
     *
     * creates a toast message saying "Liked!"
     * and swipes to the next dog profile using ImageAdapter
     *
     * TODO: add dog profile to list of matches for that user
     *
     * @param view
     */
    public void likeBtnPressed(View view)
    {
//        Toast.makeText(getApplicationContext(), "Liked!",
//                Toast.LENGTH_LONG).show();
        int index = viewPager.getCurrentItem();

        Bundle extras = getIntent().getExtras();
        final int personID = extras.getInt("personID");
        final int dogid  = dogs.get(index).dogid;

        if (viewPager.getCurrentItem() == dogs.size())
        {

            viewPager.setCurrentItem(-1);
        } else if (viewPager.getCurrentItem() == 0)
        {

            viewPager.setCurrentItem(+1);
        } else {

            viewPager.setCurrentItem(+1);
        }

        String requestUrl = "https://calvincs262-fall2018-teamc.appspot.com/pinder/v1/newMatch";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, requestUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               Toast.makeText(activity_adoptPet.this, "Received Information!", Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activity_adoptPet.this, "Failed", Toast.LENGTH_SHORT).show();

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> postMap = new HashMap<>();
                postMap.put("Content-Type","application/json");
                postMap.put("personID", String.valueOf(personID));
                postMap.put("dogID", String.valueOf(dogid));
                return postMap;
            }
        };
        //make the request to your server as indicated in your request url
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);





        dogs.remove(index);
        adapter.notifyDataSetChanged();
        viewPager.setAdapter(adapter);



    }

    /**
     * method for Dislike Button
     *
     * creates a toast message saying "Disliked!"
     * and swipes to the next dog profile using ImageAdapter
     *
     * @param view
     */
    public void dislikeBtnPressed(View view)
    {


        Toast.makeText(getApplicationContext(), "Disliked!",
                Toast.LENGTH_LONG).show();
        int index = viewPager.getCurrentItem();
        if (viewPager.getCurrentItem() == dogs.size())
        {

            viewPager.setCurrentItem(-1);
        } else if (viewPager.getCurrentItem() == 0)
        {

            viewPager.setCurrentItem(+1);
        } else {

            viewPager.setCurrentItem(+1);
        }





    }

    /**
     * class ImageAdapter for handling pet swipe
     *
     * Handler for My Matches Button
     * Launches MyMatchesActivity
     */
    public void onMyMatchesBtnPressed(View view) {
        Intent i = new Intent(activity_adoptPet.this, MyMatchesActivity.class);
        startActivity(i);
    }

    /* class ImageAdapter for handling pet swipes
     *
     * used specifically for swiping left or right
     */
    private class ImageAdapter extends PagerAdapter {
        Context context;
        List<Dogs> dogs = new ArrayList<Dogs>();


        // constructor method for ImageAdapter
        ImageAdapter(Context context,List<Dogs> listofDogs){
            this.context=context;
            this.dogs = listofDogs;

        }
        @Override
        public int getCount() {
            return dogs.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((ImageView) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            // Create Raw String and add to ..
            String encodedString = dogs.get(position).photo_string;
            //  encodedString = encodedString.replace("data:image/jpeg;base64,","");
            byte[] imageBytes = Base64.decode(encodedString.getBytes(), 0);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.length);
            imageView.setImageBitmap(bitmap);


          //  imageView.setImageResource(GalImages[position]);

            ((ViewPager) container).addView(imageView, 0);

            TextView name = findViewById(R.id.matchName);
            TextView breed = findViewById(R.id.matchBreed);
            TextView gender = findViewById(R.id.matchGender);

            name.setText(dogs.get(position).name_string);
            breed.setText(dogs.get(position).breed_string);
            gender.setText(dogs.get(position).gender_string);

            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((ImageView) object);
        }
    }

    public class Dogs
    {
        Integer dogid;
        String photo_string;
        String name_string;
        String breed_string;
        String gender_string;
    }


}
