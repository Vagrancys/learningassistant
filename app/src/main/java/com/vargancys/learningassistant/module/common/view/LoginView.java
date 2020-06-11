package com.vargancys.learningassistant.module.common.view;

/**
 * @author Vagrancy
 * @date 2020/6/11
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description:
 */
public interface LoginView {
    void requestLoginFinish(String message);
    void requestLoginError(int error,String message);
}
