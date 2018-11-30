package edu.calvin.cs262.teamc;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jgabrielfreitas.core.BlurImageView;

/** class for Take Quiz Activity
 *
 * prompts user to answers questions for the quiz
 * TODO: Update questions for the quiz and add new quiz activities as necessary
 * TODO: keep track of user answers to the questions
 *
 * @author Sam Acharya and Caroline Carlson
 * @version 2
 * @since 16-11-2018
 */
public class TakeQuiz extends AppCompatActivity {

    String result;
    Integer spaniel = 0, poodle = 0, pomeranian = 0, labrador = 0, husky = 0;
    Integer goldenRetriever = 0, germanShepherd = 0, collie = 0, bulldog = 0, beagle = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_quiz);
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.pinderlogov2));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
    }

    /**
     * on click method for Done button
     *
     * This method determines the result of the quiz by comparing the points for each breed.
     * The points are compared in a tournament style with brackets.
     * Lastly, the result activity opens with the result information.
     *
     * @param view
     */
    public void onDoneBtnPressed(View view) {

        String bracket1A, bracket1B, bracket1C, bracket1D,
                bracket1E, bracket2A, bracket2B, result;
        Integer score1A, score1B, score1C, score1D, score1E,
                score2A, score2B;

        /**
         * Bracket #1
         */
        if (spaniel > poodle) {
            bracket1A = "Spaniel";
            score1A = spaniel;
        } else {
            bracket1A = "Poodle";
            score1A = poodle;
        }
        if (pomeranian > labrador) {
            bracket1B = "Pomeranian:";
            score1B = pomeranian;
        } else {
            bracket1B = "Labrador";
            score1B = labrador;
        }
        if (husky > goldenRetriever) {
            bracket1C = "Husky";
            score1C = husky;
        } else {
            bracket1C = "Golden Retriever";
            score1C = goldenRetriever;
        }
        if (germanShepherd > collie) {
            bracket1D = "German Shepherd";
            score1D = germanShepherd;
        } else {
            bracket1D = "Collie";
            score1D = collie;
        }
        if (bulldog > beagle) {
            bracket1E = "Bulldog";
            score1E = bulldog;
        } else {
            bracket1E = "Beagle";
            score1E = beagle;
        }

        /**
         * Bracket #2
         */
        if (score1A > score1B) {
            bracket2A = bracket1A;
            score2A = score1A;
        } else {
            bracket2A = bracket1B;
            score2A = score1B;
        }
        if (score1C > score1D) {
            bracket2B = bracket1C;
            score2B = score1C;
        } else {
            bracket2B = bracket1D;
            score2B = score1C;
        }
        if (score1E > score2B) {
            bracket2B = bracket1E;
            score2B = score1E;
        }

        /**
         * Bracket #3
         */
        if (score2A > score2B) {
            result = bracket2A;
        } else {
            result = bracket2B;
        }

        Intent adopt = new Intent(TakeQuiz.this, ResultActivity.class);
        adopt.putExtra("Breed", result);
        startActivity(adopt);

        //actionBar.setTitle("");
        //myBlurImage =  (BlurImageView) findViewById(R.id.myBlurImage);
        //myBlurImage.setBlur(15);
    }

    /**
     * onClick method for Quiz Question 1
     *
     * finds which option was selected and adds a point to the matching breeds
     *
     * got help from this tutorial youtube video: https://www.youtube.com/watch?v=HMsNpVOM804
     *
     * @param view
     */
    public void onRadioButton1Clicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.question1A:
                if (checked) {
                    spaniel += 1;
                    pomeranian += 1;
                    beagle += 1;
                    break;
                }
            case R.id.question1B:
                if (checked) {
                    poodle += 1;
                    labrador += 1;
                    husky += 1;
                    goldenRetriever += 1;
                    collie += 1;
                    bulldog += 1;
                    break;
                }
            case R.id.question1C:
                if (checked) {
                    germanShepherd += 1;
                    break;
                }
        }
    }

    /**
     * onClick method for Quiz Question 2
     *
     * finds which option was selected and adds a point to the matching breeds
     *
     * got help from this tutorial youtube video: https://www.youtube.com/watch?v=HMsNpVOM804
     *
     * @param view
     */
    public void onRadioButton2Clicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.question2A:
                if (checked) {
                    bulldog += 1;
                    beagle += 1;
                    break;
                }
            case R.id.question2B:
                if (checked) {
                    pomeranian += 1;
                    labrador += 1;
                    husky += 1;
                    goldenRetriever += 1;
                    germanShepherd += 1;
                    break;
                }
            case R.id.question2C:
                if (checked) {
                    spaniel += 1;
                    poodle += 1;
                    collie += 1;
                    break;
                }
        }
    }

    /**
     * onClick method for Quiz Question 3
     *
     * finds which option was selected and adds a point to the matching breeds
     *
     * got help from this tutorial youtube video: https://www.youtube.com/watch?v=HMsNpVOM804
     *
     * @param view
     */
    public void onRadioButton3Clicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.question3A:
                if (checked) {
                    bulldog += 1;
                    break;
                }
            case R.id.question3B:
                if (checked) {
                    spaniel += 1;
                    poodle += 1;
                    pomeranian += 1;
                    collie += 1;
                    break;
                }
            case R.id.question3C:
                if (checked) {
                    labrador += 1;
                    husky += 1;
                    goldenRetriever += 1;
                    germanShepherd += 1;
                    beagle += 1;
                    break;
                }
        }
    }

    /**
     * onClick method for Quiz Question 4
     *
     * finds which option was selected and adds a point to the matching breeds
     *
     * got help from this tutorial youtube video: https://www.youtube.com/watch?v=HMsNpVOM804
     *
     * @param view
     */
    public void onRadioButton4Clicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.question4A:
                if (checked) {
                    break;
                }
            case R.id.question4B:
                if (checked) {
                    spaniel += 1;
                    poodle += 1;
                    pomeranian += 1;
                    husky += 1;
                    break;
                }
            case R.id.question4C:
                if (checked) {
                    labrador += 1;
                    goldenRetriever += 1;
                    germanShepherd += 1;
                    collie += 1;
                    bulldog += 1;
                    beagle += 1;
                    break;
                }
        }
    }

    /**
     * onClick method for Quiz Question 5
     *
     * finds which option was selected and adds a point to the matching breeds
     *
     * got help from this tutorial youtube video: https://www.youtube.com/watch?v=HMsNpVOM804
     *
     * @param view
     */
    public void onRadioButton5Clicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.question5A:
                if (checked) {
                    poodle += 1;
                    pomeranian += 1;
                    husky += 1;
                    break;
                }
            case R.id.question5B:
                if (checked) {
                    spaniel += 1;
                    labrador += 1;
                    goldenRetriever += 1;
                    germanShepherd += 1;
                    break;
                }
            case R.id.question5C:
                if (checked) {
                    collie += 1;
                    bulldog += 1;
                    beagle += 1;
                    break;
                }
        }
    }

    /**
     * onClick method for Quiz Question 6
     *
     * finds which option was selected and adds a point to the matching breeds
     *
     * got help from this tutorial youtube video: https://www.youtube.com/watch?v=HMsNpVOM804
     *
     * @param view
     */
    public void onRadioButton6Clicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.question6A:
                if (checked) {
                    poodle += 1;
                    break;
                }
            case R.id.question6B:
                if (checked) {
                    spaniel += 1;
                    husky += 1;
                    bulldog += 1;
                    beagle += 1;
                    break;
                }
            case R.id.question6C:
                if (checked) {
                    pomeranian += 1;
                    labrador += 1;
                    goldenRetriever += 1;
                    germanShepherd += 1;
                    collie += 1;
                    break;
                }
        }
    }

    /**
     * onClick method for Quiz Question 7
     *
     * finds which option was selected and adds a point to the matching breeds
     *
     * got help from this tutorial youtube video: https://www.youtube.com/watch?v=HMsNpVOM804
     *
     * @param view
     */
    public void onRadioButton7Clicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.question7A:
                if (checked) {
                    poodle += 1;
                    break;
                }
            case R.id.question7B:
                if (checked) {
                    spaniel += 1;
                    goldenRetriever += 1;
                    break;
                }
            case R.id.question7C:
                if (checked) {
                    pomeranian += 1;
                    labrador += 1;
                    husky += 1;
                    germanShepherd += 1;
                    collie += 1;
                    bulldog += 1;
                    beagle += 1;
                    break;
                }
        }
    }

    /**
     * onClick method for Quiz Question 8
     *
     * finds which option was selected and adds a point to the matching breeds
     *
     * got help from this tutorial youtube video: https://www.youtube.com/watch?v=HMsNpVOM804
     *
     * @param view
     */
    public void onRadioButton8Clicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.question8A:
                if (checked) {
                    spaniel += 1;
                    poodle += 1;
                    pomeranian += 1;
                    husky += 1;
                    goldenRetriever += 1;
                    germanShepherd += 1;
                    beagle += 1;
                    break;
                }
            case R.id.question8B:
                if (checked) {
                    labrador += 1;
                    collie += 1;
                    break;
                }
            case R.id.question8C:
                if (checked) {
                    bulldog += 1;
                    break;
                }
        }
    }

    /**
     * onClick method for Quiz Question 9
     *
     * finds which option was selected and adds a point to the matching breeds
     *
     * got help from this tutorial youtube video: https://www.youtube.com/watch?v=HMsNpVOM804
     *
     * @param view
     */
    public void onRadioButton9Clicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.question9A:
                if (checked) {
                    spaniel += 1;
                    poodle += 1;
                    bulldog += 1;
                    break;
                }
            case R.id.question9B:
                if (checked) {
                    labrador += 1;
                    husky += 1;
                    break;
                }
            case R.id.question9C:
                if (checked) {
                    pomeranian += 1;
                    goldenRetriever += 1;
                    germanShepherd += 1;
                    collie += 1;
                    beagle += 1;
                    break;
                }
        }
    }
}

