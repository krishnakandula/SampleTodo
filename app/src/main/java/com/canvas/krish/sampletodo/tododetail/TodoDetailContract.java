package com.canvas.krish.sampletodo.tododetail;

import com.canvas.krish.sampletodo.data.models.Todo;

import java.util.UUID;

/**
 * Created by Krishna Chaitanya Kandula on 4/9/17.
 */

public interface TodoDetailContract {
    interface View {
        void showDetails(Todo todo);
        void closeDetailView();
    }

    interface Presenter {
        void start(View view, UUID todoId);
        void loadTodoDetails();
        void onPositiveAction(Todo todo);
        void onNegativeAction();
    }
}
