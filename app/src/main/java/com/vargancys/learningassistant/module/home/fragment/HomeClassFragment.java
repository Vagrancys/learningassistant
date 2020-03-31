package com.vargancys.learningassistant.module.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/31
 * version:1.0
 */
public class HomeClassFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.know_language_all)
    TextView knowLanguageAll;
    @BindView(R.id.know_language_android)
    TextView knowLanguageAndroid;
    @BindView(R.id.know_language_java)
    TextView knowLanguageJava;
    @BindView(R.id.know_language_php)
    TextView knowLanguagePhp;
    @BindView(R.id.know_level_all)
    TextView knowLevelAll;
    @BindView(R.id.know_level_first)
    TextView knowLevelFirst;
    @BindView(R.id.know_level_second)
    TextView knowLevelSecond;
    @BindView(R.id.know_level_third)
    TextView knowLevelThird;
    @BindView(R.id.know_level_fourth)
    TextView knowLevelFourth;
    @BindView(R.id.know_level_fifth)
    TextView knowLevelFifth;
    @BindView(R.id.know_show_all)
    TextView knowShowAll;
    @BindView(R.id.know_show_yes)
    TextView knowShowYes;
    @BindView(R.id.know_show_no)
    TextView knowShowNo;
    @BindView(R.id.know_master_all)
    TextView knowMasterAll;
    @BindView(R.id.know_master_first)
    TextView knowMasterFirst;
    @BindView(R.id.know_master_second)
    TextView knowMasterSecond;
    @BindView(R.id.know_master_third)
    TextView knowMasterThird;
    @BindView(R.id.know_master_fourth)
    TextView knowMasterFourth;
    @BindView(R.id.know_master_fifth)
    TextView knowMasterFifth;

    private int mLanguage = 0;
    private int mLevel = 0;
    private int mShow = 0;
    private int mMaster = 0;
    public static HomeClassFragment getInstance() {
        return new HomeClassFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_class;
    }

    @Override
    protected void initView() {
        initItem();
        initListener();
    }

    private void initListener() {
        knowLanguageAll.setOnClickListener(this);
        knowLanguageAndroid.setOnClickListener(this);
        knowLanguageJava.setOnClickListener(this);
        knowLanguagePhp.setOnClickListener(this);

        knowLevelAll.setOnClickListener(this);
        knowLevelFirst.setOnClickListener(this);
        knowLevelSecond.setOnClickListener(this);
        knowLevelThird.setOnClickListener(this);
        knowLevelFourth.setOnClickListener(this);
        knowLevelFifth.setOnClickListener(this);

        knowShowAll.setOnClickListener(this);
        knowShowYes.setOnClickListener(this);
        knowShowNo.setOnClickListener(this);

        knowMasterAll.setOnClickListener(this);
        knowMasterFirst.setOnClickListener(this);
        knowMasterSecond.setOnClickListener(this);
        knowMasterThird.setOnClickListener(this);
        knowMasterFourth.setOnClickListener(this);
        knowMasterFifth.setOnClickListener(this);
    }

    private void initItem() {
        knowLanguageAll.setSelected(true);
        knowLevelAll.setSelected(true);
        knowShowAll.setSelected(true);
        knowMasterAll.setSelected(true);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.know_language_all:
                selectedLanguage(0);
                break;
            case R.id.know_language_android:
                selectedLanguage(1);
                break;
            case R.id.know_language_java:
                selectedLanguage(2);
                break;
            case R.id.know_language_php:
                selectedLanguage(3);
                break;
            case R.id.know_level_all:
                selectedLevel(0);
                break;
            case R.id.know_level_first:
                selectedLevel(1);
                break;
            case R.id.know_level_second:
                selectedLevel(2);
                break;
            case R.id.know_level_third:
                selectedLevel(3);
                break;
            case R.id.know_level_fourth:
                selectedLevel(4);
                break;
            case R.id.know_level_fifth:
                selectedLevel(5);
                break;
            case R.id.know_show_all:
                selectedShow(0);
                break;
            case R.id.know_show_yes:
                selectedShow(1);
                break;
            case R.id.know_show_no:
                selectedShow(2);
                break;
            case R.id.know_master_all:
                selectedMaster(0);
                break;
            case R.id.know_master_first:
                selectedMaster(1);
                break;
            case R.id.know_master_second:
                selectedMaster(2);
                break;
            case R.id.know_master_third:
                selectedMaster(3);
                break;
            case R.id.know_master_fourth:
                selectedMaster(4);
                break;
            case R.id.know_master_fifth:
                selectedMaster(5);
                break;
        }
    }

    private void selectedLanguage(int number){
        clearLanguage();
        switch (number){
            case 0:
                mLanguage = 0;
                knowLanguageAll.setSelected(true);
                break;
            case 1:
                mLanguage = 1;
                knowLanguageAndroid.setSelected(true);
                break;
            case 2:
                mLanguage = 2;
                knowLanguageJava.setSelected(true);
                break;
            case 3:
                mLanguage = 3;
                knowLanguagePhp.setSelected(true);
                break;
        }
    }

    private void clearLanguage() {
        switch (mLanguage){
            case 0:
                knowLanguageAll.setSelected(false);
                break;
            case 1:
                knowLanguageAndroid.setSelected(false);
                break;
            case 2:
                knowLanguageJava.setSelected(false);
                break;
            case 3:
                knowLanguagePhp.setSelected(false);
                break;
        }
    }

    private void selectedLevel(int number){
        clearLevel();
        switch (number){
            case 0:
                mLevel = 0;
                knowLevelAll.setSelected(true);
                break;
            case 1:
                mLevel = 1;
                knowLevelFirst.setSelected(true);
                break;
            case 2:
                mLevel = 2;
                knowLevelSecond.setSelected(true);
                break;
            case 3:
                mLevel = 3;
                knowLevelThird.setSelected(true);
                break;
            case 4:
                mLevel = 4;
                knowLevelFourth.setSelected(true);
                break;
            case 5:
                mLevel = 5;
                knowLevelFifth.setSelected(true);
                break;
        }
    }

    private void clearShow() {
        switch (mShow){
            case 0:
                knowShowAll.setSelected(false);
                break;
            case 1:
                knowShowYes.setSelected(false);
                break;
            case 2:
                knowShowNo.setSelected(false);
                break;
        }
    }

    private void selectedShow(int number){
        clearShow();
        switch (number){
            case 0:
                mShow = 0;
                knowShowAll.setSelected(true);
                break;
            case 1:
                mShow = 1;
                knowShowYes.setSelected(true);
                break;
            case 2:
                mShow = 2;
                knowShowNo.setSelected(true);
                break;
        }
    }

    private void clearLevel() {
        switch (mLevel){
            case 0:
                knowLevelAll.setSelected(false);
                break;
            case 1:
                knowLevelFirst.setSelected(false);
                break;
            case 2:
                knowLevelSecond.setSelected(false);
                break;
            case 3:
                knowLevelThird.setSelected(false);
                break;
            case 4:
                knowLevelFourth.setSelected(false);
                break;
            case 5:
                knowLevelFifth.setSelected(false);
                break;
        }
    }

    private void selectedMaster(int number){
        clearMaster();
        switch (number){
            case 0:
                mMaster = 0;
                knowMasterAll.setSelected(true);
                break;
            case 1:
                mMaster = 1;
                knowMasterFirst.setSelected(true);
                break;
            case 2:
                mMaster = 2;
                knowMasterSecond.setSelected(true);
                break;
            case 3:
                mMaster = 3;
                knowMasterThird.setSelected(true);
                break;
            case 4:
                mMaster = 4;
                knowMasterFourth.setSelected(true);
                break;
            case 5:
                mMaster = 5;
                knowMasterFifth.setSelected(true);
                break;
        }
    }

    private void clearMaster() {
        switch (mMaster){
            case 0:
                knowMasterAll.setSelected(false);
                break;
            case 1:
                knowMasterFirst.setSelected(false);
                break;
            case 2:
                knowMasterSecond.setSelected(false);
                break;
            case 3:
                knowMasterThird.setSelected(false);
                break;
            case 4:
                knowMasterFourth.setSelected(false);
                break;
            case 5:
                knowMasterFifth.setSelected(false);
                break;
        }
    }

    public int getLanguage() {
        return mLanguage;
    }

    public int getLevel() {
        return mLevel;
    }

    public int getShow() {
        return mShow;
    }

    public int getMaster() {
        return mMaster;
    }
}
