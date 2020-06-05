package com.vargancys.learningassistant.module.game.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
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
import com.vargancys.learningassistant.db.game.GameFillItem;
import com.vargancys.learningassistant.db.game.GameMultipleItem;
import com.vargancys.learningassistant.db.game.GameRadioItem;
import com.vargancys.learningassistant.db.game.GameSubjectItem;
import com.vargancys.learningassistant.db.game.GameSubjectiveItem;
import com.vargancys.learningassistant.module.game.view.AddGameView;
import com.vargancys.learningassistant.presenter.game.BaseGamePresenter;
import com.vargancys.learningassistant.utils.CacheUtils;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ResourceUtils;
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
    private static String TAG = "SubjectAddActivity";
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
    @BindView(R.id.add_multiple_title_edit)
    EditText addMultipleTitleEdit;
    @BindView(R.id.add_multiple_first)
    RadioButton addMultipleFirst;
    @BindView(R.id.add_multiple_first_title_edit)
    EditText addMultipleFirstTitleEdit;
    @BindView(R.id.add_multiple_second)
    RadioButton addMultipleSecond;
    @BindView(R.id.add_multiple_second_title_edit)
    EditText addMultipleSecondTitleEdit;
    @BindView(R.id.add_multiple_third)
    RadioButton addMultipleThird;
    @BindView(R.id.add_multiple_third_title_edit)
    EditText addMultipleThirdTitleEdit;
    @BindView(R.id.add_multiple_fourth)
    RadioButton addMultipleFourth;
    @BindView(R.id.add_multiple_fourth_title_edit)
    EditText addMultipleFourthTitleEdit;
    @BindView(R.id.add_multiple_module)
    LinearLayout addMultipleModule;
    @BindView(R.id.add_fill_title_edit)
    EditText addFillTitleEdit;
    @BindView(R.id.add_fill_first)
    RadioButton addFillFirst;
    @BindView(R.id.add_fill_first_title_edit)
    EditText addFillFirstTitleEdit;
    @BindView(R.id.add_fill_second)
    RadioButton addFillSecond;
    @BindView(R.id.add_fill_second_title_edit)
    EditText addFillSecondTitleEdit;
    @BindView(R.id.add_fill_third)
    RadioButton addFillThird;
    @BindView(R.id.add_fill_third_title_edit)
    EditText addFillThirdTitleEdit;
    @BindView(R.id.add_fill_fourth)
    RadioButton addFillFourth;
    @BindView(R.id.add_fill_fourth_title_edit)
    EditText addFillFourthTitleEdit;
    @BindView(R.id.add_fill_group)
    SubjectRadioGroup addFillGroup;
    @BindView(R.id.add_fill_module)
    LinearLayout addFillModule;
    @BindView(R.id.add_subjective_title_edit)
    EditText addSubjectiveTitleEdit;
    @BindView(R.id.add_subjective_consult_title_edit)
    EditText addSubjectiveConsultTitleEdit;
    @BindView(R.id.add_subjective_module)
    LinearLayout addSubjectiveModule;
    //总结知识单项的id
    private long knowId;
    //关卡的单项总结的id
    private long mSubjectContent;
    private int mSelect;
    private int mRadioId = 0;
    private boolean mMultipleFirstId = false;
    private boolean mMultipleSecondId = false;
    private boolean mMultipleThirdId = false;
    private boolean mMultipleFourthId = false;
    private int mFillId = 0;
    private long gameId =0;
    private BaseGamePresenter mPresenter;
    private void getGameId(){
        gameId = CacheUtils.getLong(getContext(),ConstantsUtils.GAME_ID,0);
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_subject_add;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            knowId = intent.getLongExtra(ConstantsUtils.KNOW_ITEM_ID, 0);
            mSubjectContent = intent.getLongExtra(ConstantsUtils.KNOW_SUBJECT_ID,0);
        }
        initData();
        mPresenter = new BaseGamePresenter(this);
        initListener();
    }

    private void initData() {
        ArrayAdapter mAdapter =ArrayAdapter.createFromResource(getContext(),R.array.common_level,
                R.layout.support_simple_spinner_dropdown_item);
        addSpinner.setAdapter(mAdapter);
        getGameId();
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
                            addClassTitle.setText(ResourceUtils.getString(getContext(),R.string.add_class_first_title));
                            addRadioModule.setVisibility(View.VISIBLE);
                            break;
                        case 2:
                            addClassTitle.setText(ResourceUtils.getString(getContext(),R.string.add_class_second_title));
                            addMultipleModule.setVisibility(View.VISIBLE);
                            break;
                        case 3:
                            addClassTitle.setText(ResourceUtils.getString(getContext(),R.string.add_class_third_title));
                            addFillModule.setVisibility(View.VISIBLE);
                            break;
                        case 4:
                            addClassTitle.setText(ResourceUtils.getString(getContext(),R.string.add_class_fourth_title));
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

        addMultipleFirst.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.e(TAG,"isChecked ="+isChecked);
                if(mMultipleFirstId){
                    mMultipleFirstId = false;
                    addMultipleFirst.setChecked(false);
                }else{
                    mMultipleFirstId = true;
                    addMultipleFirst.setChecked(true);
                }
            }
        });

        addMultipleSecond.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mMultipleSecondId){
                    mMultipleSecondId = false;
                    addMultipleSecond.setChecked(false);
                }else{
                    mMultipleSecondId = true;
                    addMultipleSecond.setChecked(true);
                }
            }
        });

        addMultipleThird.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mMultipleThirdId){
                    mMultipleThirdId = false;
                    addMultipleThird.setChecked(false);
                }else{
                    mMultipleThirdId = true;
                    addMultipleThird.setChecked(true);
                }
            }
        });

        addMultipleFourth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mMultipleFourthId){
                    mMultipleFourthId = false;
                    addMultipleFourth.setChecked(false);
                }else{
                    mMultipleFourthId = true;
                    addMultipleFourth.setChecked(true);
                }
            }
        });

        addFillGroup.setOnCheckedChangeListener(new SubjectRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SubjectRadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.add_fill_first:
                        mFillId = 1;
                        break;
                    case R.id.add_fill_second:
                        mFillId = 2;
                        break;
                    case R.id.add_fill_third:
                        mFillId = 3;
                        break;
                    case R.id.add_fill_fourth:
                        mFillId = 4;
                        break;
                }
            }
        });
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

        commonTitle.setText(ResourceUtils.getString(getContext(),R.string.subject_add_title));

        commonImg.setImageResource(R.drawable.subject_save_normal);
        commonImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.isSubjectDataEmpty();
            }
        });
    }

    public static void launch(Activity activity, long knowId,long subjectId) {
        Intent intent = new Intent(activity, SubjectAddActivity.class);
        intent.putExtra(ConstantsUtils.KNOW_ITEM_ID, knowId);
        intent.putExtra(ConstantsUtils.KNOW_SUBJECT_ID,subjectId);
        activity.startActivity(intent);
    }

    @Override
    public boolean isSubjectDataEmpty() {

        boolean result =isEmptyItem(mSelect);
        Log.e(TAG,"isSubjectDataEmpty ="+result);
        return addTitleEdit.getText().toString().isEmpty()||result;
    }

    private boolean isEmptyItem(int mSelect) {
        boolean result = true;
        switch (mSelect){
            case 1:
                result = isEmptyRadio();
                break;
            case 2:
                result = isEmptyMultiple();
                break;
            case 3:
                result = isEmptyFill();
                break;
            case 4:
                result = isEmptySubjective();
                break;
        }
        Log.e(TAG,"result ="+result);
        return result;
    }

    private boolean isEmptyMultiple() {
        return addMultipleTitleEdit.getText().toString().isEmpty()
                ||addMultipleFirstTitleEdit.getText().toString().isEmpty()
                ||addMultipleSecondTitleEdit.getText().toString().isEmpty()
                ||addMultipleThirdTitleEdit.getText().toString().isEmpty()
                ||addMultipleFourthTitleEdit.getText().toString().isEmpty()
                ||!(mMultipleFirstId| mMultipleSecondId| mMultipleThirdId | mMultipleFourthId);
    }

    private boolean isEmptyRadio() {
        return addRadioTitleEdit.getText().toString().isEmpty()
                ||mRadioId == 0||addRadioFirstTitleEdit.getText().toString().isEmpty()
                ||addRadioSecondTitleEdit.getText().toString().isEmpty()
                ||addRadioThirdTitleEdit.getText().toString().isEmpty()
                ||addRadioFourthTitleEdit.getText().toString().isEmpty();
    }

    private boolean isEmptyFill(){
        return mFillId ==0 ||addFillTitleEdit.getText().toString().isEmpty()
                ||addFillFirstTitleEdit.getText().toString().isEmpty()
                ||addFillSecondTitleEdit.getText().toString().isEmpty()
                ||addFillThirdTitleEdit.getText().toString().isEmpty()
                ||addFillFourthTitleEdit.getText().toString().isEmpty();
    }

    private boolean isEmptySubjective(){
        return addSubjectiveTitleEdit.getText().toString().isEmpty()
                ||addSubjectiveConsultTitleEdit.getText().toString().isEmpty();
    }

    @Override
    public void isSubjectDataError(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error ="+error+", Message ="+message);
    }

    @Override
    public void showAddDataFinish() {
        ToastUtils.ToastText(getContext(),"添加成功了!");
        finish();
    }

    @Override
    public void showAddDataError(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error ="+error+", Message ="+message);
    }

    @Override
    public void tidySubjectData() {
        GameSubjectItem mItem = new GameSubjectItem();
        //答题的类型
        mItem.setSelect(mSelect);
        //父级的id
        mItem.setSubjectId(mSubjectContent);
        //添加时间
        mItem.setTime(TimeUtils.getTime());
        //添加的标题
        mItem.setTitle(addTitleEdit.getText().toString());
        switch (mSelect){
            case 1:
                mItem.setRadioId(tidySubjectRadioData());
                break;
            case 2:
                mItem.setMultipleId(tidySubjectMultipleData());
                break;
            case 3:
                mItem.setFillId(tidySubjectFillData());
                break;
            case 4:
                mItem.setSubjectiveId(tidySubjectSubjectiveData());
                break;
        }
        mPresenter.saveSubjectItemData(mItem);
    }

    private long tidySubjectRadioData(){
        GameRadioItem mRadio = new GameRadioItem();
        mRadio.setTitle(addRadioTitleEdit.getText().toString());
        mRadio.setYes(mRadioId);
        mRadio.setFirst_title(addRadioFirstTitleEdit.getText().toString());
        mRadio.setSecond_title(addRadioSecondTitleEdit.getText().toString());
        mRadio.setThird_title(addRadioThirdTitleEdit.getText().toString());
        mRadio.setFourth_title(addRadioFourthTitleEdit.getText().toString());
        return mPresenter.saveGameRadioItemData(mRadio,mSubjectContent,gameId);
    }

    private long tidySubjectMultipleData(){
        GameMultipleItem mMultiple = new GameMultipleItem();
        mMultiple.setTitle(addMultipleTitleEdit.getText().toString());
        mMultiple.setFirst_title(addMultipleFirstTitleEdit.getText().toString());
        mMultiple.setFirst_answer(mMultipleFirstId);
        mMultiple.setSecond_title(addMultipleSecondTitleEdit.getText().toString());
        mMultiple.setSecond_answer(mMultipleSecondId);
        mMultiple.setThird_title(addMultipleThirdTitleEdit.getText().toString());
        mMultiple.setThird_answer(mMultipleThirdId);
        mMultiple.setFourth_title(addMultipleFourthTitleEdit.getText().toString());
        mMultiple.setFourth_answer(mMultipleFourthId);
        return mPresenter.saveGameMultipleItemData(mMultiple,mSubjectContent,gameId);
    }

    private long tidySubjectFillData(){
        GameFillItem mFill = new GameFillItem();
        mFill.setAnswer(mFillId);
        Log.e(TAG,"mFillId ="+mFillId);
        mFill.setTitle(addFillTitleEdit.getText().toString());
        mFill.setFirst_answer(addFillFirstTitleEdit.getText().toString());
        mFill.setSecond_answer(addFillSecondTitleEdit.getText().toString());
        mFill.setThird_answer(addFillThirdTitleEdit.getText().toString());
        mFill.setFourth_answer(addFillFourthTitleEdit.getText().toString());
        return mPresenter.saveGameFillItemData(mFill,mSubjectContent,gameId);
    }

    private long tidySubjectSubjectiveData(){
        GameSubjectiveItem mSubjective = new GameSubjectiveItem();
        mSubjective.setTitle(addSubjectiveTitleEdit.getText().toString());
        mSubjective.setAnswer(addSubjectiveConsultTitleEdit.getText().toString());
        return mPresenter.saveGameSubjectiveItemData(mSubjective,mSubjectContent,gameId);
    }
}
