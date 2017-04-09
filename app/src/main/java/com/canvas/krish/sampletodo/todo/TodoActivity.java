package com.canvas.krish.sampletodo.todo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.canvas.krish.sampletodo.R;
import com.canvas.krish.sampletodo.tododetail.EditTodoDialogFragment;

public class TodoActivity extends AppCompatActivity implements EditTodoDialogFragment.EditTodoDialogListener{

    private TodoFragment todoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        //Create TodoFragment
        FragmentManager fm = getSupportFragmentManager();
        todoFragment = TodoFragment.init();

        //Create TodoPresenter

        fm.beginTransaction().add(R.id.TodoActivity_Fragment_Container, todoFragment).commit();
    }

    public void reloadTodoList() {
        todoFragment.onResume();
    }

    @Override
    public void onPositiveDismiss() {
        reloadTodoList();
    }
}
