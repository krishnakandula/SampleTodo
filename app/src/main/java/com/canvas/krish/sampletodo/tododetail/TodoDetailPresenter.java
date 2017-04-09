package com.canvas.krish.sampletodo.tododetail;

import android.util.Log;

import com.canvas.krish.sampletodo.data.models.Todo;
import com.canvas.krish.sampletodo.data.source.TodoRepositoryContract;

import java.util.UUID;

/**
 * Created by Krishna Chaitanya Kandula on 4/9/17.
 */

public class TodoDetailPresenter implements TodoDetailContract.Presenter {
    private static final String LOG_TAG = TodoDetailPresenter.class.getSimpleName();
    TodoDetailContract.View view;
    TodoRepositoryContract mRepository;
    private UUID mTodoId;
    private Todo mTodo;
    public TodoDetailPresenter(TodoRepositoryContract repository) {
        mRepository = repository;
    }

    @Override
    public void start(TodoDetailContract.View view, UUID todoId) {
        this.view = view;
        mTodoId = todoId;
        loadTodoDetails();
    }

    @Override
    public void loadTodoDetails() {
        //Get details from repository
        mRepository.getTodo(mTodoId, new TodoRepositoryContract.GetTodoCallback() {
            @Override
            public void onTodoLoaded(Todo todo) {
                mTodo = todo;
                view.showDetails(todo);
            }

            @Override
            public void onDataNotAvailable() {
                Log.e(LOG_TAG, "ERROR: Could not retrieve data from repository");
            }
        });
    }

    @Override
    public void onPositiveAction(String text) {
        //Save to repository
        mTodo.setText(text);
        mRepository.updateTodo(mTodo);
        view.closeDetailView();
    }

    @Override
    public void onNegativeAction() {
        view.closeDetailView();
    }
}
