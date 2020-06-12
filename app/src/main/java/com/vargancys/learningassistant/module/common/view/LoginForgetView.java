package com.vargancys.learningassistant.module.common.view;

/**
 * @author Vagrancy
 * @date 2020/6/11
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 忘记密码视图层
 */
public interface LoginForgetView {
    void loadCodeFinish();
    void loadCodeError(int error,String message);
    void updatePasswordFinish();
    void updatePasswordError(int error,String message);
}
