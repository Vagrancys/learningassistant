package com.vargancys.learningassistant.module.overview.fragment;

import com.vargancys.learningassistant.base.BaseFragment;
import com.vargancys.learningassistant.module.overview.view.BaseHallView;
import com.vargancys.learningassistant.module.overview.view.HallHotView;

/**
 * @author Vagrancy
 * @date 2020/6/28
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识体系大厅排行热门碎片
 */
public class OverViewHallHotFragment extends BaseFragment implements BaseHallView {
    public static OverViewHallHotFragment newInstance(){
        return new OverViewHallHotFragment();
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    protected void initView() {

    }
}
