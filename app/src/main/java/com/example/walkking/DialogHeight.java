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

public class DialogHeight extends AppCompatDialogFragment {

    TextView tv_height_dialog;
    private NumberPicker height_picker;

    private DialogHeight.DialogHeightListener dialogHeightListener;



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_height, null);

        builder.setView(view).setTitle("Select Height").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int height = height_picker.getValue();

                dialogHeightListener.applyHeight(height);
            }
        });

        tv_height_dialog = view.findViewById(R.id.tv_height_dialog);
        height_picker = view.findViewById(R.id.height_picker);

        height_picker.setMinValue(80);
        height_picker.setMaxValue(250);




        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            dialogHeightListener = (DialogHeight.DialogHeightListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement ExampleDialogListener");
        }
    }

    public interface DialogHeightListener {
        void applyHeight(int height);
    }


}
