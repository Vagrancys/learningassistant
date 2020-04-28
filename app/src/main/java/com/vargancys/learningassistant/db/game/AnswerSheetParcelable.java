package com.vargancys.learningassistant.db.game;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class AnswerSheetParcelable implements Parcelable {
    private ArrayList<GameAnswerSheetBean> mBean;

    public AnswerSheetParcelable(){

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

    }


}
