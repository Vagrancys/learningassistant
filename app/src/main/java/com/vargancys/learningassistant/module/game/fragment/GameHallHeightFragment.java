package com.vargancys.learningassistant.module.game.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseFragment;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.model.game.bean.GameHallRankBean;
import com.vargancys.learningassistant.module.game.adapter.GameHallHeightAdapter;
import com.vargancys.learningassistant.module.game.view.BaseGameHallView;
import com.vargancys.learningassistant.presenter.game.BaseGamePresenter;
import com.vargancys.learningassistant.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Vagrancy
 * @date 2020/7/13
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 关卡大厅数量碎片
 */
public class GameHallHeightFragment extends BaseFragment implements BaseGameHallView {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private GameHallHeightAdapter mAdapter;
    private BaseGamePresenter mPresenter;
    private List<GameHallRankBean> mHall = new ArrayList<>();
    private AlertDialog.Builder mAlert;
    private long gameId;

    public static GameHallDifferentFragment newInstance() {
        return new GameHallDifferentFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_game_speciality;
    }

    @Override
    protected void initView() {
        mPresenter = new BaseGamePresenter<BaseGameHallView>(this);
        mAlert = new AlertDialog.Builder(getContext());
        mAlert.setNegativeButton(R.string.common_cancel_text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                gameId = 0;
                dialog.dismiss();
            }
        });
        mAlert.setPositiveButton(R.string.common_determine_text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(gameId != 0){
                    mPresenter.insertGameData(gameId);
                }
                dialog.dismiss();
            }
        });
        mAdapter = new GameHallHeightAdapter(getContext(),mHall);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
        mPresenter.getHallHeightData();
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                GameHallRankBean bean = mHall.get(position);
                gameId = bean.getId();
                mAlert.setTitle(bean.getTitle());
                mAlert.setMessage("是否关注"+bean.getTitle()+"并学习该体系!");
                mAlert.show();
            }
        });
    }

    @Override
    public void getHallDataSuccess(List<GameHallRankBean> mBean) {
        mHall.clear();
        mHall.addAll(mBean);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void getHallDataFail(int error, String message) {
        ToastUtils.ToastText(getContext(),R.string.game_hall_different_fail_text);
    }

    @Override
    public void insertHallDataFail(int error, String message) {
        ToastUtils.ToastText(getContext(),R.string.game_hall_insert_fail_text);
    }

    @Override
    public void insertHallDataSuccess() {
        ToastUtils.ToastText(getContext(),R.string.game_hall_insert_win_text);
    }
}
