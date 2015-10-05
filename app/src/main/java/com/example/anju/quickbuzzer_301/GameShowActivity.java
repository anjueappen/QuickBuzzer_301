package com.example.anju.quickbuzzer_301;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by anju on 03/10/15.
 *
 * Purpose: This activity does the following: displays buttons to users, gets the first button
 * clicked and sends the player who clicked that button to the DataBin for storage.
 *
 * Design Rationale: Unlike the reaction time activity, this activity did not have too much non-UI
 * related functionality. Therefore all of the functionality is in this class.
 *
 * Issues: I didn't notice anything major.
 *
 */
public class GameShowActivity extends ActionBarActivity {
    private Integer numPlayers;
    private Handler handler = new Handler();

    private Runnable gameshow = new Runnable() {
        @Override
        public void run() {
            Button[] buttons = {(Button)findViewById(R.id.button1), (Button)findViewById(R.id.button2),
                    (Button)findViewById(R.id.button3), (Button)findViewById(R.id.button4)};
            displayButtons(buttons);
        }
    };

    private View.OnClickListener buttonListener = new View.OnClickListener() {
        private String firstPush;
        @Override
        public void onClick(View v) {

            Button[] buttons = {(Button)findViewById(R.id.button1), (Button)findViewById(R.id.button2),
                    (Button)findViewById(R.id.button3), (Button)findViewById(R.id.button4)};
            //hideButtons(buttons);
            //Button first = (Button) findViewById(v.getId())
            switch (v.getId()){
                case R.id.button1:
                    firstPush = (String) buttons[0].getText();
                    break;
                case R.id.button2:
                    firstPush = (String) buttons[1].getText();
                    break;
                case R.id.button3:
                    firstPush = (String) buttons[2].getText();
                    break;
                case R.id.button4:
                    firstPush = (String) buttons[3].getText();
                    break;
            }

            DataBin.getInstance().addPlayerWin(numPlayers, Integer.parseInt(firstPush));
            AlertDialog rtDialog =  new AlertDialog.Builder(GameShowActivity.this).create();
            rtDialog.setTitle(R.string.title_game_show_dialog);
            Resources res = getResources();

            String text = String.format(res.getString(R.string.gameshow_results), firstPush, numPlayers.toString());
            rtDialog.setMessage(text);

            rtDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    setContentView(R.layout.plain);
                    dialog.cancel();
                    finish();
                }
            });
            rtDialog.show();

        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        DataBin.getInstance().saveInFile(getBaseContext());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_show_activity);
        Intent i = getIntent();
        numPlayers = Integer.parseInt(i.getStringExtra("numPlayers"));
        Button[] buttons = {(Button)findViewById(R.id.button1), (Button)findViewById(R.id.button2),
                (Button)findViewById(R.id.button3), (Button)findViewById(R.id.button4)};

        displayButtons(buttons);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_show_activity_new, menu);
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
    public void displayButtons(Button[] buttons ){
        for (int i=0; i<= numPlayers-1; i++){
            buttons[i].setVisibility(View.VISIBLE);
            buttons[i].setOnClickListener(buttonListener);
        }
    }

}
