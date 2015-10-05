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


/**
 * Created by anju on 03/10/15.
 *
 * Purpose: As the first activity that the user sees when they open the app, this activity is the
 * 'home base' for the application. This activity is the gateway for all others and also tells the
 * DataBin to store the data when we exit it.
 *
 * Design Rationale: The only non-UI related task this activity has is telling DataBin to save to
 * file. As such, it didn't really make sense to put that in a separate class.
 *
 * Issues: I did not notice any major flaws with this class.
 *
 */

public class ModeSelectionActivity extends ActionBarActivity {

    private static final String FILENAME = "file.sav";
    private DataBin reactionTimes = DataBin.getInstance();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
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
                rtDialog.setMessage(getResources().getString(R.string.reaction_timer_instructions));
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

    @Override
    protected void onResume() {
        super.onResume();
    }
}
