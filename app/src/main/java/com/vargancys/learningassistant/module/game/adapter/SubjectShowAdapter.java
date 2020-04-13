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
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/12
 * version:1.0
 */
public class SubjectShowAdapter extends BaseRecyclerAdapter {
    private List<GameSubjectItem> mItems;
    private Context mContext;
    private Handler mHandler;
    private Animation mUpScaleAnimation;
    private Animation mDownScaleAnimation;
    private Animation mUpRotateAnimation;
    private Animation mDownRotateAnimation;

    public SubjectShowAdapter(Context context, List<GameSubjectItem> mItems, Handler mHandler) {
        this.mHandler = mHandler;
        this.mContext = context;
        this.mItems = mItems;
        mUpScaleAnimation = AnimationUtils.loadAnimation(context,R.anim.common_scale_top_anim);
        mDownScaleAnimation = AnimationUtils.loadAnimation(context,R.anim.common_scale_buttom_anim);
        mUpRotateAnimation = AnimationUtils.loadAnimation(context,R.anim.common_rotate_buttom_anim);
        mDownRotateAnimation = AnimationUtils.loadAnimation(context,R.anim.common_rotate_top_anim);
    }

    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SubjectShowViewHolder(View.inflate(mContext, R.layout.subject_show_item, null));
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
                mHolder.subjectDetails.setText(R.string.subject_details_show);
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
        mHolder.subjectClass.setText(mContext.getString(selectText));
        mHolder.subjectDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mHolder.subjectDetails.getText().toString()
                        .equals(mContext.getResources()
                                .getString(R.string.subject_details_show))){
                    mHolder.subjectDetails.setText(R.string.subject_details_hide);
                    mHolder.subjectIndicate.startAnimation(mDownRotateAnimation);
                    showLayout(mHolder,mItem);
                    mHandler.postDelayed(mRunnable,2000);
                }else{
                    mHolder.subjectDetails.setText(R.string.subject_details_show);
                    mHolder.subjectIndicate.startAnimation(mUpRotateAnimation);
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
                    initRadioData(mRadio);
                    mHolder.radioModule.setVisibility(View.VISIBLE);
                    mHolder.radioModule.startAnimation(mDownScaleAnimation);
                }
                break;
            case 2:
                GameMultipleItem mMultiple = mItem.getMultipleItem();
                if(mMultiple != null){
                    initMultipleData(mMultiple);
                    mHolder.multipleModule.setVisibility(View.VISIBLE);
                    mHolder.multipleModule.startAnimation(mDownScaleAnimation);
                }
                break;
            case 3:
                GameFillItem mFill = mItem.getFillItem();
                if(mFill != null){
                    initFillData(mFill);
                    mHolder.fillModule.setVisibility(View.VISIBLE);
                    mHolder.fillModule.startAnimation(mDownScaleAnimation);
                }
                break;
            case 4:
                GameSubjectiveItem mSubjective = mItem.getSubjectiveItem();
                if(mSubjective != null){
                    initSubjectiveData(mSubjective);
                    mHolder.subjectiveModule.setVisibility(View.VISIBLE);
                    mHolder.subjectiveModule.startAnimation(mDownScaleAnimation);
                }
                break;
        }
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
        @BindView(R.id.radio_subject_first)
        LinearLayout radioSubjectFirst;
        @BindView(R.id.radio_subject_second_img)
        ImageView radioSubjectSecondImg;
        @BindView(R.id.radio_subject_second_title)
        TextView radioSubjectSecondTitle;
        @BindView(R.id.radio_subject_second)
        LinearLayout radioSubjectSecond;
        @BindView(R.id.radio_subject_third_img)
        ImageView radioSubjectThirdImg;
        @BindView(R.id.radio_subject_third_title)
        TextView radioSubjectThirdTitle;
        @BindView(R.id.radio_subject_third)
        LinearLayout radioSubjectThird;
        @BindView(R.id.radio_subject_fourth_img)
        ImageView radioSubjectFourthImg;
        @BindView(R.id.radio_subject_fourth_title)
        TextView radioSubjectFourthTitle;
        @BindView(R.id.radio_subject_fourth)
        LinearLayout radioSubjectFourth;
        @BindView(R.id.radio_module)
        LinearLayout radioModule;

        @BindView(R.id.multiple_subject_title)
        TextView multipleSubjectTitle;
        @BindView(R.id.multiple_subject_first_img)
        ImageView multipleSubjectFirstImg;
        @BindView(R.id.multiple_subject_first_title)
        TextView multipleSubjectFirstTitle;
        @BindView(R.id.multiple_subject_first)
        LinearLayout multipleSubjectFirst;
        @BindView(R.id.multiple_subject_second_img)
        ImageView multipleSubjectSecondImg;
        @BindView(R.id.multiple_subject_second_title)
        TextView multipleSubjectSecondTitle;
        @BindView(R.id.multiple_subject_second)
        LinearLayout multipleSubjectSecond;
        @BindView(R.id.multiple_subject_third_img)
        ImageView multipleSubjectThirdImg;
        @BindView(R.id.multiple_subject_third_title)
        TextView multipleSubjectThirdTitle;
        @BindView(R.id.multiple_subject_third)
        LinearLayout multipleSubjectThird;
        @BindView(R.id.multiple_subject_fourth_img)
        ImageView multipleSubjectFourthImg;
        @BindView(R.id.multiple_subject_fourth_title)
        TextView multipleSubjectFourthTitle;
        @BindView(R.id.multiple_subject_fourth)
        LinearLayout multipleSubjectFourth;
        @BindView(R.id.multiple_module)
        LinearLayout multipleModule;

        @BindView(R.id.fill_subject_title)
        TextView fillSubjectTitle;
        @BindView(R.id.fill_subject_first_img)
        ImageView fillSubjectFirstImg;
        @BindView(R.id.fill_subject_first_title)
        TextView fillSubjectFirstTitle;
        @BindView(R.id.fill_subject_first)
        LinearLayout fillSubjectFirst;
        @BindView(R.id.fill_subject_second_img)
        ImageView fillSubjectSecondImg;
        @BindView(R.id.fill_subject_second_title)
        TextView fillSubjectSecondTitle;
        @BindView(R.id.fill_subject_second)
        LinearLayout fillSubjectSecond;
        @BindView(R.id.fill_subject_third_img)
        ImageView fillSubjectThirdImg;
        @BindView(R.id.fill_subject_third_title)
        TextView fillSubjectThirdTitle;
        @BindView(R.id.fill_subject_third)
        LinearLayout fillSubjectThird;
        @BindView(R.id.fill_subject_fourth_img)
        ImageView fillSubjectFourthImg;
        @BindView(R.id.fill_subject_fourth_title)
        TextView fillSubjectFourthTitle;
        @BindView(R.id.fill_subject_fourth)
        LinearLayout fillSubjectFourth;
        @BindView(R.id.fill_module)
        LinearLayout fillModule;

        @BindView(R.id.subjective_subject_title)
        TextView subjectiveSubjectTitle;
        @BindView(R.id.subjective_consult_img)
        ImageView subjectiveConsultImg;
        @BindView(R.id.subjective_consult_title)
        TextView subjectiveConsultTitle;
        @BindView(R.id.subjective_subject_consult)
        LinearLayout subjectiveSubjectConsult;
        @BindView(R.id.subjective_module)
        LinearLayout subjectiveModule;
        private SubjectShowViewHolder(View view) {
            super(view);
            ButterKnife.bind(view);
        }
    }
}
