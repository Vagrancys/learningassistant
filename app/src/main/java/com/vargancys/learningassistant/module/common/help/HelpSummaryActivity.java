package com.vargancys.learningassistant.module.common.help;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.db.common.HelpCommendItem;
import com.vargancys.learningassistant.db.common.HelpContentItem;
import com.vargancys.learningassistant.module.common.adapter.HelpCommendAdapter;
import com.vargancys.learningassistant.module.common.view.HelpSummaryView;
import com.vargancys.learningassistant.persenter.common.help.HelpSummaryPresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/07
 * version:1.0
 */
public class HelpSummaryActivity extends BaseActivity implements HelpSummaryView{
    @BindView(R.id.common_back)
    ImageView commonBack;
    @BindView(R.id.common_update)
    ImageView commonUpdate;
    @BindView(R.id.help_number)
    TextView helpNumber;
    @BindView(R.id.help_title)
    TextView helpTitle;
    @BindView(R.id.help_summary)
    TextView helpSummary;
    @BindView(R.id.help_time)
    TextView helpTime;
    @BindView(R.id.help_praise)
    ImageView helpPraise;
    @BindView(R.id.help_praise_number)
    TextView helpPraiseNumber;
    @BindView(R.id.help_poor)
    ImageView helpPoor;
    @BindView(R.id.help_poor_number)
    TextView helpPoorNumber;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.commend_linear)
    LinearLayout commendLinear;
    @BindView(R.id.commend_edit)
    EditText commendEdit;
    @BindView(R.id.commend_send)
    ImageView commendSend;
    @BindView(R.id.commend_number_text)
    TextView commendNumberText;


    private HelpSummaryPresenter helpSummaryPresenter;
    private HelpCommendAdapter helpCommendAdapter;
    private int Request = 2001;
    private int help_summary_id;
    private int commendNumber;
    private String TAG = "HelpSummaryActivity";

    @Override
    public int getLayoutId() {
        return R.layout.activity_help_summary;
    }

    @Override
    public void initView() {
        help_summary_id = getIntent().getIntExtra(ConstantsUtils.HELP_SUMMARY_ID,0);
        helpSummaryPresenter = new HelpSummaryPresenter(this);
        helpSummaryPresenter.getHelpData(help_summary_id);
        helpSummaryPresenter.getCommendData(help_summary_id);
        initListener();
    }

    private void initListener() {
        commonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        commonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HelpUpdateActivity.launch(HelpSummaryActivity.this,help_summary_id,Request);
            }
        });

        helpPraise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(helpPraise.isSelected()){
                    helpPraise.setSelected(false);
                    helpSummaryPresenter.addPraiseOrPoor(0,help_summary_id);
                }else{
                    helpPraise.setSelected(true);
                    helpSummaryPresenter.subPraiseOrPoor(0,help_summary_id);
                }
            }
        });

        helpPoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(helpPraise.isSelected()){
                    helpPraise.setSelected(false);
                    helpSummaryPresenter.addPraiseOrPoor(1,help_summary_id);
                }else{
                    helpPraise.setSelected(true);
                    helpSummaryPresenter.subPraiseOrPoor(1,help_summary_id);
                }
            }
        });

        commendEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    commendEdit.setMinLines(5);
                }else{
                    commendEdit.setMinLines(1);
                }
            }
        });

        commendSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helpSummaryPresenter.saveCommendData(help_summary_id,commendEdit.getText().toString());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Request
                &&data !=null){
            switch (data.getIntExtra(ConstantsUtils.HELP_UPDATE_STATE,0)){
                case 0:
                    Log.e(TAG,"没有修改该帮助!");
                    break;
                case 1:
                    helpSummaryPresenter.reFreshSummary(help_summary_id);
                    break;
                case 2:
                    Log.e(TAG,"修改该帮助失败了!");
                    break;
            }
        }
    }

    public static void launch(Activity activity, int position) {
        Intent intent = new Intent(activity, HelpSummaryActivity.class);
        intent.putExtra(ConstantsUtils.HELP_SUMMARY_ID, position);
        activity.startActivity(intent);
    }

    private void updateData(Object object){
        HelpContentItem helpContentItem = (HelpContentItem) object;
        helpNumber.setText(helpContentItem.getNumber());
        helpTitle.setText(helpContentItem.getTitle());
        helpSummary.setText(helpContentItem.getSummary());
        helpTime.setText(helpContentItem.getTime());
        helpPraiseNumber.setText(helpContentItem.getPraise());
        helpPoorNumber.setText(helpContentItem.getPoor());
        commendNumber = helpContentItem.getCommendItems().size();
        commendNumberText.setText("("+commendNumber+")");
    }

    @Override
    public void findFinish(HelpContentItem object) {
        updateData(object);
    }

    @Override
    public void findError(int error, String msg) {
        ToastUtils.ToastText(getContext(),"Error = "+error+",Msg = "+msg);
    }

    @Override
    public void findCommendError(int error, String msg) {
        ToastUtils.ToastText(getContext(),"Error = "+error+"Msg = "+msg);
    }

    @Override
    public void findCommend(List<HelpCommendItem> items) {
        recyclerView.setVisibility(View.VISIBLE);
        commendLinear.setVisibility(View.GONE);
        helpCommendAdapter = new HelpCommendAdapter(getContext(),items);
        recyclerView.setAdapter(helpCommendAdapter);
    }

    @Override
    public void findEmpty() {
        recyclerView.setVisibility(View.GONE);
        commendLinear.setVisibility(View.VISIBLE);
    }

    @Override
    public void reFreshSummary(HelpContentItem item) {
        updateData(item);
    }

    @Override
    public void PraiseOrPoor(int state,int number,String msg) {
        switch (state){
            case 0:
                helpPraiseNumber.setText(number);
                break;
            case 1:
                helpPoorNumber.setText(number);
                break;
        }
        ToastUtils.ToastText(getContext(),msg+"成功!");
    }

    @Override
    public void saveCommendFinish() {
        ToastUtils.ToastText(getContext(),"发表评论成功了!");
        helpSummaryPresenter.reFreshSummary(help_summary_id);
        commendNumberText.setText(commendNumber+1);
    }

    @Override
    public void saveCommendError(int error, String msg) {
        ToastUtils.ToastText(getContext(),"Error = "+error+", Msg = "+msg);
    }
}
