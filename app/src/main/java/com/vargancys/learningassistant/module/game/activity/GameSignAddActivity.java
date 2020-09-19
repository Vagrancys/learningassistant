package com.vargancys.learningassistant.module.game.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.bean.game.GameContent;
import com.vargancys.learningassistant.bean.game.GameSignContent;
import com.vargancys.learningassistant.module.game.view.SignAddView;
import com.vargancys.learningassistant.presenter.game.BaseGamePresenter;
import com.vargancys.learningassistant.utils.CacheUtils;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ResourceUtils;
import com.vargancys.learningassistant.utils.TimeUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Vagrancy
 * @date 2020/4/12
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 关卡签到添加页面
 */
public class GameSignAddActivity extends BaseActivity implements SignAddView {
    @BindView(R.id.common_back)
    ImageView commonBack;
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.common_img_one)
    ImageView commonImgOne;
    @BindView(R.id.common_img)
    ImageView commonImg;
    @BindView(R.id.sign_title)
    TextView signTitle;
    @BindView(R.id.sign_title_edit)
    EditText signTitleEdit;
    @BindView(R.id.sign_summary)
    TextView signSummary;
    @BindView(R.id.sign_summary_edit)
    EditText signSummaryEdit;
    @BindView(R.id.sign_subject_number)
    TextView signSubjectNumber;
    @BindView(R.id.sign_subject_progress)
    ProgressBar signSubjectProgress;
    @BindView(R.id.sign_score_number)
    TextView signScoreNumber;
    @BindView(R.id.sign_score_progress)
    ProgressBar signScoreProgress;
    @BindView(R.id.sign_error_number)
    TextView signErrorNumber;
    @BindView(R.id.sign_error_progress)
    ProgressBar signErrorProgress;
    private BaseGamePresenter mPresenter;
    private long gameId = 0;
    private GameContent mContent;
    @Override
    public int getLayoutId() {
        return R.layout.activity_sign_add;
    }

    @Override
    public void initView() {
        gameId = CacheUtils.getLong(getContext(), ConstantsUtils.GAME_ID, 0);
        mPresenter = new BaseGamePresenter(this);
        mPresenter.getGameContentData(gameId);
    }

    @Override
    public void initToolbar() {
        commonTitle.setText(ResourceUtils.getString(getContext(),R.string.sign_game_add_title));

        commonImg.setImageResource(R.drawable.sign_add_normal);
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, GameSignAddActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void initSignAddDataError(int error, String message) {
        ToastUtils.ToastText(getContext(), "Error =" + error + ",Message =" + message);
        clearData();
    }

    @Override
    public void initSignAddDataFinish(GameContent mContent) {
        initData(mContent);
    }

    private void initData(GameContent content) {
        mContent = content;
        signSubjectNumber.setText(content.getSubject_current()+"题/总"+content.getSubject()+"题");
        signSubjectProgress.setMax(content.getSubject());
        signSubjectProgress.setProgress(content.getSubject_current());
        signErrorNumber.setText(content.getError_current()+"道/总"+content.getError()+"道");
        signErrorProgress.setMax(content.getError());
        signErrorProgress.setProgress(content.getError_current());
        signScoreNumber.setText(content.getScore_current()+"分/总"+content.getScore()+"分");
        signScoreProgress.setMax(content.getScore());
        signScoreProgress.setProgress(content.getScore_current());
    }

    private void clearData() {
        signTitleEdit.setText("");
        signSummaryEdit.setText("");
        signSubjectNumber.setText("--题/总--题");
        signSubjectProgress.setMax(1);
        signSubjectProgress.setProgress(0);
        signErrorNumber.setText("--道/总--道");
        signErrorProgress.setMax(1);
        signErrorProgress.setProgress(0);
        signScoreNumber.setText("--分/总--分");
        signScoreProgress.setMax(1);
        signScoreProgress.setProgress(0);
    }

    @Override
    public boolean isSignDataEmpty() {
        return signTitleEdit.getText().toString().isEmpty()&&signSummaryEdit.getText().toString().isEmpty();
    }

    @Override
    public void addSignData() {
        GameSignContent content = new GameSignContent();
        content.setTitle(signTitleEdit.getText().toString());
        content.setSummary(signSummaryEdit.getText().toString());
        content.setLevel(mContent.getDifficulty());
        content.setTime(TimeUtils.getTime());
        content.setGame_title(mContent.getGame_title());
        content.setGame_subject_current(mContent.getSubject_current());
        content.setGame_subject_total(mContent.getSubject());
        content.setGame_error_current(mContent.getError_current());
        content.setGame_error_total(mContent.getError());
        content.setGame_score_current(mContent.getScore_current());
        content.setGame_score_total(mContent.getScore());
        mPresenter.saveSignData(content);
    }

    @Override
    public void isSignDataEmptyError(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error ="+error+", Message ="+message);
    }

    @Override
    public void saveSignDataFinish() {
        ToastUtils.ToastText(getContext(),R.string.game_sign_success_text);
        finish();
    }

    @Override
    public void saveSignDataError(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error ="+error+", Message ="+message);
    }

    @OnClick({R.id.common_back,R.id.common_img})
    public void onViewClicked(View itemView){
        switch (itemView.getId()){
            case R.id.common_back:
                finish();
                break;
            case R.id.common_img:
                mPresenter.isSignDataEmpty();
                break;
        }
    }
}
