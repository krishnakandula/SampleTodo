package com.canvas.krish.sampletodo.data.source;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.canvas.krish.sampletodo.data.models.Todo;
import com.canvas.krish.sampletodo.data.source.local.TodoBaseHelper;
import com.canvas.krish.sampletodo.data.source.local.TodoCursorWrapper;

import java.util.ArrayList;
import java.util.List;

import static com.canvas.krish.sampletodo.data.source.local.TodoDbSchema.TodoTable;

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
        ContentValues values = getContentValues(todo);
        mDatabase.insert(TodoTable.NAME, null, values);
    }

    private ContentValues getContentValues(Todo todo){
        ContentValues values = new ContentValues();
        values.put(TodoTable.Cols.UUID, todo.getUuid().toString());
        values.put(TodoTable.Cols.TEXT, todo.getText());
        values.put(TodoTable.Cols.COMPLETED, todo.isCompleted() ? 1 : 0);
        values.put(TodoTable.Cols.CREATED_ON, todo.getCreatedOn().toString());
        if(todo.getCompletedOn() != null)
            values.put(TodoTable.Cols.COMPLETED_ON, todo.getCompletedOn().toString());

        return values;
    }

    @Override
    public void getTodo(String todoId, GetTodoCallback callback) {
        Todo todo = new Todo();
        todo.setText("Hello, World!");
        callback.onTodoLoaded(todo);
    }

    @Override
    public void getTodos(LoadTodosCallback callback) {
//        if(cachedData == null) {
//
//        } else {
//            Todo todo = new Todo();
//            todo.setText("1");
//            List<Todo> todoList = new ArrayList<>();
//            todoList.add(todo);
//            callback.onTodosLoaded(todoList);
//        }
        List<Todo> todos = new ArrayList<>();
        //Gets everything
        TodoCursorWrapper cursor = queryTodos(null, null);
        if(cursor.getCount() == 0) {
            cursor.close();
            callback.onDataNotAvailable();
        } else {
            cursor.moveToFirst();
        }
    }

    private TodoCursorWrapper queryTodos(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                                        TodoTable.NAME,
                                        null,
                                        whereClause,
                                        whereArgs,
                                        null,
                                        null,
                                        null);
        return new TodoCursorWrapper(cursor);
    }

    @Override
    public void deleteTodo(String todoId) {

    }
}
