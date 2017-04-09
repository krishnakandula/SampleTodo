package com.canvas.krish.sampletodo.tododetail;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;

import com.canvas.krish.sampletodo.R;
import com.canvas.krish.sampletodo.TodoApplication;
import com.canvas.krish.sampletodo.data.models.Todo;

import java.util.UUID;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Krishna Chaitanya Kandula on 4/8/17.
 */

public class EditTodoDialogFragment extends DialogFragment implements TodoDetailContract.View{

    @Inject
    TodoDetailContract.Presenter mPresenter;
    private Unbinder mUnbinder;
    public static String DIALOG_ID_KEY = "todo_id_key";

    @BindView(R.id.fragment_dialog_edit_todo_edit_text)
    EditText editText;

    public static EditTodoDialogFragment init(UUID todoId) {
        Bundle args = new Bundle();
        args.putSerializable(DIALOG_ID_KEY, todoId);

        EditTodoDialogFragment fragment = new EditTodoDialogFragment();
        fragment.setArguments(args);

        return fragment;
    }

    private UUID todoId;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((TodoApplication) getActivity().getApplication()).getComponent().inject(this);
        Bundle args = getArguments();
        todoId = (UUID) args.getSerializable(DIALOG_ID_KEY);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start(this, todoId);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_dialog_edit_todo, null);
        mUnbinder = ButterKnife.bind(this, view);
        builder.setView(view)
                .setPositiveButton(R.string.edit_todo_dialog_fragment_positive_button, (dialog, which) -> {

                })
                .setNegativeButton(R.string.edit_todo_dialog_fragment_negative_button, (dialog, which) -> {

                });

        return builder.create();
    }

    @Override
    public void showDetails(Todo todo) {
        editText.setText(todo.getText());
    }

    @Override
    public void closeDetailView() {
        this.dismiss();
    }
}
