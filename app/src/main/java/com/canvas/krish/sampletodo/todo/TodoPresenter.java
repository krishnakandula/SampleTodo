package com.canvas.krish.sampletodo.todo;

/**
 * Created by Krishna Chaitanya Kandula on 3/31/2017.
 */

public class TodoPresenter implements TodoContract.Presenter {
    private TodoContract.View mTodoView;

    public TodoPresenter(){}

    @Override
    public void start(TodoContract.View view) {
        mTodoView = view;
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
