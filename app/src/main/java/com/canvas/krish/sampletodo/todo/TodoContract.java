package com.canvas.krish.sampletodo.todo;

import com.canvas.krish.sampletodo.BasePresenter;
import com.canvas.krish.sampletodo.BaseView;

/**
 * Created by Krishna Chaitanya Kandula on 3/31/2017.
 */

public interface TodoContract {
    interface View extends BaseView <Presenter>{
        void showTasks();
        void showTaskCompleted();
        void showNoTasks();
        void setLoadingIndicator();
    }

    interface Presenter extends BasePresenter<View> {
        void loadTasks();
        void addNewTask();
        void completeTask();
    }
}
