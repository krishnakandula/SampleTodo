package com.canvas.krish.sampletodo.todo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.canvas.krish.sampletodo.R;
import com.canvas.krish.sampletodo.data.models.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Krishna Chaitanya Kandula on 4/1/17.
 */

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.TodoListViewHolder> {

    private Context mContext;
    private List<Todo> data;
    private TodoItemViewChangeListener mChangeListener;

    protected TodoListAdapter(Context context, TodoItemViewChangeListener changeListener){
        mContext = context;
        data = new ArrayList<>();
        mChangeListener = changeListener;
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

    /**
     * Moves a todoitem to the bottom of the list
     * @param todoId
     */
    public void moveTodoItemToBottom(UUID todoId){
        int todoIndex = 0; //Not found
        boolean found = false;

        while(todoIndex < data.size()){
            Todo current = data.get(todoIndex);
            if(current.getUuid().equals(todoId)){
                found = true;
                break;
            }
            todoIndex++;
        }

        //Move item in data list to end
        if(found){
            Todo todo = data.get(todoIndex);
            data.remove(todoIndex);
            data.add(todo);
            notifyItemMoved(todoIndex, data.size() - 1);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class TodoListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.itemview_todolist_text)
        TextView mTextView;

        @BindView(R.id.itemview_todolist_completed_chkbox)
        CheckBox completedTodoChkbox;

        private UUID todoId;

        public TodoListViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            completedTodoChkbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mChangeListener.onCompletedChanged(todoId, completedTodoChkbox.isChecked());
                }
            });
        }

        public void onBind(Todo todo){
            this.todoId = todo.getUuid();
            mTextView.setText(todo.getText());
            completedTodoChkbox.setChecked(todo.isCompleted());
        }
    }

    /**
     * Interface for getting changes from TodoListAdapter
     */
    interface TodoItemViewChangeListener {
        void onCompletedChanged(UUID todoId, boolean isCompleted);
    }
}
