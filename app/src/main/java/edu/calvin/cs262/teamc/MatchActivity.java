package edu.calvin.cs262.teamc;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/* class for Match Activity
 * allows user to see a potential "match" with a dog they have previously liked
 *      and provides the means to contact the dog's owner/seller
 * TODO: Provide a list of matches, not just one match (double check with Gavin as to the layout)
 */
public class MatchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.pinderlogov2));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");

    }

    //TODO: This function should be deleted, since this button is not in activity_match.xml
    public void onAdoptOneBtnPressed(View view) {
    }

    /* function for Action Send Button
     * pulls up email on user's phone so user can email dog owner/seller
     */
    public void ACTION_SEND(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "some@email.address" });
        intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
        intent.putExtra(Intent.EXTRA_TEXT, "mail body");
        startActivity(Intent.createChooser(intent, ""));
    }
}
