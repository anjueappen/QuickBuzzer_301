package com.example.anju.quickbuzzer_301;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by anju on 26/09/15.
 */

//http://stackoverflow.com/questions/5070618/how-to-start-an-activity-from-a-dialog-in-android by EricLarch

public class ReactionTimerDialog extends Dialog implements View.OnClickListener {
    Button okButton;
    Activity ourActivity;

    public ReactionTimerDialog(Activity activity){
        super(activity);
        ourActivity = activity;
        setContentView(R.layout.singleplayer_dialog_layout);
        okButton = (Button) findViewById(R.id.okButton);
        okButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){

    }

    /*@Override
    public Dialog onCreateDialog(Bundle savedInstance){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.single_player_instructions);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               // dismiss();
                            *//*Start the game*//*
                Intent intent = new Intent(getContext(), ReactionTimer.class);
                startActivity(intent);
            }
        });
        builder.setTitle("Reaction Timer");
        return builder.create();
    }*/
}