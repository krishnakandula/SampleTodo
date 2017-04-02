package com.canvas.krish.sampletodo.todo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.canvas.krish.sampletodo.R;
import com.canvas.krish.sampletodo.TodoApplication;
import com.canvas.krish.sampletodo.data.models.Todo;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Krishna Chaitanya Kandula on 3/31/2017.
 */

public class TodoFragment extends Fragment implements TodoContract.View{

    @Inject
    TodoContract.Presenter mPresenter;
    private Unbinder mUnbinder;
    private TodoListAdapter mAdapter;

    @BindView(R.id.TodoFragment_Todo_RecyclerView)
    RecyclerView todoRecyclerView;

    @BindView(R.id.fragment_todo_add_todo_btn)
    Button addTodoBtn;

    public static TodoFragment init(){
        Bundle args = new Bundle();

        TodoFragment fragment = new TodoFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((TodoApplication) getActivity().getApplication()).getComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        setupRecyclerView();
        return view;
    }

    private void setupRecyclerView(){
        mAdapter = new TodoListAdapter(getContext());
        todoRecyclerView.setAdapter(mAdapter);
        todoRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start(this);
    }

    @Override
    public void showTodos(List<Todo> todoList) {
        mAdapter.setData(todoList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showTodoCompleted() {

    }

    @Override
    public void showNoTodo() {

    }

    @Override
    public void setLoadingIndicator() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @OnClick(R.id.fragment_todo_add_todo_btn)
    public void onAddTodoBtnClick(){
        mPresenter.addNewTodo();
    }
}
