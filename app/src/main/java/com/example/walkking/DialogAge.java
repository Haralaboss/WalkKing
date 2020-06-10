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

public class DialogAge extends AppCompatDialogFragment {

    private TextView tv_age_dialog;
    private NumberPicker age_picker;

    private DialogAge.DialogAgeListener dialogAgeListener;



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_age, null);

        builder.setView(view).setTitle("Select age").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int age = age_picker.getValue();

                dialogAgeListener.applyAge(age);
            }
        });

        tv_age_dialog = view.findViewById(R.id.tv_age_dialog);
        age_picker = view.findViewById(R.id.age_picker);

        age_picker.setMinValue(1);
        age_picker.setMaxValue(100);




        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            dialogAgeListener = (DialogAge.DialogAgeListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement ExampleDialogListener");
        }
    }

    public interface DialogAgeListener{
        void applyAge(int age);
    }


}
