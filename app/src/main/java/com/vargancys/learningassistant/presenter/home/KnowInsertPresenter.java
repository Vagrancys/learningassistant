package com.vargancys.learningassistant.presenter.home;

import android.util.Log;

import com.vargancys.learningassistant.bean.home.HomeKnowFunction;
import com.vargancys.learningassistant.model.home.request.KnowInsertRequest;
import com.vargancys.learningassistant.module.home.view.KnowInsertDefaultView;
import com.vargancys.learningassistant.module.home.view.KnowInsertFifthView;
import com.vargancys.learningassistant.module.home.view.KnowInsertFourthView;
import com.vargancys.learningassistant.module.home.view.KnowInsertThirdView;

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
        boolean result = false;//((InsertArticleView) mView.isFirstEmpty();
        if(!result){
            //((InsertArticleView) mView).isFirstEqualsItem();
        }else{
            //((InsertArticleView) mView).isFirstEmptyError(502,"没有填写完!");
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
            //((InsertArticleView) mView).saveFirstKnowItem();
        }else{
            //((InsertArticleView) mView).isFirstEqualsError(501,"已经有该数据了!");
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
        Log.e("save1","is=11" );
        boolean result = mRequest.saveKnowFirstItem(know_id,title,summary,show,
                explain,heed,experience);
        if(result){
            //((InsertArticleView) mView).saveFirstItemFinish();
        }else{
            //((InsertArticleView) mView).saveFirstItemError(501,"该数据项保存失败了!");
        }
    }

    //添加熟悉级的函数项
    public void addFunctionSecondData(int mCommon, String title, String summary, String explain) {
//        boolean result =  mView.addFunctionData(mCommon, title, summary, explain);
//        if(result){
//             mView.addFunctionFinish();
//        }else{
//            mView.addFunctionError(501,"添加函数项失败了!");
//        }
    }

    //添加精通级的函数项
    public void addFunctionFourthData(int mCommon, String title, String summary, String explain) {
        boolean result = ((KnowInsertFourthView) mView).addFunctionData(mCommon, title, summary, explain);
        if(result){
            ((KnowInsertFourthView) mView).addFunctionFinish();
        }else{
            ((KnowInsertFourthView) mView).addFunctionError(501,"添加函数项失败了!");
        }
    }

    //熟悉级判断是否已经存在
    public void isEqualsSecondItem(String title) {
//        boolean result = mRequest.isEqualsSecondItem(title);
//        if(result){
//            mView.saveSecondKnowItem();
//        }else{
//            mView.isSecondEqualsError(501,"已经有该数据项了!");
//        }
    }

    //显示熟悉级的函数添加窗口
    public void showSecondFunctionWindow() {
//        mView.showFunctionWindow();
    }

    //显示精通级的函数添加窗口
    public void showFourthFunctionWindow() {
        ((KnowInsertFourthView) mView).showFunctionWindow();
    }

    //熟悉级判断是否为空
    public void isSecondEmpty() {
//        boolean result = mView.isSecondEmpty();
//        if(!result){
//            mView.isSecondEqualsItem();
//        }else{
//            mView.isSecondEmptyError(502,"没有填写完!");
//        }
    }

    //保存熟悉级知识项
    public void saveKnowSecondItem(int know_item_id, String title, String summary,
                                   List<HomeKnowFunction> homeKnowFunctions, String heed, String experience) {
//        boolean result = mRequest.saveKnowSecondItem(know_item_id,title,summary,homeKnowFunctions,
//                heed,experience);
//        if(result){
//            mView.saveSecondItemFinish();
//        }else{
//            mView.saveSecondItemError(501,"该数据项保存失败了!");
//        }
    }

    //判断熟悉级的函数是否为空
    public boolean isFunctionSecondEmpty(int mCommon, String title,
                                   String summary, String explain) {
//        return mView.isFunctionEmpty(mCommon, title, summary, explain);
        return false;
    }

    //判断熟练级的函数是否为空
    public boolean isFunctionFourthEmpty(int mCommon, String title,
                                   String summary, String explain) {
        return ((KnowInsertFourthView) mView).isFunctionEmpty(mCommon, title, summary, explain);
    }

    //熟练级判断是否为空
    public void isThirdEmpty() {
        boolean result = ((KnowInsertThirdView) mView).isThirdEmpty();
        if(!result){
            ((KnowInsertThirdView) mView).isThirdEqualsItem();
        }else{
            ((KnowInsertThirdView) mView).isThirdEmptyError(502,"没有填写完!");
        }
    }

    //熟练级判断是否已经存在
    public void isEqualsThirdItem(String title) {
        boolean result = mRequest.isEqualsThirdItem(title);
        if(result){
            ((KnowInsertThirdView) mView).saveThirdKnowItem();
        }else{
            ((KnowInsertThirdView) mView).isThirdEqualsError(501,"已经有该数据项了!");
        }
    }

    //保存熟练级知识项
    public void saveKnowThirdItem(int know_item_id, String title, String summary,
                                   String show,String explain, String heed, String experience) {
        boolean result = mRequest.saveKnowThirdItem(know_item_id,title,summary,show,explain,
                heed,experience);
        if(result){
            ((KnowInsertThirdView) mView).saveThirdItemFinish();
        }else{
            ((KnowInsertThirdView) mView).saveThirdItemError(501,"该数据项保存失败了!");
        }
    }

    //精通级判断是否已经存在
    public void isEqualsFourthItem(String title) {
        boolean result = mRequest.isEqualsFourthItem(title);
        if(result){
            ((KnowInsertFourthView) mView).saveFourthKnowItem();
        }else{
            ((KnowInsertFourthView) mView).isFourthEqualsError(501,"已经有该数据项了!");
        }
    }

    //保存精通级知识项
    public void saveKnowFourthItem(int know_item_id, String title, String summary,
                                   List<HomeKnowFunction> homeKnowFunctions, String heed, String experience) {
        boolean result = mRequest.saveKnowFourthItem(know_item_id,title,summary,homeKnowFunctions,
                heed,experience);
        if(result){
            ((KnowInsertFourthView) mView).saveFourthItemFinish();
        }else{
            ((KnowInsertFourthView) mView).saveFourthItemError(501,"该数据项保存失败了!");
        }
    }

    //熟练级判断是否为空
    public void isFourthEmpty() {
        boolean result = ((KnowInsertFourthView) mView).isFourthEmpty();
        if(!result){
            ((KnowInsertFourthView) mView).isFourthEqualsItem();
        }else{
            ((KnowInsertFourthView) mView).isFourthEmptyError(502,"没有填写完!");
        }
    }

    //专家级判断是否为空
    public void isFifthEmpty() {
        boolean result = ((KnowInsertFifthView) mView).isFifthEmpty();
        if(!result){
            ((KnowInsertFifthView) mView).isFifthEqualsItem();
        }else{
            ((KnowInsertFifthView) mView).isFifthEmptyError(502,"没有填写完!");
        }
    }

    //专家级判断是否已经存在
    public void isEqualsFifthItem(String title) {
        boolean result = mRequest.isEqualsFifthItem(title);
        if(result){
            ((KnowInsertFifthView) mView).saveFifthKnowItem();
        }else{
            ((KnowInsertFifthView) mView).isFifthEqualsError(501,"已经有该数据项了!");
        }
    }

    //保存专家级知识项
    public void saveKnowFifthItem(int know_item_id, String title, String summary,
                                  String show,String explain, String heed, String experience) {
        boolean result = mRequest.saveKnowFifthItem(know_item_id,title,summary,show,explain,
                heed,experience);
        if(result){
            ((KnowInsertFifthView) mView).saveFifthItemFinish();
        }else{
            ((KnowInsertFifthView) mView).saveFifthItemError(501,"该数据项保存失败了!");
        }
    }
}
