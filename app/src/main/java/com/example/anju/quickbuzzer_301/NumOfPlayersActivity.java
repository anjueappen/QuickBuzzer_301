package com.example.anju.quickbuzzer_301;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
/**
 *
 * Purpose: This activity only asks the user for how many players to play the Buzzer game and passes
 * this onto the Buzzer activity, itself.
 *
 * Design Rationale: A spinner allows the user to see the choices they can make and not enter a number
 * that is too large or too small (more than what the Game Show Buzzer can handle).
 *
 * Issues: Nothing noted.
 *
 */
public class NumOfPlayersActivity extends ActionBarActivity {

       @Override
        protected void onCreate(Bundle savedInstanceState) {
               super.onCreate(savedInstanceState);
               setContentView(R.layout.activity_num_of_players);
                Spinner spinner = (Spinner) findViewById(R.id.spinner);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                                R.array.playerCounts, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);

                        Button submit =  (Button)findViewById(R.id.submit);
                submit.setOnClickListener(new View.OnClickListener() {
                       @Override
                        public void onClick(View v) {
                                Spinner spinner = (Spinner) findViewById(R.id.spinner);
                                String itemStr = spinner.getSelectedItem().toString();

                                        Intent next = new Intent(NumOfPlayersActivity.this, GameShowActivity.class);
                                next.putExtra("numPlayers", itemStr);
                                startActivity(next);
                            }
                    });

                    }

                @Override
        public boolean onCreateOptionsMenu(Menu menu) {
                // Inflate the menu; this adds items to the action bar if it is present.
                        getMenuInflater().inflate(R.menu.menu_num_of_players, menu);
                return true;
            }

                @Override
        public boolean onOptionsItemSelected(MenuItem item) {
                // Handle action bar item clicks here. The action bar will
                        // automatically handle clicks on the Home/Up button, so long
                                // as you specify a parent activity in AndroidManifest.xml.
                                        int id = item.getItemId();

                                if (id == R.id.action_settings) {
                   return true;
                   }
                      return super.onOptionsItemSelected(item);}



}
