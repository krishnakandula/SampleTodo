package com.canvas.krish.sampletodo.data.source;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.canvas.krish.sampletodo.data.models.Todo;
import com.canvas.krish.sampletodo.data.source.local.TodoBaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Krishna Chaitanya Kandula on 4/1/2017.
 */

public class TodoRepositoryImpl implements TodoRepositoryContract {
    private TodoBaseHelper mDbHelper;
    private SQLiteDatabase mDatabase;

    private List<Todo> cachedData;

    public TodoRepositoryImpl(Context context){
        mDbHelper = new TodoBaseHelper(context);
        mDatabase = mDbHelper.getWritableDatabase();
        cachedData = new ArrayList<>();
    }

    @Override
    public void saveTodo(Todo todo) {

    }

    @Override
    public void getTodo(String todoId, GetTodoCallback callback) {
        Todo todo = new Todo();
        todo.setText("Hello, World!");
        callback.onTodoLoaded(todo);
    }

    @Override
    public void getTodos(LoadTodosCallback callback) {
        if(cachedData == null) {

        } else {
            Todo todo = new Todo();
            todo.setText("1");
            List<Todo> todoList = new ArrayList<>();
            todoList.add(todo);
            callback.onTodosLoaded(todoList);
        }

    }

    @Override
    public void deleteTodo(String todoId) {

    }
}
