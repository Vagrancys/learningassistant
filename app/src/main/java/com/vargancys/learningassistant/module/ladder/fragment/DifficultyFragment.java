package com.vargancys.learningassistant.module.ladder.fragment;

import android.os.Bundle;

import com.vargancys.learningassistant.base.BaseFragment;
import com.vargancys.learningassistant.utils.ConstantsUtils;

/**
 * @author Vagrancy
 * @date 2020/5/10
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description:
 */
public class DifficultyFragment extends BaseFragment {
    public static DifficultyFragment newInstance(int type){
        DifficultyFragment mFragment = new DifficultyFragment();
        Bundle mBundle = new Bundle();
        mBundle.putInt(ConstantsUtils.LADDER_DIFFICULTY_TYPE,type);
        mFragment.setArguments(mBundle);
        return mFragment;
    }
    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    protected void initView() {
        //TODO 难度区详细页面等待中....

    }

    public void autoRefreshData(){

    }
}
