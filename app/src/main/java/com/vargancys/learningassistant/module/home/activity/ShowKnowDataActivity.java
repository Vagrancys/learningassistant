package com.vargancys.learningassistant.module.home.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.bean.home.HomeKnowCommend;
import com.vargancys.learningassistant.bean.home.HomeKnowContent;
import com.vargancys.learningassistant.bean.home.HomeKnowData;
import com.vargancys.learningassistant.bean.home.HomeKnowHistory;
import com.vargancys.learningassistant.module.home.activity.update.KnowLedgeUpdateFifthActivity;
import com.vargancys.learningassistant.module.home.activity.update.KnowLedgeUpdateDefaultActivity;
import com.vargancys.learningassistant.module.home.activity.update.UpdateBookActivity;
import com.vargancys.learningassistant.module.home.activity.update.UpdateArticleActivity;
import com.vargancys.learningassistant.module.home.activity.update.KnowLedgeUpdateFourthActivity;
import com.vargancys.learningassistant.module.home.activity.update.KnowLedgeUpdateThirdActivity;
import com.vargancys.learningassistant.module.home.adapter.HomeKnowCommendAdapter;
import com.vargancys.learningassistant.module.home.adapter.HomeKnowHistoryAdapter;
import com.vargancys.learningassistant.module.home.view.KnowDataView;
import com.vargancys.learningassistant.presenter.home.KnowDataPresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ResourceUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/25
 * version:1.0
 * 展示知识数据页面
 */
public class ShowKnowDataActivity extends BaseActivity implements KnowDataView {
    private static String TAG = "ShowKnowDataActivity";
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
    private KnowDataPresenter mPresenter;
    private int commendCount;
    private int contentId;
    private int dataId;
    private int itemId;
    public static int RESULT_CODE = 2000;
    public static int REQUEST_CODE = 1900;
    public static int RESULT_UPDATE_CODE = 2002;
    private ArrayList<HomeKnowCommend> mCommends = new ArrayList<>();
    private HomeKnowCommendAdapter mCommendAdapter;
    private HomeKnowHistoryAdapter mHistoryAdapter;
    private ArrayList<HomeKnowHistory> mHistory = new ArrayList<>();
    private HomeKnowData mData;
    private boolean update_status = false;

    @Override
    public int getLayoutId() {
        return R.layout.activity_show_know_data;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        if(intent !=null){
            itemId = intent.getIntExtra(ConstantsUtils.KNOWLEDGE_ARTICLE_ID,0);
        }
        init();
        mPresenter = new KnowDataPresenter(this);
        mPresenter.getShowData(itemId);
        contentId = mPresenter.getContentId(itemId);
        initListener();
    }

    private void initListener() {
        mHistoryAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                KnowHistoryDataActivity.launch(ShowKnowDataActivity.this,dataId,mData.getLevel());
            }
        });
    }

    private void selectUpdateLevel(int level) {
        switch (level){
            case 1:
                UpdateArticleActivity.launch(this,REQUEST_CODE,contentId,dataId);
                break;
            case 2:
                UpdateBookActivity.launch(this,REQUEST_CODE,contentId,dataId);
                break;
            case 3:
                KnowLedgeUpdateThirdActivity.launch(this,REQUEST_CODE,contentId,dataId);
                break;
            case 4:
                KnowLedgeUpdateFourthActivity.launch(this,REQUEST_CODE,contentId,dataId);
                break;
            case 5:
                KnowLedgeUpdateFifthActivity.launch(this,REQUEST_CODE,contentId,dataId);
                break;
            default:
                KnowLedgeUpdateDefaultActivity.launch(this,REQUEST_CODE,contentId,dataId);
                break;
        }
    }

    private void init(){
        /*mCommendAdapter = new HomeKnowCommendAdapter(getContext(),mCommends);
        commendRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        commendRecycler.setAdapter(mCommendAdapter);
        mHistoryAdapter = new HomeKnowHistoryAdapter(getContext(),mHistory);
        historyRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        historyRecycler.setAdapter(mHistoryAdapter);*/
    }

    public static void launch(Activity activity,int SHOW_REQUEST,long know_id){
        Intent intent = new Intent(activity,ShowKnowDataActivity.class);
        intent.putExtra(ConstantsUtils.KNOWLEDGE_ARTICLE_ID,know_id);
        activity.startActivityForResult(intent,SHOW_REQUEST);
    }

    @Override
    public void showDataFinish(HomeKnowData homeKnowData) {
        /*mData=homeKnowData;
        dataId = homeKnowData.getId();
        commonTitleData.setText(homeKnowData.getTitle());
        knowDataTitle.setText(homeKnowData.getTitle());
        SelectDataLevel(homeKnowData.getLevel());
        knowDataCount.setText(homeKnowData.getCount()+"次");
        knowDataMaster.setText(homeKnowData.getMaster());
        knowDataTime.setText(homeKnowData.getTime());
        if(homeKnowData.getHistorycount() >0&&homeKnowData.getHomeKnowHistorys().size()>0){
            historyRecycler.setVisibility(View.VISIBLE);
            knowDataHistoryCount.setVisibility(View.VISIBLE);
            knowDataHistoryTime.setVisibility(View.VISIBLE);
            knowDataHistoryCount.setText("("+homeKnowData.getHistorycount()+")");
            knowDataHistoryTime.setText(homeKnowData.getHistorytime());
            historyEmpty.setVisibility(View.GONE);
            mHistory.addAll(homeKnowData.getHomeKnowHistorys());
            mHistoryAdapter.notifyDataSetChanged();
        }else{
            historyRecycler.setVisibility(View.GONE);
            knowDataHistoryCount.setVisibility(View.GONE);
            knowDataHistoryTime.setVisibility(View.GONE);
            historyEmpty.setVisibility(View.VISIBLE);
        }
        commendCount = homeKnowData.getCommendcount();
        if(commendCount > 0){
            commendRecycler.setVisibility(View.VISIBLE);
            commendEmpty.setVisibility(View.GONE);
            knowDataCommend.setVisibility(View.VISIBLE);
            knowDataCommend.setText("("+commendCount+")");
            mCommends.addAll(homeKnowData.getHomeKnowCommends());
            mCommendAdapter.notifyDataSetChanged();
        }else{
            commendRecycler.setVisibility(View.GONE);
            commendEmpty.setVisibility(View.VISIBLE);
            knowDataCommend.setVisibility(View.GONE);
        }*/
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE&&resultCode == RESULT_UPDATE_CODE&&data !=null){
            if(data.getIntExtra(ConstantsUtils.ITEM_UPDATE_STATUS,0) == 1){
                update_status = true;
                mPresenter.getHistoryRefreshData(dataId,contentId);
            }
        }
    }

    @Override
    public void showCommendSaveFinish(HomeKnowCommend homeKnowCommend) {
        int number = commendCount++;
        if( number > 0){
            /*commendRecycler.setVisibility(View.VISIBLE);
            commendEmpty.setVisibility(View.GONE);
            knowDataCommend.setVisibility(View.VISIBLE);
            knowDataCommend.setText("("+number+")");
            mCommends.add(homeKnowCommend);
            mCommendAdapter.notifyDataSetChanged();*/
        }
    }

    @Override
    public void showSaveCommend() {
        //mPresenter.saveCommend(dataId,knowDataCommendEdit.getText().toString());
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
        Intent intent = new Intent();
        //0表示没有状态，1表示删除状态,2表示更新状态
        intent.putExtra(ConstantsUtils.ITEM_DELETE_STATUS,1);
        setResult(RESULT_CODE,intent);
        finish();
    }

    @Override
    public void showRefreshHistoryError(int error, String message) {
        Log.e(TAG,"Error ="+error+", Message ="+message);
    }

    @Override
    public void showRefreshHistoryFinish(List<HomeKnowHistory> homeKnowHistories,HomeKnowContent homeKnowContent) {
        int count = homeKnowHistories.size();
        mHistory.clear();
        if(count>0){
            /*historyRecycler.setVisibility(View.VISIBLE);
            knowDataHistoryCount.setVisibility(View.VISIBLE);
            knowDataHistoryTime.setVisibility(View.VISIBLE);
            knowDataHistoryCount.setText("("+count+")");
            knowDataHistoryTime.setText(homeKnowHistories.get(count-1).getTime());
            historyEmpty.setVisibility(View.GONE);
            mHistory.addAll(homeKnowHistories);
            mHistoryAdapter.notifyDataSetChanged();*/
        }

        commonTitleData.setText(homeKnowContent.getTitle());
        knowDataTitle.setText(homeKnowContent.getTitle());
    }

    @OnClick({R.id.common_back,R.id.know_data_setting,R.id.know_data_update,R.id.know_data_delete})
    public void onViewClicked(View itemView){
        switch (itemView.getId()){
            case R.id.common_back:
                //0表示没有状态，1表示删除状态,2表示更新状态
                if(update_status){
                    Intent intent = new Intent();
                    intent.putExtra(ConstantsUtils.ITEM_DELETE_STATUS,2);
                    setResult(RESULT_CODE,intent);
                }
                finish();
                break;
            case R.id.know_data_setting:
                KnowSettingContentActivity.launch(ShowKnowDataActivity.this,dataId);
                break;
            case R.id.know_data_update:
                selectUpdateLevel(mData.getLevel());
                break;
            case R.id.know_data_delete:
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle(ResourceUtils.getString(getContext(),R.string.dialog_data_delete_title));
                dialog.setMessage(ResourceUtils.getString(getContext(),R.string.dialog_data_delete_message));
                dialog.setNegativeButton(R.string.common_cancel_text, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.setPositiveButton(R.string.common_determine_text, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        mPresenter.deleteDataItem(itemId);
                    }
                });
                dialog.show();
                break;

        }
    }
}
