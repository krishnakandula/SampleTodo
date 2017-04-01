package com.canvas.krish.sampletodo.modules;

import com.canvas.krish.sampletodo.data.source.TodoRepositoryContract;
import com.canvas.krish.sampletodo.data.source.TodoRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Krishna Chaitanya Kandula on 4/1/2017.
 */
@Module
public class RepositoryModule {

    @Provides
    @Singleton
    public static TodoRepositoryContract providesTodo(){
        return new TodoRepositoryImpl();
    }
}
