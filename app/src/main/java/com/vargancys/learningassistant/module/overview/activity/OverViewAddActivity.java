package com.vargancys.learningassistant.module.overview.activity;

import android.app.Activity;
import android.content.Intent;
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
import com.vargancys.learningassistant.db.common.KnowListBean;
import com.vargancys.learningassistant.db.overview.OverViewListContent;
import com.vargancys.learningassistant.db.overview.OverViewListItem;
import com.vargancys.learningassistant.module.overview.adapter.AddTreeAdapter;
import com.vargancys.learningassistant.module.overview.view.OverViewAddView;
import com.vargancys.learningassistant.presenter.overview.BaseOverViewPresenter;
import com.vargancys.learningassistant.utils.ResourceUtils;
import com.vargancys.learningassistant.utils.TimeUtils;
import com.vargancys.learningassistant.utils.ToastUtils;
import com.vargancys.learningassistant.widget.AddKnowItemDialog;
import com.vargancys.learningassistant.widget.AddKnowTitleDialog;
import com.vargancys.learningassistant.widget.TreeDirectory.Node;
import com.vargancys.learningassistant.widget.TreeDirectory.TreeListViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Vagrancy
 * @date 2020/4/3
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识体系添加页面
 */
public class OverViewAddActivity extends BaseActivity implements OverViewAddView {
    private static String TAG = "OverViewAddActivity";
    public static int OVERVIEW_RESULT_CODE = 30001;
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.common_img)
    ImageView commonImg;
    @BindView(R.id.overview_add_empty)
    LinearLayout overviewAddEmpty;
    @BindView(R.id.tree_roof_title)
    TextView treeRoofTitle;
    @BindView(R.id.overview_add_layout)
    LinearLayout overviewAddLayout;
    @BindView(R.id.overview_list)
    ListView overviewList;
    @BindView(R.id.tree_tool_layout)
    LinearLayout treeToolLayout;
    @BindView(R.id.tree_roof_tool)
    ImageView treeRoofTool;
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
    private long mPid = 0;
    private int selectNode = 0;
    //总知识分数
    private int mGrade = 0;
    //总知识层级
    private int mLayer = 0;
    //总知识等级
    private int mLevel = 0;
    //总知识个数
    private int mNumber = 0;
    //简介
    private String mSummary;
    private List<KnowListBean> mBeans = new ArrayList<>();
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
            public void OnDeterMine(String title,String summary) {
                if(title.isEmpty()&&summary.isEmpty()){
                    ToastUtils.ToastText(getContext(),R.string.overview_add_tidy_text);
                }else{
                    showKnowItem(title);
                    mSummary = summary;
                    mTitleAlert.dismiss();
                }
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
                    ToastUtils.ToastText(getContext(),R.string.overview_add_success_text);
                }else{
                    try {
                        addKnowContent(title,level,score);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    mItemAlert.clearData();
                    mItemAlert.dismiss();
                }

            }
        });
    }

    private void addKnowContent(String title, String level, String score)throws IllegalAccessException {
        if(selectNode == 0){

            KnowListBean mBean = new KnowListBean(mId,mPid,title);
            mBean.setLevel(Integer.valueOf(level));
            mBean.setScore(Integer.valueOf(score));
            mAdapter.addNode(mBean);
            mId++;
            //Log.e(TAG,"length="+mBeans.size());
            mAdapter.notifyDataSetChanged();
            if(mPid != 0){
                mPid = 0;
            }
        }else{
            mAdapter.getNodes(selectNode).setName(title);
            mAdapter.getNodes(selectNode).setLevel(Integer.valueOf(level));
            mAdapter.getNodes(selectNode).setScore(Integer.valueOf(score));
            mAdapter.notifyDataSetChanged();
        }
        selectNode = 0;
    }

    private void initListener() {
        mAdapter.setOnTreeNodeClickListener(new TreeListViewAdapter.OnTreeNodeClickListener() {
            @Override
            public void onShow(int position) {

            }

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

        commonTitle.setText(getText(R.string.overview_toolbar_title));

        commonImg.setImageResource(R.drawable.overview_add_normal);

        commonInsert.setVisibility(View.VISIBLE);
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
        new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handlerItem();
                        handlerContent();
                        mPresenter.saveOverViewAllData(mHandler,mContent,mItems);
                    }
                }
        ).start();
    }

    //增加知识项的array
    private void handlerItem() {
        List<Node> nodes = mAdapter.getAllNodes();
        for (Node node : nodes){
            OverViewListItem mItem = new OverViewListItem();
            //标题
            mItem.setTitle(node.getName());
            //是否学习
            mItem.setStudy(false);
            //增加总分数
            mGrade = mGrade + node.getScore();
            //学习分
            mItem.setScore(node.getScore());
            //增加最高等级
            if(mLevel < node.getLevel()){
                mLevel = node.getLevel();
            }

            //增加层级
            if(mLayer < node.getLayer()){
                mLayer = node.getLayer();
            }
            //掌握知识为零
            mItem.setMasterLevel(0);
            //知识等级
            mItem.setLevel(node.getLevel());
            mItem.setSortId(node.getId());
            //父类id
            mItem.setParentId(node.getpId());

            //创造者id
            mItem.setCreate(mContent.getAuthorId());
            mItems.add(mItem);
        }
        mNumber = mItems.size();
    }

    //增加知识项作者的item
    private void handlerContent() {
        //作者名称
        mContent.setAuthor("Vagrancys");
        //作者id
        mContent.setAuthorId(1);
        //总分数
        mContent.setGrade(mGrade);
        //总层级
        mContent.setLayer(mLayer);
        //总等级
        mContent.setLevel(mLevel);
        //总个数
        mContent.setNumber(mNumber);
        //使用人数
        mContent.setPeople(0);
        //官方推荐
        mContent.setRecommend(true);
        mContent.setSummary(mSummary);
        mContent.setTime(TimeUtils.getTime());
        mContent.setTitle(treeRoofTitle.getText().toString());
    }

    @Override
    public void saveDataError(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error = "+error+", Message ="+message);
    }

    @Override
    public void saveDataFinish() {
        //总知识分数
        mGrade = 0;
        //总知识层级
        mLayer = 0;
        //总知识等级
        mLevel = 0;
        //总知识个数
        mNumber = 0;
        //简介
        mSummary = "";
        mBeans.clear();
        mItems.clear();
        mContent = null;
        hideKnowItem();
    }

    @OnClick({R.id.common_back,R.id.common_img,R.id.common_img_one,R.id.overview_roof_layout,R.id.tree_roof_tool,
            R.id.tree_tool_update,R.id.tree_tool_delete})
    public void onViewClicked(View itemView){
        switch (itemView.getId()){
            case R.id.common_back:
                finish();
                break;
            case R.id.common_img:
                mTitleAlert.show();
                break;
            case R.id.common_img_one:
                mPresenter.TidyAllData();
                break;
            case R.id.tree_tool_update:
            case R.id.overview_roof_layout:
                mItemAlert.show();
                break;
            case R.id.tree_roof_tool:
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
                break;
            case R.id.tree_tool_delete:
                hideKnowItem();
                break;
        }
    }
}
