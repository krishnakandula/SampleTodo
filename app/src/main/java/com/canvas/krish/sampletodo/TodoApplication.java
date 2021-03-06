package com.canvas.krish.sampletodo;

import android.app.Application;
import android.content.Context;

import com.canvas.krish.sampletodo.data.source.TodoRepositoryContract;
import com.canvas.krish.sampletodo.data.source.TodoRepositoryImpl;
import com.canvas.krish.sampletodo.modules.ApplicationModule;
import com.canvas.krish.sampletodo.modules.PresenterModule;
import com.canvas.krish.sampletodo.modules.RepositoryModule;
import com.canvas.krish.sampletodo.todo.TodoContract;
import com.canvas.krish.sampletodo.todo.TodoFragment;
import com.canvas.krish.sampletodo.tododetail.EditTodoDialogFragment;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Provides;

/**
 * Created by Krishna Chaitanya Kandula on 4/1/2017.
 */

public class TodoApplication extends Application {

    ApplicationComponent mComponent;

    @Singleton
    @Component(modules = {PresenterModule.class, ApplicationModule.class, RepositoryModule.class})
    public interface ApplicationComponent {
        void inject(TodoFragment fragment);
        void inject(EditTodoDialogFragment fragment);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mComponent = DaggerTodoApplication_ApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .presenterModule(new PresenterModule())
                .repositoryModule(new RepositoryModule())
                .build();
    }

    public ApplicationComponent getComponent() {
        return mComponent;
    }

}
