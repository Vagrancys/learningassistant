package com.vargancys.learningassistant.module.common.view;

import com.vargancys.learningassistant.base.BaseView;
import com.vargancys.learningassistant.db.common.HelpCommendItem;
import com.vargancys.learningassistant.db.common.HelpContentItem;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/08
 * version:1.0
 */
public interface HelpSummaryView{

    void findFinish(HelpContentItem object);
    void findError(int error,String msg);
    void findCommendError(int error,String msg);

    void findEmpty();

    void findCommend(List<HelpCommendItem> items);

    void reFreshSummary(HelpContentItem item);

    void PraiseOrPoor(int state,int number,String msg);

    void saveCommendFinish(HelpCommendItem item);
    void saveCommendError(int error,String msg);
}

