package com.example.anju.quickbuzzer_301;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ModeSelectionActivity extends ActionBarActivity {

    private static final String FILENAME = "file.sav";
    private ReactionTimeCollection reactionTimes = new ReactionTimeCollection();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                reactionTimes.addAll((ArrayList<Long>) data.getSerializableExtra("REACTION_TIMES_ARRAY"));
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mode_selection);
        Button singlePlayerButton = (Button) findViewById(R.id.single_player_button);
        Button multiplayerButton = (Button) findViewById(R.id.multiplayer_button);
        Button statsButton = (Button) findViewById(R.id.statistics_button);

        singlePlayerButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                /*Start the dialog*/
                AlertDialog rtDialog =  new AlertDialog.Builder(ModeSelectionActivity.this).create();
                rtDialog.setTitle(R.string.title_activity_reaction_timer);
                rtDialog.setMessage("Reaction Timer Instructions go here");

                rtDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setContentView(R.layout.plain);
                        dialog.cancel();
                        Intent i = new Intent(((Dialog) dialog).getContext(), ReactionTimer.class);
                        startActivityForResult(i, 1);

                    }
                });
                rtDialog.show();
            }
        });

        multiplayerButton.setOnClickListener(new View.OnClickListener(){

        public void onClick(View v){
                /*Make a new Activity for the multiplayer game*/
            }
        });

        statsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, 0);
            BufferedWriter out =  new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(reactionTimes, out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            //https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html, 2015-09-23
            Type reactionTimeCollectionType = new TypeToken<ReactionTimeCollection>() {}.getType();
            Gson gson = new Gson();
            reactionTimes = gson.fromJson(in, reactionTimeCollectionType);

        } catch (FileNotFoundException e) {
            reactionTimes = new ReactionTimeCollection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
