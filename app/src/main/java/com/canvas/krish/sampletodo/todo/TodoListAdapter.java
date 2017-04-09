package com.canvas.krish.sampletodo.todo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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
    public void moveItemToBottom(UUID todoId){
       int fromIndex = getTodoLocation(todoId);

        //Move item in data list to end
        if(fromIndex > 0)
            moveItem(fromIndex, data.size() - 1);
    }

    public void moveItemToTop(UUID todoId){
        int fromIndex = getTodoLocation(todoId);

        if(fromIndex > 0)
            moveItem(fromIndex, 0);
    }

    private void moveItem(int fromIndex, int endIndex){
        Todo todo = data.get(fromIndex);
        data.remove(fromIndex);
        data.add(endIndex, todo);
        notifyItemMoved(fromIndex, endIndex);
    }

    /**
     * Gets the index of an item in data
     * @param todoId the item to get the index of
     * @return index location of item, < 0 if item not found
     */
    private int getTodoLocation(UUID todoId){
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

        if(found)
            return todoIndex;
        else
            return -1;
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
            completedTodoChkbox.setOnClickListener(v -> mChangeListener.onCompletedChanged(todoId, completedTodoChkbox.isChecked()));
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
