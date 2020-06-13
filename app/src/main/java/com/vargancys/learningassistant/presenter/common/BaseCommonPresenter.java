package com.vargancys.learningassistant.presenter.common;

import com.vargancys.learningassistant.model.common.request.CommonRequest;
import com.vargancys.learningassistant.module.common.view.LoginForgetView;
import com.vargancys.learningassistant.module.common.view.LoginView;
import com.vargancys.learningassistant.module.common.view.RegisterView;

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

    //判断验证码是否正确
    public boolean isCodeSuccessful(String code) {
        if(code.equals("123456")){
            return true;
        }
        return false;
    }

    //更新密码
    public void updatePasswordData(String name) {
        boolean result = mRequest.updatePasswordData(name);
        if(result){
            ((LoginForgetView) mView).updatePasswordFinish();
        }else{
            ((LoginForgetView) mView).updatePasswordError(404,"没有保存成功!");
        }
    }

    //加载验证码
    public void loadCodeName() {
        boolean result = mRequest.loadCodeName();
        if(result){
            ((LoginForgetView) mView).loadCodeFinish();
        }else{
            ((LoginForgetView) mView).loadCodeError(404,"没有发送成功!");
        }
    }

    //注册用户
    public void saveMember(String name, String password) {
        boolean result = mRequest.saveMember(name,password);
        if(result){
            ((RegisterView) mView).saveNameFinish();
        }else{
            ((RegisterView) mView).saveNameError(404,"注册失败!");
        }
    }
}
