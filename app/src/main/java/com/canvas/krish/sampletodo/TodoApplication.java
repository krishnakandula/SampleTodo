package com.canvas.krish.sampletodo;

import android.app.Application;

import com.canvas.krish.sampletodo.modules.PresenterModule;
import com.canvas.krish.sampletodo.todo.TodoFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Krishna Chaitanya Kandula on 4/1/2017.
 */

public class TodoApplication extends Application {

    ApplicationComponent mComponent;

    @Singleton
    @Component(modules = {PresenterModule.class})
    public interface ApplicationComponent {
        void inject(TodoFragment fragment);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mComponent = DaggerTodoApplication_ApplicationComponent.builder().build();
    }

    public ApplicationComponent getComponent() {
        return mComponent;
    }

}
