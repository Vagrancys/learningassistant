package com.vargancys.learningassistant.module.game.activity;

import android.app.Activity;
import android.content.Intent;

import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.module.game.view.GameHallView;


/**
 * @author Vagrancy
 * @date 2020/7/6
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 关卡大厅模块
 */
public class GameHallActivity extends BaseActivity implements GameHallView {
    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initView() {

    }

    public static void launch(Activity activity){
        Intent intent = new Intent(activity,GameHallActivity.class);
        activity.startActivity(intent);
    }
}
