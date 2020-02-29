package com.vargancys.learningassistant.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/02/29
 * version:1.0
 */
public abstract class BaseActivity extends AppCompatActivity {
    private Unbinder binder;
    @LayoutRes
    public abstract int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        binder = ButterKnife.bind(this);
        initView();
        loadData();
    }

    public abstract void initView();

    public void initToolbar(){}

    public void loadData(){}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(binder != null){
            binder.unbind();
        }
    }
}
