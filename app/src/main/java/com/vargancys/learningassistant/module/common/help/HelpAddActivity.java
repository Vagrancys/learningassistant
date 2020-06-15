package com.vargancys.learningassistant.module.common.help;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.module.common.view.HelpAddView;
import com.vargancys.learningassistant.presenter.common.help.HelpAddPresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ResourceUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/07
 * version:1.0
 * 帮助添加页
 */
public class HelpAddActivity extends BaseActivity implements HelpAddView {

    @BindView(R.id.common_img)
    ImageView commonImg;
    @BindView(R.id.help_title_edit)
    EditText helpTitleEdit;
    @BindView(R.id.help_summary_edit)
    EditText helpSummaryEdit;
    @BindView(R.id.common_title)
    TextView commonTitle;
    private HelpAddPresenter helpAddPresenter;
    public static int ResultCode = 2005;
    private int addCount = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_help_add;
    }

    @Override
    public void initView() {
        helpAddPresenter = new HelpAddPresenter(this);
    }

    @Override
    public void initToolbar() {
        commonTitle.setText(ResourceUtils.getString(getContext(),R.string.help_add_toolbar));
        commonImg.setBackgroundResource(R.drawable.comment_complete_selector);
    }

    public static void launch(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, HelpAddActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    public void addFinish() {
        ToastUtils.ToastText(getContext(), R.string.help_add_successful_text);
        addCount++;
        resetView();
    }

    @Override
    public void addError(int error, String msg) {
        ToastUtils.ToastText(getContext(), R.string.help_add_fail_text);
        resetView();
    }

    private void resetView() {
        helpTitleEdit.setText("");
        helpSummaryEdit.setText("");
    }

    @OnClick({R.id.common_back,R.id.common_img})
    public void onViewClicked(View itemView) {
        switch (itemView.getId()){
            case R.id.common_back:
                finishHelp();
                break;
            case R.id.common_img:
                helpAddPresenter.saveHelpData(helpTitleEdit.getText().toString(),
                        helpSummaryEdit.getText().toString());
                break;
        }
    }

    //添加数据返回
    private void finishHelp(){
        Intent intent = new Intent();
        intent.putExtra(ConstantsUtils.HELP_ADD_COUNT, addCount);
        setResult(ResultCode, intent);
        finish();
    }
}
