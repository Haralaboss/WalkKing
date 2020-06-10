package com.example.walkking;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

public class DialogGender extends AppCompatDialogFragment {

    private TextView tv_gender_dialog;
    private NumberPicker gender_picker;

    private DialogGenderListener dialogGenderListener;



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_gender, null);

        builder.setView(view).setTitle("Select gender").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int gender = gender_picker.getValue();

                dialogGenderListener.applyGender(gender);
            }
        });

        tv_gender_dialog = view.findViewById(R.id.tv_gender_dialog);
        gender_picker = view.findViewById(R.id.gender_picker);

        gender_picker.setMinValue(0);
        gender_picker.setMaxValue(1);
        gender_picker.setDisplayedValues( new String[] { "Male", "Female" } );




        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            dialogGenderListener = (DialogGenderListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement ExampleDialogListener");
        }
    }

    public interface DialogGenderListener{
        void applyGender(int gender);
    }


}
