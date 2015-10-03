package com.example.anju.quickbuzzer_301;

import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class StatisticsActivity extends ActionBarActivity {
    private ReactionTimeList reactionTimes;
    private Handler handler;


    Runnable displayStatistics = new Runnable() {
        @Override
        public void run() {
            //populateStatisticsScreen();
            //((TextView)findViewById(R.id.max10)).setText(reactionTimes.getMaxTimeOfLast(10).toString());
            String s = "";
            for(ReactionTime rt: reactionTimes){
                s += rt.getDuration().toString() + " ";
            }
            ((TextView)findViewById(R.id.max10)).setText(s);
        }
    };
    @Override
    public View onCreatePanelView(int featureId) {
        return super.onCreatePanelView(featureId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        reactionTimes = b.getParcelable("com.example.anju.quickbuzzer_301");
        setContentView(R.layout.activity_statistics);
        handler = new Handler();
        handler.post(displayStatistics);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_statistics, menu);
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

    public void populateStatisticsScreen(){

        TextView max10 = (TextView)findViewById(R.id.max10);
        TextView max100 = (TextView)findViewById(R.id.max100);
        TextView maxAll= (TextView)findViewById(R.id.maxAll);
        max10.setText(reactionTimes.getMaxTimeOfLast(10).toString());
        max100.setText(reactionTimes.getMaxTimeOfLast(100).toString());
        maxAll.setText(reactionTimes.getMaxTimeOfLast(reactionTimes.size()).toString());

        TextView min10 = (TextView)findViewById(R.id.min10);
        TextView min100 = (TextView)findViewById(R.id.min100);
        TextView minAll = (TextView)findViewById(R.id.minAll);
        min10.setText(reactionTimes.getMinTimeOfLast(10).toString());
        min100.setText(reactionTimes.getMinTimeOfLast(100).toString());
        minAll.setText(reactionTimes.getMinTimeOfLast(reactionTimes.size()).toString());

        TextView med10 = (TextView)findViewById(R.id.median10);
        TextView med100 = (TextView)findViewById(R.id.median100);
        TextView medAll = (TextView)findViewById(R.id.medianAll);
        med10.setText(reactionTimes.getMedianTimeOfLast(10).toString());
        med100.setText(reactionTimes.getMedianTimeOfLast(100).toString());
        medAll.setText(reactionTimes.getMedianTimeOfLast(reactionTimes.size()).toString());

        TextView avg10 = (TextView)findViewById(R.id.average10);
        TextView avg100 = (TextView)findViewById(R.id.average100);
        TextView avgAll = (TextView)findViewById(R.id.averageAll);
        avg10.setText(reactionTimes.getMedianTimeOfLast(10).toString());
        avg100.setText(reactionTimes.getMedianTimeOfLast(100).toString());
        avgAll.setText(reactionTimes.getMedianTimeOfLast(reactionTimes.size()).toString());


    }


}
