package com.vargancys.learningassistant.module.ladder.activity;

import android.app.Activity;
import android.content.Intent;

import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.utils.ConstantsUtils;

/**
 * @author Vagrancy
 * @date 2020/5/8
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 评论回复中心
 */
public class LadderCommentReplyActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initView() {

    }

    public static void launch(Activity activity, long commentId){
        Intent intent = new Intent(activity,LadderCommentReplyActivity.class);
        intent.putExtra(ConstantsUtils.COMMENT_ID,commentId);
        activity.startActivity(intent);
    }
}
