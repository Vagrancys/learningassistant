package com.vargancys.learningassistant.model.ladder;

import android.app.DownloadManager;

import com.vargancys.learningassistant.db.ladder.LadderDataBean;
import com.vargancys.learningassistant.db.ladder.LadderTopicBean;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2020/5/5
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description:
 */
public class LadderRequest {
    private static LadderRequest mRequest;

    private LadderRequest(){

    }

    public static LadderRequest getInstance(){
        if(mRequest != null){
            synchronized (LadderRequest.class){
                if(mRequest != null){
                    mRequest = new LadderRequest();
                }
            }
        }
        return mRequest;
    }


    public LadderDataBean getLadderData(long ladderId) {
        return null;
    }

    public List<LadderTopicBean> getLadderAllTopicItem(int highest) {
        return null;
    }

    public void saveLadderData(long ladderId) {

    }
}
