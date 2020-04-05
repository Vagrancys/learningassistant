package com.vargancys.learningassistant.presenter.overview;

import android.os.Handler;

import com.vargancys.learningassistant.db.overview.OverViewListContent;
import com.vargancys.learningassistant.db.overview.OverViewListItem;
import com.vargancys.learningassistant.model.overview.request.OverViewRequest;
import com.vargancys.learningassistant.module.overview.view.BaseOverView;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/03
 * version:1.0
 */
public class BaseOverViewPresenter {
    private BaseOverView mView;
    private OverViewRequest mRequest;

    public BaseOverViewPresenter(BaseOverView view){
        this.mView = view;
        mRequest = OverViewRequest.getInstance();
    }


    public void getAllContentData() {
        List<OverViewListContent> mObject = mRequest.getAllContentData();
        if(mObject !=null && mObject.size()>0){
            mView.getAllData(mObject);
        }else{
            mView.getAllDataError(404,"没有找到数据!");
        }
    }

    public void TidyAllData() {
        mView.TidyAllData();
    }

    public void saveOverViewAllData(Handler handler, OverViewListContent mContent, List<OverViewListItem> mItems) {
        long parentId = mRequest.saveOverViewContent(mContent);
        boolean result = mRequest.saveOverViewItem(parentId,mItems);
        if(result){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    mView.saveDataFinish();
                }
            });
        }else{
            handler.post(new Runnable() {
                @Override
                public void run() {
                    mView.saveDataError(200,"没有保存成功");
                }
            });

        }
    }
}
