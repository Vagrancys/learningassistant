package com.vargancys.learningassistant.module.ladder.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.module.ladder.adapter.CommunicationPagerAdapter;
import com.vargancys.learningassistant.module.ladder.fragment.CommunicationFragment;
import com.vargancys.learningassistant.module.ladder.view.LadderCommunicationView;
import com.vargancys.learningassistant.presenter.ladder.BaseLadderPresenter;
import com.vargancys.learningassistant.utils.ResourceUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Vagrancy
 * @date 2020/5/7
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 天梯交流中心
 */
public class LadderCommunicationActivity extends BaseActivity implements LadderCommunicationView {
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.common_img)
    ImageView commonImg;
    @BindView(R.id.sliding_tab)
    SlidingTabLayout slidingTab;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.comment_edit)
    EditText commentEdit;
    private String[] mTab;
    private BaseLadderPresenter mPresenter;
    private CommunicationPagerAdapter mAdapter;
    //当前所选择的交流区
    private int mCurrent = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_ladder_communication;
    }

    @Override
    public void initView() {
        mTab = ResourceUtils.getStringArray(getContext(),R.array.ladder_communication);
        mPresenter = new BaseLadderPresenter(this);
        initAdapter();
        initListener();
    }

    private void initListener() {
        slidingTab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mCurrent = position;
                viewPager.setCurrentItem(mCurrent);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrent = position;
                slidingTab.setCurrentTab(mCurrent);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void initAdapter() {
        mAdapter = new CommunicationPagerAdapter(getSupportFragmentManager(),mTab);
        viewPager.setAdapter(mAdapter);
        viewPager.setOffscreenPageLimit(mTab.length);
        viewPager.setCurrentItem(mCurrent);
        slidingTab.setViewPager(viewPager,mTab);
        slidingTab.setCurrentTab(mCurrent);
    }

    @Override
    public void initToolbar() {
        commonTitle.setText(getText(R.string.ladder_communication_toolbar));
        commonImg.setImageResource(R.drawable.permission_setting_normal);
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, LadderCommunicationActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void sendCommentFinish() {
        ToastUtils.ToastText(getContext(),R.string.ladder_comment_success);
        mPresenter.refreshCommentLayout();
        commentEdit.setText("");
    }

    @Override
    public void refreshCommentLayout() {
        CommunicationFragment mFragment = (CommunicationFragment) mAdapter.getItem(mCurrent);
        mFragment.refreshData();
    }

    @Override
    public void sendCommentError(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error = "+error+", Message ="+message);
    }

    @OnClick({R.id.common_back,R.id.common_img,R.id.comment_send})
    public void onViewClicked(View itemView){
        switch (itemView.getId()){
            case R.id.common_back:
                finish();
                break;
            case R.id.common_img:
                //TODO 权限设置
                break;
            case R.id.comment_send:
                if(commentEdit.getText().length()>0){
                    mPresenter.saveCommentData(mCurrent,commentEdit.getText().toString());
                }else{
                    ToastUtils.ToastText(getBaseContext(), R.string.ladder_communication_empty);
                }
                break;
        }
    }
}
