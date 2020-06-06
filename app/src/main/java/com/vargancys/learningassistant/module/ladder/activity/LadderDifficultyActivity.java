package com.vargancys.learningassistant.module.ladder.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.module.ladder.adapter.DifficultyPagerAdapter;
import com.vargancys.learningassistant.module.ladder.fragment.DifficultyFragment;
import com.vargancys.learningassistant.module.ladder.view.LadderDifficultyView;
import com.vargancys.learningassistant.presenter.ladder.BaseLadderPresenter;
import com.vargancys.learningassistant.utils.CacheUtils;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ResourceUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Vagrancy
 * @date 2020/5/7
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 天梯难度设置
 */
public class LadderDifficultyActivity extends BaseActivity implements LadderDifficultyView {
    @BindView(R.id.common_back)
    ImageView commonBack;
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.common_img)
    ImageView commonImg;
    @BindView(R.id.sliding_tab)
    SlidingTabLayout slidingTab;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.difficulty_edit)
    EditText difficultyEdit;
    @BindView(R.id.difficulty_send)
    ImageView difficultySend;
    private int difficultyType = 1;
    private DifficultyPagerAdapter mAdapter;
    private String[] mDifficulty;
    private AlertDialog.Builder mDialog;
    private BaseLadderPresenter mPresenter;
    private int selectDifficulty = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_ladder_difficulty;
    }

    @Override
    public void initView() {
        difficultyType = CacheUtils.getInt(getContext(), ConstantsUtils.LADDER_DIFFICULTY_TYPE, 0);
        initData();
    }

    private void initData() {
        mPresenter = new BaseLadderPresenter(this);
        mDifficulty = ResourceUtils.getStringArray(getContext(),R.array.ladder_difficulty);
        mAdapter = new DifficultyPagerAdapter(getSupportFragmentManager(),mDifficulty);
        viewPager.setAdapter(mAdapter);

        viewPager.setOffscreenPageLimit(mDifficulty.length);

        viewPager.setCurrentItem(difficultyType);
        slidingTab.setViewPager(viewPager,mDifficulty);
        slidingTab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                selectDifficulty = position;
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        mDialog = new AlertDialog.Builder(this);
        mDialog.setPositiveButton(ResourceUtils.getString(getContext(),R.string.common_cancel_text), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i){
                dialogInterface.dismiss();
            }
        });
        mDialog.setNegativeButton(ResourceUtils.getString(getContext(),R.string.common_determine_text), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mPresenter.showSelectType();
                CacheUtils.putInt(getContext(),ConstantsUtils.LADDER_DIFFICULTY_TYPE,slidingTab.getCurrentTab());
                dialogInterface.dismiss();
                finish();
            }
        });

        difficultySend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(difficultyEdit.getText().length()>0){
                    mPresenter.saveDifficultData(selectDifficulty,difficultyEdit.getText().toString());
                }else{
                    ToastUtils.ToastText(getBaseContext(),ResourceUtils.getString(getContext(),R.string.difficulty_edit_empty_text));
                }
            }
        });
    }

    @Override
    public void initToolbar() {
        commonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        commonTitle.setText(ResourceUtils.getString(getContext(),R.string.difficulty_toolbar));

        commonImg.setImageResource(R.drawable.difficulty_select_normal);

        commonImg.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("StringFormatMatches")
            @Override
            public void onClick(View view) {
                mDialog.setTitle(mDifficulty[slidingTab.getCurrentTab()]);
                mDialog.setMessage(String.format(ResourceUtils.getString(getContext(),R.string.difficulty_select_type_text),mDifficulty[slidingTab.getCurrentTab()]));
                mDialog.show();
            }
        });
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, LadderDifficultyActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void showDifficultySendFinish() {
        ToastUtils.ToastText(getContext(),ResourceUtils.getString(getContext(),R.string.difficulty_send_successful_text));
        DifficultyFragment mFragment = (DifficultyFragment) mAdapter.getItem(selectDifficulty);
        mFragment.autoRefreshData();
    }

    @Override
    public void showDifficultySendError(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error = "+error+", Message ="+message);
        difficultyEdit.setText("");
    }

    @Override
    public void showSelectType() {
        ToastUtils.ToastText(getContext(),ResourceUtils.getString(getContext(),R.string.difficulty_select_successful_text));
    }
}
