package com.canvas.krish.sampletodo.todo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.canvas.krish.sampletodo.R;

/**
 * Created by Krishna Chaitanya Kandula on 4/8/17.
 */

public class EditTodoDialogFragment extends DialogFragment {

    private DialogActionListener callback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (DialogActionListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement DialogActionListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_dialog_edit_todo, null);
        builder.setView(view)
                .setPositiveButton(R.string.edit_todo_dialog_fragment_positive_button, (dialog, which) -> {

                })
                .setNegativeButton(R.string.edit_todo_dialog_fragment_negative_button, (dialog, which) -> {

                });

        return builder.create();
    }

    public interface DialogActionListener {
        void onPositiveButtonClicked();
        void onNegativeButtonClicked();
    }
}
