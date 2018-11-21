package edu.calvin.cs262.teamc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MyMatchesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_matches);

        MatchInfo[] matches = new MatchInfo[2]; // For testing purposes
        matches[0] = new MatchInfo("Bozo", "Golden", R.drawable.goldenretrver);
        matches[1] = new MatchInfo("Penny", "Beagle", R.drawable.beagle);

        ListView lv = ((ListView) findViewById(R.id.myMatchesLv));
        MatchAdapter adapter = new MatchAdapter(this, R.layout.match_list_item, matches);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent i = new Intent(getBaseContext(), MatchActivity.class);
                i.putExtra("name", ((MatchInfo) parent.getItemAtPosition(position)).getName());
                i.putExtra("imgId", ((MatchInfo) parent.getItemAtPosition(position)).getImgSrc());
                startActivity(i);
            }
        });

    }



    private class MatchAdapter extends ArrayAdapter<MatchInfo> {

        private final Context context;
        private final MatchInfo[] values;

        public MatchAdapter(Context context, int textViewResourceId,
                                  MatchInfo[] objects) {
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

            pictureIv.setImageResource(values[position].getImgSrc());
            dogNameTv.setText(values[position].getName());
            dogBreedTv.setText(values[position].getBreed());

            return rowView;
        }
    }

}
