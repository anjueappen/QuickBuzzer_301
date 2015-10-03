package com.example.anju.quickbuzzer_301;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class ReactionTimer extends ActionBarActivity{

    private Handler handler = new Handler(); //UI facing
    private ReactionGame game = new ReactionGame();

    private View.OnClickListener reaction =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            (findViewById(R.id.game_button)).setVisibility(View.GONE);
            game.endGame();
            ((TextView) findViewById(R.id.reaction_time_display)).setText(
                    "Reaction Time: " + game.getLastReactionTime().toString() + " ms");
            handler.postDelayed(reactionButton, makeNewDelay());
        }
    };

    private View.OnClickListener earlyClick =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            interrupt(earlyClickMessage);

        }
    };

    //Runnable to repeat
    Runnable reactionButton =  new Runnable() {
        @Override
        public void run() {
            game.startGame();
            Button click = (Button) findViewById(R.id.game_button);
            click.setVisibility(View.VISIBLE);
            click.setOnClickListener(reaction);
            ((TextView) findViewById(R.id.error_textbox)).setText("");
        }
    };

    //Runnable for unwanted clicks
    Runnable earlyClickMessage = new Runnable() {
        @Override
        public void run() {
            ((TextView) findViewById(R.id.error_textbox)).setText("Hey! No clicking early!");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaction_timer);

        //http://stackoverflow.com/questions/10126268/howto-fire-a-event-when-someone-click-anywhere-on-the-screen-in-a-android-app
        // by Samir Mangroliya

        RelativeLayout rlayout = (RelativeLayout) findViewById(R.id.button_container);
        rlayout.setOnClickListener(earlyClick);

        //default start settings
        Button click = (Button) findViewById(R.id.game_button);
        click.setVisibility(View.GONE);
        handler.postDelayed(reactionButton, makeNewDelay());

    }

    @Override
    protected void onPause() {
        super.onPause();

        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);

        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reaction_timer, menu);
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

    private int makeNewDelay(){
        return (01 + new Random().nextInt(1995));
    }

       private void interrupt(Runnable interruption){
           handler.removeCallbacks(reactionButton); //stop
           handler.post(interruption);
                handler.postDelayed(reactionButton, makeNewDelay()); //restart
            }
}
