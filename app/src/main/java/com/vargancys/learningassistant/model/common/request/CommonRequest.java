package com.vargancys.learningassistant.model.common.request;

import com.vagrancys.learningassistant.db.DaoSession;
import com.vargancys.learningassistant.base.BaseApplication;

/**
 * @author Vagrancy
 * @date 2020/6/11
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 公共数据请求层
 */
public class CommonRequest {
    private static CommonRequest mRequest;
    private DaoSession mDaoSession;
    private CommonRequest(){
        mDaoSession = BaseApplication.getInstance().getDaoSession();
    }

    public static CommonRequest getInstance(){
        if(mRequest == null){
            synchronized (CommonRequest.class){
                if(mRequest == null){
                    mRequest = new CommonRequest();
                }
            }
        }
        return mRequest;
    }

    //判断帐号是否存在
    public boolean isExistName(String name) {
        return true;
    }

    public boolean loginName(String name, String password) {
        return true;
    }
}
