package com.canvas.krish.sampletodo.data.source;

import android.database.sqlite.SQLiteDatabase;

import com.canvas.krish.sampletodo.data.models.Todo;

/**
 * Created by Krishna Chaitanya Kandula on 4/1/2017.
 */

public class TodoRepositoryImpl implements TodoRepositoryContract {

    private SQLiteDatabase mDatabase;



    @Override
    public void saveTodo(Todo todo) {

    }

    @Override
    public void getTodo(String todoId, GetTodoCallback callback) {

    }

    @Override
    public void getTodos(LoadTodosCallback callback) {

    }

    @Override
    public void deleteTodo(String todoId) {

    }
}
