package com.vargancys.learningassistant.module.mine.view;

import com.vargancys.learningassistant.db.mine.MineLevelPrivilegeBean;
import com.vargancys.learningassistant.model.mine.bean.LevelItemBean;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2020/5/31
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description:个人中心等级详情视图层
 */
public interface LevelDetailsView {
    void loadLevelDetailsDataFinish(LevelItemBean.LevelItem mItem);
    void loadLevelDetailsDataError(int error ,String message);
    void loadLevelPrivilegeDataFinish(List<MineLevelPrivilegeBean> mPrivilege);
    void loadLevelPrivilegeDataError(int error,String message);
}
