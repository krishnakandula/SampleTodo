package com.canvas.krish.sampletodo.todo;

import com.canvas.krish.sampletodo.data.models.Todo;
import com.canvas.krish.sampletodo.data.source.TodoRepositoryContract;

import java.util.List;
import java.util.UUID;

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
        //Show loading indicator
        mTodoView.showLoadingIndicator(true);
        //Need to force update here
        mTodoRepository.getTodos(new TodoRepositoryContract.LoadTodosCallback() {
            @Override
            public void onTodosLoaded(List<Todo> todos) {
                mTodoView.showTodos(todos);
            }

            @Override
            public void onDataNotAvailable() {
                //Create toast saying not data found
            }
        }, true);
        mTodoView.showLoadingIndicator(false);
    }

    @Override
    public void addNewTodo() {
        Todo todo = new Todo();
        todo.setText("Dragon");
        mTodoRepository.saveTodo(todo);
        //Don't need to force update here
        mTodoRepository.getTodos(new TodoRepositoryContract.LoadTodosCallback() {
            @Override
            public void onTodosLoaded(List<Todo> todos) {
                mTodoView.showTodos(todos);
            }

            @Override
            public void onDataNotAvailable() {
                String errorMessage = "Could not update todo list";
                mTodoView.showMessage(errorMessage);
            }
        }, false);
    }

    @Override
    public void completeTodo(UUID todoId, boolean isCompleted) {
        if(isCompleted) {
            mTodoView.showTodoCompleted();
            //Move to bottom of list
            mTodoView.moveTodoToBottom(todoId);
        }

        //Update db
    }
}
