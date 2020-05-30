package com.vargancys.learningassistant.module.mine.view;

import com.vargancys.learningassistant.db.mine.MineDataBean;
import com.vargancys.learningassistant.db.overview.OverViewListContent;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2020/5/30
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description:个人中心知识体系视图层
 */
public interface SystemView {
    void loadSystemDataError(int error,String message);
    void loadSystemDataFinish(MineDataBean mBean);
    void loadSystemTypeDataError(int error,String message);
    void loadSystemTypeDataFinish(List<OverViewListContent> mBean);
}
