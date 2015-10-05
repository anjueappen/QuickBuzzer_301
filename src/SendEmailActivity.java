package com.example.anju.quickbuzzer_301;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * Copyright 2015  Anju Eappen

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.

 * Purpose: This activity allows the user to send an email of the their data.
 *
 * Design Rationale: The screen does appear to leave the application(into the email) , so the user is
 * notified before the app goes the email sending screen.
 *
 * Issues: None noticed.
 *
 */

public class SendEmailActivity extends ActionBarActivity {

    Button buttonSend;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email);

        buttonSend = (Button) findViewById(R.id.buttonSend);

        final Intent email = new Intent(Intent.ACTION_SENDTO);

        email.setData(Uri.parse("mailto:"));
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"anjueappen@gmail.com"});
        email.putExtra(Intent.EXTRA_TEXT, DataBin.getInstance().getAllData().toString());
        buttonSend.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    startActivity(Intent.createChooser(email, "Choose an Email client :"));
                }catch (android.content.ActivityNotFoundException ex){
                    Toast.makeText(SendEmailActivity.this,
                            "There are no email clients installed", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }

}
