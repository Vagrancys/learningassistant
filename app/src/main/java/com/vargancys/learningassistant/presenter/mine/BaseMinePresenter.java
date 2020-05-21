package com.vargancys.learningassistant.presenter.mine;

import com.vargancys.learningassistant.db.mine.MineDataBean;
import com.vargancys.learningassistant.model.mine.request.MineRequest;
import com.vargancys.learningassistant.module.mine.view.BaseMineView;

/**
 * @author Vagrancy
 * @date 2020/5/20
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 基础我的中心逻辑层
 */
public class BaseMinePresenter {
    private static String TAG = "BaseMinePresenter";
    private Object mView;
    private MineRequest mRequest;

    public BaseMinePresenter(Object view){
        mView = view;
        mRequest = MineRequest.getInstance();
    }

    public void getMineData(long mineId) {
        MineDataBean mBean = mRequest.getMineData(mineId);
        if(mBean != null){
            ((BaseMineView) mView).showMineDataFinish(mBean);
        }else{
            ((BaseMineView) mView).showMineDataError(404,"没有找到该数据!");
        }
    }
}
