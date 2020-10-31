package com.vargancys.learningassistant.base;

import java.lang.ref.WeakReference;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/10/31
 * version:1.0
 * 模块名:
 */
public abstract class BasePresenter<V extends BaseActivity,M extends BaseModel,CONTRACT> {
    public M m;
    private WeakReference<V> vWeakReference;
    public BasePresenter(){
        m = getModel();
    }

    public abstract M getModel();

    public void unBindView() {
        if(vWeakReference != null){
            vWeakReference.clear();
            vWeakReference = null;
            System.gc();
        }
    }

    public void bindView(V v) {
        vWeakReference = new WeakReference<>(v);
    }
    
    public V getView(){
        if(vWeakReference != null){
            return vWeakReference.get();
        }
        return null;
    }

    public abstract CONTRACT getContract();
}























