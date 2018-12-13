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
        getSupportActionBar().hide();


        String requestUrl = "https://calvincs262-fall2018-teamc.appspot.com/pinder/v1/adoptaPet";
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                requestUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObj = new JSONObject(response);
                    JSONArray arrJson = jsonObj.getJSONArray("items");
                    for (int i=0; i<arrJson.length(); i++)
                    {
                        JSONObject object = arrJson.getJSONObject(i);
                        Log.e("GetDogs-Name",object.getString("photo"));
                        String photo = object.getString("photo");
                        Log.e("GetDogs-Breed",object.getString("name"));
                        String name = object.getString("name");
                        Log.e("GetDogs-ProfilePhoto",object.getString("breed"));
                        String breed = object.getString("breed");
                        Log.e("GetDogs-ProfilePhoto",object.getString("gender"));
                        String gender = object.getString("gender");

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

                    viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        public void onPageScrollStateChanged(int state) {
                        }

                        public void onPageScrolled(
                                int position, float positionOffset, int positionOffsetPixels) {

                            // Find views
                            TextView name = findViewById(R.id.matchName);
                            TextView breed = findViewById(R.id.matchBreed);
                            TextView gender = findViewById(R.id.matchGender);
                            // Set view information
                            name.setText(dogs.get(position).name_string);
                            breed.setText(dogs.get(position).breed_string);
                            gender.setText(dogs.get(position).gender_string);
                        }

                        public void onPageSelected(int position) {
                        }
                    });

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
        Intent home = new Intent(activity_adoptPet.this, MainActivity.class);
        home.putExtra("personID", getIntent().getExtras().getInt("personID"));
        startActivity(home);
    }



    /**
     * method for Like Button
     *
     * Sends a request to the server to create a match
     * and swipes to the next dog profile using ImageAdapter
     *
     * TODO: add dog profile to list of matches for that user
     *
     * @param view
     */
    public void likeBtnPressed(View view)
    {

        int index = viewPager.getCurrentItem();

        // Get information for POST request
        Bundle extras = getIntent().getExtras();
        final int personID = extras.getInt("personID");
        final int dogid  = dogs.get(index).dogid;

        String requestUrl = "https://calvincs262-fall2018-teamc.appspot.com/pinder/v1/newMatch";
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST, requestUrl, new Response.Listener<String>() {
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
                postMap.put("personID", String.valueOf(personID));
                postMap.put("dogID", String.valueOf(dogid));
                return postMap;
            }
        };
        //make the request to your server as indicated in your request url
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);

        // Remove the dog liked and advance the view pager
        dogs.remove(index);
        adapter.notifyDataSetChanged();
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem( viewPager.getCurrentItem() == dogs.size() ? 0
                : viewPager.getCurrentItem() + 1);

    }

    /**
     * method for Dislike Button
     *
     * Swipes to the next dog in the ViewPager
     *
     * @param view
     */
    public void dislikeBtnPressed(View view)
    {
        // If the next item is over the end of the list, loop to the beggining
        viewPager.setCurrentItem( viewPager.getCurrentItem() + 1 == dogs.size() ? 0
                : viewPager.getCurrentItem() + 1);

    }

    /**
     * class ImageAdapter for handling pet swipe
     *
     * Handler for My Matches Button
     * Launches MyMatchesActivity
     */
    public void onMyMatchesBtnPressed(View view) {
        Bundle extras = getIntent().getExtras();
        final int personID = extras.getInt("personID");

        Intent i = new Intent(activity_adoptPet.this, MyMatchesActivity.class);
        i.putExtra("personID", personID);
        startActivity(i);
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
        Intent home = new Intent(activity_adoptPet.this, Help.class);
        startActivity(home);
    }

    /* class ImageAdapter for handling pet swipes
     *
     * used specifically for swiping left or right
     */
    private class ImageAdapter extends PagerAdapter {
        Context context;
        List<Dogs> dogs;

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

            // Convert image string to image
            String encodedString = dogs.get(position).photo_string;
            byte[] imageBytes = Base64.decode(encodedString.getBytes(), 0);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.length);
            imageView.setImageBitmap(bitmap);

            ((ViewPager) container).addView(imageView, 0);

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
