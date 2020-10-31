package com.vargancys.learningassistant.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.vargancys.learningassistant.presenter.BaseCallBackListener;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/02/29
 * version:1.0
 */
public abstract class BaseActivity<P extends BasePresenter,CONTRACT> extends AppCompatActivity implements BaseCallBackListener {
    private Unbinder binder;
    private Context context;
    private P presenter;
    @LayoutRes
    public abstract int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        binder = ButterKnife.bind(this);
        context = this;
        presenter = getPresenter();
        presenter.bindView(this);
        initView();
        initToolbar();
        loadData();
    }

    public Context getContext() {
        return context;
    }

    public abstract void initView();

    public void initToolbar(){}

    public void loadData(){}

    public abstract CONTRACT getContract();

    public abstract P getPresenter();
    public void error(Exception e){};

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unBindView();
        if(binder != null){
            binder.unbind();
        }
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onSuccess(Object object) {

    }

    @Override
    public void onNoData() {

    }

    @Override
    public void onFail() {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onFinish() {

    }
}
