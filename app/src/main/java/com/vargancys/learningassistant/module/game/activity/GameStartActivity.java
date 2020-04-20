package com.vargancys.learningassistant.module.game.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.db.game.GameStartContent;
import com.vargancys.learningassistant.module.game.view.StartGameView;
import com.vargancys.learningassistant.presenter.game.BaseGamePresenter;
import com.vargancys.learningassistant.utils.CacheUtils;
import com.vargancys.learningassistant.utils.ConstantsUtils;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/16
 * version:1.0
 */
public class GameStartActivity extends BaseActivity implements StartGameView {
    private BaseGamePresenter mPresenter;
    //关卡id
    private long gameId;
    private Handler mHandler;
    @Override
    public int getLayoutId() {
        return R.layout.activity_game_start;
    }

    @Override
    public void initView() {
        mHandler = new Handler();
        mPresenter = new BaseGamePresenter(this);
        gameId = CacheUtils.getLong(getContext(), ConstantsUtils.GAME_ID,0);
        mPresenter.getGameStartAllData(mHandler,gameId);
    }

    public static void launch(Activity activity){
        Intent intent = new Intent(activity,GameStartActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void showTidyAllDataFinish(List<GameStartContent> contents) {

    }

    @Override
    public void showTidyAllDataError(int error, String message) {

    }
}
