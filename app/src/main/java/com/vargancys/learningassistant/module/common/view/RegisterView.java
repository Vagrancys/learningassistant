package com.vargancys.learningassistant.module.common.view;

/**
 * @author Vagrancy
 * @date 2020/6/13
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 注册视图层
 */
public interface RegisterView {
    void saveNameFinish();
    void saveNameError(int error,String message);
}
