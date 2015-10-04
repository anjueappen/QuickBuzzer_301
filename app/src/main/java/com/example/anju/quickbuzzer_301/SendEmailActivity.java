package com.example.anju.quickbuzzer_301;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SendEmailActivity extends ActionBarActivity {

    Button buttonSend;
    EditText textTo;
    EditText textSubject;
    EditText textMessage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email);

        textTo = (EditText) findViewById(R.id.editTextTo);
        textSubject = (EditText) findViewById(R.id.editTextSubject);
        buttonSend = (Button) findViewById(R.id.buttonSend);
        String to = textTo.getText().toString();
        String subject = textSubject.getText().toString();
        //String message = textMessage.getText().toString();

        final Intent email = new Intent(Intent.ACTION_SENDTO);

        email.setData(Uri.parse("mailto:"));
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
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

            }
        });
    }

}
