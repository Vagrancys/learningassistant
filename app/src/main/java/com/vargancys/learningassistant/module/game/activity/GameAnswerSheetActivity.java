package com.vargancys.learningassistant.module.game.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.db.game.GameAnswerSheetBean;
import com.vargancys.learningassistant.module.game.adapter.AnswerSheetAdapter;
import com.vargancys.learningassistant.module.game.view.AnswerSheetView;
import com.vargancys.learningassistant.presenter.game.BaseGamePresenter;
import com.vargancys.learningassistant.utils.CacheUtils;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Vagrancy
 * @date 2020/5/5
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 关卡答题卡页面
 */
public class GameAnswerSheetActivity extends BaseActivity implements AnswerSheetView {
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.sheet_determine)
    TextView sheetDetermine;
    private ArrayList<GameAnswerSheetBean> mBean = new ArrayList<>();
    private AnswerSheetAdapter mAdapter;
    private BaseGamePresenter mPresenter;
    private long gameId;

    @Override
    public int getLayoutId() {
        return R.layout.activity_answer_sheet;
    }

    @Override
    public void initToolbar() {
        commonTitle.setText(R.string.answer_sheet_toolbar);
    }

    @Override
    public void initView() {
        mBean = getIntent().getParcelableArrayListExtra(ConstantsUtils.ANSWER_SHEET_NAME);
        gameId = CacheUtils.getLong(getContext(), ConstantsUtils.GAME_ID, 0);
        mAdapter = new AnswerSheetAdapter(getContext(), mBean);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),4));
        recyclerView.setAdapter(mAdapter);
        mPresenter = new BaseGamePresenter(this);
    }

    public static void launch(Activity activity, Parcelable parcelable) {
        Intent intent = new Intent(activity, GameAnswerSheetActivity.class);
        intent.putExtra(ConstantsUtils.ANSWER_SHEET_NAME, parcelable);
        activity.startActivity(intent);
    }

    @Override
    public void updateDataFinish() {
        ToastUtils.ToastText(getContext(),R.string.answer_sheet_success_text);
    }

    @Override
    public void updateDataError(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error ="+error +",Message ="+message);
    }

    @OnClick({R.id.common_back,R.id.sheet_determine})
    public void onViewClicked(View itemView){
        switch (itemView.getId()){
            case R.id.common_back:
                finish();
                break;
            case R.id.sheet_determine:
                mPresenter.updateAnswerSheetData(gameId,mBean);
                break;
        }
    }
}
