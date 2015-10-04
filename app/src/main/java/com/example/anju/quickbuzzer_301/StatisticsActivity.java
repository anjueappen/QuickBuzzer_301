package com.example.anju.quickbuzzer_301;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class StatisticsActivity extends ActionBarActivity {
    private Handler handler;


    private View.OnClickListener clearListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DataBin.getInstance().clearAll(getBaseContext());
            handler.post(displayStatistics);
        }
    };

    private View.OnClickListener emailListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(StatisticsActivity.this, SendEmailActivity.class);
            startActivity(i);
        }
    };


    Runnable displayStatistics = new Runnable() {
        @Override
        public void run() {
            populateStatisticsScreen();
        }
    };
    @Override
    public View onCreatePanelView(int featureId) {
        return super.onCreatePanelView(featureId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBin.getInstance().loadFromFile(getBaseContext());
        setContentView(R.layout.activity_statistics);
        Button clearButton = (Button)findViewById(R.id.clear_stats_button);
        Button emailButton = (Button)findViewById(R.id.email_stats_button);
        clearButton.setOnClickListener(clearListener);
        emailButton.setOnClickListener(emailListener);
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
        Long test = DataBin.getInstance().getMaxTimeOfLast(10);
        max10.setText(test.toString());
        max100.setText(DataBin.getInstance().getMaxTimeOfLast(100).toString());
        maxAll.setText(DataBin.getInstance().getMaxTimeOfLast(DataBin.getInstance().getReactionTimeData().size()).toString());

        TextView min10 = (TextView)findViewById(R.id.min10);
        TextView min100 = (TextView)findViewById(R.id.min100);
        TextView minAll = (TextView)findViewById(R.id.minAll);
        min10.setText(DataBin.getInstance().getMinTimeOfLast(10).toString());
        min100.setText(DataBin.getInstance().getMinTimeOfLast(100).toString());
        minAll.setText(DataBin.getInstance().getMinTimeOfLast(DataBin.getInstance().getReactionTimeData().size()).toString());

        TextView med10 = (TextView)findViewById(R.id.median10);
        TextView med100 = (TextView)findViewById(R.id.median100);
        TextView medAll = (TextView)findViewById(R.id.medianAll);
        med10.setText(DataBin.getInstance().getMedianTimeOfLast(10).toString());
        med100.setText(DataBin.getInstance().getMedianTimeOfLast(100).toString());
        medAll.setText(DataBin.getInstance().getMedianTimeOfLast(DataBin.getInstance().getReactionTimeData().size()).toString());

        TextView avg10 = (TextView)findViewById(R.id.average10);
        TextView avg100 = (TextView)findViewById(R.id.average100);
        TextView avgAll = (TextView)findViewById(R.id.averageAll);
        avg10.setText(DataBin.getInstance().getAverageTimeOfLast(10).toString());
        avg100.setText(DataBin.getInstance().getAverageTimeOfLast(100).toString());
        avgAll.setText(DataBin.getInstance().getAverageTimeOfLast(DataBin.getInstance().getReactionTimeData().size()).toString());

        TextView p2_1 = (TextView) findViewById(R.id.player2_1);
        TextView p2_2 = (TextView) findViewById(R.id.player2_2);
        p2_1.setText(DataBin.getInstance().getPlayerWin(2, 1).toString());
        p2_2.setText(DataBin.getInstance().getPlayerWin(2, 2).toString());

        TextView p3_1 = (TextView) findViewById(R.id.player3_1);
        TextView p3_2 = (TextView) findViewById(R.id.player3_2);
        TextView p3_3 = (TextView) findViewById(R.id.player3_3);
        p3_1.setText(DataBin.getInstance().getPlayerWin(3, 1).toString());
        p3_2.setText(DataBin.getInstance().getPlayerWin(3, 2).toString());
        p3_3.setText(DataBin.getInstance().getPlayerWin(3, 3).toString());

        TextView p4_1 = (TextView) findViewById(R.id.player4_1);
        TextView p4_2 = (TextView) findViewById(R.id.player4_2);
        TextView p4_3 = (TextView) findViewById(R.id.player4_3);
        TextView p4_4 = (TextView) findViewById(R.id.player4_4);
        p4_1.setText(DataBin.getInstance().getPlayerWin(4, 1).toString());
        p4_2.setText(DataBin.getInstance().getPlayerWin(4, 2).toString());
        p4_3.setText(DataBin.getInstance().getPlayerWin(4, 3).toString());
        p4_4.setText(DataBin.getInstance().getPlayerWin(4, 4).toString());
    }


}
