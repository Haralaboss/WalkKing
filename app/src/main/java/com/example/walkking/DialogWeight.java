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

public class DialogWeight extends AppCompatDialogFragment {

    private TextView tv_weight_dialog;
    private NumberPicker weight_picker;

    private DialogWeight.DialogWeightListener dialogWeightListener;



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_weight, null);

        builder.setView(view).setTitle("Select weight").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int weight = weight_picker.getValue();

                dialogWeightListener.applyWeight(weight);
            }
        });

        tv_weight_dialog = view.findViewById(R.id.tv_weight_dialog);
        weight_picker = view.findViewById(R.id.weight_picker);

        weight_picker.setMinValue(1);
        weight_picker.setMaxValue(200);




        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            dialogWeightListener = (DialogWeight.DialogWeightListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement ExampleDialogListener");
        }
    }

    public interface DialogWeightListener{
        void applyWeight(int weight);
    }


}