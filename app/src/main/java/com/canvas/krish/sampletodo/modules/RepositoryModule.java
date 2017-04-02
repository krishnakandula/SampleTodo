package com.canvas.krish.sampletodo.modules;

import android.content.Context;

import com.canvas.krish.sampletodo.data.source.TodoRepositoryContract;
import com.canvas.krish.sampletodo.data.source.TodoRepositoryImpl;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Krishna Chaitanya Kandula on 4/1/2017.
 */
@Module
public class RepositoryModule {
    @Inject
    Context context;

    @Provides
    @Singleton
    public TodoRepositoryContract providesTodo(){
        return new TodoRepositoryImpl(context);
    }
}
