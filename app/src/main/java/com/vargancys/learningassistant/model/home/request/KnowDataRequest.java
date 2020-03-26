package com.vargancys.learningassistant.model.home.request;

import com.vagrancys.learningassistant.db.HomeKnowCommendDao;
import com.vagrancys.learningassistant.db.HomeKnowDataDao;
import com.vagrancys.learningassistant.db.HomeKnowItemDao;
import com.vargancys.learningassistant.base.BaseApplication;
import com.vargancys.learningassistant.db.home.HomeKnowCommend;
import com.vargancys.learningassistant.db.home.HomeKnowData;
import com.vargancys.learningassistant.db.home.HomeKnowItem;
import com.vargancys.learningassistant.utils.TimeUtils;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/25
 * version:1.0
 */
public class KnowDataRequest {
    private static KnowDataRequest mRequest;
    private HomeKnowCommendDao mKnowCommendDao;
    private HomeKnowDataDao mKnowDataDao;
    private HomeKnowItemDao mKnowItemDao;
    private KnowDataRequest(){
        mKnowDataDao = BaseApplication.getInstance().getDaoSession().getHomeKnowDataDao();
    }

    public static KnowDataRequest getRequest() {
        if(mRequest ==null){
            synchronized (KnowDataRequest.class){
                if(mRequest == null){
                    mRequest = new KnowDataRequest();
                }
            }
        }
        return mRequest;
    }

    public HomeKnowData getShowData(Long know_id){
        return mKnowDataDao.load(know_id);
    }

    public HomeKnowCommend saveCommend(long know_id,String commend) {
        HomeKnowCommend homeKnowCommend = new HomeKnowCommend();
        homeKnowCommend.setCommendId(know_id);
        homeKnowCommend.setTime(TimeUtils.getTime());
        homeKnowCommend.setTitle(commend);
        mKnowCommendDao.save(homeKnowCommend);
        return homeKnowCommend;
    }

    public int deleteDataItem(Long know_id) {
        mKnowItemDao.deleteByKey(know_id);
        return 200;
    }
}
