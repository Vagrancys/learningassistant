package com.vargancys.learningassistant.presenter.overview;

import android.os.Handler;
import android.util.Log;

import com.vargancys.learningassistant.db.overview.OverViewListContent;
import com.vargancys.learningassistant.db.overview.OverViewListItem;
import com.vargancys.learningassistant.model.overview.request.OverViewRequest;
import com.vargancys.learningassistant.module.overview.view.BaseOverView;
import com.vargancys.learningassistant.module.overview.view.OverViewAddView;
import com.vargancys.learningassistant.module.overview.view.OverViewInformationView;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/03
 * version:1.0
 */
public class BaseOverViewPresenter {
    private static String TAG = "BaseOverViewPresenter";
    private Object mView;
    private OverViewRequest mRequest;

    public BaseOverViewPresenter(Object view){
        this.mView = view;
        mRequest = OverViewRequest.getInstance();
    }


    public void getAllContentData() {
        List<OverViewListContent> mObject = mRequest.getAllContentData();
        if(mObject !=null && mObject.size()>0){
            ((BaseOverView)mView).getAllData(mObject);
        }else{
            ((BaseOverView)mView).getAllDataError(404,"没有找到数据!");
        }
    }

    public void TidyAllData() {
        ((OverViewAddView)mView).TidyAllData();
    }

    public void saveOverViewAllData(Handler handler, OverViewListContent mContent, List<OverViewListItem> mItems) {
        long parentId = mRequest.saveOverViewContent(mContent);
        boolean result = mRequest.saveOverViewItem(parentId,mItems);
        if(result){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    ((OverViewAddView)mView).saveDataFinish();
                }
            });
        }else{
            handler.post(new Runnable() {
                @Override
                public void run() {
                    ((OverViewAddView)mView).saveDataError(200,"没有保存成功");
                }
            });

        }
    }

    //获得知识集的详细信息
    public void getContentData(long selectId) {
        OverViewListContent mContent = mRequest.getContentData(selectId);
        if(mContent !=null){
            Log.e(TAG,"断点2");
            ((OverViewInformationView)mView).getContentData(mContent);
        }else{
            Log.e(TAG,"断点3");
            ((OverViewInformationView)mView).getContentDataError(404,"没有该数据!");
        }

    }
}
