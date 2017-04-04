package com.canvas.krish.sampletodo.data.source;

import com.canvas.krish.sampletodo.data.models.Todo;

import java.util.List;
import java.util.UUID;

/**
 * Created by krishnakandula on 4/1/17.
 */

public interface TodoRepositoryContract {
    void saveTodo(Todo todo);
    void getTodo(UUID todoId, GetTodoCallback callback);
    void getTodos(LoadTodosCallback callback, boolean forceUpdate);
    void deleteTodo(UUID todoId);
    void updateTodo(Todo todo);

    interface LoadTodosCallback {
        void onTodosLoaded(List<Todo> todos);
        void onDataNotAvailable();
    }

    interface GetTodoCallback {
        void onTodoLoaded(Todo todo);
        void onDataNotAvailable();
    }
}
