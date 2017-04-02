package com.canvas.krish.sampletodo.todo;

import android.util.Log;

import com.canvas.krish.sampletodo.data.models.Todo;
import com.canvas.krish.sampletodo.data.source.TodoRepositoryContract;

import javax.inject.Inject;

/**
 * Created by Krishna Chaitanya Kandula on 3/31/2017.
 */

public class TodoPresenter implements TodoContract.Presenter {

    private static final String LOG_TAG = TodoPresenter.class.getSimpleName();

    TodoRepositoryContract mTodoRepository;
    private TodoContract.View mTodoView;

    @Override
    public void start(TodoContract.View view) {
        mTodoView = view;
        loadTasks();
    }

    @Override
    public void loadTasks() {
        mTodoRepository.getTodo("", new TodoRepositoryContract.GetTodoCallback() {
            @Override
            public void onTodoLoaded(Todo todo) {
                Log.d(LOG_TAG, todo.getText());
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    @Override
    public void addNewTask() {

    }

    @Override
    public void completeTask() {

    }
}
