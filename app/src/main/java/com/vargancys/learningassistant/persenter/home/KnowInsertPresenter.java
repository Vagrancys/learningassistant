package com.vargancys.learningassistant.persenter.home;

import com.vargancys.learningassistant.db.home.HomeKnowContent;
import com.vargancys.learningassistant.db.home.HomeKnowFunction;
import com.vargancys.learningassistant.model.home.request.KnowInsertRequest;
import com.vargancys.learningassistant.module.home.view.KnowInsertDefaultView;
import com.vargancys.learningassistant.module.home.view.KnowInsertFirstView;
import com.vargancys.learningassistant.module.home.view.KnowInsertSecondView;

import org.litepal.LitePal;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/09
 * version:1.0
 */
public class KnowInsertPresenter {
    private KnowInsertRequest mRequest;
    private Object mView;
    public KnowInsertPresenter(Object view){
        mView = view;
        mRequest = new KnowInsertRequest();
    }

    //默认级判断是否为空
    public void isDefaultEmpty(){
        boolean result = ((KnowInsertDefaultView) mView).isDefaultEmpty();
        if(!result){
            ((KnowInsertDefaultView) mView).isDefaultEqualsItem();
        }else{
            ((KnowInsertDefaultView) mView).isDefaultEmptyError(502,"没有填写完!");
        }
    }

    //入门级判断是否为空
    public void isFirstEmpty(){
        boolean result = ((KnowInsertFirstView) mView).isFirstEmpty();
        if(!result){
            ((KnowInsertFirstView) mView).isFirstEqualsItem();
        }else{
            ((KnowInsertFirstView) mView).isFirstEmptyError(502,"没有填写完!");
        }
    }

    //默认级判断是否已经存在
    public void isEqualsDefaultItem(String title){
        boolean result = mRequest.isEqualsDefaultItem(title);
        if(result){
            ((KnowInsertDefaultView) mView).saveDefaultKnowItem();
        }else{
            ((KnowInsertDefaultView) mView).isDefaultEqualsError(501,"已经有该数据了!");
        }
    }

    //入门级判断是否已经存在
    public void isEqualsFirstItem(String title){
        boolean result = mRequest.isEqualsFirstItem(title);
        if(result){
            ((KnowInsertFirstView) mView).saveFirstKnowItem();
        }else{
            ((KnowInsertFirstView) mView).isFirstEqualsError(501,"已经有该数据了!");
        }
    }

    //保存默认级知识项
    public void saveKnowDefaultItem(int know_id,String title,String summary,String show,
                                    String explain,String heed,String experience){
        boolean result = mRequest.saveKnowDefaultItem(know_id,title,summary,show,
                explain,heed,experience);
        if(result){
            ((KnowInsertDefaultView) mView).saveDefaultItemFinish();
        }else{
            ((KnowInsertDefaultView) mView).saveDefaultItemError(501,"该数据项保存失败了!");
        }
    }

    //保存入门级知识项
    public void saveKnowFirstItem(int know_id,String title,String summary,String show,
                                    String explain,String heed,String experience){
        boolean result = mRequest.saveKnowFirstItem(know_id,title,summary,show,
                explain,heed,experience);
        if(result){
            ((KnowInsertFirstView) mView).saveFirstItemFinish();
        }else{
            ((KnowInsertFirstView) mView).saveFirstItemError(501,"该数据项保存失败了!");
        }
    }

    //添加熟悉级的函数项
    public void addFunctionData(int mCommon, String title, String summary, String explain) {
        boolean result = ((KnowInsertSecondView) mView).addFunctionData(mCommon, title, summary, explain);
        if(result){
            ((KnowInsertSecondView) mView).addFunctionFinish();
        }else{
            ((KnowInsertSecondView) mView).addFunctionError(501,"添加函数项失败了!");
        }
    }

    //熟悉级判断是否已经存在
    public void isEqualsSecondItem(String title) {
        boolean result = mRequest.isEqualsSecondItem(title);
        if(!result){
            ((KnowInsertSecondView) mView).saveSecondKnowItem();
        }else{
            ((KnowInsertSecondView) mView).isSecondEqualsError(501,"已经有该数据项了!");
        }
    }

    //显示熟悉级的函数添加窗口
    public void showFunctionWindow() {
        ((KnowInsertSecondView) mView).showFunctionWindow();
    }

    //熟悉级判断是否为空
    public void isSecondEmpty() {
        boolean result = ((KnowInsertSecondView) mView).isSecondEmpty();
        if(!result){
            ((KnowInsertSecondView) mView).isSecondEqualsItem();
        }else{
            ((KnowInsertSecondView) mView).isSecondEmptyError(502,"没有填写完!");
        }
    }

    //保存熟悉级知识项
    public void saveKnowSecondItem(int know_item_id, String title, String summary,
                                   List<HomeKnowFunction> homeKnowFunctions, String heed, String experience) {
        boolean result = mRequest.saveKnowSecondItem(know_item_id,title,summary,homeKnowFunctions,
                heed,experience);
        if(result){
            ((KnowInsertSecondView) mView).saveSecondItemFinish();
        }else{
            ((KnowInsertSecondView) mView).saveSecondItemError(501,"该数据项保存失败了!");
        }
    }

    //判断熟悉级的函数是否为空
    public boolean isFunctionEmpty(int mCommon, String title,
                                   String summary, String explain) {
        return ((KnowInsertSecondView) mView).isFunctionEmpty(mCommon, title, summary, explain);
    }
}
