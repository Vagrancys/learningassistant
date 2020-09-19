package com.vargancys.learningassistant.bean.game;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Vagrancy
 * @date 2020/4/28
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description:
 */
public class GameAnswerSheetBean implements Parcelable {
    private Long answer_id;
    private Long content_id;
    private boolean win = false;

    public GameAnswerSheetBean(Long id,boolean win,Long content_id){
        this.answer_id = id;
        this.win = win;
        this.content_id = content_id;
    }

    protected GameAnswerSheetBean(Parcel in) {
        answer_id = in.readLong();
        win = in.readByte() !=0;
        content_id = in.readLong();
    }

    public static final Creator<GameAnswerSheetBean> CREATOR = new Creator<GameAnswerSheetBean>() {
        @Override
        public GameAnswerSheetBean createFromParcel(Parcel in) {
            return new GameAnswerSheetBean(in);
        }

        @Override
        public GameAnswerSheetBean[] newArray(int size) {
            return new GameAnswerSheetBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(answer_id);
        parcel.writeByte((byte) (win? 1:0));
        parcel.writeLong(content_id);
    }

    public Long getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(Long answer_id) {
        this.answer_id = answer_id;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public void setContent_id(Long content_id) {
        this.content_id = content_id;
    }

    public Long getContent_id() {
        return content_id;
    }
}
