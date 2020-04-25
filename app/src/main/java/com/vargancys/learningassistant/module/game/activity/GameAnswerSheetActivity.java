package com.vargancys.learningassistant.module.game.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.utils.ConstantsUtils;

public class GameAnswerSheetActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initView() {

    }

    public static void launch(Activity activity, Parcelable parcelable){
        Intent intent = new Intent(activity,GameAnswerSheetActivity.class);
        Bundle bundle = new Bundle();
        intent.putExtra(ConstantsUtils.ANSWER_SHEET_NAME,parcelable);
        activity.startActivity(intent);
    }
}
