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
import android.widget.Toast;

import com.jgabrielfreitas.core.BlurImageView;

/** class for Take Quiz Activity
 *
 * prompts user to answers questions for the quiz
 *
 * @author Sam Acharya and Caroline Carlson
 * @version 4
 * @since 04-Dec-2018
 */
public class TakeQuiz extends AppCompatActivity {

    Integer spaniel = 0, poodle = 0, pomeranian = 0, labrador = 0, husky = 0;
    Integer goldenRetriever = 0, germanShepherd = 0, collie = 0, bulldog = 0, beagle = 0;
    Boolean question1Checked = false, question2Checked = false, question3Checked = false,
            question4Checked = false, question5Checked = false, question6Checked = false,
            question7Checked = false, question8Checked = false, question9Checked = false,
            question10Checked = false, question11Checked = false;

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
    public void onFinishBtnPressed(View view) {

        if (question1Checked == false) {
            Toast.makeText(TakeQuiz.this, "Question 1 not answered", Toast.LENGTH_LONG).show();
            return;
        } if (question2Checked == false) {
            Toast.makeText(TakeQuiz.this, "Question 2 not answered", Toast.LENGTH_LONG).show();
            return;
        } if (question3Checked == false) {
            Toast.makeText(TakeQuiz.this, "Question 3 not answered", Toast.LENGTH_LONG).show();
            return;
        } if (question4Checked == false) {
            Toast.makeText(TakeQuiz.this, "Question 4 not answered", Toast.LENGTH_LONG).show();
            return;
        } if (question5Checked == false) {
            Toast.makeText(TakeQuiz.this, "Question 5 not answered", Toast.LENGTH_LONG).show();
            return;
        } if (question6Checked == false) {
            Toast.makeText(TakeQuiz.this, "Question 6 not answered", Toast.LENGTH_LONG).show();
            return;
        } if (question7Checked == false) {
            Toast.makeText(TakeQuiz.this, "Question 7 not answered", Toast.LENGTH_LONG).show();
            return;
        } if (question8Checked == false) {
            Toast.makeText(TakeQuiz.this, "Question 8 not answered", Toast.LENGTH_LONG).show();
            return;
        } if (question9Checked == false) {
            Toast.makeText(TakeQuiz.this, "Question 9 not answered", Toast.LENGTH_LONG).show();
            return;
        } if (question10Checked == false) {
            Toast.makeText(TakeQuiz.this, "Question 10 not answered", Toast.LENGTH_LONG).show();
            return;
        } if (question11Checked == false) {
            Toast.makeText(TakeQuiz.this, "Question 11 not answered", Toast.LENGTH_LONG).show();
            return;
        }

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

        //Create Intent for Result Activity
        Intent adopt = new Intent(TakeQuiz.this, ResultActivity.class);
        adopt.putExtra("Breed", result);
        startActivity(adopt);
        finish();
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
        question1Checked = checked;

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
        question2Checked = checked;

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
        question3Checked = checked;

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
        question4Checked = checked;

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
        question5Checked = checked;

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
        question6Checked = checked;

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
        question7Checked = checked;

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
        question8Checked = checked;

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
        question9Checked = checked;

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

    /**
     * onClick method for Quiz Question 10
     *
     * finds which option was selected and adds a point to the matching breeds
     *
     * got help from this tutorial youtube video: https://www.youtube.com/watch?v=HMsNpVOM804
     *
     * @param view
     */
    public void onRadioButton10Clicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();
        question10Checked = checked;

        switch (view.getId()) {
            case R.id.question10A:
                if (checked) {
                    husky += 1;
                    bulldog += 1;
                    break;
                }
            case R.id.question10B:
                if (checked) {
                    spaniel += 1;
                    poodle += 1;
                    pomeranian += 1;
                    labrador += 1;
                    goldenRetriever += 1;
                    germanShepherd += 1;
                    collie += 1;
                    beagle += 1;
                    break;
                }
        }
    }

    /**
     * onClick method for Quiz Question 11
     *
     * finds which option was selected and adds a point to the matching breeds
     *
     * got help from this tutorial youtube video: https://www.youtube.com/watch?v=HMsNpVOM804
     *
     * @param view
     */
    public void onRadioButton11Clicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();
        question11Checked = checked;

        switch (view.getId()) {
            case R.id.question11A:
                if (checked) {
                    pomeranian += 1;
                    germanShepherd += 1;
                    break;
                }
            case R.id.question11B:
                if (checked) {
                    spaniel += 1;
                    poodle += 1;
                    labrador += 1;
                    husky += 1;
                    goldenRetriever += 1;
                    collie += 1;
                    beagle += 1;
                    bulldog += 1;
                    break;
                }
        }
    }
}

