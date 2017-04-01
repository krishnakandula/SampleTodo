package com.canvas.krish.sampletodo.todo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.canvas.krish.sampletodo.R;
import com.canvas.krish.sampletodo.TodoApplication;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Krishna Chaitanya Kandula on 3/31/2017.
 */

public class TodoFragment extends Fragment implements TodoContract.View{

    @Inject
    TodoContract.Presenter mPresenter;
    private Unbinder mUnbinder;

    @BindView(R.id.TodoFragment_Todo_RecyclerView)
    RecyclerView todoRecyclerView;

    public static TodoFragment init(){
        Bundle args = new Bundle();

        TodoFragment fragment = new TodoFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((TodoApplication)getActivity().getApplication()).getComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start(this);
    }

    @Override
    public void showTasks() {

    }

    @Override
    public void showTaskCompleted() {

    }

    @Override
    public void showNoTasks() {

    }

    @Override
    public void setLoadingIndicator() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
