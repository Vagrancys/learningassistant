package com.vargancys.learningassistant.module.overview.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.db.overview.OverViewListContent;
import com.vargancys.learningassistant.module.overview.view.OverViewInformationView;
import com.vargancys.learningassistant.presenter.overview.BaseOverViewPresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Vagrancy
 * @date 2020/4/3
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识体系信息页面
 */
public class OverViewInformationActivity extends BaseActivity implements OverViewInformationView{
    private static String TAG = "OverViewInformationActivity";
    @BindView(R.id.common_title_data)
    TextView commonTitleData;
    @BindView(R.id.information_title)
    TextView informationTitle;
    @BindView(R.id.information_summary)
    TextView informationSummary;
    @BindView(R.id.information_level)
    ImageView informationLevel;
    @BindView(R.id.information_number)
    TextView informationNumber;
    @BindView(R.id.information_layer)
    TextView informationLayer;
    @BindView(R.id.information_recommend)
    ImageView informationRecommend;
    @BindView(R.id.information_grade)
    TextView informationGrade;
    @BindView(R.id.information_people)
    TextView informationPeople;
    @BindView(R.id.information_author_img)
    ImageView informationAuthorImg;
    @BindView(R.id.information_author)
    TextView informationAuthor;
    @BindView(R.id.information_time)
    TextView informationTime;

    private long selectId;
    private BaseOverViewPresenter mPresenter;
    @Override
    public int getLayoutId() {
        return R.layout.activity_overview_information;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        if(intent !=null){
            selectId = intent.getLongExtra(ConstantsUtils.OVERVIEW_SELECTED_ID,0);
        }
        mPresenter = new BaseOverViewPresenter(this);
        mPresenter.getContentData(selectId);
    }

    public static void launch(Activity activity, long selectId) {
        Intent intent = new Intent(activity, OverViewInformationActivity.class);
        intent.putExtra(ConstantsUtils.OVERVIEW_SELECTED_ID, selectId);
        activity.startActivity(intent);
    }

    @Override
    public void getAllData(List<OverViewListContent> objects) {

    }

    @Override
    public void getAllDataError(int error, String message) {

    }

    @Override
    public void getContentData(OverViewListContent mContent) {
        informationTitle.setText(mContent.getTitle());
        commonTitleData.setText(mContent.getTitle());
        informationSummary.setText(mContent.getSummary());
        informationGrade.setText(mContent.getGrade()+"分");
        informationLayer.setText(String.valueOf(mContent.getLayer()));
        int resId = 0;
        switch (mContent.getLevel()){
            case 0:
                resId = R.drawable.know_level_0;
                break;
            case 1:
                resId = R.drawable.know_level_1;
                break;
            case 2:
                resId = R.drawable.know_level_2;
                break;
            case 3:
                resId = R.drawable.know_level_3;
                break;
            case 4:
                resId = R.drawable.know_level_4;
                break;
        }
        informationLevel.setImageResource(resId);
        informationPeople.setText(mContent.getPeople()+"人");
        informationNumber.setText(mContent.getNumber()+"个");
        informationAuthor.setText(mContent.getAuthor());
        if(mContent.getRecommend()){
            informationRecommend.setVisibility(View.VISIBLE);
        }else{
            informationRecommend.setVisibility(View.GONE);
        }
        informationTime.setText(mContent.getTime());
    }

    @Override
    public void getContentDataError(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error ="+error+",Message ="+message);
    }

    @OnClick({R.id.common_back})
    public void onViewClicked(View itemView){
        finish();
    }}
