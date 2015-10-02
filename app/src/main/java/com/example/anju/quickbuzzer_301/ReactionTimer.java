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

import java.util.ArrayList;
import java.util.Random;

public class ReactionTimer extends ActionBarActivity{
    private ArrayList<ReactionTime> reactionTimes = new ArrayList<ReactionTime>();
    private Handler handler;
    private int buttonDelay;

    Runnable reactionButton =  new Runnable() {
        @Override
        public void run() {
            final ReactionTime rt = new ReactionTime();
            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    (findViewById(R.id.game_button)).setVisibility(View.GONE);
                    rt.setDuration();
                    ((TextView) findViewById(R.id.reaction_time_display)).setText(
                            "Reaction Time: " + rt.getDuration().toString() + " ms");
                    reactionTimes.add(rt);
                }
            };
            Button click = (Button) findViewById(R.id.game_button);
            click.setVisibility(View.VISIBLE);
            click.setOnClickListener(listener);
            ((TextView) findViewById(R.id.error_textbox)).setText("");
            handler.postDelayed(this, (01 + new Random().nextInt(1995)));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaction_timer);

        //http://stackoverflow.com/questions/10126268/howto-fire-a-event-when-someone-click-anywhere-on-the-screen-in-a-android-app
        // by Samir Mangroliya
        RelativeLayout rlayout = (RelativeLayout) findViewById(R.id.button_container);
        buttonDelay = (01 + new Random().nextInt(1995));
        rlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView) findViewById(R.id.error_textbox)).setText("Hey! No clicking early!");
                handler.removeCallbacks(reactionButton);
                buttonDelay = (01 + new Random().nextInt(1995));
                handler.postDelayed(reactionButton, buttonDelay);

            }
        });

        Button click = (Button) findViewById(R.id.game_button);
        click.setVisibility(View.GONE);
        showButton(buttonDelay);
    }

    @Override
    protected void onPause() {
        super.onPause();

        Bundle b = new Bundle();
        b.putParcelableArrayList("com.example.anju.quickbuzzer_301", reactionTimes);

        Intent returnIntent = new Intent();
        returnIntent.putExtras(b);
        setResult(RESULT_OK, returnIntent);

        reactionTimes.clear();
        finish();
    }

    private void showButton(long delay){
        handler = new Handler();
        handler.postDelayed(reactionButton, delay);

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
}
