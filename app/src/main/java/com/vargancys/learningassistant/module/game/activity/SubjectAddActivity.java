package com.vargancys.learningassistant.module.game.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.db.game.GameRadioItem;
import com.vargancys.learningassistant.db.game.GameSubjectItem;
import com.vargancys.learningassistant.module.game.view.AddGameView;
import com.vargancys.learningassistant.presenter.game.BaseGamePresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.TimeUtils;
import com.vargancys.learningassistant.utils.ToastUtils;
import com.vargancys.learningassistant.widget.SubjectRadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/13
 * version:1.0
 * 添加单个问题
 */
public class SubjectAddActivity extends BaseActivity implements AddGameView {
    @BindView(R.id.common_back)
    ImageView commonBack;
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.common_img)
    ImageView commonImg;
    @BindView(R.id.add_title_edit)
    EditText addTitleEdit;
    @BindView(R.id.add_class_text)
    TextView addClassText;
    @BindView(R.id.add_spinner)
    Spinner addSpinner;
    @BindView(R.id.add_class_title)
    TextView addClassTitle;
    @BindView(R.id.add_radio_title_edit)
    EditText addRadioTitleEdit;
    @BindView(R.id.add_radio_first)
    RadioButton addRadioFirst;
    @BindView(R.id.add_radio_first_title_edit)
    EditText addRadioFirstTitleEdit;
    @BindView(R.id.add_radio_second)
    RadioButton addRadioSecond;
    @BindView(R.id.add_radio_second_title_edit)
    EditText addRadioSecondTitleEdit;
    @BindView(R.id.add_radio_third)
    RadioButton addRadioThird;
    @BindView(R.id.add_radio_third_title_edit)
    EditText addRadioThirdTitleEdit;
    @BindView(R.id.add_radio_fourth)
    RadioButton addRadioFourth;
    @BindView(R.id.add_radio_fourth_title_edit)
    EditText addRadioFourthTitleEdit;
    @BindView(R.id.add_radio_group)
    SubjectRadioGroup addRadioGroup;
    @BindView(R.id.add_radio_module)
    LinearLayout addRadioModule;
    @BindView(R.id.add_multiple_title)
    EditText addMultipleTitle;
    @BindView(R.id.add_multiple_first)
    RadioButton addMultipleFirst;
    @BindView(R.id.add_multiple_first_title)
    EditText addMultipleFirstTitle;
    @BindView(R.id.add_multiple_second)
    RadioButton addMultipleSecond;
    @BindView(R.id.add_multiple_second_title)
    EditText addMultipleSecondTitle;
    @BindView(R.id.add_multiple_third)
    RadioButton addMultipleThird;
    @BindView(R.id.add_multiple_third_title)
    EditText addMultipleThirdTitle;
    @BindView(R.id.add_multiple_fourth)
    RadioButton addMultipleFourth;
    @BindView(R.id.add_multiple_fourth_title)
    EditText addMultipleFourthTitle;
    @BindView(R.id.add_multiple_module)
    LinearLayout addMultipleModule;
    @BindView(R.id.add_fill_title_edit)
    EditText addFillTitleEdit;
    @BindView(R.id.add_fill_first)
    RadioButton addFillFirst;
    @BindView(R.id.add_fill_first_title)
    EditText addFillFirstTitle;
    @BindView(R.id.add_fill_second)
    RadioButton addFillSecond;
    @BindView(R.id.add_fill_second_title)
    EditText addFillSecondTitle;
    @BindView(R.id.add_fill_third)
    RadioButton addFillThird;
    @BindView(R.id.add_fill_third_title)
    EditText addFillThirdTitle;
    @BindView(R.id.add_fill_fourth)
    RadioButton addFillFourth;
    @BindView(R.id.add_fill_fourth_title)
    EditText addFillFourthTitle;
    @BindView(R.id.add_fill_group)
    SubjectRadioGroup addFillGroup;
    @BindView(R.id.add_fill_module)
    LinearLayout addFillModule;
    @BindView(R.id.add_subjective_title)
    EditText addSubjectiveTitle;
    @BindView(R.id.add_subjective_consult_title)
    EditText addSubjectiveConsultTitle;
    @BindView(R.id.add_subjective_module)
    LinearLayout addSubjectiveModule;
    private long knowId;
    private int mSelect;
    private int mRadioId = 0;
    private int mMultipleFirstId = 0;
    private int mMultipleSecondId = 0;
    private int mMultipleThirdId = 0;
    private int mMultipleFourthId = 0;
    private BaseGamePresenter mPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_subject_add;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            knowId = intent.getLongExtra(ConstantsUtils.KNOW_ITEM_ID, 0);
        }
        initData();
        mPresenter = new BaseGamePresenter(this);
        initListener();
    }

    private void initData() {
        ArrayAdapter mAdapter =ArrayAdapter.createFromResource(getContext(),R.array.common_level,
                R.layout.support_simple_spinner_dropdown_item);
        addSpinner.setAdapter(mAdapter);
    }

    private void initListener() {
        addSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(mSelect != position+1){
                    mSelect = position +1;
                    hideItemLayout();
                    switch (mSelect){
                        case 1:
                            addClassTitle.setText(getResources().getString(R.string.add_class_first_title));
                            addRadioModule.setVisibility(View.VISIBLE);
                            break;
                        case 2:
                            addClassTitle.setText(getResources().getString(R.string.add_class_second_title));
                            addMultipleModule.setVisibility(View.VISIBLE);
                            break;
                        case 3:
                            addClassTitle.setText(getResources().getString(R.string.add_class_third_title));
                            addFillModule.setVisibility(View.VISIBLE);
                            break;
                        case 4:
                            addClassTitle.setText(getResources().getString(R.string.add_class_fourth_title));
                            addSubjectiveModule.setVisibility(View.VISIBLE);
                            break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        addRadioGroup.setOnCheckedChangeListener(new SubjectRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SubjectRadioGroup group, int checkedId) {
                ToastUtils.ToastText(getContext(),"CheckedId ="+checkedId);
                switch (checkedId){
                    case R.id.add_radio_first:
                        mRadioId = 1;
                        break;
                    case R.id.add_radio_second:
                        mRadioId = 2;
                        break;
                    case R.id.add_radio_third:
                        mRadioId = 3;
                        break;
                    case R.id.add_radio_fourth:
                        mRadioId = 4;
                        break;
                }
            }
        });

        //TODO 进行到处理各种答题方式选项
    }

    private void hideItemLayout(){
        addRadioModule.setVisibility(View.GONE);
        addMultipleModule.setVisibility(View.GONE);
        addFillModule.setVisibility(View.GONE);
        addSubjectiveModule.setVisibility(View.GONE);
    }

    @Override
    public void initToolbar() {
        commonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        commonTitle.setText(getResources().getString(R.string.subject_add_title));

        commonImg.setImageResource(R.drawable.subject_save_normal);
        commonImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.isSubjectDataEmpty();
            }
        });
    }

    public static void launch(Activity activity, long knowId) {
        Intent intent = new Intent(activity, SubjectAddActivity.class);
        intent.putExtra(ConstantsUtils.KNOW_ITEM_ID, knowId);
        activity.startActivity(intent);
    }

    @Override
    public boolean isSubjectDataEmpty() {
        return false;
    }

    @Override
    public void isSubjectDataError(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error ="+error+", Message ="+message);
    }

    @Override
    public void tidySubjectData() {
        GameSubjectItem mItem = new GameSubjectItem();
        //答题的类型
        mItem.setSelect(mSelect);
        //父级的id
        mItem.setSubjectId(knowId);
        //添加时间
        mItem.setTime(TimeUtils.getTime());
        //添加的标题
        mItem.setTitle(addTitleEdit.getText().toString());
        long subjectId = mPresenter.saveSubjectItemData(mItem);
        switch (mSelect){
            case 1:
                tidySubjectRadioData(subjectId);
                break;
            case 2:
                tidySubjectMultipleData(subjectId);
                break;
            case 3:
                tidySubjectFillData(subjectId);
                break;
            case 4:
                tidySubjectSubjectiveData(subjectId);
                break;
        }
    }

    private void tidySubjectRadioData(long result){
        GameRadioItem mRadios = new GameRadioItem();
        mRadios.setSubjectId(result);
        mRadios.setTitle(addRadioTitleEdit.getText().toString());
        mRadios.setYes(mRadioId);
        mRadios.setFirst_title(addRadioFirstTitleEdit.getText().toString());
        mRadios.setSecond_title(addRadioSecondTitleEdit.getText().toString());
        mRadios.setThird_title(addRadioThirdTitleEdit.getText().toString());
        mRadios.setFourth_title(addRadioFourthTitleEdit.getText().toString());
    }

    private void tidySubjectMultipleData(long result){

    }

    private void tidySubjectFillData(long result){

    }

    private void tidySubjectSubjectiveData(long result){

    }
}
