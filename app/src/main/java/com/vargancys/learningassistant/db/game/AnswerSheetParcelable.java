package com.vargancys.learningassistant.db.game;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class AnswerSheetParcelable implements Parcelable {
    //知识模块的id
    private long game_id;
    //知识总项的id
    private long content_id;
    //知识最低项的id
    private long start_id;

    private ArrayList<GameAnswerSheetBean> mBean;

    public AnswerSheetParcelable(){

    }

    public ArrayList<GameAnswerSheetBean> getBean() {
        return mBean;
    }

    public void setContent_id(long content_id) {
        this.content_id = content_id;
    }

    public void setGame_id(long game_id) {
        this.game_id = game_id;
    }

    public void setStart_id(long start_id) {
        this.start_id = start_id;
    }

    public long getContent_id() {
        return content_id;
    }

    public long getGame_id() {
        return game_id;
    }

    public long getStart_id() {
        return start_id;
    }

    public void setBean(ArrayList<GameAnswerSheetBean> mBean) {
        this.mBean = mBean;
    }

    public AnswerSheetParcelable(Parcel in) {
        if(mBean == null){
            mBean = new ArrayList<>();
        }
        in.readTypedList(mBean,GameAnswerSheetBean.CREATOR);
    }

    public static final Parcelable.Creator<AnswerSheetParcelable> CREATOR = new Creator<AnswerSheetParcelable>() {
        @Override
        public AnswerSheetParcelable createFromParcel(Parcel in) {
            return new AnswerSheetParcelable(in);
        }

        @Override
        public AnswerSheetParcelable[] newArray(int size) {
            return new AnswerSheetParcelable[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(mBean);
        parcel.writeLong(game_id);
        parcel.writeLong(content_id);
        parcel.writeLong(start_id);
    }


}
