package com.vargancys.learningassistant.model.ladder.request;

import com.vagrancys.learningassistant.db.DaoSession;
import com.vagrancys.learningassistant.db.GameSubjectItemDao;
import com.vagrancys.learningassistant.db.LadderCommentBeanDao;
import com.vagrancys.learningassistant.db.LadderCommentReplyBeanDao;
import com.vagrancys.learningassistant.db.LadderDataBeanDao;
import com.vagrancys.learningassistant.db.LadderDifficultyCommentBeanDao;
import com.vagrancys.learningassistant.db.LadderDifficultyDataBeanDao;
import com.vagrancys.learningassistant.db.LadderHelpBeanDao;
import com.vagrancys.learningassistant.db.LadderRankDataBeanDao;
import com.vagrancys.learningassistant.db.LadderTopicBeanDao;
import com.vargancys.learningassistant.base.BaseApplication;
import com.vargancys.learningassistant.db.game.GameFillItem;
import com.vargancys.learningassistant.db.game.GameMultipleItem;
import com.vargancys.learningassistant.db.game.GameRadioItem;
import com.vargancys.learningassistant.db.game.GameSubjectItem;
import com.vargancys.learningassistant.db.game.GameSubjectiveItem;
import com.vargancys.learningassistant.db.ladder.LadderCommentBean;
import com.vargancys.learningassistant.db.ladder.LadderCommentReplyBean;
import com.vargancys.learningassistant.db.ladder.LadderDataBean;
import com.vargancys.learningassistant.db.ladder.LadderDifficultyCommentBean;
import com.vargancys.learningassistant.db.ladder.LadderDifficultyDataBean;
import com.vargancys.learningassistant.db.ladder.LadderHelpBean;
import com.vargancys.learningassistant.db.ladder.LadderModeUtils;
import com.vargancys.learningassistant.db.ladder.LadderRankDataBean;
import com.vargancys.learningassistant.db.ladder.LadderTopicBean;
import com.vargancys.learningassistant.utils.CacheUtils;
import com.vargancys.learningassistant.utils.TimeUtils;

import org.greenrobot.greendao.query.QueryBuilder;

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
    private LadderDifficultyDataBeanDao mDifficultyDataDao;
    private LadderRankDataBeanDao mRankDataDao;
    private LadderHelpBeanDao mHelpDao;
    private GameSubjectItemDao mSubjectDao;
    private LadderRequest(){
        mDaoSession = BaseApplication.getInstance().getDaoSession();
        mCommentDao = mDaoSession.getLadderCommentBeanDao();
        mDataDao = mDaoSession.getLadderDataBeanDao();
        mTopicDao = mDaoSession.getLadderTopicBeanDao();
        mCommentReplyDao = mDaoSession.getLadderCommentReplyBeanDao();
        mDifficultyCommentDao = mDaoSession.getLadderDifficultyCommentBeanDao();
        mDifficultyDataDao = mDaoSession.getLadderDifficultyDataBeanDao();
        mHelpDao = mDaoSession.getLadderHelpBeanDao();
        mRankDataDao = mDaoSession.getLadderRankDataBeanDao();
        mSubjectDao = mDaoSession.getGameSubjectItemDao();
    }

    public static LadderRequest getInstance(){
        if(mRequest == null){
            synchronized (LadderRequest.class){
                if(mRequest == null){
                    mRequest = new LadderRequest();
                }
            }
        }
        return mRequest;
    }

    //获得天梯数据
    public LadderDataBean getLadderData(long ladderId) {
        return mDataDao.load(ladderId);
    }

    //得到天梯挑战的题目
    public List<LadderTopicBean> getLadderAllTopicItem(int difficulty,int highest) {
        QueryBuilder<GameSubjectItem> mSubject = mSubjectDao.queryBuilder();
        List<GameSubjectItem> mItems;
        List<LadderTopicBean> mBeans = new ArrayList<>();
        if(difficulty == 0){
            mItems = mSubject.limit(LadderModeUtils.CONFIG_NUMBER).list();
        }else{
            mItems = mSubject.where(GameSubjectItemDao.Properties.Level.eq(difficulty)).limit(LadderModeUtils.CONFIG_NUMBER).list();
        }
        if(mItems != null &&mItems.size() > 0){
            int length = 0;
            if(mItems.size()> highest){
                length = highest-1;
            }else{
                length = 0;
            }
            for(int i = length;i<mItems.size();i++){
                GameSubjectItem mItem = mItems.get(i);
                LadderTopicBean mBean = new LadderTopicBean();
                switch (mItem.getSelect()){
                    case 1:
                        initRadio(mItem, mBean);
                        break;
                    case 2:
                        initMultiple(mItem, mBean);
                        break;
                    case 3:
                        initFill(mItem, mBean);
                        break;
                    case 4:
                        initSubjective(mItem, mBean);
                        break;
                }
                mBeans.add(mBean);
            }
            return mBeans;
        }
        //TODO 天梯所有问题
        return null;
    }

    //添加单选到天梯开始
    private void initRadio(GameSubjectItem mSubject, LadderTopicBean mBean) {
        GameRadioItem mRadio = mSubject.getRadioItem();
        //处理单选需要的数据
        mBean.setType(1);
        mBean.setRadio_title(mRadio.getTitle());
        mBean.setRadio_answer(mRadio.getYes());
        mBean.setRadio_first_answer(mRadio.getFirst_title());
        mBean.setRadio_second_answer(mRadio.getSecond_title());
        mBean.setRadio_third_answer(mRadio.getThird_title());
        mBean.setRadio_fourth_answer(mRadio.getFourth_title());
    }

    //添加多选到天梯开始
    private void initMultiple(GameSubjectItem mSubject, LadderTopicBean mBean) {
        GameMultipleItem mMultiple = mSubject.getMultipleItem();
        mBean.setType(2);
        mBean.setMultiple_title(mMultiple.getTitle());
        mBean.setMultiple_first_title(mMultiple.getFirst_title());
        mBean.setMultiple_first_answer(mMultiple.getFirst_answer());
        mBean.setMultiple_second_title(mMultiple.getSecond_title());
        mBean.setMultiple_second_answer(mMultiple.getSecond_answer());
        mBean.setMultiple_third_title(mMultiple.getThird_title());
        mBean.setMultiple_third_answer(mMultiple.getThird_answer());
        mBean.setMultiple_fourth_title(mMultiple.getFourth_title());
        mBean.setMultiple_fourth_answer(mMultiple.getFourth_answer());
    }

    //添加填空到天梯开始
    private void initFill(GameSubjectItem mSubject, LadderTopicBean mBean) {
        GameFillItem mFill = mSubject.getFillItem();
        mBean.setType(3);
        mBean.setFill_title(mFill.getTitle());
        mBean.setFill_answer(mFill.getAnswer());
        mBean.setFill_first_answer(mFill.getFirst_answer());
        mBean.setFill_second_answer(mFill.getSecond_answer());
        mBean.setFill_third_answer(mFill.getThird_answer());
        mBean.setFill_fourth_answer(mFill.getFourth_answer());
    }

    //添加主观到天梯开始
    private void initSubjective(GameSubjectItem mSubject, LadderTopicBean mBean) {
        GameSubjectiveItem mSubjective= mSubject.getSubjectiveItem();
        mBean.setType(4);
        mBean.setSubjective_title(mSubjective.getTitle());
        mBean.setSubjective_answer(mSubjective.getAnswer());
    }

    //保存天梯数据
    public void saveLadderData(LadderDataBean bean) {
        //TODO 天梯数据
        mDataDao.update(bean);
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

    //得到难度区详情数据
    public LadderDifficultyDataBean getLadderDetailsData(int difficultyType) {
        return mDifficultyDataDao.queryBuilder().where(LadderDifficultyDataBeanDao.Properties.Type.eq(difficultyType)).unique();
    }

    //得到难度区所有的评论数据
    public List<LadderDifficultyCommentBean> getLadderCommentAllData(int difficultyType) {
        return mDifficultyCommentDao.queryBuilder().where(LadderDifficultyCommentBeanDao.Properties.Type.eq(difficultyType)).list();
    }

    //等到所有的帮助数据
    public List<LadderHelpBean> getLadderHelpAllData() {
        return mHelpDao.loadAll();
    }

    //得到帮助详情数据
    public LadderHelpBean getLadderHelpDetailsData(long helpId) {
        return mHelpDao.load(helpId);
    }

    //获取排行数据
    public List<LadderRankDataBean> getLadderZoneRankData(int zoneId) {
        List<LadderRankDataBean> mBean;
        QueryBuilder<LadderRankDataBean> mQuery = mRankDataDao.queryBuilder();
        if(zoneId == 0){
            mBean = mQuery.list();
        }else{
            mBean = mQuery.where(LadderRankDataBeanDao.Properties.Zone.eq(zoneId)).list();
        }
        return mBean;
    }

    //第一次天梯排名创建用户数据
    public long insertLadderData() {
        LadderDataBean mBean = new LadderDataBean();
        mBean.setTitle("入门级攀登者");
        mBean.setTitle_level(0);
        mBean.setUpgrade(500);
        mBean.setUpgrade_total(0);
        mBean.setDifficulty("1级");
        mBean.setHighest(1);
        mBean.setFail(0);
        mBean.setTime(0);
        mBean.setTotal_time(0);
        mBean.setTotal(0);
        mBean.setMaster(0);
        mBean.setChance("0%");
        return mDataDao.insert(mBean);
    }
}
