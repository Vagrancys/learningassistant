package com.vargancys.learningassistant.module.game.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.db.overview.OverViewListContent;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Vagrancy
 * @date 2020/4/10
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 关卡选择适配器
 */
public class GameSelectAdapter extends BaseRecyclerAdapter {
    private Context mContext;
    private List<OverViewListContent> mContents;
    public GameSelectAdapter(Context context,List<OverViewListContent> contents){
        this.mContents = contents;
        this.mContext = context;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        GameSelectViewHolder mHolder = (GameSelectViewHolder) holder;
        OverViewListContent mContent = mContents.get(position);
        mHolder.selectNumber.setText(String.valueOf(mContent.getId()));
        mHolder.selectTitle.setText(mContent.getTitle());
        mHolder.selectPeople.setText(mContent.getPeople()+"人使用");
        mHolder.selectSubject.setText(mContent.getNumber()+"题");
        mHolder.selectDifficulty.setText(mContent.getLevel()+"级");
        mHolder.selectScore.setText(mContent.getGrade()+"分");
        super.onBindViewHolder(holder, position);
    }

    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new GameSelectViewHolder(getView(mContext,R.layout.game_select_item));
    }

    @Override
    public int getItemCount() {
        return mContents.size();
    }

    public class GameSelectViewHolder extends CommonViewHolder{
        @BindView(R.id.select_number)
        TextView selectNumber;
        @BindView(R.id.select_title)
        TextView selectTitle;
        @BindView(R.id.select_people)
        TextView selectPeople;
        @BindView(R.id.select_subject)
        TextView selectSubject;
        @BindView(R.id.select_score)
        TextView selectScore;
        @BindView(R.id.select_difficulty)
        TextView selectDifficulty;
        private GameSelectViewHolder(View view){
            super(view);
            ButterKnife.bind(view);
        }
    }
}
