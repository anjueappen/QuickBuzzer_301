package com.example.anju.quickbuzzer_301;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by anju on 26/09/15.
 */
public class SinglePlayerInstructionsFragment extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View instrView = inflater.inflate(R.layout.singleplayer_dialog_layout, container, false);
        getDialog().setTitle("Test Title");
        return instrView;
    }

    /*@Override
    public Dialog onCreateDialog(Bundle savedInstance){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.single_player_instructions);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //What should be done at this point? start the game?
            }
        });
        return builder.create();
    }*/
}