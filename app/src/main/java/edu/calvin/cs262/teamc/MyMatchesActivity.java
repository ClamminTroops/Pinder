package edu.calvin.cs262.teamc;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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






        String requestUrl = String.format("https://calvincs262-fall2018-teamc.appspot.com/pinder/v1/matches/%d",personID);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, requestUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
             //   Toast.makeText(MyMatchesActivity.this, "Recieved Information!", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject jsonObj = new JSONObject(response);
                    JSONArray arrJson = jsonObj.getJSONArray("items");
                    //Log.e("JSON object",arrJson.toString());
                    for (int i=0; i<arrJson.length(); i++)
                    {
                        JSONObject object = arrJson.getJSONObject(i);
                        Log.e("GetDogs-Name",object.getString("dogName"));
                        String Name =object.getString("dogName");
                        Log.e("GetDogs-Breed",object.getString("dogBreed"));
                        String Breed =object.getString("dogBreed");
                        Log.e("GetDogs-ProfilePhoto",object.getString("profilePicture"));
                        String Photo =object.getString("profilePicture");

                        MatchInfo mi = new MatchInfo(Name,Breed,Photo);
                       matches.add(mi);



                    }

                    Log.e("Volley-MatchesList", String.valueOf(matches.size()));

                    MatchAdapter adapter = new MatchAdapter(MyMatchesActivity.this, R.layout.match_list_item, matches);
                    lv.setAdapter(adapter);

                    TextView tv1 = findViewById(R.id.matches_background_text);
                    if (matches.size() == 0)
                    {
                        lv.setVisibility(View.GONE);
                        tv1.setVisibility(View.VISIBLE);

                    } else {
                        lv.setVisibility(View.VISIBLE);
                        tv1.setVisibility(View.GONE);

                    }





                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
             //   Toast.makeText(MyMatchesActivity.this, "Failed", Toast.LENGTH_SHORT).show();

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

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent i = new Intent(getBaseContext(), MatchActivity.class);
                i.putExtra("loginID",getIntent().getStringExtra("loginID"));
                i.putExtra("name", matches.get(position).getName());
                Log.e("imgID", matches.get(position).imgSrc);
                i.putExtra("imgId", matches.get(position).imgSrc);
                startActivity(i);
            }
        });

    }



    private class MatchAdapter extends ArrayAdapter<MatchInfo> {

        private  Context context;
        private  List<MatchInfo> values = new ArrayList<MatchInfo>();

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

            ImageView pictureIv = (ImageView) rowView.findViewById(R.id.dogImageIv);
            TextView dogNameTv = (TextView) rowView.findViewById(R.id.dogNameTv);
            TextView dogBreedTv = (TextView) rowView.findViewById(R.id.dogBreedTv);
            Log.e("getView-POsition", String.valueOf(position));
            Log.e("getView-Values",String.valueOf(values.get(position).getImgSrc()));

            Log.e("Size", String.valueOf(values.size()));





            dogNameTv.setText(values.get(position).getName());
            dogBreedTv.setText(values.get(position).getBreed());


            Log.e("Image Source ",values.get(position).imgSrc);

            String encodedString = values.get(position).imgSrc;
          //  encodedString = encodedString.replace("data:image/jpeg;base64,","");
            byte[] imageBytes = Base64.decode(encodedString.getBytes(), 0);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.length);
            pictureIv.setImageBitmap(bitmap);




            return rowView;
        }
    }

}
