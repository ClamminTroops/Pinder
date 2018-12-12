package edu.calvin.cs262.teamc;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.*;
import java.lang.*;

/** class for Take Quiz Activity
 *
 * prompts user to answers questions for the quiz
 *
 * @author Sam Acharya and Caroline Carlson
 * @version 4
 * @since 04-Dec-2018
 */
public class TakeQuiz extends AppCompatActivity {

    int spaniel = 10,
            poodle = 11,
            pomeranian = 12,
            labrador = 13,
            husky = 14,
            goldenRetriever = 15,
            germanShepherd = 16,
            collie = 17,
            bulldog = 18,
            beagle = 19;

    boolean question1Checked = false,
            question2Checked = false,
            question3Checked = false,
            question4Checked = false,
            question5Checked = false,
            question6Checked = false,
            question7Checked = false,
            question8Checked = false,
            question9Checked = false,
            question10Checked = false,
            question11Checked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_quiz);
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.pinderlogov2));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
    }

    /**
     * Determines the breed from the breed's unique number identifier
     *
     * @param difference double
     * @return breed String
     */
    public String determineBreed (int difference) {
        String breed = "";
        if (difference == 10) {
            breed = "Spaniel";
        } else if (difference == 11) {
            breed = "Poodle";
        } else if (difference == 12) {
            breed = "Pomeranian";
        } else if (difference == 13) {
            breed = "Labrador";
        } else if (difference == 14) {
            breed = "Husky";
        } else if (difference == 15) {
            breed = "Golden Retriever";
        } else if (difference == 16) {
            breed = "German Shepherd";
        } else if (difference == 17) {
            breed = "Collie";
        } else if (difference == 18) {
            breed = "Bulldog";
        } else if (difference == 19) {
            breed = "Beagle";
        }
        return breed;
    }

    /**
     * on click method for Done button
     *
     * This method determines the result of the quiz by comparing the points for each breed.
     * The points are compared in a tournament style with brackets.
     * Lastly, the result activity opens with the result information.
     *
     * @param view View
     */
    public void onFinishBtnPressed(View view) {

        // Check that each question was answered
        if (!question1Checked) {
            Toast.makeText(TakeQuiz.this, "Question 1 not answered", Toast.LENGTH_LONG).show();
            return;
        } if (!question2Checked) {
            Toast.makeText(TakeQuiz.this, "Question 2 not answered", Toast.LENGTH_LONG).show();
            return;
        } if (!question3Checked) {
            Toast.makeText(TakeQuiz.this, "Question 3 not answered", Toast.LENGTH_LONG).show();
            return;
        } if (!question4Checked) {
            Toast.makeText(TakeQuiz.this, "Question 4 not answered", Toast.LENGTH_LONG).show();
            return;
        } if (!question5Checked) {
            Toast.makeText(TakeQuiz.this, "Question 5 not answered", Toast.LENGTH_LONG).show();
            return;
        } if (!question6Checked) {
            Toast.makeText(TakeQuiz.this, "Question 6 not answered", Toast.LENGTH_LONG).show();
            return;
        } if (!question7Checked) {
            Toast.makeText(TakeQuiz.this, "Question 7 not answered", Toast.LENGTH_LONG).show();
            return;
        } if (!question8Checked) {
            Toast.makeText(TakeQuiz.this, "Question 8 not answered", Toast.LENGTH_LONG).show();
            return;
        } if (!question9Checked) {
            Toast.makeText(TakeQuiz.this, "Question 9 not answered", Toast.LENGTH_LONG).show();
            return;
        } if (!question10Checked) {
            Toast.makeText(TakeQuiz.this, "Question 10 not answered", Toast.LENGTH_LONG).show();
            return;
        } if (!question11Checked) {
            Toast.makeText(TakeQuiz.this, "Question 11 not answered", Toast.LENGTH_LONG).show();
            return;
        }

        // Create an array for the scores
        int[] intArray = new int[] {spaniel, poodle, pomeranian, labrador, husky,
                                goldenRetriever, germanShepherd, collie, bulldog, beagle};
        // Sort array in descending order
        Arrays.sort(intArray);

        // determine breed and score for the first entry in the array;
        int entry1 = intArray[9];
        int breedIdentifier1 = entry1 % 100;
        String firstBreed = determineBreed(breedIdentifier1);
        int firstScore = (entry1 - breedIdentifier1) / 11;

        // determine breed and score for the first entry in the array;
        int entry2 = intArray[8];
        int breedIdentifier2 = entry2 % 100;
        String secondBreed = determineBreed(breedIdentifier2);
        int secondScore = (entry2 - breedIdentifier2) / 11;

        // determine breed and score for the first entry in the array;
        int entry3 = intArray[7];
        int breedIdentifier3 = entry3 % 100;
        String thirdBreed = determineBreed(breedIdentifier3);
        int thirdScore = (entry3 - breedIdentifier3) / 11;

        // Create Intent for Result Activity
        Intent adopt = new Intent(TakeQuiz.this, ResultActivity.class);
        adopt.putExtra("gold", firstBreed);
        adopt.putExtra("goldScore", firstScore);
        adopt.putExtra("silver", secondBreed);
        adopt.putExtra("silverScore", secondScore);
        adopt.putExtra("bronze", thirdBreed);
        adopt.putExtra("bronzeScore", thirdScore);
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
                    spaniel += 100;
                    pomeranian += 100;
                    beagle += 100;
                    break;
                }
            case R.id.question1B:
                if (checked) {
                    poodle += 100;
                    labrador += 100;
                    husky += 100;
                    goldenRetriever += 100;
                    collie += 100;
                    bulldog += 100;
                    break;
                }
            case R.id.question1C:
                if (checked) {
                    germanShepherd += 100;
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
                    bulldog += 100;
                    beagle += 100;
                    break;
                }
            case R.id.question2B:
                if (checked) {
                    pomeranian += 100;
                    labrador += 100;
                    husky += 100;
                    goldenRetriever += 100;
                    germanShepherd += 100;
                    break;
                }
            case R.id.question2C:
                if (checked) {
                    spaniel += 100;
                    poodle += 100;
                    collie += 100;
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
                    bulldog += 100;
                    break;
                }
            case R.id.question3B:
                if (checked) {
                    spaniel += 100;
                    poodle += 100;
                    pomeranian += 100;
                    collie += 100;
                    break;
                }
            case R.id.question3C:
                if (checked) {
                    labrador += 100;
                    husky += 100;
                    goldenRetriever += 100;
                    germanShepherd += 100;
                    beagle += 100;
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
                    spaniel += 100;
                    poodle += 100;
                    pomeranian += 100;
                    husky += 100;
                    break;
                }
            case R.id.question4C:
                if (checked) {
                    labrador += 100;
                    goldenRetriever += 100;
                    germanShepherd += 100;
                    collie += 100;
                    bulldog += 100;
                    beagle += 100;
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
                    poodle += 100;
                    pomeranian += 100;
                    husky += 100;
                    break;
                }
            case R.id.question5B:
                if (checked) {
                    spaniel += 100;
                    labrador += 100;
                    goldenRetriever += 100;
                    germanShepherd += 100;
                    break;
                }
            case R.id.question5C:
                if (checked) {
                    collie += 100;
                    bulldog += 100;
                    beagle += 100;
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
                    poodle += 100;
                    break;
                }
            case R.id.question6B:
                if (checked) {
                    spaniel += 100;
                    husky += 100;
                    bulldog += 100;
                    beagle += 100;
                    break;
                }
            case R.id.question6C:
                if (checked) {
                    pomeranian += 100;
                    labrador += 100;
                    goldenRetriever += 100;
                    germanShepherd += 100;
                    collie += 100;
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
                    poodle += 100;
                    break;
                }
            case R.id.question7B:
                if (checked) {
                    spaniel += 100;
                    goldenRetriever += 100;
                    break;
                }
            case R.id.question7C:
                if (checked) {
                    pomeranian += 100;
                    labrador += 100;
                    husky += 100;
                    germanShepherd += 100;
                    collie += 100;
                    bulldog += 100;
                    beagle += 100;
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
                    spaniel += 100;
                    poodle += 100;
                    bulldog += 100;
                    break;
                }
            case R.id.question8B:
                if (checked) {
                    pomeranian += 100;
                    goldenRetriever += 100;
                    germanShepherd += 100;
                    collie += 100;
                    beagle += 100;
                    break;
                }
            case R.id.question8C:
                if (checked) {
                    labrador += 100;
                    husky += 100;
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
                    spaniel += 100;
                    poodle += 100;
                    pomeranian += 100;
                    husky += 100;
                    goldenRetriever += 100;
                    germanShepherd += 100;
                    beagle += 100;
                    break;
                }
            case R.id.question9B:
                if (checked) {
                    labrador += 100;
                    collie += 100;
                    break;
                }
            case R.id.question9C:
                if (checked) {
                    bulldog += 100;
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
                    spaniel += 100;
                    poodle += 100;
                    pomeranian += 100;
                    labrador += 100;
                    goldenRetriever += 100;
                    germanShepherd += 100;
                    collie += 100;
                    beagle += 100;
                    break;
                }
            case R.id.question10B:
                if (checked) {
                    husky += 100;
                    bulldog += 100;
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
                    spaniel += 100;
                    poodle += 100;
                    labrador += 100;
                    husky += 100;
                    goldenRetriever += 100;
                    collie += 100;
                    beagle += 100;
                    bulldog += 100;
                    break;
                }
            case R.id.question11B:
                if (checked) {
                    pomeranian += 100;
                    germanShepherd += 100;
                    break;
                }
        }
    }
}

