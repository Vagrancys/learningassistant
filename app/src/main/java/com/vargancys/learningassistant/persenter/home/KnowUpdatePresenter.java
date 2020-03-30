package com.vargancys.learningassistant.persenter.home;

import com.vargancys.learningassistant.db.home.HomeKnowContent;
import com.vargancys.learningassistant.db.home.HomeKnowFunction;
import com.vargancys.learningassistant.db.home.HomeKnowHistory;
import com.vargancys.learningassistant.db.home.HomeKnowHistoryFunction;
import com.vargancys.learningassistant.model.home.request.KnowUpdateRequest;
import com.vargancys.learningassistant.module.home.view.BaseKnowUpdateView;
import com.vargancys.learningassistant.module.home.view.KnowUpdateFourthView;
import com.vargancys.learningassistant.module.home.view.KnowUpdateSecondView;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/28
 * version:1.0
 */
public class KnowUpdatePresenter {
    private KnowUpdateRequest mRequest;
    private BaseKnowUpdateView mView;
    public KnowUpdatePresenter(BaseKnowUpdateView view){
        mView = view;
        mRequest = KnowUpdateRequest.getInstance();
    };

    //查找默认级数据
    public void getKnowDefaultContent(long content_id){
        HomeKnowContent homeKnowContent = mRequest.getKnowDefaultContent(content_id);
        if(homeKnowContent != null){
             mView.showKnowDataFinish(homeKnowContent);
        }else{
            mView.showKnowDataError(404,"没有该数据!");
        }
    }

    //判断默认级数据是否为空
    public void isKnowUpdateDefaultEmpty(){
        boolean result = mView.isKnowUpdateDefaultEmpty();
        if(result){
            mView.showKnowEmptyError(404,"该数据没有填写完成!");
        }else{
            boolean result2 = mView.isKnowUpdateDefaultEquals();
            if(!result2){
                mView.saveKnowUpdateContent();
            }else{
                mView.showKnowEqualsError(404,"您没有修改!");
            }
        }
    }

    //更新默认级数据
    public void saveKnowUpdateDefault(HomeKnowHistory mOldHistory, HomeKnowContent mNewContent) {
        boolean result = mRequest.saveKnowUpdateDefault(mOldHistory,mNewContent);
        if(result){
            mView.showKnowSaveFinish();
        }else{
            mView.showKnowSaveError(404,"保存失败了!");
        }
    }

    //查找入门级数据
    public void getKnowFirstContent(long content_id){
        HomeKnowContent homeKnowContent = mRequest.getKnowDefaultContent(content_id);
        if(homeKnowContent != null){
            mView.showKnowDataFinish(homeKnowContent);
        }else{
            mView.showKnowDataError(404,"没有该数据!");
        }
    }

    //判断入门级数据是否为空
    public void isKnowUpdateFirstEmpty(){
        boolean result = mView.isKnowUpdateDefaultEmpty();
        if(result){
            mView.showKnowEmptyError(404,"该数据没有填写完成!");
        }else{
            boolean result2 = mView.isKnowUpdateDefaultEquals();
            if(!result2){
                mView.saveKnowUpdateContent();
            }else{
                mView.showKnowEqualsError(404,"您没有修改!");
            }
        }
    }

    //更新入门级数据
    public void saveKnowUpdateFirst(HomeKnowHistory mOldHistory, HomeKnowContent mNewContent) {
        boolean result = mRequest.saveKnowUpdateFirst(mOldHistory,mNewContent);
        if(result){
            mView.showKnowSaveFinish();
        }else{
            mView.showKnowSaveError(404,"保存失败了!");
        }
    }

    //查找熟悉级数据
    public void getKnowSecondContent(long content_id){
        HomeKnowContent homeKnowContent = mRequest.getKnowDefaultContent(content_id);
        if(homeKnowContent != null){
            mView.showKnowDataFinish(homeKnowContent);
        }else{
            mView.showKnowDataError(404,"没有该数据!");
        }
    }

    //判断熟悉级数据是否为空
    public void isKnowUpdateSecondEmpty(){
        boolean result = mView.isKnowUpdateDefaultEmpty();
        if(result){
            mView.showKnowEmptyError(404,"该数据没有填写完成!");
        }else{
            boolean result2 = mView.isKnowUpdateDefaultEquals();
            if(!result2){
                mView.saveKnowUpdateContent();
            }else{
                mView.showKnowEqualsError(404,"您没有修改!");
            }
        }
    }

    //更新熟悉级数据
    public void saveKnowUpdateSecond(HomeKnowHistory mOldHistory, List<HomeKnowHistoryFunction> mOldHistoryFunction, HomeKnowContent mNewContent, List<HomeKnowFunction> homeKnowFunctions) {
        boolean result = mRequest.saveKnowUpdateSecond(mOldHistory,mOldHistoryFunction,mNewContent,homeKnowFunctions);
        if(result){
            mView.showKnowSaveFinish();
        }else{
            mView.showKnowSaveError(404,"保存失败了!");
        }
    }

    //显示更新熟悉级的函数
    public void showSecondFunctionWindow() {
        ((KnowUpdateSecondView) mView).showFunctionWindow();
    }

    //添加熟悉级的函数项
    public void addFunctionSecondData(int mCommon, String title, String summary, String explain) {
        boolean result = ((KnowUpdateSecondView) mView).addFunctionData(mCommon, title, summary, explain);
        if(result){
            ((KnowUpdateSecondView) mView).addFunctionFinish();
        }else{
            ((KnowUpdateSecondView) mView).addFunctionError(501,"添加函数项失败了!");
        }
    }

    //判断熟悉级的函数是否为空
    public boolean isFunctionSecondEmpty(int mCommon, String title,
                                         String summary, String explain) {
        return ((KnowUpdateSecondView) mView).isFunctionEmpty(mCommon, title, summary, explain);
    }


    //查找熟练级数据
    public void getKnowThirdContent(long content_id){
        HomeKnowContent homeKnowContent = mRequest.getKnowDefaultContent(content_id);
        if(homeKnowContent != null){
            mView.showKnowDataFinish(homeKnowContent);
        }else{
            mView.showKnowDataError(404,"没有该数据!");
        }
    }

    //判断熟练级数据是否为空
    public void isKnowUpdateThirdEmpty(){
        boolean result = mView.isKnowUpdateDefaultEmpty();
        if(result){
            mView.showKnowEmptyError(404,"该数据没有填写完成!");
        }else{
            boolean result2 = mView.isKnowUpdateDefaultEquals();
            if(!result2){
                mView.saveKnowUpdateContent();
            }else{
                mView.showKnowEqualsError(404,"您没有修改!");
            }
        }
    }

    //更新熟练级数据
    public void saveKnowUpdateThird(HomeKnowHistory mOldHistory, HomeKnowContent mNewContent) {
        boolean result = mRequest.saveKnowUpdateThird(mOldHistory,mNewContent);
        if(result){
            mView.showKnowSaveFinish();
        }else{
            mView.showKnowSaveError(404,"保存失败了!");
        }
    }

    //查找精通级数据
    public void getKnowFourthContent(long content_id){
        HomeKnowContent homeKnowContent = mRequest.getKnowDefaultContent(content_id);
        if(homeKnowContent != null){
            mView.showKnowDataFinish(homeKnowContent);
        }else{
            mView.showKnowDataError(404,"没有该数据!");
        }
    }

    //判断精通级数据是否为空
    public void isKnowUpdateFourthEmpty(){
        boolean result = mView.isKnowUpdateDefaultEmpty();
        if(result){
            mView.showKnowEmptyError(404,"该数据没有填写完成!");
        }else{
            boolean result2 = mView.isKnowUpdateDefaultEquals();
            if(!result2){
                mView.saveKnowUpdateContent();
            }else{
                mView.showKnowEqualsError(404,"您没有修改!");
            }
        }
    }

    //更新精通级数据
    public void saveKnowUpdateFourth(HomeKnowHistory mOldHistory, List<HomeKnowHistoryFunction> mOldHistoryFunction, HomeKnowContent mNewContent, List<HomeKnowFunction> homeKnowFunctions) {
        boolean result = mRequest.saveKnowUpdateFourth(mOldHistory,mOldHistoryFunction,mNewContent,homeKnowFunctions);
        if(result){
            mView.showKnowSaveFinish();
        }else{
            mView.showKnowSaveError(404,"保存失败了!");
        }
    }

    //显示更新精通级的函数
    public void showFourthFunctionWindow() {
        ((KnowUpdateFourthView) mView).showFunctionWindow();
    }

    //添加精通级的函数项
    public void addFunctionFourthData(int mCommon, String title, String summary, String explain) {
        boolean result = ((KnowUpdateFourthView) mView).addFunctionData(mCommon, title, summary, explain);
        if(result){
            ((KnowUpdateFourthView) mView).addFunctionFinish();
        }else{
            ((KnowUpdateFourthView) mView).addFunctionError(501,"添加函数项失败了!");
        }
    }

    //判断精通级的函数是否为空
    public boolean isFunctionFourthEmpty(int mCommon, String title,
                                         String summary, String explain) {
        return ((KnowUpdateFourthView) mView).isFunctionEmpty(mCommon, title, summary, explain);
    }

    //查找创造级数据
    public void getKnowFifthContent(long content_id){
        HomeKnowContent homeKnowContent = mRequest.getKnowDefaultContent(content_id);
        if(homeKnowContent != null){
            mView.showKnowDataFinish(homeKnowContent);
        }else{
            mView.showKnowDataError(404,"没有该数据!");
        }
    }

    //判断创造级数据是否为空
    public void isKnowUpdateFifthEmpty(){
        boolean result = mView.isKnowUpdateDefaultEmpty();
        if(result){
            mView.showKnowEmptyError(404,"该数据没有填写完成!");
        }else{
            boolean result2 = mView.isKnowUpdateDefaultEquals();
            if(!result2){
                mView.saveKnowUpdateContent();
            }else{
                mView.showKnowEqualsError(404,"您没有修改!");
            }
        }
    }

    //更新创造级数据
    public void saveKnowUpdateFifth(HomeKnowHistory mOldHistory, HomeKnowContent mNewContent) {
        boolean result = mRequest.saveKnowUpdateFifth(mOldHistory,mNewContent);
        if(result){
            mView.showKnowSaveFinish();
        }else{
            mView.showKnowSaveError(404,"保存失败了!");
        }
    }
}
