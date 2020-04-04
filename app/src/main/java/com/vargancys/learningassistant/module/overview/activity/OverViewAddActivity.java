package com.vargancys.learningassistant.module.overview.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.db.overview.OverViewListBean;
import com.vargancys.learningassistant.db.overview.OverViewListContent;
import com.vargancys.learningassistant.db.overview.OverViewListItem;
import com.vargancys.learningassistant.module.overview.adapter.AddTreeAdapter;
import com.vargancys.learningassistant.module.overview.view.BaseOverView;
import com.vargancys.learningassistant.presenter.overview.BaseOverViewPresenter;
import com.vargancys.learningassistant.utils.ToastUtils;
import com.vargancys.learningassistant.widget.AddKnowItemDialog;
import com.vargancys.learningassistant.widget.AddKnowTitleDialog;
import com.vargancys.learningassistant.widget.TreeDirectory.Node;
import com.vargancys.learningassistant.widget.TreeDirectory.TreeListViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/03
 * version:1.0
 */
public class OverViewAddActivity extends BaseActivity implements BaseOverView {
    private static String TAG = "OverViewAddActivity";
    @BindView(R.id.common_back)
    ImageView commonBack;
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.common_img)
    ImageView commonImg;
    @BindView(R.id.overview_add_empty)
    LinearLayout overviewAddEmpty;
    @BindView(R.id.tree_roof_title)
    TextView treeRoofTitle;
    @BindView(R.id.overview_roof_layout)
    RelativeLayout overviewRoofLayout;
    @BindView(R.id.overview_add_layout)
    LinearLayout overviewAddLayout;
    @BindView(R.id.overview_list)
    ListView overviewList;
    @BindView(R.id.tree_tool_layout)
    LinearLayout treeToolLayout;
    @BindView(R.id.tree_roof_tool)
    ImageView treeRoofTool;
    @BindView(R.id.tree_tool_update)
    ImageView treeToolUpdate;
    @BindView(R.id.tree_tool_delete)
    ImageView treeToolDelete;
    @BindView(R.id.common_img_one)
    ImageView commonInsert;

    private Handler mHandler;
    private BaseOverViewPresenter mPresenter;
    private AddTreeAdapter mAdapter;
    private Animation mShowAnim;
    private Animation mHideAnim;
    public static void launch(Activity activity, int request_code) {
        Intent intent = new Intent(activity, OverViewAddActivity.class);
        activity.startActivityForResult(intent, request_code);
    }

    private AddKnowTitleDialog mTitleAlert;
    private AddKnowItemDialog mItemAlert;
    private OverViewListContent mContent;
    private int mId = 1;
    private int mPid = 0;
    private int selectNode = 0;
    private List<OverViewListBean> mBeans = new ArrayList<>();
    private List<OverViewListItem> mItems = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_overview_add;
    }

    @Override
    public void initView() {
        init();
        try {
            mContent = new OverViewListContent();
            mAdapter = new AddTreeAdapter<>(overviewList,getContext(),mHandler,mBeans,1);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        overviewList.setAdapter(mAdapter);
        initListener();
    }

    private void init() {
        mHandler = new Handler();
        mPresenter = new BaseOverViewPresenter(this);
        mShowAnim = AnimationUtils.loadAnimation(getContext(),R.anim.common_show_item_anim);
        mHideAnim = AnimationUtils.loadAnimation(getContext(),R.anim.common_hide_item_anim);

        mTitleAlert = new AddKnowTitleDialog(getContext());
        mTitleAlert.setOnClickCancelListener(new AddKnowTitleDialog.OnClickCancelListener() {
            @Override
            public void OnCancel() {
                mTitleAlert.dismiss();
            }
        });
        mTitleAlert.setOnClickDeterMineListener(new AddKnowTitleDialog.OnClickDeterMineListener() {
            @Override
            public void OnDeterMine(String message) {
                showKnowItem(message);
                mTitleAlert.dismiss();
            }
        });

        mItemAlert = new AddKnowItemDialog(getContext());
        mItemAlert.setOnClickCancelListener(new AddKnowItemDialog.OnClickCancelListener() {
            @Override
            public void OnCancel() {
                mItemAlert.dismiss();
            }
        });
        mItemAlert.setOnClickDeterMineListener(new AddKnowItemDialog.OnClickDeterMineListener() {
            @Override
            public void OnDeterMine(String title,String level,String score) {
                if(title.isEmpty()&&level.isEmpty()&&score.isEmpty()){
                    ToastUtils.ToastText(getContext(),"请输入完成!");
                }else{
                    try {
                        addKnowContent(title,level,score);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    mItemAlert.dismiss();
                }

            }
        });
    }

    private void addKnowContent(String title, String level, String score)throws IllegalAccessException {
        if(selectNode == 0){
            OverViewListBean mBean = new OverViewListBean(mId,mPid,title);
            mBean.setMasterLevel(Integer.valueOf(level));
            mBean.setScore(Integer.valueOf(score));
            mAdapter.addNode(mBean);
            mId++;
            Log.e(TAG,"length="+mBeans.size());
            mAdapter.notifyDataSetChanged();
            if(mPid != 0){
                mPid = 0;
            }
        }else{
           mAdapter.getNodes(selectNode).setName(title);
            mAdapter.getNodes(selectNode).setLevel(Integer.valueOf(level));
            mAdapter.getNodes(selectNode).setScore(Integer.valueOf(score));
        }
        selectNode = 0;
    }

    private void initListener() {
        overviewRoofLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemAlert.show();
            }
        });

        treeRoofTool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                treeRoofTool.setVisibility(View.GONE);
                treeToolLayout.setVisibility(View.VISIBLE);
                treeToolLayout.startAnimation(mShowAnim);
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        treeToolLayout.startAnimation(mHideAnim);
                        mHideAnim.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                treeRoofTool.setVisibility(View.VISIBLE);
                                treeToolLayout.setVisibility(View.GONE);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });

                    }
                },3000);
            }
        });

        treeToolUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTitleAlert.show();
            }
        });

        treeToolDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKnowItem();
            }
        });

        mAdapter.setOnTreeNodeClickListener(new TreeListViewAdapter.OnTreeNodeClickListener() {
            @Override
            public void onClick(Node node, int position) {
                mPid = node.getId();
                mItemAlert.show();
            }

            @Override
            public void onDelete(Node node, int position) {
                mAdapter.deleteNode(position);
            }

            @Override
            public void onUpdate(Node node, int position) {
                mItemAlert.setTitle(node.getName());
                mItemAlert.setLevel(node.getLevel());
                mItemAlert.setScore(node.getScore());
                mItemAlert.show();
                selectNode = position;
            }
        });
    }

    @Override
    public void initToolbar() {
        commonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        commonTitle.setText(getResources().getString(R.string.overview_toolbar_title));

        commonImg.setImageResource(R.drawable.overview_add_normal);

        commonImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTitleAlert.show();
            }
        });

        commonInsert.setVisibility(View.VISIBLE);
        commonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.TidyAllData();
            }
        });
    }

    private void showKnowItem(String message) {
        if(overviewAddLayout.getVisibility() == View.GONE){
            overviewAddLayout.setVisibility(View.VISIBLE);
            overviewAddEmpty.setVisibility(View.GONE);
            treeRoofTitle.setText(message);
            commonImg.setVisibility(View.GONE);
        }
    }

    private void hideKnowItem(){
        if(overviewAddLayout.getVisibility() == View.VISIBLE){
            overviewAddLayout.setVisibility(View.GONE);
            overviewAddEmpty.setVisibility(View.VISIBLE);
            treeRoofTitle.setText("");
            commonImg.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void getAllData(List<OverViewListContent> objects) {

    }

    @Override
    public void getAllDataError(int error, String message) {

    }

    @Override
    public void TidyAllData() {
        handlerContent();
        handlerItem();
        mPresenter.saveOverViewAllData(mContent,mItems);
    }

    private void handlerItem() {
        
    }

    private void handlerContent() {

    }
}
