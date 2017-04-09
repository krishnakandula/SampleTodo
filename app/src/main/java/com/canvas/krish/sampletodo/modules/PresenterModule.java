package com.canvas.krish.sampletodo.modules;

import com.canvas.krish.sampletodo.data.source.TodoRepositoryContract;
import com.canvas.krish.sampletodo.todo.TodoContract;
import com.canvas.krish.sampletodo.todo.TodoPresenter;
import com.canvas.krish.sampletodo.tododetail.TodoDetailContract;
import com.canvas.krish.sampletodo.tododetail.TodoDetailPresenter;

import javax.inject.Inject;
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
    @Inject
    public TodoContract.Presenter providesTodo(TodoRepositoryContract repositoryContract){
        return new TodoPresenter(repositoryContract);
    }
    
    @Provides
    @Singleton
    @Inject
    public TodoDetailContract.Presenter providesDetail(TodoRepositoryContract repositoryContract){
        return new TodoDetailPresenter(repositoryContract);
    }
}