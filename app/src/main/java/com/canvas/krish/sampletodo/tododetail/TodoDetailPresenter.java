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
    private UUID todoId;
    public TodoDetailPresenter(TodoRepositoryContract repository) {
        mRepository = repository;
    }

    @Override
    public void start(TodoDetailContract.View view, UUID todoId) {
        this.todoId = todoId;
    }

    @Override
    public void loadTodoDetails() {
        //Get details from repository
        mRepository.getTodo(todoId, new TodoRepositoryContract.GetTodoCallback() {
            @Override
            public void onTodoLoaded(Todo todo) {
                view.showDetails(todo);
            }

            @Override
            public void onDataNotAvailable() {
                Log.e(LOG_TAG, "ERROR: Could not retrieve data from repository");
            }
        });
    }

    @Override
    public void onPositiveAction(Todo todo) {
        //Save to repository
        mRepository.updateTodo(todo);
    }

    @Override
    public void onNegativeAction() {
        view.closeDetailView();
    }
}
