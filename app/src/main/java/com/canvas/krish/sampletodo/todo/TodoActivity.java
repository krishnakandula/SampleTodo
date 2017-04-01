package com.canvas.krish.sampletodo.todo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.canvas.krish.sampletodo.R;

public class TodoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        //Create TodoFragment
        FragmentManager fm = getSupportFragmentManager();
        Fragment todoFragment = TodoFragment.init();

        //Create TodoPresenter

        fm.beginTransaction().add(R.id.TodoActivity_Fragment_Container, todoFragment).commit();
    }
}
