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
import java.util.UUID;

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
        //Insert into database
        ContentValues values = getContentValues(todo);
        mDatabase.insert(TodoTable.NAME, null, values);

        //Insert into cached data
        cachedData.add(todo);
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
    public void getTodo(UUID todoId, GetTodoCallback callback) {
        String whereClause = TodoTable.Cols.UUID + " = ?";
        String[] whereArgs = new String[]{todoId.toString()};

        TodoCursorWrapper cursor = queryTodos(whereClause, whereArgs);
        List<Todo> todos = new ArrayList<>();
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                todos.add(cursor.getTodo());
                cursor.moveToNext();
            }
            callback.onTodoLoaded(todos.get(0));
        } else {
            callback.onDataNotAvailable();
        }
    }

    @Override
    public void getTodos(LoadTodosCallback callback, boolean forceUpdate) {
        if(forceUpdate || cachedData == null || cachedData.isEmpty())
            updateCache();
        //Returns whatever is in cache
        callback.onTodosLoaded(cachedData);
    }

    public void updateCache(){
        List<Todo> todos = new ArrayList<>();
        //Gets everything from db and refreshes cache
        TodoCursorWrapper cursor = queryTodos(null, null);
        if (cursor.getCount() == 0) {
            cursor.close();
            cachedData.clear();
        } else {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                todos.add(cursor.getTodo());
                cursor.moveToNext();
            }
            cursor.close();
            cachedData.clear();
            cachedData.addAll(todos);
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
    public void deleteTodo(UUID todoId) {

    }

    @Override
    public void updateTodo(Todo todo) {
        //Update cache
        for(Todo t : cachedData){
            if(todo.getUuid().equals(t.getUuid())){
                t.setText(todo.getText());
                t.setCompleted(todo.isCompleted());
                t.setCompletedOn(todo.getCompletedOn());
            }
        }

        //Update db
        int completed = todo.isCompleted() ? 1 : 0;
        String completedOn = todo.getCompletedOn() == null ? null : todo.getCompletedOn().toString();
        String updateStatement = "UPDATE " + TodoTable.NAME +
                                    " SET " + TodoTable.Cols.TEXT + " = '" + todo.getText() + "'" +
                                    ", " + TodoTable.Cols.COMPLETED + " = " + completed +
                                    ", " + TodoTable.Cols.COMPLETED_ON + " = " + completedOn +
                                    " WHERE " + TodoTable.Cols.UUID + " = '" + todo.getUuid().toString() + "'";
        mDatabase.execSQL(updateStatement);
    }
}
