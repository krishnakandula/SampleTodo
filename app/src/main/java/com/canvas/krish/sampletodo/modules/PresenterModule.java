package com.canvas.krish.sampletodo.modules;

import com.canvas.krish.sampletodo.todo.TodoContract;
import com.canvas.krish.sampletodo.todo.TodoPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Krishna Chaitanya Kandula on 4/1/2017.
 */
@Module
public class PresenterModule {

    @Provides
    @Singleton
    public TodoContract.Presenter providesTodo(){
        return new TodoPresenter();
    }
}
