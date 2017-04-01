package com.canvas.krish.sampletodo.data.source;

import com.canvas.krish.sampletodo.data.models.Todo;

/**
 * Created by krishnakandula on 4/1/17.
 */

public interface TodoRepositoryContract {
    void saveTodo(Todo todo);
    void getTodo(String todoId);
    void getTodos();
    void deleteTodo(String todoId);
}
