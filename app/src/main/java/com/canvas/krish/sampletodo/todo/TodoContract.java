package com.canvas.krish.sampletodo.todo;

import com.canvas.krish.sampletodo.BasePresenter;
import com.canvas.krish.sampletodo.BaseView;
import com.canvas.krish.sampletodo.data.models.Todo;

import java.util.List;
import java.util.UUID;

/**
 * Created by Krishna Chaitanya Kandula on 3/31/2017.
 */

public interface TodoContract {
    interface View extends BaseView <Presenter>{
        void showTodos(List<Todo> todoList);
        void showTodoCompleted();
        void showLoadingIndicator(boolean isLoading);
        void showMessage(String message);
        void moveTodoToBottom(UUID todoId);
        void moveTodoToTop(UUID todoId);
        void showTodoDetails(UUID todoId);
    }

    interface Presenter extends BasePresenter<View> {
        void loadTodos();
        void addNewTodo();
        void completeTodo(UUID todoId, boolean isCompleted);
        void onTodoClicked(UUID todoId);
    }
}
