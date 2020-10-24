package com.vargancys.learningassistant.module.mine.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseFragment;
import com.vargancys.learningassistant.bean.mine.MineDataBean;
import com.vargancys.learningassistant.model.mine.bean.ChallengeTypeDataBean;
import com.vargancys.learningassistant.module.mine.adapter.ChallengeItemSection;
import com.vargancys.learningassistant.module.mine.view.ChallengeView;
import com.vargancys.learningassistant.presenter.mine.BaseMinePresenter;
import com.vargancys.learningassistant.utils.CacheUtils;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ResourceUtils;
import com.vargancys.learningassistant.utils.ToastUtils;
import com.vargancys.learningassistant.widget.section.SectionedRecyclerViewAdapter;

import butterknife.BindView;

/**
 * @author Vagrancy
 * @date 2020/5/21
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 个人中心天梯碎片页面
 */
public class ChallengeFragment extends BaseFragment implements ChallengeView {
    private static String TAG = "ChallengeFragment";
    public static ChallengeFragment newInstance(){
        return new ChallengeFragment();
    }

    @BindView(R.id.mine_challenge_day)
    TextView mineChallengeDay;
    @BindView(R.id.mine_challenge_highest)
    TextView mineChallengeHighest;
    @BindView(R.id.mine_challenge_level)
    TextView mineChallengeLevel;
    @BindView(R.id.mine_challenge_area)
    TextView mineChallengeArea;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.challenge_scrollview)
    ScrollView challengeScrollView;
    @BindView(R.id.fragment_empty)
    LinearLayout fragmentEmpty;
    @BindView(R.id.fragment_content)
    TextView fragmentContent;
    private BaseMinePresenter mPresenter;
    private long mineId;
    private SectionedRecyclerViewAdapter mSectionAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_challenge;
    }

    /**
     * 业务架构
     * 界面重构
     * 后台处理
     */

    /**
     * 知识型数据
     * 1.知识
     * 多种语言 2种模式(文章和函数模式)
     * 中心 和 知识单向
     * 2.知识体系
     * 修改，删除
     * 知识模块 自己的和大众的区分 然后弹出确认 以及权限控制 查看详情
     *
     * 3.关卡
     * 2种模式，一种是闯关模式，一种是题目阅读模式
     *
     * 4.个人中心
     * 5种等级联动
     *
     * 问题模块 成为编辑者
     * 关卡模块 2种模式一种是原知识闯关 一种是超级闯关模式 等级控制
     * 成就勋章选择显示
     * 知识
     * 注册要创建一堆表
     * 关卡选项换成图案
     *
     */

    /**
     * 1.知识等级
     * 分类别 java,android,php,html
     * 1.知识数量 详细页面 时间:参与时间和更新时间
     * 2.观看人数 收藏人数
     * 3.欢迎页面加数据流
     * 登录模式3中 手机 用户名
     * 注册 手机 用户名或邮箱 安全 和通用配置
     */

    /**
     * 帮助模块
     * 多级分类
     * 一个大类下面有多个小类 小类进去是详情
     * 大类字段 id 标题
     * 小类字段 id 上级id 标题 时间
     * 增删改查
     *
     */

    /**
     * 个人中心
     * 处理数据为空的情况
     * 封装好数据为空
     * 数据出错的情况
     */

    /**
     * 设置功能
     * 帐号设置
     * 权限设置
     * 反馈设置
     *
     */

    @Override
    protected void initView() {
        //TODO 需求制作
        mineId = CacheUtils.getLong(getContext(), ConstantsUtils.MINE_MEMBER_ID,0);
        mPresenter = new BaseMinePresenter(this);
        initRefresh();
        mPresenter.getChallengeData(mineId);
        mSectionAdapter = new SectionedRecyclerViewAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mSectionAdapter);
        fragmentContent.setText(getText(R.string.challenge_data_empty_text));
        autoRefresh();
    }

    private void initRefresh() {
        swipeRefresh.setColorSchemeColors(ResourceUtils.getColor(getContext(),R.color.pink));
        swipeRefresh.setOnRefreshListener(() -> autoRefresh());
    }

    private void autoRefresh(){
        swipeRefresh.setRefreshing(true);
        mPresenter.getChallengeTypeData(getContext(),mineId);
    }

    @Override
    public void loadChallengeDataError(int error, String message) {
        ToastUtils.ToastText(getContext(), "Error =" + error + ", Message =" + message);
        mineChallengeDay.setText("--");
        mineChallengeHighest.setText("--");
        mineChallengeLevel.setText("--");
        mineChallengeArea.setText("--");
    }

    @Override
    public void loadChallengeDataFinish(MineDataBean mine) {
        mineChallengeDay.setText(String.valueOf(mine.getDay()));
        mineChallengeHighest.setText(String.valueOf(mine.getHighest()));
        mineChallengeLevel.setText(String.valueOf(mine.getLevel()));
        mineChallengeArea.setText(String.valueOf(mine.getArea()));
    }

    @Override
    public void loadChallengeTypeDataError(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error ="+error+",Message ="+message);
        swipeRefresh.setRefreshing(false);
        fragmentEmpty.setVisibility(View.VISIBLE);
        challengeScrollView.setVisibility(View.GONE);
    }

    @Override
    public void loadChallengeTypeDataFinish(ChallengeTypeDataBean mBean) {
        swipeRefresh.setRefreshing(false);
        fragmentEmpty.setVisibility(View.GONE);
        challengeScrollView.setVisibility(View.VISIBLE);
        if(mBean.getItemBeans() != null){
            for (int i=0; i<mBean.getItemBeans().size();i++){
                mSectionAdapter.addSection(new ChallengeItemSection(getContext(),getActivity(),mBean.getItemBeans().get(i)));
            }
            mSectionAdapter.notifyDataSetChanged();
        }
    }
}
