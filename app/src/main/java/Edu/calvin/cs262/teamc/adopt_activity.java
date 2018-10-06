package Edu.calvin.cs262.teamc;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

public class adopt_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adopt_activity);

        ViewPager viewPager = (ViewPager) findViewById(R.id.dogimage);
        ImageAdapter adapter = new ImageAdapter(this);
        viewPager.setAdapter(adapter);


    }

    public void like(View view)
    {
        Toast.makeText(getApplicationContext(), "Liked!",
                Toast.LENGTH_LONG).show();
    }
    public void dislike(View view)
    {
        Toast.makeText(getApplicationContext(), "DisLiked!",
                Toast.LENGTH_LONG).show();
    }


    private class ImageAdapter extends PagerAdapter {
        Context context;
        private int[] GalImages = new int[] {
                R.drawable.puppy,
                R.drawable.puppy2

        };
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
