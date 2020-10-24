package com.vargancys.learningassistant.module.home.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.bean.home.KnowLedgeBean;
import com.vargancys.learningassistant.module.home.view.HomeAddView;
import com.vargancys.learningassistant.presenter.home.KnowLedgePresenter;
import com.vargancys.learningassistant.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/07
 * version:1.0
 * 添加知识页面
 */
public class AddKnowLedgeActivity extends BaseActivity implements HomeAddView {
    private static final String TAG = "AddKnowActivity";
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

    private KnowLedgePresenter knowLedgePresenter;
    private boolean mHave = false;
    private int mLevel = 1;
    @Override
    public int getLayoutId() {
        return R.layout.activity_add_know;
    }

    @Override
    public void initView() {
        knowLedgePresenter = new KnowLedgePresenter(this);
        ArrayAdapter simpleAdapter = ArrayAdapter.createFromResource(getContext(),R.array.level_title,
                R.layout.support_simple_spinner_dropdown_item);
        addKnowLevel.setAdapter(simpleAdapter);
        initListener();
    }

    @Override
    public void initToolbar() {
        commonImg.setBackgroundResource(R.drawable.comment_complete_selector);
    }

    private void initListener() {
        addKnowHave.setOnCheckedChangeListener((group, checkedId) -> {
            if(checkedId == R.id.know_true){
                mHave = true;
            }else{
                mHave = false;
            }
        });

        addKnowLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mLevel = position+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void addKnowLedge(){
        Map<String,Object> knowLedge = new HashMap<>();
        knowLedge.put(KnowLedgeBean.TITLE,addKnowTitle.getText().toString());
        knowLedge.put(KnowLedgeBean.ACTIVITY,addKnowClass.getText().toString());
        knowLedge.put(KnowLedgeBean.HAVE,mHave);
        knowLedge.put(KnowLedgeBean.LEVEL,mLevel);
        knowLedge.put(KnowLedgeBean.SUMMARY,addKnowSummary.getText().toString());
        knowLedge.put(KnowLedgeBean.STUDTTITLE,addKnowMaster.getText().toString());
        knowLedgePresenter.saveKnowData(knowLedge);
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, AddKnowLedgeActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void queryRepeatFinish() {
        addKnowLedge();
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

    @OnClick({R.id.common_back,R.id.common_img})
    public void onViewClicked(View itemView){
        switch (itemView.getId()){
            case R.id.common_back:
                finish();
                break;
            case R.id.common_img:
                knowLedgePresenter.queryKnowRepeat(addKnowTitle.getText().toString());
                break;
        }
    }
}
