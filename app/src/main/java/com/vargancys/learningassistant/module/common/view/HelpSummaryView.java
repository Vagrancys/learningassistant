package com.vargancys.learningassistant.module.common.view;

import com.vargancys.learningassistant.bean.common.HelpCommendItem;
import com.vargancys.learningassistant.bean.common.HelpContentBean;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/08
 * version:1.0
 */
public interface HelpSummaryView{

    void findFinish(HelpContentBean object);
    void findError(int error,String msg);
    void findCommendError(int error,String msg);

    void findEmpty();

    void findCommend(List<HelpCommendItem> items);

    void reFreshSummary(HelpContentBean item);

    void PraiseOrPoor(int state,int number,String msg);

    void saveCommendFinish(HelpCommendItem item);
    void saveCommendError(int error,String msg);
}

