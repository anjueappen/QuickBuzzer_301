package com.example.anju.quickbuzzer_301;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class ModeSelectionActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mode_selection);
        Button singlePlayerButton = (Button) findViewById(R.id.single_player_button);
        Button multiplayerButton = (Button) findViewById(R.id.multiplayer_button);


        singlePlayerButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                /*Start the dialog*/
                //FragmentManager fragmentManager = getFragmentManager();
                AlertDialog rtDialog =  new AlertDialog.Builder(ModeSelectionActivity.this).create();
                rtDialog.setTitle(R.string.title_activity_reaction_timer);
                rtDialog.setMessage("Reaction Timer Instructions go here");

                rtDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setContentView(R.layout.plain);
                        dialog.cancel();
                        Intent i = new Intent(((Dialog) dialog).getContext(), ReactionTimer.class);
                        startActivity(i);

                    }
                });
                rtDialog.show();
                //ReactionTimerDialog dialog = new ReactionTimerDialog(getParent());
                //dialog.show();
                //dialog.show(fragmentManager, "Not sure what this does");
            }
        });

    multiplayerButton.setOnClickListener(new View.OnClickListener(){

        public void onClick(View v){
                /*Make a new Activity for the multiplayer game*/
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
}
