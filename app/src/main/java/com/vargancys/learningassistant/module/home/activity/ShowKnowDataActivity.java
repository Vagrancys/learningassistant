package com.vargancys.learningassistant.module.home.activity;

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
import com.vargancys.learningassistant.db.home.HomeKnowCommend;
import com.vargancys.learningassistant.db.home.HomeKnowData;
import com.vargancys.learningassistant.db.home.HomeKnowHistory;
import com.vargancys.learningassistant.module.home.activity.update.KnowUpdateDefaultActivity;
import com.vargancys.learningassistant.module.home.adapter.HomeKnowCommendAdapter;
import com.vargancys.learningassistant.module.home.adapter.HomeKnowHistoryAdapter;
import com.vargancys.learningassistant.module.home.view.KnowDataView;
import com.vargancys.learningassistant.persenter.home.KnowDataPresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import java.nio.LongBuffer;
import java.util.ArrayList;

import butterknife.BindView;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/25
 * version:1.0
 */
public class ShowKnowDataActivity extends BaseActivity implements KnowDataView {
    @BindView(R.id.common_back)
    ImageView commonBack;
    @BindView(R.id.common_title_data)
    TextView commonTitleData;
    @BindView(R.id.know_data_title)
    TextView knowDataTitle;
    @BindView(R.id.know_data_level)
    ImageView knowDataLevel;
    @BindView(R.id.know_data_count)
    TextView knowDataCount;
    @BindView(R.id.know_data_master)
    TextView knowDataMaster;
    @BindView(R.id.know_data_time)
    TextView knowDataTime;
    @BindView(R.id.know_data_update)
    ImageView knowDataUpdate;
    @BindView(R.id.know_data_delete)
    ImageView knowDataDelete;
    @BindView(R.id.know_data_setting)
    ImageView knowDataSetting;
    @BindView(R.id.know_data_history_text)
    TextView knowDataHistoryText;
    @BindView(R.id.know_data_history_count)
    TextView knowDataHistoryCount;
    @BindView(R.id.history_recycler)
    RecyclerView historyRecycler;
    @BindView(R.id.know_data_commend)
    TextView knowDataCommend;
    @BindView(R.id.commend_recycler)
    RecyclerView commendRecycler;
    @BindView(R.id.know_data_commend_edit)
    EditText knowDataCommendEdit;
    @BindView(R.id.know_data_commend_send)
    ImageView knowDataCommendSend;
    @BindView(R.id.know_data_history_time)
    TextView knowDataHistoryTime;
    private KnowDataPresenter mPresenter;
    private Long know_id;
    private int commendCount;
    private ArrayList<HomeKnowCommend> mCommends = new ArrayList<>();
    private HomeKnowCommendAdapter mCommendAdapter;
    private HomeKnowHistoryAdapter mHistoryAdapter;
    private ArrayList<HomeKnowHistory> mHistory = new ArrayList<>();
    private HomeKnowData mData;

    @Override
    public int getLayoutId() {
        return R.layout.activity_show_know_data;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        if(intent !=null){
            know_id = intent.getLongExtra(ConstantsUtils.KNOW_ITEM_ID,0);
        }
        init();
        mPresenter = new KnowDataPresenter(this);
        mPresenter.getShowData(know_id);
        initListener();
    }

    private void initListener() {
        mHistoryAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                KnowHistoryDataActivity.launch(ShowKnowDataActivity.this,mHistory.get(position).getId(),mData.getLevel());
            }
        });

        knowDataCommendSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.isCommendEmpty(knowDataCommendEdit.getText().toString());
            }
        });

        knowDataDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle(getResources().getString(R.string.dialog_data_delete_title));
                dialog.setMessage(getResources().getString(R.string.dialog_data_delete_message));
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        mPresenter.deleteDataItem(know_id);
                    }
                });
                dialog.show();
            }
        });

        knowDataUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectUpdateLevel(mData.getLevel());
            }
        });

        knowDataSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KnowSettingContentActivity.launch(ShowKnowDataActivity.this,know_id);
            }
        });
    }

    private void selectUpdateLevel(int level) {
        switch (level){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            default:
                KnowUpdateDefaultActivity.launch(this,know_id);
                break;
        }
    }

    @Override
    public void initToolbar() {
        commonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void init(){
        mCommendAdapter = new HomeKnowCommendAdapter(getContext(),mCommends);
        commendRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        commendRecycler.setAdapter(mCommendAdapter);
        mHistoryAdapter = new HomeKnowHistoryAdapter(getContext(),mHistory);
        historyRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        historyRecycler.setAdapter(mHistoryAdapter);
    }

    public static void launch(Activity activity,Long know_id){
        Intent intent = new Intent(activity,ShowKnowDataActivity.class);
        intent.putExtra(ConstantsUtils.KNOW_ITEM_ID,know_id);
        activity.startActivity(intent);
    }

    @Override
    public void showDataFinish(HomeKnowData homeKnowData) {
        mData=homeKnowData;
        commonTitleData.setText(homeKnowData.getTitle());
        knowDataTitle.setText(homeKnowData.getTitle());
        SelectDataLevel(homeKnowData.getLevel());
        knowDataCount.setText(homeKnowData.getCount()+"次");
        knowDataMaster.setText(homeKnowData.getMaster());
        knowDataTime.setText(homeKnowData.getTime());
        knowDataHistoryCount.setText(homeKnowData.getHistorycount());
        knowDataHistoryTime.setText(homeKnowData.getHistorytime());
        mHistory.addAll(homeKnowData.getHomeKnowHistorys());
        mHistoryAdapter.notifyDataSetChanged();
        commendCount = homeKnowData.getCommendcount();
        knowDataCommend.setText(commendCount);
        mCommends.addAll(homeKnowData.getHomeKnowCommends());
        mCommendAdapter.notifyDataSetChanged();
    }

    private void SelectDataLevel(int level){
        switch (level){
            case 1:
                knowDataLevel.setImageResource(R.drawable.know_level_1);
                break;
            case 2:
                knowDataLevel.setImageResource(R.drawable.know_level_2);
                break;
            case 3:
                knowDataLevel.setImageResource(R.drawable.know_level_3);
                break;
            case 4:
                knowDataLevel.setImageResource(R.drawable.know_level_4);
                break;
            case 5:
                knowDataLevel.setImageResource(R.drawable.know_level_5);
                break;
        }
    }

    @Override
    public void showDataError(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error = "+error+", Massage = "+message);
    }

    @Override
    public void showCommendEmptyError(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error = "+error+", Message = "+message);
    }

    @Override
    public void showCommendSaveFinish(HomeKnowCommend homeKnowCommend) {
        knowDataCommend.setText(commendCount+1);
        mCommends.add(homeKnowCommend);
        mCommendAdapter.notifyDataSetChanged();
    }

    @Override
    public void showSaveCommend() {
        mPresenter.saveCommend(know_id,knowDataCommendEdit.getText().toString());
    }

    @Override
    public void showCommendSaveError(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error = "+error+",Message = "+message);
    }

    @Override
    public void deleteDataError(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error = "+error+",Message ="+message);
    }

    @Override
    public void deleteDataFinish() {
        finish();
    }
}
