package com.vargancys.learningassistant.module.game.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.db.game.GameFillItem;
import com.vargancys.learningassistant.db.game.GameMultipleItem;
import com.vargancys.learningassistant.db.game.GameRadioItem;
import com.vargancys.learningassistant.db.game.GameSubjectItem;
import com.vargancys.learningassistant.db.game.GameSubjectiveItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Vagrancy
 * @date 2020/4/12
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 关卡问题展示适配器
 */
public class SubjectShowAdapter extends BaseRecyclerAdapter {
    private String TAG = "SubjectShowAdapter";
    private List<GameSubjectItem> mItems;
    private Handler mHandler;
    private Animation mUpScaleAnimation;
    private Animation mDownScaleAnimation;
    private Animation mUpRotateAnimation;
    private Animation mDownRotateAnimation;

    public SubjectShowAdapter(Context context, List<GameSubjectItem> mItems, Handler mHandler) {
        super(context);
        this.mHandler = mHandler;
        this.mItems = mItems;
        mUpScaleAnimation = AnimationUtils.loadAnimation(getContext(),R.anim.common_scale_top_anim);
        mDownScaleAnimation = AnimationUtils.loadAnimation(getContext(),R.anim.common_scale_buttom_anim);
        mUpRotateAnimation = AnimationUtils.loadAnimation(getContext(),R.anim.common_rotate_buttom_anim);
        mDownRotateAnimation = AnimationUtils.loadAnimation(getContext(),R.anim.common_rotate_top_anim);
    }

    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SubjectShowViewHolder(getView(R.layout.subject_show_item));
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        final SubjectShowViewHolder mHolder = (SubjectShowViewHolder) holder;
        final GameSubjectItem mItem = mItems.get(position);
        mHolder.subjectProblemTitle.setText(mItem.getTitle());
        mHolder.subjectTime.setText(mItem.getTime());
        final Runnable mRunnable = new Runnable() {
            @Override
            public void run() {
                mHolder.subjectDetails.setText(getString(R.string.subject_details_show));
                mHolder.subjectIndicate.startAnimation(mUpRotateAnimation);
                hideLayout(mHolder,mItem.getSelect());
            }
        };
        int selectText = 0;
        switch (mItem.getSelect()){
            case 1:
                selectText = R.string.subject_class_first;
                break;
            case 2:
                selectText = R.string.subject_class_second;
                break;
            case 3:
                selectText = R.string.subject_class_third;
                break;
            case 4:
                selectText = R.string.subject_class_fourth;
                break;
        }

        mHolder.subjectClass.setText(getString(selectText));

        mHolder.subjectDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mHolder.subjectDetails.getText().toString()
                        .equals(getString(R.string.subject_details_show))){
                    mHolder.subjectDetails.setText(getString(R.string.subject_details_hide));
                    mHolder.subjectIndicate.startAnimation(mDownRotateAnimation);
                    showLayout(mHolder,mItem);
                    mHandler.postDelayed(mRunnable,2000);
                }else{
                    mHolder.subjectDetails.setText(getString(R.string.subject_details_show));
                    mHolder.subjectIndicate.startAnimation(mUpRotateAnimation);
                    mHandler.removeCallbacks(mRunnable);
                    hideLayout(mHolder,mItem.getSelect());
                }
            }
        });

        super.onBindViewHolder(holder, position);
    }

    private void hideLayout(SubjectShowViewHolder mHolder, int select) {
        switch (select){
            case 1:
                mHolder.radioModule.setVisibility(View.GONE);
                mHolder.radioModule.startAnimation(mUpScaleAnimation);
                break;
            case 2:
                mHolder.multipleModule.setVisibility(View.GONE);
                mHolder.multipleModule.startAnimation(mUpScaleAnimation);
                break;
            case 3:
                mHolder.fillModule.setVisibility(View.GONE);
                mHolder.fillModule.startAnimation(mUpScaleAnimation);
                break;
            case 4:
                mHolder.subjectiveModule.setVisibility(View.GONE);
                mHolder.subjectiveModule.startAnimation(mUpScaleAnimation);
        }
    }

    private void showLayout(SubjectShowViewHolder mHolder,GameSubjectItem mItem) {
        switch (mItem.getSelect()){
            case 1:
                GameRadioItem mRadio = mItem.getRadioItem();
                if(mRadio !=null){
                    initRadioData(mHolder,mRadio);
                    mHolder.radioModule.setVisibility(View.VISIBLE);
                    mHolder.radioModule.startAnimation(mDownScaleAnimation);
                }
                break;
            case 2:
                GameMultipleItem mMultiple = mItem.getMultipleItem();
                if(mMultiple != null){
                    initMultipleData(mHolder,mMultiple);
                    mHolder.multipleModule.setVisibility(View.VISIBLE);
                    mHolder.multipleModule.startAnimation(mDownScaleAnimation);
                }
                break;
            case 3:
                GameFillItem mFill = mItem.getFillItem();
                if(mFill != null){
                    initFillData(mHolder,mFill);
                    mHolder.fillModule.setVisibility(View.VISIBLE);
                    mHolder.fillModule.startAnimation(mDownScaleAnimation);
                }
                break;
            case 4:
                GameSubjectiveItem mSubjective = mItem.getSubjectiveItem();
                if(mSubjective != null){
                    initSubjectiveData(mHolder,mSubjective);
                    mHolder.subjectiveModule.setVisibility(View.VISIBLE);
                    mHolder.subjectiveModule.startAnimation(mDownScaleAnimation);
                }
                break;
        }
    }

    private void initSubjectiveData(SubjectShowViewHolder mHolder, GameSubjectiveItem mSubjective) {
        mHolder.subjectiveConsultTitle.setText(mSubjective.getTitle());
        mHolder.subjectiveSubjectTitle.setText(mSubjective.getAnswer());
    }

    private void initFillData(SubjectShowViewHolder mHolder, GameFillItem mFill) {
        mHolder.fillSubjectTitle.setText(mFill.getTitle());
        initFillState(mHolder);
        switch (mFill.getAnswer()){
            case 1:
                mHolder.fillSubjectFirstImg.setImageResource(R.drawable.subject_yes_normal);
                break;
            case 2:
                mHolder.fillSubjectSecondImg.setImageResource(R.drawable.subject_yes_normal);
                break;
            case 3:
                mHolder.fillSubjectThirdImg.setImageResource(R.drawable.subject_yes_normal);
                break;
            case 4:
                mHolder.fillSubjectFourthImg.setImageResource(R.drawable.subject_yes_normal);
                break;
        }

        mHolder.fillSubjectFirstTitle.setText(mFill.getFirst_answer());
        mHolder.fillSubjectSecondTitle.setText(mFill.getSecond_answer());
        mHolder.fillSubjectThirdTitle.setText(mFill.getThird_answer());
        mHolder.fillSubjectFourthTitle.setText(mFill.getFourth_answer());
    }

    private void initFillState(SubjectShowViewHolder mHolder) {
        mHolder.fillSubjectFirstImg.setImageResource(R.drawable.subject_wrong_normal);
        mHolder.fillSubjectSecondImg.setImageResource(R.drawable.subject_wrong_normal);
        mHolder.fillSubjectThirdImg.setImageResource(R.drawable.subject_wrong_normal);
        mHolder.fillSubjectFourthImg.setImageResource(R.drawable.subject_wrong_normal);
    }

    private void initMultipleData(SubjectShowViewHolder mHolder, GameMultipleItem mMultiple) {
        mHolder.multipleSubjectTitle.setText(mMultiple.getTitle());
        mHolder.multipleSubjectFirstTitle.setText(mMultiple.getFirst_title());
        mHolder.multipleSubjectSecondTitle.setText(mMultiple.getSecond_title());
        mHolder.multipleSubjectThirdTitle.setText(mMultiple.getThird_title());
        mHolder.multipleSubjectFourthTitle.setText(mMultiple.getFourth_title());
        int YesId = R.drawable.subject_yes_normal;
        int WrongId = R.drawable.subject_wrong_normal;
        if(mMultiple.getFirst_answer()){
            mHolder.multipleSubjectFirstImg.setImageResource(YesId);
        }else{
            mHolder.multipleSubjectFirstImg.setImageResource(WrongId);
        }
        if(mMultiple.getSecond_answer()){
            mHolder.multipleSubjectSecondImg.setImageResource(YesId);
        }else{
            mHolder.multipleSubjectSecondImg.setImageResource(WrongId);
        }
        if(mMultiple.getThird_answer()){
            mHolder.multipleSubjectThirdImg.setImageResource(YesId);
        }else{
            mHolder.multipleSubjectThirdImg.setImageResource(WrongId);
        }
        if(mMultiple.getFourth_answer()){
            mHolder.multipleSubjectFourthImg.setImageResource(YesId);
        }else{
            mHolder.multipleSubjectFourthImg.setImageResource(WrongId);
        }
    }

    private void initRadioData(SubjectShowViewHolder mHolder,GameRadioItem mRadio) {
        mHolder.radioSubjectTitle.setText(mRadio.getTitle());
        initRadioState(mHolder);
        switch (mRadio.getYes()){
            case 1:
                mHolder.radioSubjectFirstImg.setImageResource(R.drawable.subject_yes_normal);
                break;
            case 2:
                mHolder.radioSubjectSecondImg.setImageResource(R.drawable.subject_yes_normal);
                break;
            case 3:
                mHolder.radioSubjectThirdImg.setImageResource(R.drawable.subject_yes_normal);
                break;
            case 4:
                mHolder.radioSubjectFourthImg.setImageResource(R.drawable.subject_yes_normal);
                break;
        }
        mHolder.radioSubjectFirstTitle.setText(mRadio.getFirst_title());
        mHolder.radioSubjectSecondTitle.setText(mRadio.getSecond_title());
        mHolder.radioSubjectThirdTitle.setText(mRadio.getThird_title());
        mHolder.radioSubjectFourthTitle.setText(mRadio.getFourth_title());
    }

    private void initRadioState(SubjectShowViewHolder mHolder) {
        mHolder.radioSubjectFirstImg.setImageResource(R.drawable.subject_wrong_normal);
        mHolder.radioSubjectSecondImg.setImageResource(R.drawable.subject_wrong_normal);
        mHolder.radioSubjectThirdImg.setImageResource(R.drawable.subject_wrong_normal);
        mHolder.radioSubjectFourthImg.setImageResource(R.drawable.subject_wrong_normal);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    public class SubjectShowViewHolder extends CommonViewHolder {
        @BindView(R.id.subject_indicate)
        ImageView subjectIndicate;
        @BindView(R.id.subject_problem_title)
        TextView subjectProblemTitle;
        @BindView(R.id.subject_details)
        TextView subjectDetails;
        @BindView(R.id.subject_class)
        TextView subjectClass;
        @BindView(R.id.subject_time)
        TextView subjectTime;

        @BindView(R.id.radio_subject_title)
        TextView radioSubjectTitle;
        @BindView(R.id.radio_subject_first_img)
        ImageView radioSubjectFirstImg;
        @BindView(R.id.radio_subject_first_title)
        TextView radioSubjectFirstTitle;
        @BindView(R.id.radio_subject_second_img)
        ImageView radioSubjectSecondImg;
        @BindView(R.id.radio_subject_second_title)
        TextView radioSubjectSecondTitle;
        @BindView(R.id.radio_subject_third_img)
        ImageView radioSubjectThirdImg;
        @BindView(R.id.radio_subject_third_title)
        TextView radioSubjectThirdTitle;
        @BindView(R.id.radio_subject_fourth_img)
        ImageView radioSubjectFourthImg;
        @BindView(R.id.radio_subject_fourth_title)
        TextView radioSubjectFourthTitle;
        @BindView(R.id.radio_module)
        LinearLayout radioModule;

        @BindView(R.id.multiple_subject_title)
        TextView multipleSubjectTitle;
        @BindView(R.id.multiple_subject_first_img)
        ImageView multipleSubjectFirstImg;
        @BindView(R.id.multiple_subject_first_title)
        TextView multipleSubjectFirstTitle;
        @BindView(R.id.multiple_subject_second_img)
        ImageView multipleSubjectSecondImg;
        @BindView(R.id.multiple_subject_second_title)
        TextView multipleSubjectSecondTitle;
        @BindView(R.id.multiple_subject_third_img)
        ImageView multipleSubjectThirdImg;
        @BindView(R.id.multiple_subject_third_title)
        TextView multipleSubjectThirdTitle;
        @BindView(R.id.multiple_subject_fourth_img)
        ImageView multipleSubjectFourthImg;
        @BindView(R.id.multiple_subject_fourth_title)
        TextView multipleSubjectFourthTitle;
        @BindView(R.id.multiple_module)
        LinearLayout multipleModule;

        @BindView(R.id.fill_subject_title)
        TextView fillSubjectTitle;
        @BindView(R.id.fill_subject_first_img)
        ImageView fillSubjectFirstImg;
        @BindView(R.id.fill_subject_first_title)
        TextView fillSubjectFirstTitle;
        @BindView(R.id.fill_subject_second_img)
        ImageView fillSubjectSecondImg;
        @BindView(R.id.fill_subject_second_title)
        TextView fillSubjectSecondTitle;
        @BindView(R.id.fill_subject_third_img)
        ImageView fillSubjectThirdImg;
        @BindView(R.id.fill_subject_third_title)
        TextView fillSubjectThirdTitle;
        @BindView(R.id.fill_subject_fourth_img)
        ImageView fillSubjectFourthImg;
        @BindView(R.id.fill_subject_fourth_title)
        TextView fillSubjectFourthTitle;
        @BindView(R.id.fill_module)
        LinearLayout fillModule;

        @BindView(R.id.subjective_subject_title)
        TextView subjectiveSubjectTitle;
        @BindView(R.id.subjective_consult_title)
        TextView subjectiveConsultTitle;
        @BindView(R.id.subjective_module)
        LinearLayout subjectiveModule;
        private SubjectShowViewHolder(View view) {
            super(view);
            ButterKnife.bind(view);
        }
    }
}
