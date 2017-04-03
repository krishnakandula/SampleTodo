package com.canvas.krish.sampletodo.todo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.canvas.krish.sampletodo.R;
import com.canvas.krish.sampletodo.data.models.Todo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Krishna Chaitanya Kandula on 4/1/17.
 */

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.TodoListViewHolder> {

    private Context mContext;
    private List<Todo> data;

    protected TodoListAdapter(Context context){
        mContext = context;
        data = new ArrayList<>();

    }

    @Override
    public TodoListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.itemview_todolist, viewGroup, false);

        return new TodoListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TodoListViewHolder todoListViewHolder, int i) {
        todoListViewHolder.onBind(data.get(i));
    }

    public void setData(List<Todo> data){
        //Remove all items from recyclerview
        notifyItemRangeRemoved(0, getItemCount() - 1);
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class TodoListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.itemview_todolist_text)
        TextView mTextView;
        public TodoListViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(Todo todo){
            mTextView.setText(todo.getText());
        }
    }
}
