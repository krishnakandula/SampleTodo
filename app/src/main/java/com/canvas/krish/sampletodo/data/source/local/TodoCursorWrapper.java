package com.canvas.krish.sampletodo.data.source.local;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.canvas.krish.sampletodo.data.models.Todo;

import java.util.Date;
import java.util.UUID;

import static com.canvas.krish.sampletodo.data.source.local.TodoDbSchema.*;

/**
 * Created by Krishna Chaitanya Kandula on 4/2/2017.
 */

public class TodoCursorWrapper extends CursorWrapper {
    public TodoCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Todo getTodo(){
        String uuidString = getString(getColumnIndex(TodoTable.Cols.UUID));
        String text = getString(getColumnIndex(TodoTable.Cols.TEXT));
        boolean completed = getInt(getColumnIndex(TodoTable.Cols.COMPLETED)) == 1;
        String createdOnString = getString(getColumnIndex(TodoTable.Cols.CREATED_ON));
        String completedOnString = getString(getColumnIndex(TodoTable.Cols.COMPLETED_ON));

        UUID uuid = UUID.fromString(uuidString);
        Date createdOn = new Date(createdOnString);
        Date completedOn = null;
        if(completedOnString != null && !completedOnString.isEmpty())
            completedOn = new Date(completedOnString);

        return new Todo(uuid, text, completed, createdOn, completedOn);
    }
}
