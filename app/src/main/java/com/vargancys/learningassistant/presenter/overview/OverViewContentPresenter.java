package com.vargancys.learningassistant.presenter.overview;

import com.vargancys.learningassistant.db.overview.OverViewListItem;
import com.vargancys.learningassistant.model.overview.request.OverViewRequest;
import com.vargancys.learningassistant.module.overview.view.OverViewContentView;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/03
 * version:1.0
 */
public class OverViewContentPresenter {
    private OverViewContentView mView;
    private OverViewRequest mRequest;
    public OverViewContentPresenter(OverViewContentView view){
        mRequest = OverViewRequest.getInstance();
        mView = view;
    }


    public void getOverViewListData(long selectId) {
        List<OverViewListItem> overViewListItemList = mRequest.getOverViewListData(selectId);
        if(overViewListItemList !=null && overViewListItemList.size() >0){
            mView.showOverViewDataFinish(overViewListItemList);
        }else{
            mView.showOverViewDataError(403,"获取数据失败!");
        }
    }
}
