package com.example.walkking;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DialogGoal extends AppCompatDialogFragment {

    TextView tv_goal_dialog;
    EditText et_goal;

    private DialogGoal.DialogGoalListener dialogGoalListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_goal, null);

        builder.setView(view).setTitle("Edit goal").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String goalS= et_goal.getText().toString();
                int goal = Integer.parseInt(goalS);

                dialogGoalListener.applyGoal(goal);
            }
        });

        tv_goal_dialog = view.findViewById(R.id.tv_goal_dialog);
        et_goal = view.findViewById(R.id.et_goal);


        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            dialogGoalListener = (DialogGoal.DialogGoalListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement ExampleDialogListener");
        }
    }

    public interface DialogGoalListener{
        void applyGoal(int goal);
    }
}
