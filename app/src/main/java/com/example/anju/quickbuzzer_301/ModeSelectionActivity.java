package com.example.anju.quickbuzzer_301;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class ModeSelectionActivity extends ActionBarActivity {

    private static final String FILENAME = "file.sav";
    private DataBin reactionTimes = DataBin.getInstance();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                //reactionTimes.addAll(newTimes);
                this.onResume();
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

            @Override
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
                        Intent i = new Intent(((Dialog) dialog).getContext(), ReactionTimerActivity.class);
                        startActivityForResult(i, 1);

                    }
                });
                rtDialog.show();
            }
        });

        multiplayerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                /*Make a new Activity for the multiplayer game*/
                Intent i = new Intent(ModeSelectionActivity.this, NumOfPlayersActivity.class);
                startActivity(i);

            }
        });
        statsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ModeSelectionActivity.this, StatisticsActivity.class);
                startActivity(i);
            }
        });
}


/*    @Override
    protected void onStop() {
        super.onStop();
        Context c = getBaseContext();
        reactionTimes.saveInFile(getBaseContext());
    }*/

    @Override
    protected void onPause() {
        super.onPause();
        reactionTimes.saveInFile(getBaseContext());
    }

    @Override
    protected void onStart() {
        super.onStart();
        reactionTimes.loadFromFile(getBaseContext());
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

    /*private void saveInFile() {
        try {
            FileOutputStream fos = getBaseContext().openFileOutput(FILENAME, 0);
            BufferedWriter out =  new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(reactionTimes.getReactionTimeData(), out);
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

            FileInputStream fis = getBaseContext().openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            //https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html, 2015-09-23
            Type reactionTimeCollectionType = new TypeToken<List<Long>>() {}.getType();
            Gson gson = new Gson();
            ArrayList<Long> list = gson.fromJson(in, reactionTimeCollectionType);
            reactionTimes.setData(list);

        } catch (FileNotFoundException e) {
            reactionTimes.setData(new ArrayList<Long>());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/

    @Override
    protected void onResume() {
        super.onResume();
    }
}
