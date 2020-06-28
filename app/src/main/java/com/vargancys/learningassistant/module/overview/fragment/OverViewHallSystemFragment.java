package com.vargancys.learningassistant.module.overview.fragment;

import com.vargancys.learningassistant.base.BaseFragment;
import com.vargancys.learningassistant.module.overview.view.BaseHallView;

import HallSystemView;

/**
 * @author Vagrancy
 * @date 2020/6/28
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识体系大厅排行体系碎片
 */
public class OverViewHallSystemFragment extends BaseFragment implements BaseHallView {
    public static OverViewHallSystemFragment newInstance(){
        return new OverViewHallSystemFragment();
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    protected void initView() {

    }
}
