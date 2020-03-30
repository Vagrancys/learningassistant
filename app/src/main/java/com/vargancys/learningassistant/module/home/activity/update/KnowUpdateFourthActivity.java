package com.vargancys.learningassistant.module.home.activity.update;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.db.home.HomeKnowContent;
import com.vargancys.learningassistant.db.home.HomeKnowFunction;
import com.vargancys.learningassistant.db.home.HomeKnowHistory;
import com.vargancys.learningassistant.db.home.HomeKnowHistoryFunction;
import com.vargancys.learningassistant.module.home.adapter.HomeKnowFourthAdapter;
import com.vargancys.learningassistant.module.home.view.KnowInsertFourthView;
import com.vargancys.learningassistant.module.home.view.KnowUpdateFourthView;
import com.vargancys.learningassistant.persenter.home.KnowInsertPresenter;
import com.vargancys.learningassistant.persenter.home.KnowUpdatePresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;
import com.vargancys.learningassistant.widget.FunctionDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/09
 * version:1.0
 */
public class KnowUpdateFourthActivity extends BaseActivity implements KnowUpdateFourthView {
    private String TAG = "KnowInsertFourthActivity";
    @BindView(R.id.common_back)
    ImageView commonBack;
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
    @BindView(R.id.show_hint_fourth)
    TextView showHintFourth;
    private KnowUpdatePresenter mPresenter;
    private long know_item_id;
    private List<HomeKnowFunction> homeKnowFunctions = new ArrayList<>();
    private List<HomeKnowHistoryFunction> mOldHistoryFunction = new ArrayList<>();
    private HomeKnowFourthAdapter mAdapter;
    private int mCommon = 1;
    private FunctionDialog mDialog;
    private int RESULT_CODE = 2002;
    private HomeKnowHistory mOldHistory;

    @Override
    public int getLayoutId() {
        return R.layout.activity_know_update_fourth;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        if(intent != null){
            know_item_id = intent.getLongExtra(ConstantsUtils.KNOW_ITEM_ID,0);
        }
        mPresenter = new KnowUpdatePresenter(this);
        initRecyclerView();
        initListener();
        initDialog();
        mPresenter.getKnowFourthContent(know_item_id);
    }

    private void initRecyclerView() {
        mAdapter = new HomeKnowFourthAdapter(getContext(),homeKnowFunctions);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initToolbar() {

        commonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CODE);
                finish();
            }
        });

        commonTitle.setText(getResources().getString(R.string.common_update_second));

        commonImg.setImageResource(R.drawable.common_update_normal);

        commonImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.isKnowUpdateFourthEmpty();
            }
        });
    }


    private void initListener() {
        mAdapter.setOnItemLongClickListener(new BaseRecyclerAdapter.OnItemLongClickListener() {
            @Override
            public void OnItemLongClick(final int position) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setTitle(homeKnowFunctions.get(position).getTitle());
                alert.setMessage(homeKnowFunctions.get(position).getSummary());
                alert.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alert.setNegativeButton("确定", new DialogInterface.OnClickListener() {
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
                mPresenter.showFourthFunctionWindow();
            }
        });
    }

    private void initDialog(){
        mDialog = new FunctionDialog(this);
        mDialog = new FunctionDialog(this);
        final View popView = View.inflate(getContext(),R.layout.pop_function_fourth,null);
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
                if(mPresenter.isFunctionFourthEmpty(mCommon,title,
                        summary,explain)){
                    ToastUtils.ToastText(getContext(),"请输入完整!");
                }else{
                    mPresenter.addFunctionFourthData(mCommon,title,
                            summary,explain);
                }
            }
        });
    }

    public static void launch(Activity activity,int REQUEST_CODE, long know_id){
        Intent intent = new Intent(activity, KnowUpdateFourthActivity.class);
        intent.putExtra(ConstantsUtils.KNOW_ITEM_ID,know_id);
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
            showHintFourth.setVisibility(View.GONE);
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
        showHintFourth.setVisibility(View.VISIBLE);
        updateShowCount.setVisibility(View.GONE);
    }

    @Override
    public void showFunctionWindow() {
        mDialog.show();
        Log.e(TAG,"popupWindow");
    }

    @Override
    public void showKnowDataFinish(HomeKnowContent content) {
        addHistory(content);
        updateTitleEdit.setText(content.getTitle());
        updateSummaryEdit.setText(content.getSummary());
        updateHeedEdit.setText(content.getHeed());
        updateExperienceEdit.setText(content.getExperience());
        int count = content.getHomeKnowFunctions().size();
        if(count>0){
            homeKnowFunctions.addAll(content.getHomeKnowFunctions());
            recyclerView.setVisibility(View.VISIBLE);
            showHintFourth.setVisibility(View.GONE);
            updateShowCount.setVisibility(View.VISIBLE);
            updateShowCount.setText("("+count+")");
            mAdapter.notifyDataSetChanged();
        }else{
            recyclerView.setVisibility(View.GONE);
            showHintFourth.setVisibility(View.VISIBLE);
            updateShowCount.setVisibility(View.GONE);
        }


    }

    private void addHistory(HomeKnowContent content){
        mOldHistory = new HomeKnowHistory();
        mOldHistory.setDataId(know_item_id);
        mOldHistory.setTitle(content.getTitle());
        mOldHistory.setSummary(content.getSummary());
        mOldHistory.setExplain(content.getExplain());
        mOldHistory.setExperience(content.getExperience());
        mOldHistory.setHeed(content.getHeed());
        mOldHistory.setShow(content.getShow());
        if(content.getHomeKnowFunctions().size()>0){
            for (HomeKnowFunction mFunction :content.getHomeKnowFunctions()){
                HomeKnowHistoryFunction mHistory = new HomeKnowHistoryFunction();
                mHistory.setId(mFunction.getId());
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
        return updateTitleEdit.getText().toString().equals(mOldHistory.getTitle())||
                updateSummaryEdit.getText().toString().equals(mOldHistory.getSummary())||
                updateExperienceEdit.getText().toString().equals(mOldHistory.getExperience())||
                updateHeedEdit.getText().toString().equals(mOldHistory.getHeed());
    }

    @Override
    public void saveKnowUpdateContent() {
        HomeKnowContent mNewContent = new HomeKnowContent();
        mNewContent.setId(know_item_id);
        mNewContent.setTitle(updateTitleEdit.getText().toString());
        mNewContent.setSummary(updateSummaryEdit.getText().toString());
        mNewContent.setExperience(updateExperienceEdit.getText().toString());
        mNewContent.setHeed(updateHeedEdit.getText().toString());
        mPresenter.saveKnowUpdateFourth(mOldHistory,mOldHistoryFunction,mNewContent,homeKnowFunctions);
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
}
