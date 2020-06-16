package com.vargancys.learningassistant.module.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.db.home.HomeKnowFunction;
import com.vargancys.learningassistant.db.home.HomeKnowHistoryFunction;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/09
 * version:1.0
 * 首页历史第二适配器
 */
public class HomeHistorySecondAdapter extends BaseRecyclerAdapter {
    private List<HomeKnowHistoryFunction> homeKnowFunctions;
    private Context mContext;
    private String[] mCommon = {"不认识","偶尔","经常","已遗忘"};
    public HomeHistorySecondAdapter(Context context, List<HomeKnowHistoryFunction> homeKnowFunctions){
        this.mContext = context;
        this.homeKnowFunctions = homeKnowFunctions;
    }

    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new KnowSecondViewHolder(getView(mContext, R.layout.know_function_second_item));
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        KnowSecondViewHolder mHolder = (KnowSecondViewHolder) holder;
        HomeKnowHistoryFunction homeKnowHistoryFunction = homeKnowFunctions.get(position);
        mHolder.functionCommon.setText(mCommon[homeKnowHistoryFunction.getCommon()]);
        mHolder.functionTitle.setText(homeKnowHistoryFunction.getTitle());
        mHolder.functionSummary.setText(homeKnowHistoryFunction.getSummary());
        mHolder.functionExplain.setText(homeKnowHistoryFunction.getExplain());
    }

    @Override
    public int getItemCount() {
        return homeKnowFunctions.size();
    }

    public class KnowSecondViewHolder extends CommonViewHolder{
        @BindView(R.id.item_function_common_second)
        TextView functionCommon;
        @BindView(R.id.item_function_title_second)
        TextView functionTitle;
        @BindView(R.id.item_function_summary_second)
        TextView functionSummary;
        @BindView(R.id.item_function_explain_second)
        TextView functionExplain;
        private KnowSecondViewHolder(View view){
            super(view);
            ButterKnife.bind(view);
        }
    }
}
