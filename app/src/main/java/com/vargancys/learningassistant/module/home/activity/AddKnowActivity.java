package com.vargancys.learningassistant.module.home.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.db.home.HomeKnowItem;
import com.vargancys.learningassistant.module.common.view.HelpAddView;
import com.vargancys.learningassistant.module.home.view.HomeAddView;
import com.vargancys.learningassistant.persenter.common.help.HelpAddPresenter;
import com.vargancys.learningassistant.persenter.home.HomeKnowPresenter;
import com.vargancys.learningassistant.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/07
 * version:1.0
 */
public class AddKnowActivity extends BaseActivity implements HomeAddView {
    private static final String TAG = "AddKnowActivity";
    @BindView(R.id.common_back)
    ImageView commonBack;
    @BindView(R.id.common_img)
    ImageView commonImg;
    @BindView(R.id.add_know_title)
    EditText addKnowTitle;
    @BindView(R.id.add_know_have)
    RadioGroup addKnowHave;
    @BindView(R.id.add_know_class)
    EditText addKnowClass;
    @BindView(R.id.add_know_level)
    Spinner addKnowLevel;
    @BindView(R.id.add_know_summary)
    EditText addKnowSummary;
    @BindView(R.id.add_know_master)
    EditText addKnowMaster;

    private HomeKnowPresenter homeKnowPresenter;
    private boolean mHave = false;
    private int mLevel = 1;
    @Override
    public int getLayoutId() {
        return R.layout.activity_add_know;
    }

    @Override
    public void initView() {
        homeKnowPresenter = new HomeKnowPresenter(this);
        ArrayAdapter simpleAdapter = ArrayAdapter.createFromResource(getContext(),R.array.level_title,
                R.layout.support_simple_spinner_dropdown_item);
        addKnowLevel.setAdapter(simpleAdapter);
        initListener();
    }

    private void initListener() {
        commonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        commonImg.setBackgroundResource(R.drawable.commend_complete_selector);
        commonImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeKnowPresenter.queryKnowRepeat(addKnowTitle.getText().toString());
                addItem();
            }
        });
        addKnowHave.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.know_true){
                    mHave = true;
                }else{
                    mHave = false;
                }
            }
        });

        addKnowLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mLevel = position+1;
                Log.e(TAG,"level="+mLevel);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void addItem(){
        HomeKnowItem homeKnowItem = new HomeKnowItem();
        homeKnowItem.setTitle(addKnowTitle.getText().toString());
        homeKnowItem.setActivity(addKnowClass.getText().toString());
        homeKnowItem.setHave(mHave);
        homeKnowItem.setLevel(mLevel);
        homeKnowItem.setSummary(addKnowSummary.getText().toString());
        homeKnowItem.setStudyTitle(addKnowMaster.getText().toString());
        homeKnowPresenter.saveKnowData(homeKnowItem);
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, AddKnowActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void queryRepeatFinish() {
        Log.e(TAG,"没有重复添加!");
    }

    @Override
    public void queryRepeatError() {
        ToastUtils.ToastText(getContext(),"该知识已经添加了!您可以换一个!");
    }

    @Override
    public void saveFinish() {
        ToastUtils.ToastText(getContext(),"保存成功了!您可以在添加!");
        reFreshView();
    }

    @Override
    public void saveError(int i, String s) {
        ToastUtils.ToastText(getContext(),"保存失败了!请稍后重试!");
    }

    public void reFreshView(){
        addKnowTitle.setText("");
        addKnowHave.getChildAt(1).setSelected(true);
        addKnowClass.setText("");
        addKnowMaster.setText("");
        addKnowSummary.setText("");
    }
}
