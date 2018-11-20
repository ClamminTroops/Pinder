package edu.calvin.cs262.teamc;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.view.WindowManager.LayoutParams;

import com.jgabrielfreitas.core.BlurImageView;
import com.obsez.android.lib.filechooser.ChooserDialog;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;

//TODO: delete commented-out code if code is no longer needed

/**
 * class for Pet For Adoption Activity
 *
 * It allows users to add information about their dog and to put their
 *      dog up for adoption
 *
 * @author Justin Baskaran
 * @version 1
 * @since 16-11-2018
 */
public class activity_PetforAdoption extends AppCompatActivity {

    BlurImageView myBlurImage;
    public Button yesbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put_pet_adoption);
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.pinderlogov2));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");

        //ActionBar actionBar = getSupportActionBar();
        //actionBar.setTitle("Pinder Quiz");

        //set default values to ToggleButton "toggleButton4"
        ToggleButton toggleButton = findViewById(R.id.toggleButton4);
        toggleButton.setText("Female");
        toggleButton.setTextOff("Female");
        toggleButton.setTextOn("Male");

        //set default values to ToggleButton "houseTrained"
        ToggleButton tg1 = findViewById(R.id.houseTrained);
        tg1.setText("No");
        tg1.setTextOff("Yes");
        tg1.setTextOn("No");

        //TODO: explain what this piece of code does
        final TextView tv1 = findViewById(R.id.chooseFile);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Clicked!",
                        Toast.LENGTH_LONG).show();
                new FileChooser(activity_PetforAdoption.this).setFileListener(new FileChooser.FileSelectedListener() {
                    @Override public void fileSelected(final File file) {
                        Toast.makeText(getApplicationContext(), String.valueOf(file),
                                Toast.LENGTH_LONG).show();
                        tv1.setText(String.valueOf(file));
                    }
                    }).showDialog();
            }
        });
//        myBlurImage =  (BlurImageView) findViewById(R.id.myBlurImage);
//        myBlurImage.setBlur(15);
    }

    /**
     * class FileChooser
     *
     * TODO: explain what the class FileChooser does here
     */
    public static class FileChooser {
        private static final String PARENT_DIR = "..";

        private final Activity activity;
        private ListView list;
        private Dialog dialog;
        private File currentPath;

        /**
         * filter on file extension
         *
         * @param string
         */
        private String extension = null;
        public void setExtension(String extension) {
            this.extension = (extension == null) ? null :
                    extension.toLowerCase();
        }

        /**
         * file selection event handling
         */
        public interface FileSelectedListener {
            void fileSelected(File file);
        }

        /**
         * TODO: add comment for this function
         *
         * @param fileListener
         * @return this
         */
        public FileChooser setFileListener(FileSelectedListener fileListener) {
            this.fileListener = fileListener;
            return this;
        }

        /**
         * TODO: add comment for this function
         *
         * @param activity
         */
        private FileSelectedListener fileListener;
        public FileChooser(Activity activity) {
            this.activity = activity;
            dialog = new Dialog(activity);
            list = new ListView(activity);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override public void onItemClick(AdapterView<?> parent, View view, int which, long id) {
                    String fileChosen = (String) list.getItemAtPosition(which);
                    File chosenFile = getChosenFile(fileChosen);
                    if (chosenFile.isDirectory()) {
                        refresh(chosenFile);
                    } else {
                        if (fileListener != null) {
                            fileListener.fileSelected(chosenFile);
                        }
                        dialog.dismiss();
                    }
                }
            });
            dialog.setContentView(list);
            dialog.getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
            refresh(Environment.getExternalStorageDirectory());
        }

        /**
         * TODO: add comment for this function
         */
        public void showDialog() {
            dialog.show();
        }


        /**
         * Sort, filter and display the files for the given path.
         */
        private void refresh(File path) {
            this.currentPath = path;
            if (path.exists()) {
                File[] dirs = path.listFiles(new FileFilter() {
                    @Override public boolean accept(File file) {
                        return (file.isDirectory() && file.canRead());
                    }
                });
                File[] files = path.listFiles(new FileFilter() {
                    @Override public boolean accept(File file) {
                        if (!file.isDirectory()) {
                            if (!file.canRead()) {
                                return false;
                            } else if (extension == null) {
                                return true;
                            } else {
                                return file.getName().toLowerCase().endsWith(extension);
                            }
                        } else {
                            return false;
                        }
                    }
                });

                // convert to an array
                int i = 0;
                String[] fileList;
                if (path.getParentFile() == null) {
                    fileList = new String[dirs.length + files.length];
                } else {
                    fileList = new String[dirs.length + files.length + 1];
                    fileList[i++] = PARENT_DIR;
                }
                Arrays.sort(dirs);
                Arrays.sort(files);
                for (File dir : dirs) { fileList[i++] = dir.getName(); }
                for (File file : files ) { fileList[i++] = file.getName(); }

                // refresh the user interface
                // TODO: maybe fix code organization; Android Studio does not like the definition of getView inside setAdapter
                dialog.setTitle(currentPath.getPath());
                list.setAdapter(new ArrayAdapter(activity,
                        android.R.layout.simple_list_item_1, fileList) {
                    @Override public View getView(int pos, View view, ViewGroup parent) {
                        view = super.getView(pos, view, parent);
                        ((TextView) view).setSingleLine(true);
                        return view;
                    }
                });
            }
        }


        /**
         * Convert a relative filename into an actual File object.
         */
        private File getChosenFile(String fileChosen) {
            if (fileChosen.equals(PARENT_DIR)) {
                return currentPath.getParentFile();
            } else {
                return new File(currentPath, fileChosen);
            }
        }
    }

    /**
     * TODO: explain what this function does here
     *
     * @param view
     */
    public void saveNewPetSwipe(View view)
    {
        EditText NameText = findViewById(R.id.NameEdit);
       // EditText BreedText = findViewById(R.id.BreedText);
        EditText AgeText = findViewById(R.id.age);

        if( TextUtils.isEmpty(NameText.getText())){
            NameText.setError( getString(R.string.Name_Required) );
        }
//        else if( TextUtils.isEmpty(BreedText.getText())){
//            BreedText.setError( getString(R.string.Breed_Required) );
//        }
        else if( TextUtils.isEmpty(AgeText.getText())){
            AgeText.setError( getString(R.string.Age_Required) );
        }
        else {
            Toast.makeText(getApplicationContext(), "Saved to database!",
                    Toast.LENGTH_LONG).show();
        }

    }
}
