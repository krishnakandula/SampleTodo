package com.canvas.krish.sampletodo.todo;

import android.util.Log;

import com.canvas.krish.sampletodo.data.models.Todo;
import com.canvas.krish.sampletodo.data.source.TodoRepositoryContract;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Krishna Chaitanya Kandula on 3/31/2017.
 */

public class TodoPresenter implements TodoContract.Presenter {

    private static final String LOG_TAG = TodoPresenter.class.getSimpleName();


    TodoRepositoryContract mTodoRepository;
    private TodoContract.View mTodoView;

    @Inject
    public TodoPresenter(TodoRepositoryContract todoRepository){
        mTodoRepository = todoRepository;
    }

    @Override
    public void start(TodoContract.View view) {
        mTodoView = view;
        loadTodos();
    }

    @Override
    public void loadTodos() {
        mTodoRepository.getTodos(new TodoRepositoryContract.LoadTodosCallback() {
            @Override
            public void onTodosLoaded(List<Todo> todos) {
                mTodoView.showTodos();
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    @Override
    public void addNewTodo() {

    }

    @Override
    public void completeTodo() {

    }
}
