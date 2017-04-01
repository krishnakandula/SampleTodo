package com.canvas.krish.sampletodo.data.source.local;

/**
 * Created by krishnakandula on 4/1/17.
 */

public class TodoDbSchema {
    public static final class TodoTable {
        public static final String NAME = "todos";

        public static final class Cols {
            //Primary key
            public static final String UUID = "uuid";
            public static final String TEXT = "text";
            public static final String COMPLETED = "completed";
            public static final String COMPLETED_ON = "completed_on";
            public static final String CREATED_ON = "created_on";
        }
    }
}
