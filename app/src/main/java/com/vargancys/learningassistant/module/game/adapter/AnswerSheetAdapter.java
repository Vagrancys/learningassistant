package com.vargancys.learningassistant.module.game.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.db.game.GameAnswerSheetBean;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author Vagrancy
 * @date 2020/4/28
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description:
 */
public class AnswerSheetAdapter extends BaseRecyclerAdapter {
    private ArrayList<GameAnswerSheetBean> mBeans;
    private Context mContext;

    public AnswerSheetAdapter(Context context,ArrayList<GameAnswerSheetBean> mBean){
        this.mBeans = mBean;
        mContext = context;
    }
    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new AnswerSheetViewHolder(View.inflate(mContext, R.layout.item_answer_sheet,null));
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        AnswerSheetViewHolder mHolder = (AnswerSheetViewHolder) holder;
        GameAnswerSheetBean mBean = mBeans.get(position);
        if(mBean.isWin()){
            mHolder.llAnswerSheet.setBackgroundResource(R.drawable.answer_sheet_win_bg);
        }else{
            mHolder.llAnswerSheet.setBackgroundResource(R.drawable.answer_sheet_fail_bg);
        }
        mHolder.answerSheetTitle.setText(String.valueOf(position));
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return mBeans.size();
    }

    public class AnswerSheetViewHolder extends CommonViewHolder{
        @BindView(R.id.ll_answer_sheet)
        LinearLayout llAnswerSheet;
        @BindView(R.id.answer_sheet_title)
        TextView answerSheetTitle;
        public AnswerSheetViewHolder(View itemView){
            super(itemView);
        }
    }
}
