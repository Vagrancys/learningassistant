package com.vargancys.learningassistant.module.home.activity.update;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.bean.home.HomeKnowContent;
import com.vargancys.learningassistant.bean.home.HomeKnowFunction;
import com.vargancys.learningassistant.bean.home.HomeKnowHistory;
import com.vargancys.learningassistant.bean.home.HomeKnowHistoryFunction;
import com.vargancys.learningassistant.module.home.adapter.HomeKnowSecondAdapter;
import com.vargancys.learningassistant.module.home.view.KnowUpdateSecondView;
import com.vargancys.learningassistant.presenter.home.KnowUpdatePresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;
import com.vargancys.learningassistant.widget.FunctionDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/09
 * version:1.0
 * 知识更新二级页面
 */
public class KnowUpdateSecondActivity extends BaseActivity  implements KnowUpdateSecondView {
    private String TAG = "KnowInsertSecondActivity";
    @BindView(R.id.common_img)
    ImageView commonImg;
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.update_title_edit)
    EditText updateTitleEdit;
    @BindView(R.id.update_summary_edit)
    EditText updateSummaryEdit;
    @BindView(R.id.update_show_add)
    ImageView updateShowAdd;
    @BindView(R.id.update_show_count)
    TextView updateShowCount;
    @BindView(R.id.update_heed_edit)
    EditText updateHeedEdit;
    @BindView(R.id.update_experience_edit)
    EditText updateExperienceEdit;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.show_hint_second)
    TextView showHintSecond;
    private KnowUpdatePresenter mPresenter;
    private long contentId;
    private long dataId;
    private List<HomeKnowFunction> homeKnowFunctions = new ArrayList<>();
    private List<HomeKnowHistoryFunction> mOldHistoryFunction = new ArrayList<>();
    private HomeKnowSecondAdapter mAdapter;
    private int mCommon = 1;
    private FunctionDialog mDialog;
    private int RESULT_CODE = 2002;
    private HomeKnowHistory mOldHistory;
    private HomeKnowContent mNewContent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_know_update_second;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        if(intent != null){
            contentId = intent.getLongExtra(ConstantsUtils.KNOW_CONTENT_ID,0);
            dataId = intent.getLongExtra(ConstantsUtils.KNOW_DATA_ID,0);
        }
        mPresenter = new KnowUpdatePresenter(this);
        initRecyclerView();
        initListener();
        initDialog();
        mPresenter.getKnowSecondContent(contentId);
    }

    private void initRecyclerView() {
        mAdapter = new HomeKnowSecondAdapter(getContext(),homeKnowFunctions);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initToolbar() {
        commonTitle.setText(getText(R.string.common_update_second));

        commonImg.setImageResource(R.drawable.common_update_normal);
    }

    private void initListener() {
        mAdapter.setOnItemLongClickListener(new BaseRecyclerAdapter.OnItemLongClickListener() {
            @Override
            public void OnItemLongClick(final int position) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setTitle(homeKnowFunctions.get(position).getTitle());
                alert.setMessage(homeKnowFunctions.get(position).getSummary());
                alert.setPositiveButton(R.string.common_cancel_text, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alert.setNegativeButton(R.string.common_determine_text, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        homeKnowFunctions.remove(position);
                        mAdapter.notifyItemRemoved(position);
                        dialog.dismiss();
                    }
                });
            }
        });

        updateShowAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.showSecondFunctionWindow();
            }
        });
    }

    private void initDialog(){
        mDialog = new FunctionDialog(this);
        final View popView = View.inflate(getContext(),R.layout.pop_function_second,null);
        mDialog.setParentView(popView);
        mDialog.setOnClickCancelListener(new FunctionDialog.OnClickCancelListener() {
            @Override
            public void OnCancel() {
                mDialog.cancel();
            }
        });
        mDialog.setOnClickDeterMineListener(new FunctionDialog.OnClickDeterMineListener() {
            @Override
            public void OnDeterMine(int common,String title,String summary,String explain) {
                mCommon = common;
                if(mPresenter.isFunctionSecondEmpty(mCommon,title,
                        summary,explain)){
                    ToastUtils.ToastText(getContext(),"请输入完整!");
                }else{
                    mPresenter.addFunctionSecondData(mCommon,title,
                            summary,explain);
                }
            }
        });
    }

    public static void launch(Activity activity,int REQUEST_CODE,long content_id,long data_id){
        Intent intent = new Intent(activity, KnowUpdateSecondActivity.class);
        intent.putExtra(ConstantsUtils.KNOW_CONTENT_ID,content_id);
        intent.putExtra(ConstantsUtils.KNOW_DATA_ID,data_id);
        activity.startActivityForResult(intent,REQUEST_CODE);
    }

    @Override
    public boolean isFunctionEmpty(int common, String title, String summary, String explain) {
        return common != 0 &&title.isEmpty()&&summary.isEmpty()&&explain.isEmpty();
    }

    @Override
    public void addFunctionFinish() {
        ToastUtils.ToastText(getContext(),"添加函数项成功了!");
        if(homeKnowFunctions.size() > 0){
            recyclerView.setVisibility(View.VISIBLE);
            showHintSecond.setVisibility(View.GONE);
            updateShowCount.setVisibility(View.VISIBLE);
            updateShowCount.setText(String.valueOf(homeKnowFunctions.size()));
        }
        mDialog.clearData();
        mDialog.cancel();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void addFunctionError(int error, String msg) {
        ToastUtils.ToastText(getContext(),"Error = "+error+", Msg = "+msg);
    }

    @Override
    public boolean addFunctionData(int common, String title, String summary, String explain) {
        HomeKnowFunction homeKnowFunction = new HomeKnowFunction();
        homeKnowFunction.setFunctionId(contentId);
        homeKnowFunction.setCommon(common);
        homeKnowFunction.setTitle(title);
        homeKnowFunction.setSummary(summary);
        homeKnowFunction.setExplain(explain);
        return homeKnowFunctions.add(homeKnowFunction);
    }

    private void initEmpty() {
        updateTitleEdit.setText("");
        updateSummaryEdit.setText("");
        updateHeedEdit.setText("");
        updateExperienceEdit.setText("");
        homeKnowFunctions.clear();
        mAdapter.notifyDataSetChanged();
        recyclerView.setVisibility(View.GONE);
        showHintSecond.setVisibility(View.VISIBLE);
        updateShowCount.setVisibility(View.GONE);
    }

    @Override
    public void showFunctionWindow() {
        mDialog.show();
    }

    @Override
    public void showKnowDataFinish(HomeKnowContent content) {
        mNewContent = content;
        addHistory(mNewContent);
        updateTitleEdit.setText(content.getTitle());
        updateSummaryEdit.setText(content.getSummary());
        updateHeedEdit.setText(content.getHeed());
        updateExperienceEdit.setText(content.getExperience());
        int count = content.getHomeKnowFunctions().size();
        if(count>0){
            homeKnowFunctions.addAll(content.getHomeKnowFunctions());
            recyclerView.setVisibility(View.VISIBLE);
            showHintSecond.setVisibility(View.GONE);
            updateShowCount.setVisibility(View.VISIBLE);
            updateShowCount.setText("("+count+")");
            mAdapter.notifyDataSetChanged();
        }else{
            recyclerView.setVisibility(View.GONE);
            showHintSecond.setVisibility(View.VISIBLE);
            updateShowCount.setVisibility(View.GONE);
        }
    }

    private void addHistory(HomeKnowContent content){
        mOldHistory = new HomeKnowHistory();
        mOldHistory.setDataId(dataId);
        mOldHistory.setTitle(content.getTitle());
        mOldHistory.setSummary(content.getSummary());
        mOldHistory.setExplain(content.getExplain());
        mOldHistory.setExperience(content.getExperience());
        mOldHistory.setHeed(content.getHeed());
        mOldHistory.setShow(content.getShow());
        if(content.getHomeKnowFunctions().size()>0){
            for (HomeKnowFunction mFunction :content.getHomeKnowFunctions()){
                HomeKnowHistoryFunction mHistory = new HomeKnowHistoryFunction();
                mHistory.setCommon(mFunction.getCommon());
                mHistory.setExplain(mFunction.getExplain());
                mHistory.setFunctionId(mFunction.getFunctionId());
                mHistory.setSummary(mFunction.getSummary());
                mHistory.setTitle(mFunction.getTitle());
                mOldHistoryFunction.add(mHistory);
            }
        }
    }

    @Override
    public void showKnowDataError(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error ="+error+", Message ="+message);
    }

    @Override
    public boolean isKnowUpdateDefaultEmpty() {
        return updateTitleEdit.getText().toString().isEmpty()&&
                updateSummaryEdit.getText().toString().isEmpty()&&
                updateExperienceEdit.getText().toString().isEmpty()&&
                updateHeedEdit.getText().toString().isEmpty()&&
                homeKnowFunctions.size()>0;
    }

    @Override
    public void showKnowEmptyError(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error = "+error+",Message = "+message);
    }


    @Override
    public boolean isKnowUpdateDefaultEquals() {
        return updateTitleEdit.getText().toString().equals(mOldHistory.getTitle())&&
                updateSummaryEdit.getText().toString().equals(mOldHistory.getSummary())&&
                updateExperienceEdit.getText().toString().equals(mOldHistory.getExperience())&&
                updateHeedEdit.getText().toString().equals(mOldHistory.getHeed());
    }

    @Override
    public void saveKnowUpdateContent() {
        mNewContent.setTitle(updateTitleEdit.getText().toString());
        mNewContent.setSummary(updateSummaryEdit.getText().toString());
        mNewContent.setExperience(updateExperienceEdit.getText().toString());
        mNewContent.setHeed(updateHeedEdit.getText().toString());
        mPresenter.saveKnowUpdateSecond(mOldHistory,mOldHistoryFunction,mNewContent,homeKnowFunctions);
    }

    @Override
    public void showKnowEqualsError(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error ="+error+",Message ="+message);
    }

    @Override
    public void showKnowSaveFinish() {
        ToastUtils.ToastText(getContext(),"修改成功了正在退出!");
        initEmpty();
        //0没有更新 1更新了
        Intent intent = new Intent();
        intent.putExtra(ConstantsUtils.ITEM_UPDATE_STATUS,1);
        setResult(RESULT_CODE,intent);
        finish();
    }

    @Override
    public void showKnowSaveError(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error ="+error+", Message ="+message);
    }

    @OnClick({R.id.common_back,R.id.common_img})
    public void onViewClicked(View itemView){
        switch (itemView.getId()){
            case R.id.common_back:
                setResult(RESULT_CODE);
                finish();
                break;
            case R.id.common_img:
                mPresenter.isKnowUpdateSecondEmpty();
                break;
        }
    }
}
