package com.vargancys.learningassistant.module.game.adapter;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.module.overview.adapter.OverViewHallRankAdapter;
import com.vargancys.learningassistant.utils.ResourceUtils;
import com.vargancys.learningassistant.widget.section.SectionedRecyclerViewAdapter;
import com.vargancys.learningassistant.widget.section.StatelessSection;

import butterknife.BindView;

/**
 * @author Vagrancy
 * @date 2020/7/12
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 关卡大厅排行部分
 */
public class GameHallRankSection extends StatelessSection {
    private String[] mRank;
    private GameHallRankAdapter mAdapter;
    private FragmentManager mManager;
    public GameHallRankSection(Context context, FragmentManager manager){
        super(context, R.layout.game_hall_rank_item);
        mManager = manager;
        mRank = ResourceUtils.getStringArray(getContext(),R.array.game_hall_rank);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindItemViewHolder(holder, position);
        GameHallRankViewHolder mHolder = (GameHallRankViewHolder) holder;
        mAdapter = new GameHallRankAdapter(mManager,mRank);
        mHolder.viewPager.setAdapter(mAdapter);
        mHolder.viewPager.setCurrentItem(0);
        mHolder.viewPager.setOffscreenPageLimit(3);
        mHolder.tabLayout.setupWithViewPager(mHolder.viewPager);
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new GameHallRankViewHolder(view);
    }

    @Override
    public int getContentItemsTotal() {
        return 1;
    }

    private static class GameHallRankViewHolder extends SectionedRecyclerViewAdapter.EmptyViewHolder{
        @BindView(R.id.viewPager)
        ViewPager viewPager;
        @BindView(R.id.tab_layout)
        TabLayout tabLayout;
        public GameHallRankViewHolder(View view){
            super(view);
        }
    }
}
