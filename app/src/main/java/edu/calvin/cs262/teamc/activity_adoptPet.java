package edu.calvin.cs262.teamc;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adopt_activity);
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.pinderlogov2));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");


        viewPager = (ViewPager) findViewById(R.id.pictureWindow);
        adapter = new ImageAdapter(this);
        viewPager.setAdapter(adapter);
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
        Intent match = new Intent(activity_adoptPet.this, MatchActivity.class);
        startActivity(match);
//        Toast.makeText(getApplicationContext(), "Liked!",
//                Toast.LENGTH_LONG).show();
//        viewPager.setCurrentItem(+1);
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
        viewPager.setCurrentItem(+1);
    }

    /**
     * class ImageAdapter for handling pet swipes
     *
     * used specifically for swiping left or right
     */
    private class ImageAdapter extends PagerAdapter {
        Context context;
        private int[] GalImages = new int[] {
                R.drawable.puppy,
                R.drawable.puppy2
        };

        // constructor method for ImageAdapter
        ImageAdapter(Context context){
            this.context=context;

        }
        @Override
        public int getCount() {
            return GalImages.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((ImageView) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setImageResource(GalImages[position]);
            ((ViewPager) container).addView(imageView, 0);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((ImageView) object);
        }
    }
}
