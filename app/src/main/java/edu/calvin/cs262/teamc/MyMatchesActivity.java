package edu.calvin.cs262.teamc;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
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
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** class for My MAtches Activity
 *
 * It allows users to see all the dogs they have matched with
 *
 * @author Justin Baskaran
 * @version 2
 * @since 16-11-2018
 */
public class MyMatchesActivity extends AppCompatActivity {
    Integer personID;
    List<MatchInfo> matches= new ArrayList<MatchInfo>();
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_matches);
        Bundle extras = getIntent().getExtras();
        personID = extras.getInt("personID");
        lv = ((ListView) findViewById(R.id.myMatchesLv));


        String requestUrl = String.format(
                "https://calvincs262-fall2018-teamc.appspot.com/pinder/v1/matches/%d", personID);

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                requestUrl, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    // Convert response to JSON
                    JSONObject jsonObj = new JSONObject(response);
                    JSONArray arrJson = jsonObj.getJSONArray("items");
                    // Add each match to memory
                    for (int i=0; i<arrJson.length(); i++)
                    {
                        JSONObject object = arrJson.getJSONObject(i);
                        String Name =object.getString("dogName");
                        String Breed =object.getString("dogBreed");
                        String Photo =object.getString("profilePicture");

                        MatchInfo mi = new MatchInfo(Name,Breed,Photo);
                        matches.add(mi);

                    }

                    // Populate ListView
                    MatchAdapter adapter = new MatchAdapter(
                            MyMatchesActivity.this, R.layout.match_list_item, matches);
                    lv.setAdapter(adapter);
                    TextView label = findViewById(R.id.myMatches_labelTv);

                    if (matches.size() == 0)
                    {
                        label.setText(R.string.myMatches_noMatches);
                    } else {
                        label.setText(R.string.myMatches_myMAthces);
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
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);

        // Proc each ListView item to open the activity of the corresponding match
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent i = new Intent(getBaseContext(), MatchActivity.class);

                i.putExtra("loginID",getIntent().getStringExtra("loginID"));
                i.putExtra("name", matches.get(position).getName());
                i.putExtra("imgId", matches.get(position).imgSrc);

                startActivity(i);
            }
        });

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
        Intent home = new Intent(MyMatchesActivity.this, MainActivity.class);
        startActivity(home);
    }


    /** MatchAdapter
     *
     *  ArrayAdapter extension for the matches ListView
     *
     */
    private class MatchAdapter extends ArrayAdapter<MatchInfo> {

        private  Context context;
        private  List<MatchInfo> values;

        public MatchAdapter(Context context, int textViewResourceId,
                            List<MatchInfo> objects) {
            super(context, textViewResourceId, objects);
            this.context = context;
            this.values = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View rowView = inflater.inflate(R.layout.match_list_item, parent, false);

            TextView dogNameTv = (TextView) rowView.findViewById(R.id.dogNameTv);
            TextView dogBreedTv = (TextView) rowView.findViewById(R.id.dogBreedTv);

            dogNameTv.setText(values.get(position).getName());
            dogBreedTv.setText(values.get(position).getBreed());

            ImageView pictureIv = (ImageView) rowView.findViewById(R.id.dogImageIv);

            String encodedString = values.get(position).imgSrc;
            encodedString = encodedString.replace("data:image/jpeg;base64,","");
            byte[] imageBytes = Base64.decode(encodedString.getBytes(), 0);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.length);

            pictureIv.setImageBitmap(bitmap);

            return rowView;
        }
    }

}
