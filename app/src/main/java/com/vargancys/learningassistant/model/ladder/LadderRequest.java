package com.vargancys.learningassistant.model.ladder;

import android.app.DownloadManager;

import com.vagrancys.learningassistant.db.DaoSession;
import com.vagrancys.learningassistant.db.LadderCommentBeanDao;
import com.vagrancys.learningassistant.db.LadderCommentReplyBeanDao;
import com.vagrancys.learningassistant.db.LadderDataBeanDao;
import com.vagrancys.learningassistant.db.LadderDifficultyCommentBeanDao;
import com.vagrancys.learningassistant.db.LadderTopicBeanDao;
import com.vargancys.learningassistant.base.BaseApplication;
import com.vargancys.learningassistant.db.ladder.LadderCommentBean;
import com.vargancys.learningassistant.db.ladder.LadderCommentReplyBean;
import com.vargancys.learningassistant.db.ladder.LadderDataBean;
import com.vargancys.learningassistant.db.ladder.LadderDifficultyCommentBean;
import com.vargancys.learningassistant.db.ladder.LadderTopicBean;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.TimeUtils;

import org.greenrobot.greendao.query.QueryBuilder;

import java.sql.Time;
import java.util.ArrayList;
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
    private LadderCommentReplyBeanDao mCommentReplyDao;
    private LadderDifficultyCommentBeanDao mDifficultyCommentDao;
    private LadderRequest(){
        mDaoSession = BaseApplication.getInstance().getDaoSession();
        mCommentDao = mDaoSession.getLadderCommentBeanDao();
        mDataDao = mDaoSession.getLadderDataBeanDao();
        mTopicDao = mDaoSession.getLadderTopicBeanDao();
        mCommentReplyDao = mDaoSession.getLadderCommentReplyBeanDao();
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

    //获取单个评论数据
    public LadderCommentBean getLadderCommentData(long commentId) {
        return mCommentDao.load(commentId);
    }

    //获取评论下的所有回复
    public List<LadderCommentReplyBean> getLadderCommentReplyAllData(long commentId) {
        QueryBuilder<LadderCommentReplyBean> mBean = mCommentReplyDao.queryBuilder();
        return mBean.where(LadderCommentReplyBeanDao.Properties.CommentId.eq(commentId)).list();
    }

    //保存回复评论数据
    public boolean saveCommentReplyData(long comment, String content) {
        LadderCommentBean mBean = mCommentDao.load(comment);
        mBean.setReply_count(mBean.getReply_count()+1);
        mCommentDao.update(mBean);
        LadderCommentReplyBean mReplyBean = new LadderCommentReplyBean();
        mReplyBean.setAuthor(1);
        mReplyBean.setAuthor_title("11");
        mReplyBean.setAvatar("11");
        mReplyBean.setComment(content);
        mReplyBean.setLevel("初级攀登者");
        mReplyBean.setCommentId(comment);
        mReplyBean.setTime(TimeUtils.getTime());
        long result =mCommentReplyDao.insert(mReplyBean);
        if(result != 0){
            return true;
        }else{
            return false;
        }
    }

    //保持难度评论的数据
    public boolean saveDifficultyData(int selectDifficulty, String data) {
        LadderDifficultyCommentBean mBean = new LadderDifficultyCommentBean();
        mBean.setAuthor(0);
        mBean.setAuthor_title("你好");
        mBean.setComment(data);
        mBean.setTime(TimeUtils.getTime());
        mBean.setType(selectDifficulty);
        long result =mDifficultyCommentDao.insert(mBean);
        if(result != 0){
            return true;
        }else{
            return false;
        }
    }
}
