package com.vargancys.learningassistant.model.ladder;

import android.app.DownloadManager;

import com.vagrancys.learningassistant.db.DaoSession;
import com.vagrancys.learningassistant.db.LadderCommentBeanDao;
import com.vagrancys.learningassistant.db.LadderDataBeanDao;
import com.vagrancys.learningassistant.db.LadderTopicBeanDao;
import com.vargancys.learningassistant.base.BaseApplication;
import com.vargancys.learningassistant.db.ladder.LadderCommentBean;
import com.vargancys.learningassistant.db.ladder.LadderDataBean;
import com.vargancys.learningassistant.db.ladder.LadderTopicBean;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.TimeUtils;

import java.sql.Time;
import java.util.List;

/**
 * @author Vagrancy
 * @date 2020/5/5
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 天梯数据请求中心
 */
public class LadderRequest {
    private static LadderRequest mRequest;
    private DaoSession mDaoSession;
    private LadderCommentBeanDao mCommentDao;
    private LadderDataBeanDao mDataDao;
    private LadderTopicBeanDao mTopicDao;
    private LadderRequest(){
        mDaoSession = BaseApplication.getInstance().getDaoSession();
        mCommentDao = mDaoSession.getLadderCommentBeanDao();
        mDataDao = mDaoSession.getLadderDataBeanDao();
        mTopicDao = mDaoSession.getLadderTopicBeanDao();
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
        return mDataDao.load(ladderId);
    }

    public List<LadderTopicBean> getLadderAllTopicItem(int highest) {
        return null;
    }

    public void saveLadderData(long ladderId) {

    }

    //保持评论数据
    public boolean saveCommentData(int current,String comment) {
        LadderCommentBean mBean = new LadderCommentBean();
        mBean.setAuthor(0);
        mBean.setAvatar("");
        mBean.setLevel("初级攀登者");
        mBean.setPraise(0);
        mBean.setStep(0);
        mBean.setCurrent(current);
        mBean.setComment(comment);
        mBean.setTime(TimeUtils.getTime());
        mBean.setReply_count(0);
        mBean.setFloor(0);
        long commentId = mCommentDao.insert(mBean);
        if(commentId == 0){
            return false;
        }else{
            return true;
        }
    }

    //刷新评论数据
    public List<LadderCommentBean> refreshCommentData(int mCurrent) {
        return mCommentDao.queryBuilder().where(LadderCommentBeanDao.Properties.Current.eq(mCurrent)).list();
    }

    //更新赞的数据
    public void updatePraiseData(Long commentId, boolean state) {
        LadderCommentBean mBean = mCommentDao.load(commentId);
        if(state){
            mBean.setPraise(mBean.getPraise()+1);
        }else{
            mBean.setPraise(mBean.getPraise()-1);
        }
        mCommentDao.update(mBean);
    }

    //更新踩的数据
    public void updateStepData(Long commentId,boolean state){
        LadderCommentBean mBean = mCommentDao.load(commentId);
        if(state){
            mBean.setStep(mBean.getStep()+1);
        }else{
            mBean.setStep(mBean.getStep()-1);
        }
        mCommentDao.update(mBean);
    }
}
