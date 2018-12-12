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

    HashMap<String,Integer> breeds = new HashMap<>();
    Boolean question1Checked = false,
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
        getSupportActionBar().hide();

        breeds.put("Spaniel",0);
        breeds.put("Poodle",0);
        breeds.put("Pomeranian",0);
        breeds.put("Labrador",0);
        breeds.put("Husky",0);
        breeds.put("Golden Retriever",0);
        breeds.put("German Shepherd",0);
        breeds.put("Collie",0);
        breeds.put("Bulldog",0);
        breeds.put("Beagle",0);
    }

    /**
     * function to sort HashMap by values
     *
     * taken from: https://www.geeksforgeeks.org/sorting-a-hashmap-according-to-values/
     */
    public static HashMap<String, Integer> sortByValue (HashMap<String, Integer> hm) {

        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list = new LinkedList<>(hm.entrySet());

        // Sort the list in ascending order
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // Reverse list in descending order
        Collections.reverse(list);

        // put data from sorted list into LinkedHashMap
        HashMap<String, Integer> temp = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
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

        // Sort scores in a LinkedHashMap
        HashMap<String, Integer> breedsSorted = sortByValue(breeds);

        // Get first entry
        Map.Entry<String, Integer> mapEntry = breedsSorted.entrySet().iterator().next();
        String firstBreed = mapEntry.getKey();
        Integer firstScore = mapEntry.getValue() * 100 / 11;
        breedsSorted.remove(firstBreed);

        // Get second entry
        mapEntry = breedsSorted.entrySet().iterator().next();
        String secondBreed = mapEntry.getKey();
        Integer secondScore = mapEntry.getValue() * 100 / 11;
        breedsSorted.remove(secondBreed);

        // Get third entry
        mapEntry = breedsSorted.entrySet().iterator().next();
        String thirdBreed = mapEntry.getKey();
        Integer thirdScore = mapEntry.getValue() * 100 / 11;

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
                    breeds.put("Spaniel", breeds.get("Spaniel") + 1);
                    breeds.put("Pomeranian", breeds.get("Pomeranian") + 1);
                    breeds.put("Beagle", breeds.get("Beagle") + 1);
                    break;
                }
            case R.id.question1B:
                if (checked) {
                    breeds.put("Poodle", breeds.get("Poodle") + 1);
                    breeds.put("Labrador", breeds.get("Labrador") + 1);
                    breeds.put("Husky", breeds.get("Husky") + 1);
                    breeds.put("Golden Retriever", breeds.get("Golden Retriever") + 1);
                    breeds.put("Collie", breeds.get("Collie") + 1);
                    breeds.put("Bulldog", breeds.get("Bulldog") + 1);
                    break;
                }
            case R.id.question1C:
                if (checked) {
                    breeds.put("German Shepherd", breeds.get("German Shepherd") + 1);
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
                    breeds.put("Bulldog", breeds.get("Bulldog") + 1);
                    breeds.put("Beagle", breeds.get("Beagle") + 1);
                    break;
                }
            case R.id.question2B:
                if (checked) {
                    breeds.put("Pomeranian", breeds.get("Pomeranian") + 1);
                    breeds.put("Labrador", breeds.get("Labrador") + 1);
                    breeds.put("Husky", breeds.get("Husky") + 1);
                    breeds.put("Golden Retriever", breeds.get("Golden Retriever") + 1);
                    breeds.put("German Shepherd", breeds.get("German Shepherd") + 1);
                    break;
                }
            case R.id.question2C:
                if (checked) {
                    breeds.put("Spaniel", breeds.get("Spaniel") + 1);
                    breeds.put("Poodle", breeds.get("Poodle") + 1);
                    breeds.put("Collie", breeds.get("Collie") + 1);
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
                    breeds.put("Bulldog", breeds.get("Bulldog") + 1);
                    break;
                }
            case R.id.question3B:
                if (checked) {
                    breeds.put("Spaniel", breeds.get("Spaniel") + 1);
                    breeds.put("Poodle", breeds.get("Poodle") + 1);
                    breeds.put("Pomeranian", breeds.get("Pomeranian") + 1);
                    breeds.put("Collie", breeds.get("Collie") + 1);
                    break;
                }
            case R.id.question3C:
                if (checked) {
                    breeds.put("Labrador", breeds.get("Labrador") + 1);
                    breeds.put("Husky", breeds.get("Husky") + 1);
                    breeds.put("Golden Retriever", breeds.get("Golden Retriever") + 1);
                    breeds.put("German Shepherd", breeds.get("German Shepherd") + 1);
                    breeds.put("Beagle", breeds.get("Beagle") + 1);
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
                    breeds.put("Spaniel", breeds.get("Spaniel") + 1);
                    breeds.put("Poodle", breeds.get("Poodle") + 1);
                    breeds.put("Pomeranian", breeds.get("Pomeranian") + 1);
                    breeds.put("Husky", breeds.get("Husky") + 1);
                    break;
                }
            case R.id.question4C:
                if (checked) {
                    breeds.put("Labrador", breeds.get("Labrador") + 1);
                    breeds.put("Golden Retriever", breeds.get("Golden Retriever") + 1);
                    breeds.put("German Shepherd", breeds.get("German Shepherd") + 1);
                    breeds.put("Collie", breeds.get("Collie") + 1);
                    breeds.put("Bulldog", breeds.get("Bulldog") + 1);
                    breeds.put("Beagle", breeds.get("Beagle") + 1);
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
                    breeds.put("Poodle", breeds.get("Poodle") + 1);
                    breeds.put("Pomeranian", breeds.get("Pomeranian") + 1);
                    breeds.put("Husky", breeds.get("Husky") + 1);
                    break;
                }
            case R.id.question5B:
                if (checked) {
                    breeds.put("Spaniel", breeds.get("Spaniel") + 1);
                    breeds.put("Labrador", breeds.get("Labrador") + 1);
                    breeds.put("Golden Retriever", breeds.get("Golden Retriever") + 1);
                    breeds.put("German Shepherd", breeds.get("German Shepherd") + 1);
                    break;
                }
            case R.id.question5C:
                if (checked) {
                    breeds.put("Collie", breeds.get("Collie") + 1);
                    breeds.put("Bulldog", breeds.get("Bulldog") + 1);
                    breeds.put("Beagle", breeds.get("Beagle") + 1);
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
                    breeds.put("Poodle", breeds.get("Poodle") + 1);
                    break;
                }
            case R.id.question6B:
                if (checked) {
                    breeds.put("Spaniel", breeds.get("Spaniel") + 1);
                    breeds.put("Husky", breeds.get("Husky") + 1);
                    breeds.put("Bulldog", breeds.get("Bulldog") + 1);
                    breeds.put("Beagle", breeds.get("Beagle") + 1);
                    break;
                }
            case R.id.question6C:
                if (checked) {
                    breeds.put("Pomeranian", breeds.get("Pomeranian") + 1);
                    breeds.put("Labrador", breeds.get("Labrador") + 1);
                    breeds.put("Golden Retriever", breeds.get("Golden Retriever") + 1);
                    breeds.put("German Shepherd", breeds.get("German Shepherd") + 1);
                    breeds.put("Collie", breeds.get("Collie") + 1);
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
                    breeds.put("Poodle", breeds.get("Poodle") + 1);
                    break;
                }
            case R.id.question7B:
                if (checked) {
                    breeds.put("Spaniel", breeds.get("Spaniel") + 1);
                    breeds.put("Golden Retriever", breeds.get("Golden Retriever") + 1);
                    break;
                }
            case R.id.question7C:
                if (checked) {
                    breeds.put("Pomeranian", breeds.get("Pomeranian") + 1);
                    breeds.put("Labrador", breeds.get("Labrador") + 1);
                    breeds.put("Husky", breeds.get("Husky") + 1);
                    breeds.put("German Shepherd", breeds.get("German Shepherd") + 1);
                    breeds.put("Collie", breeds.get("Collie") + 1);
                    breeds.put("Bulldog", breeds.get("Bulldog") + 1);
                    breeds.put("Beagle", breeds.get("Beagle") + 1);
                    break;
                }
        }
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
        Intent home = new Intent( TakeQuiz.this, MainActivity.class);
        startActivity(home);
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
        Intent home = new Intent(TakeQuiz.this, Help.class);
        startActivity(home);
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
                    breeds.put("Spaniel", breeds.get("Spaniel") + 1);
                    breeds.put("Poodle", breeds.get("Poodle") + 1);
                    breeds.put("Pomeranian", breeds.get("Pomeranian") + 1);
                    breeds.put("Husky", breeds.get("Husky") + 1);
                    breeds.put("Golden Retriever", breeds.get("Golden Retriever") + 1);
                    breeds.put("German Shepherd", breeds.get("German Shepherd") + 1);
                    breeds.put("Beagle", breeds.get("Beagle") + 1);
                    break;
                }
            case R.id.question8B:
                if (checked) {
                    breeds.put("Labrador", breeds.get("Labrador") + 1);
                    breeds.put("Collie", breeds.get("Collie") + 1);
                    break;
                }
            case R.id.question8C:
                if (checked) {
                    breeds.put("Bulldog", breeds.get("Bulldog") + 1);
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
                    breeds.put("Spaniel", breeds.get("Spaniel") + 1);
                    breeds.put("Poodle", breeds.get("Poodle") + 1);
                    breeds.put("Bulldog", breeds.get("Bulldog") + 1);
                    break;
                }
            case R.id.question9B:
                if (checked) {
                    breeds.put("Labrador", breeds.get("Labrador") + 1);
                    breeds.put("Husky", breeds.get("Husky") + 1);
                    break;
                }
            case R.id.question9C:
                if (checked) {
                    breeds.put("Pomeranian", breeds.get("Pomeranian") + 1);
                    breeds.put("Golden Retriever", breeds.get("Golden Retriever") + 1);
                    breeds.put("German Shepherd", breeds.get("German Shepherd") + 1);
                    breeds.put("Collie", breeds.get("Collie") + 1);
                    breeds.put("Beagle", breeds.get("Beagle") + 1);
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
                    breeds.put("Spaniel", breeds.get("Spaniel") + 1);
                    breeds.put("Poodle", breeds.get("Poodle") + 1);
                    breeds.put("Pomeranian", breeds.get("Pomeranian") + 1);
                    breeds.put("Labrador", breeds.get("Labrador") + 1);
                    breeds.put("Golden Retriever", breeds.get("Golden Retriever") + 1);
                    breeds.put("German Shepherd", breeds.get("German Shepherd") + 1);
                    breeds.put("Collie", breeds.get("Collie") + 1);
                    breeds.put("Beagle", breeds.get("Beagle") + 1);
                    break;
                }
            case R.id.question10B:
                if (checked) {
                    breeds.put("Husky", breeds.get("Husky") + 1);
                    breeds.put("Bulldog", breeds.get("Bulldog") + 1);
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
                    breeds.put("Spaniel", breeds.get("Spaniel") + 1);
                    breeds.put("Poodle", breeds.get("Poodle") + 1);
                    breeds.put("Labrador", breeds.get("Labrador") + 1);
                    breeds.put("Husky", breeds.get("Husky") + 1);
                    breeds.put("Golden Retriever", breeds.get("Golden Retriever") + 1);
                    breeds.put("Collie", breeds.get("Collie") + 1);
                    breeds.put("Beagle", breeds.get("Beagle") + 1);
                    breeds.put("Bulldog", breeds.get("Bulldog") + 1);
                    break;
                }
            case R.id.question11B:
                if (checked) {
                    breeds.put("Pomeranian", breeds.get("Pomeranian") + 1);
                    breeds.put("German Shepherd", breeds.get("German Shepherd") + 1);
                    break;
                }
        }
    }
}

