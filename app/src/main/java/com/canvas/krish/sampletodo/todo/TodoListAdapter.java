package com.canvas.krish.sampletodo.todo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.canvas.krish.sampletodo.R;

/**
 * Created by Krishna Chaitanya Kandula on 4/1/17.
 */

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.TodoListViewHolder> {

    private Context mContext;

    protected TodoListAdapter(Context context){
        mContext = context;
    }

    @Override
    public TodoListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.itemview_todolist, viewGroup, false);

        return new TodoListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TodoListViewHolder todoListViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class TodoListViewHolder extends RecyclerView.ViewHolder {
        public TodoListViewHolder(View itemView){
            super(itemView);
        }
    }
}
