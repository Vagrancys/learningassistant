package com.vargancys.learningassistant.module.game.fragment;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseFragment;
import com.vargancys.learningassistant.db.common.KnowListBean;
import com.vargancys.learningassistant.db.game.GameContent;
import com.vargancys.learningassistant.db.overview.OverViewListItem;
import com.vargancys.learningassistant.module.game.activity.GameSelectActivity;
import com.vargancys.learningassistant.module.game.activity.GameSignActivity;
import com.vargancys.learningassistant.module.game.activity.GameStartActivity;
import com.vargancys.learningassistant.module.game.activity.GameConfigActivity;
import com.vargancys.learningassistant.module.game.activity.SubjectShowActivity;
import com.vargancys.learningassistant.module.game.adapter.GameTreeAdapter;
import com.vargancys.learningassistant.module.game.view.GameView;
import com.vargancys.learningassistant.presenter.game.BaseGamePresenter;
import com.vargancys.learningassistant.utils.CacheUtils;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.widget.TreeDirectory.Node;
import com.vargancys.learningassistant.widget.TreeDirectory.TreeListViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/06
 * version:1.0
 */
public class GameFragment extends BaseFragment implements GameView {
    private static String TAG = "GameFragment";
    @BindView(R.id.game_select)
    ImageView gameSelect;
    @BindView(R.id.game_text)
    TextView gameText;
    @BindView(R.id.game_sign)
    ImageView gameSign;
    @BindView(R.id.game_title)
    TextView gameTitle;
    @BindView(R.id.game_subject)
    TextView gameSubject;
    @BindView(R.id.game_error)
    TextView gameError;
    @BindView(R.id.game_score)
    TextView gameScore;
    @BindView(R.id.game_level)
    TextView gameLevel;
    @BindView(R.id.game_list)
    ListView gameList;
    @BindView(R.id.game_linear)
    LinearLayout gameLinear;
    @BindView(R.id.fragment_empty)
    LinearLayout fragmentEmpty;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.game_tidy)
    TextView gameTidy;
    private long gameId;
    private BaseGamePresenter mPresenter;
    private GameTreeAdapter mAdapter;
    private List<OverViewListItem> mItems = new ArrayList<>();
    private Handler mHandler;
    private List<KnowListBean> mBeans = new ArrayList<>();
    public static Fragment newInstance() {
        return new GameFragment();
    }

    private void getGameId(){
        gameId = CacheUtils.getLong(getContext(), ConstantsUtils.GAME_ID,0);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_game;
    }

    @Override
    protected void initView() {
        mPresenter = new BaseGamePresenter(this);
        getGameId();
        init();
        gameList.setAdapter(mAdapter);
        initToolbar();
        mPresenter.getGameListData(gameId);
    }

    private void init(){
        gameTidy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CacheUtils.getBoolean(getContext(),ConstantsUtils.GAME_STATE)){
                    GameConfigActivity.launch(getActivity());
                }else{
                    GameStartActivity.launch(getActivity());
                }

            }
        });
        mHandler = new Handler();
        try {
            mAdapter = new GameTreeAdapter<>(gameList,getContext(),mBeans,mItems,mHandler,10);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.pink));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getGameId();
                swipeRefreshLayout.setRefreshing(true);
                mPresenter.getGameListData(gameId);
            }
        });

        mAdapter.setOnTreeNodeClickListener(new TreeListViewAdapter.OnTreeNodeClickListener() {
            @Override
            public void onShow(int position) {
                SubjectShowActivity.launch(getActivity(),mItems.get(position).getId());
            }

            @Override
            public void onClick(Node node, int position) {

            }

            @Override
            public void onDelete(Node node, int position) {

            }

            @Override
            public void onUpdate(Node node, int position) {

            }
        });
        gameList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                if(i >= 1){
                    swipeRefreshLayout.setEnabled(false);
                }else{
                    swipeRefreshLayout.setEnabled(true);
                }
            }
        });
    }

    private void initToolbar(){
        gameSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameSelectActivity.launch(getActivity());
            }
        });

        gameSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameSignActivity.launch(getActivity());
            }
        });
    }

    @Override
    public void showGameContentFinish(GameContent gameContent) {
        gameTitle.setText(gameContent.getTitle());
        gameSubject.setText(String.valueOf(gameContent.getSubject()));
        gameError.setText(String.valueOf(gameContent.getError()));
        gameScore.setText(String.valueOf(gameContent.getScore()));
        gameLevel.setText(String.valueOf(gameContent.getDifficulty()));
        swipeRefreshLayout.setRefreshing(false);
        fragmentEmpty.setVisibility(View.GONE);
        gameLinear.setVisibility(View.VISIBLE);
    }

    @Override
    public void showGameContentError(int error, String message) {
        Log.e(TAG,"Error ="+error+", Message ="+message);
        gameTitle.setText(getResources().getString(R.string.game_title_error));
        gameSubject.setText("--");
        gameError.setText("--");
        gameScore.setText("--");
        gameLevel.setText("--");
        fragmentEmpty.setVisibility(View.VISIBLE);
        gameLinear.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showGameBeanError(int error, String message) {
        Log.e(TAG,"Error = "+error+", Message ="+message);
    }

    @Override
    public void showGameBeanFinish(List<OverViewListItem> gameListBean) {
        initData(gameListBean);
    }

    private void initData(List<OverViewListItem> overViewListItemList){
        mBeans.clear();
        mItems.clear();
        gameTidy.setVisibility(View.VISIBLE);
        mItems.addAll(overViewListItemList);
        for (OverViewListItem item:overViewListItemList){
            KnowListBean mBean = new KnowListBean(item.getSortId(),item.getParentId(),item.getTitle());
            mBean.setMasterLevel(item.getMasterLevel());
            mBean.setScore(item.getScore());
            mBean.setStudy(item.getStudy());
            mBeans.add(mBean);
        }
        try {
            Log.e(TAG,"断点 mBeans ="+mBeans.size());
            mAdapter.swipeData(mBeans);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        mAdapter.notifyDataSetChanged();
    }
}
