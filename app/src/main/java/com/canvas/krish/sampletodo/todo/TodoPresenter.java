package com.canvas.krish.sampletodo.todo;

import com.canvas.krish.sampletodo.data.source.TodoRepositoryContract;

import javax.inject.Inject;

/**
 * Created by Krishna Chaitanya Kandula on 3/31/2017.
 */

public class TodoPresenter implements TodoContract.Presenter {
    @Inject
    private TodoRepositoryContract mTodoRepository;
    private TodoContract.View mTodoView;

    @Override
    public void start(TodoContract.View view) {
        mTodoView = view;
        loadTasks();
    }

    @Override
    public void loadTasks() {

    }

    @Override
    public void addNewTask() {

    }

    @Override
    public void completeTask() {

    }
}
