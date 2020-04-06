package com.vargancys.learningassistant.module.game.fragment;

import android.support.v4.app.Fragment;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseFragment;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/06
 * version:1.0
 */
public class GameFragment extends BaseFragment {
    public static Fragment newInstance() {
        return new GameFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_game;
    }

    @Override
    protected void initView() {

    }
}
