package com.vargancys.learningassistant.module.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.model.mine.bean.ProblemDetailsBean;
import com.vargancys.learningassistant.module.mine.view.ProblemDetailsView;
import com.vargancys.learningassistant.presenter.mine.BaseMinePresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Vagrancy
 * @date 2020/6/1
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 个人中心问题详情页面
 */
public class ProblemDetailsActivity extends BaseActivity implements ProblemDetailsView {
    @BindView(R.id.common_title_data)
    TextView commonTitleData;
    @BindView(R.id.problem_details_title)
    TextView problemDetailsTitle;
    @BindView(R.id.problem_details_count)
    TextView problemDetailsCount;
    @BindView(R.id.problem_details_level)
    TextView problemDetailsLevel;
    @BindView(R.id.problem_details_people)
    TextView problemDetailsPeople;
    @BindView(R.id.problem_details_use)
    TextView problemDetailsUse;
    @BindView(R.id.problem_details_answer)
    TextView problemDetailsAnswer;
    @BindView(R.id.problem_answer_radio_first_answer)
    ImageView problemAnswerRadioFirstAnswer;
    @BindView(R.id.problem_answer_radio_first_title)
    TextView problemAnswerRadioFirstTitle;
    @BindView(R.id.problem_answer_radio_second_answer)
    ImageView problemAnswerRadioSecondAnswer;
    @BindView(R.id.problem_answer_radio_second_title)
    TextView problemAnswerRadioSecondTitle;
    @BindView(R.id.problem_answer_radio_third_answer)
    ImageView problemAnswerRadioThirdAnswer;
    @BindView(R.id.problem_answer_radio_third_title)
    TextView problemAnswerRadioThirdTitle;
    @BindView(R.id.problem_answer_radio_fourth_answer)
    ImageView problemAnswerRadioFourthAnswer;
    @BindView(R.id.problem_answer_radio_fourth_title)
    TextView problemAnswerRadioFourthTitle;
    @BindView(R.id.problem_details_radio)
    LinearLayout problemDetailsRadio;
    @BindView(R.id.problem_answer_multiple_first_answer)
    ImageView problemAnswerMultipleFirstAnswer;
    @BindView(R.id.problem_answer_multiple_first_title)
    TextView problemAnswerMultipleFirstTitle;
    @BindView(R.id.problem_answer_multiple_second_answer)
    ImageView problemAnswerMultipleSecondAnswer;
    @BindView(R.id.problem_answer_multiple_second_title)
    TextView problemAnswerMultipleSecondTitle;
    @BindView(R.id.problem_answer_multiple_third_answer)
    ImageView problemAnswerMultipleThirdAnswer;
    @BindView(R.id.problem_answer_multiple_third_title)
    TextView problemAnswerMultipleThirdTitle;
    @BindView(R.id.problem_answer_multiple_fourth_answer)
    ImageView problemAnswerMultipleFourthAnswer;
    @BindView(R.id.problem_answer_multiple_fourth_title)
    TextView problemAnswerMultipleFourthTitle;
    @BindView(R.id.problem_details_multiple)
    LinearLayout problemDetailsMultiple;
    @BindView(R.id.problem_answer_fill_first_answer)
    ImageView problemAnswerFillFirstAnswer;
    @BindView(R.id.problem_answer_fill_first_title)
    TextView problemAnswerFillFirstTitle;
    @BindView(R.id.problem_answer_fill_second_answer)
    ImageView problemAnswerFillSecondAnswer;
    @BindView(R.id.problem_answer_fill_second_title)
    TextView problemAnswerFillSecondTitle;
    @BindView(R.id.problem_answer_fill_third_answer)
    ImageView problemAnswerFillThirdAnswer;
    @BindView(R.id.problem_answer_fill_third_title)
    TextView problemAnswerFillThirdTitle;
    @BindView(R.id.problem_answer_fill_fourth_answer)
    ImageView problemAnswerFillFourthAnswer;
    @BindView(R.id.problem_answer_fill_fourth_title)
    TextView problemAnswerFillFourthTitle;
    @BindView(R.id.problem_details_fill)
    LinearLayout problemDetailsFill;
    @BindView(R.id.problem_answer_subjective_title)
    TextView problemAnswerSubjectiveTitle;
    @BindView(R.id.problem_details_subjective)
    LinearLayout problemDetailsSubjective;
    private long detailsId;
    private BaseMinePresenter mPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_problem_details;
    }

    @Override
    public void initView() {
        detailsId = getIntent().getLongExtra(ConstantsUtils.PROBLEM_ID, 0);
        mPresenter = new BaseMinePresenter(this);
        mPresenter.getProblemDetailsData(detailsId);
    }

    @Override
    public void initToolbar() {
        commonTitleData.setText(getText(R.string.problem_details_toolbar));
    }

    public static void launch(Activity activity, long type) {
        Intent intent = new Intent(activity, ProblemDetailsActivity.class);
        intent.putExtra(ConstantsUtils.PROBLEM_ID, type);
        activity.startActivity(intent);
    }

    @Override
    public void loadDetailsDataError(int error, String message) {
        ToastUtils.ToastText(getContext(), "Error =" + error + ",Message =" + message);
        problemDetailsTitle.setText("--");
        problemDetailsCount.setText("--");
        problemDetailsLevel.setText("--");
        problemDetailsPeople.setText("--");
        problemDetailsUse.setText("--");
    }

    @Override
    public void loadDetailsDataFinish(ProblemDetailsBean mBean) {
        problemDetailsTitle.setText(mBean.getTitle());
        problemDetailsCount.setText(mBean.getCount());
        problemDetailsLevel.setText(mBean.getLevel());
        problemDetailsPeople.setText(mBean.getPeople());
        problemDetailsUse.setText(mBean.getUse());
        isProblemType(mBean.getType(),mBean);
    }

    //判断问题类型
    private void isProblemType(int type,ProblemDetailsBean mBean){
        switch (type){
            case 1:
                problemDetailsRadio.setVisibility(View.VISIBLE);
                initRadio(mBean.getRadio());
                break;
            case 2:
                problemDetailsMultiple.setVisibility(View.VISIBLE);
                initMultiple(mBean.getMultiple());
                break;
            case 3:
                problemDetailsFill.setVisibility(View.VISIBLE);
                initFill(mBean.getFill());
                break;
            case 4:
                problemDetailsSubjective.setVisibility(View.VISIBLE);
                initSubjective(mBean.getSubjective());
                break;
        }
    }

    private void initSubjective(ProblemDetailsBean.SubjectiveItem mSubjective) {
        problemDetailsAnswer.setText(mSubjective.getTitle());
        problemAnswerSubjectiveTitle.setText(mSubjective.getAnswer());
    }

    //初始化填空
    private void initFill(ProblemDetailsBean.FillItem mFill) {
        hideFillAnswer();
        problemDetailsAnswer.setText(mFill.getTitle());
        problemAnswerFillFirstTitle.setText(mFill.getFill_first_title());
        problemAnswerFillSecondTitle.setText(mFill.getFill_second_title());
        problemAnswerFillThirdTitle.setText(mFill.getFill_third_title());
        problemAnswerFillFourthTitle.setText(mFill.getFill_fourth_title());
        switch (mFill.getFill_answer()){
            case 1:
                problemAnswerFillFirstAnswer.setVisibility(View.VISIBLE);
                break;
            case 2:
                problemAnswerFillSecondAnswer.setVisibility(View.VISIBLE);
                break;
            case 3:
                problemAnswerFillThirdAnswer.setVisibility(View.VISIBLE);
                break;
            case 4:
                problemAnswerFillFourthAnswer.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void hideFillAnswer() {
        problemAnswerFillFirstAnswer.setVisibility(View.GONE);
        problemAnswerFillSecondAnswer.setVisibility(View.GONE);
        problemAnswerFillThirdAnswer.setVisibility(View.GONE);
        problemAnswerFillFourthAnswer.setVisibility(View.GONE);
    }

    //初始化单选
    private void initRadio(ProblemDetailsBean.RadioItem mRadio) {
        hideRadioAnswer();
        problemDetailsAnswer.setText(mRadio.getTitle());
        problemAnswerRadioFirstTitle.setText(mRadio.getRadio_first_answer());
        problemAnswerRadioSecondTitle.setText(mRadio.getRadio_second_answer());
        problemAnswerRadioThirdTitle.setText(mRadio.getRadio_third_answer());
        problemAnswerRadioFourthTitle.setText(mRadio.getRadio_fourth_answer());
        switch (mRadio.getAnswer()){
            case 1:
                problemAnswerRadioFirstAnswer.setVisibility(View.VISIBLE);
                break;
            case 2:
                problemAnswerRadioSecondAnswer.setVisibility(View.VISIBLE);
                break;
            case 3:
                problemAnswerRadioThirdAnswer.setVisibility(View.VISIBLE);
                break;
            case 4:
                problemAnswerRadioFourthAnswer.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void hideRadioAnswer() {
        problemAnswerRadioFirstAnswer.setVisibility(View.GONE);
        problemAnswerRadioSecondAnswer.setVisibility(View.GONE);
        problemAnswerRadioThirdAnswer.setVisibility(View.GONE);
        problemAnswerRadioFourthAnswer.setVisibility(View.GONE);
    }

    //初始化多选
    private void initMultiple(ProblemDetailsBean.MultipleItem mMultiple) {
        hideMultipleAnswer();
        problemDetailsAnswer.setText(mMultiple.getTitle());
        problemAnswerMultipleFirstTitle.setText(mMultiple.getMultiple_first_title());
        problemAnswerMultipleSecondTitle.setText(mMultiple.getMultiple_second_title());
        problemAnswerMultipleThirdTitle.setText(mMultiple.getMultiple_third_title());
        problemAnswerMultipleFourthTitle.setText(mMultiple.getMultiple_fourth_title());
        if(mMultiple.isMultiple_first_answer()){
            problemAnswerMultipleFirstAnswer.setVisibility(View.VISIBLE);
        }else{
            problemAnswerMultipleFirstAnswer.setVisibility(View.GONE);
        }
        if(mMultiple.isMultiple_second_answer()){
            problemAnswerMultipleSecondAnswer.setVisibility(View.VISIBLE);
        }else{
            problemAnswerMultipleSecondAnswer.setVisibility(View.GONE);
        }
        if(mMultiple.isMultiple_third_answer()){
            problemAnswerMultipleThirdAnswer.setVisibility(View.VISIBLE);
        }else{
            problemAnswerMultipleThirdAnswer.setVisibility(View.GONE);
        }
        if(mMultiple.isMultiple_fourth_answer()){
            problemAnswerMultipleFourthAnswer.setVisibility(View.VISIBLE);
        }else{
            problemAnswerMultipleFourthAnswer.setVisibility(View.GONE);
        }
    }

    private void hideMultipleAnswer() {
        problemAnswerMultipleFirstAnswer.setVisibility(View.GONE);
        problemAnswerMultipleSecondAnswer.setVisibility(View.GONE);
        problemAnswerMultipleThirdAnswer.setVisibility(View.GONE);
        problemAnswerMultipleFourthAnswer.setVisibility(View.GONE);
    }

    @OnClick({R.id.common_back})
    public void onViewClicked(View itemView){
        finish();
    }
}
