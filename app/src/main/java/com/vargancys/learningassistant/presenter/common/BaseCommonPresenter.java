package com.vargancys.learningassistant.presenter.common;

import com.vargancys.learningassistant.model.common.request.CommonRequest;
import com.vargancys.learningassistant.model.mine.request.MineRequest;
import com.vargancys.learningassistant.module.common.view.LoginView;

/**
 * @author Vagrancy
 * @date 2020/6/11
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 公共逻辑层
 */
public class BaseCommonPresenter {
    private static String TAG = "BaseCommonPresenter";
    private Object mView;
    private CommonRequest mRequest;

    public BaseCommonPresenter(Object view){
        mView = view;
        mRequest = CommonRequest.getInstance();
    }

    //判断帐号是否存在
    public boolean isExistName(String name) {
        return mRequest.isExistName(name);
    }

    //登录账户
    public void loginName(String name, String password) {
        boolean result = mRequest.loginName(name,password);
        if(result){
            ((LoginView) mView).requestLoginFinish("登录成功!");
        }else{
            ((LoginView) mView).requestLoginError(404,"登录失败了");
        }
    }
}
