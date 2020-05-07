package com.vargancys.learningassistant.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/01
 * version:1.0
 */
public abstract class BaseFragment extends Fragment {
    private Activity context;
    private View parentView;
    private Unbinder binder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @LayoutRes
    public abstract int getLayoutId();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        parentView = View.inflate(context,getLayoutId(),null);
        binder = ButterKnife.bind(this,parentView);
        initView();
        return parentView;
    }

    protected abstract void initView();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    public void initData(){

    }

    @Nullable
    @Override
    public Activity getContext() {
        return context;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(binder != null){
            binder.unbind();
        }
    }
}
