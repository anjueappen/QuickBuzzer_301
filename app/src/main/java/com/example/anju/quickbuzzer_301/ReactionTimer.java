package com.example.anju.quickbuzzer_301;

import android.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ReactionTimer extends ActionBarActivity{
    private Button click;
    private LinkedList<Long> reactionTimes = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_reaction_timer, null);
        click = (Button) view.findViewById(R.id.game_button);

        FragmentManager fragmentManager = getFragmentManager();

        ReactionTimerDialogFragment dialog = new ReactionTimerDialogFragment();
        dialog.show(fragmentManager, "Not sure what this does");

        click.setVisibility(View.GONE);
        setContentView(R.layout.test_screen);
        //measureReactionTime();

        //setContentView(R.layout.activity_reaction_timer);
    }

    protected void measureReactionTime(){
        Timer timer = new Timer();
        int delay = (10 + new Random().nextInt(1995));

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                //showButtonAndStartReactionTimer();
                reactionTimes.add(System.currentTimeMillis() - startTime);
            }
        }, delay, (10 + new Random().nextInt(1995)));

    }

/*    private void showButtonAndStartReactionTimer(){
        click.setVisibility(View.VISIBLE);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.setVisibility(View.INVISIBLE);
            }
        });
    }*/

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
