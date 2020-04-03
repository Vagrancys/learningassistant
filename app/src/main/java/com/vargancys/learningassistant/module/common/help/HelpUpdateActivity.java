package com.vargancys.learningassistant.module.common.help;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.db.common.HelpContentItem;
import com.vargancys.learningassistant.module.common.view.HelpUpdateView;
import com.vargancys.learningassistant.presenter.common.help.HelpUpdatePresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import butterknife.BindView;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/08
 * version:1.0
 */
public class HelpUpdateActivity extends BaseActivity implements HelpUpdateView {
    @BindView(R.id.common_back)
    ImageView commonBack;
    @BindView(R.id.common_img)
    ImageView commonImg;
    @BindView(R.id.help_number)
    TextView helpNumber;
    @BindView(R.id.help_title_edit)
    EditText helpTitleEdit;
    @BindView(R.id.help_summary_edit)
    EditText helpSummaryEdit;
    @BindView(R.id.update_relative)
    RelativeLayout updateRelative;
    @BindView(R.id.update_empty)
    TextView updateEmpty;
    private int ResultCode = 2002;
    private int SummaryId;
    private HelpUpdatePresenter helpUpdatePresenter;
    private String mTitle;
    private String mSummary;

    @Override
    public int getLayoutId() {
        return R.layout.activity_help_update;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            SummaryId = intent.getIntExtra(ConstantsUtils.HELP_SUMMARY_ID, 0);
        }
        helpUpdatePresenter = new HelpUpdatePresenter(this);
        helpUpdatePresenter.getHelpData(SummaryId);
        initListener();
    }

    private void initListener() {
        commonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(ConstantsUtils.HELP_UPDATE_STATE,0);
                setResult(ResultCode,intent);
                finish();
            }
        });

        commonImg.setBackgroundResource(R.drawable.commend_complete_selector);
        commonImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean equals = helpUpdatePresenter.equalsHelpData(helpTitleEdit.getText().toString(),
                        helpSummaryEdit.getText().toString());
                if(equals){
                    ToastUtils.ToastText(getContext(),"该帮助没有任何修改!请考虑清楚要不要修改!");
                }else{
                    helpUpdatePresenter.updateHelpData(SummaryId,helpTitleEdit.getText().toString(),
                            helpSummaryEdit.getText().toString());
                }
            }
        });
    }

    public static void launch(Activity activity, int id, int requestCode) {
        Intent intent = new Intent(activity, HelpUpdateActivity.class);
        intent.putExtra(ConstantsUtils.HELP_SUMMARY_ID, id);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    public boolean equalsHelpData(String title, String summary) {
        return mTitle.equals(title)&&mSummary.equals(summary);
    }

    @Override
    public void getHelpData(HelpContentItem item) {
        mTitle = item.getTitle();
        mSummary = item.getSummary();
        helpNumber.setText(String.valueOf(item.getNumber()));
        helpTitleEdit.setText(mTitle);
        helpSummaryEdit.setText(mSummary);
    }

    @Override
    public void getHelpError(int error, String msg) {
        ToastUtils.ToastText(getContext(),"获取数据库数据失败!");
        updateRelative.setVisibility(View.GONE);
        updateEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void updateError(int error, String msg) {
        ToastUtils.ToastText(getContext(),"更新该帮助失败!请稍后再试或者联系官方!");
        Intent intent = new Intent();
        intent.putExtra(ConstantsUtils.HELP_UPDATE_STATE,2);
        setResult(ResultCode,intent);
        finish();
    }

    @Override
    public void updateFinish() {
        ToastUtils.ToastText(getContext(),"更新该帮助成功!返回中!");
        Intent intent = new Intent();
        intent.putExtra(ConstantsUtils.HELP_UPDATE_STATE,1);
        setResult(ResultCode,intent);
        finish();
    }
}
