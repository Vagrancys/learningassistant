package com.vargancys.learningassistant.base;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/10/31
 * version:1.0
 * 模块名:
 */
public abstract class BaseModel<P extends BasePresenter,CONTRACT> {
    public P p;
    public BaseModel(P p){
        this.p = p;
    }

    public abstract CONTRACT getContract();
}
