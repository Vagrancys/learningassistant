package com.vargancys.learningassistant.module.common.view;

import com.vargancys.learningassistant.db.common.HelpContentBean;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/08
 * version:1.0
 */
public interface HelpUpdateView{
    void getHelpData(HelpContentBean item);
    void getHelpError(int error,String msg);
    boolean equalSingleHelpData(String title,String summary);
    void updateFinish();
    void updateError(int error,String msg);
}
