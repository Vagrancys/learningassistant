package com.vargancys.learningassistant.model.game.request;

import android.util.Log;

import com.vagrancys.learningassistant.db.DaoSession;
import com.vagrancys.learningassistant.db.GameContentDao;
import com.vagrancys.learningassistant.db.GameFillItemDao;
import com.vagrancys.learningassistant.db.GameMultipleItemDao;
import com.vagrancys.learningassistant.db.GameRadioItemDao;
import com.vagrancys.learningassistant.db.GameSignContentDao;
import com.vagrancys.learningassistant.db.GameSubjectContentDao;
import com.vagrancys.learningassistant.db.GameSubjectItemDao;
import com.vagrancys.learningassistant.db.GameSubjectiveItemDao;
import com.vagrancys.learningassistant.db.OverViewListContentDao;
import com.vagrancys.learningassistant.db.OverViewListItemDao;
import com.vargancys.learningassistant.base.BaseApplication;
import com.vargancys.learningassistant.bean.game.GameAnswerSheetBean;
import com.vargancys.learningassistant.bean.game.GameConfigUtils;
import com.vargancys.learningassistant.bean.game.GameContent;
import com.vargancys.learningassistant.bean.game.GameFillItem;
import com.vargancys.learningassistant.bean.game.GameMultipleItem;
import com.vargancys.learningassistant.bean.game.GameRadioItem;
import com.vargancys.learningassistant.bean.game.GameSignContent;
import com.vargancys.learningassistant.bean.game.GameStartContent;
import com.vargancys.learningassistant.bean.game.GameSubjectContent;
import com.vargancys.learningassistant.bean.game.GameSubjectItem;
import com.vargancys.learningassistant.bean.game.GameSubjectiveItem;
import com.vargancys.learningassistant.bean.overview.OverViewListContent;
import com.vargancys.learningassistant.bean.overview.OverViewListItem;
import com.vargancys.learningassistant.model.game.bean.GameHallBean;
import com.vargancys.learningassistant.model.game.bean.GameHallRankBean;
import com.vargancys.learningassistant.presenter.game.BaseGamePresenter;
import com.vargancys.learningassistant.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/10
 * version:1.0
 */
public class BaseGameRequest {
    private static String TAG = "BaseGameRequest";
    private static BaseGameRequest mRequest;
    private DaoSession mDaoSession;
    private GameContentDao mGameContentDao;
    private OverViewListItemDao mListItemDao;
    private OverViewListContentDao mListContentDao;
    private GameSignContentDao mSignContentDao;
    private GameSubjectContentDao mSubjectContentDao;
    private GameSubjectItemDao mSubjectItemDao;
    private GameRadioItemDao mRadioItemDao;
    private GameMultipleItemDao mMultipleItemDao;
    private GameFillItemDao mFillItemDao;
    private GameSubjectiveItemDao mSubjectiveItemDao;
    private OverViewListItemDao mOverViewItemDao;

    private BaseGameRequest(){
        mDaoSession = BaseApplication.getInstance().getDaoSession();
        mGameContentDao = mDaoSession.getGameContentDao();
        mListItemDao = mDaoSession.getOverViewListItemDao();
        mListContentDao = mDaoSession.getOverViewListContentDao();
        mSignContentDao = mDaoSession.getGameSignContentDao();
        mSubjectContentDao = mDaoSession.getGameSubjectContentDao();
        mSubjectItemDao = mDaoSession.getGameSubjectItemDao();
        mRadioItemDao = mDaoSession.getGameRadioItemDao();
        mMultipleItemDao = mDaoSession.getGameMultipleItemDao();
        mFillItemDao = mDaoSession.getGameFillItemDao();
        mSubjectiveItemDao = mDaoSession.getGameSubjectiveItemDao();
        mOverViewItemDao = mDaoSession.getOverViewListItemDao();
    }

    public static BaseGameRequest getInstance(){
        if(mRequest == null){
            synchronized (BaseGameRequest.class){
                if(mRequest == null){
                    mRequest = new BaseGameRequest();
                }
            }
        }
        return mRequest;
    }

    public GameContent getGameListData(long gameId) {
        return mGameContentDao.load(gameId);
    }

    public List<OverViewListItem> getGameListBean(long overviewId) {
        return mListItemDao.queryBuilder().where(OverViewListItemDao.Properties.ContentId.eq(overviewId)).list();
    }

    public List<OverViewListContent> getGameSelectListData() {
        return mListContentDao.loadAll();
    }

    public long saveGameContentData(GameContent gameContent) {
        return mGameContentDao.insert(gameContent);
    }

    //判断关卡是否已经创建了 有返回true 没有返回false
    public long isOverViewEmpty(long overviewId) {
        GameContent gameContent =mGameContentDao.queryBuilder().where(GameContentDao.Properties.OverviewId.eq(overviewId)).unique();
        if(gameContent !=null){
            return gameContent.getId();
        }else{
            return 0;
        }
    }


    public OverViewListContent getOverViewListContentData(long overviewId) {
        return mListContentDao.load(overviewId);
    }


    public List<GameSignContent> getGameSignAllData() {
        return mSignContentDao.loadAll();
    }

    public GameContent getGameContentData(long gameId) {
        return mGameContentDao.load(gameId);
    }

    public boolean saveSignData(GameSignContent content) {
        long id =mSignContentDao.insert(content);
        if(id != 0){
            return true;
        }else{
            return false;
        }
    }

    public GameSubjectContent getSubjectContentData(long knowId) {
        return mSubjectContentDao.queryBuilder().where(GameSubjectContentDao.Properties.KnowId.eq(knowId)).unique();
    }


    public List<GameSubjectItem> getSubjectItemData(Long id) {
        return mSubjectItemDao.queryBuilder().where(GameSubjectItemDao.Properties.SubjectId.eq(id)).list();
    }

    public long saveSubjectItemData(GameSubjectItem mItem) {
        return mSubjectItemDao.insert(mItem);
    }

    public long saveGameRadioItemData(GameRadioItem mRadio,long subjectId,long gameId) {
        updateContent(subjectId,gameId);
        return mRadioItemDao.insert(mRadio);
    }

    private void updateContent(long subjectId,long gameId) {
        GameContent gameContent = mGameContentDao.load(gameId);
        gameContent.setSubject(gameContent.getSubject()+1);
        mGameContentDao.update(gameContent);
        GameSubjectContent gameSubjectContent = mSubjectContentDao.load(subjectId);
        gameSubjectContent.setProblem(gameSubjectContent.getProblem()+1);
        mSubjectContentDao.update(gameSubjectContent);
    }

    public long saveGameMultipleItemData(GameMultipleItem mMultiple,long subjectId,long gameId) {
        updateContent(subjectId,gameId);
        return mMultipleItemDao.insert(mMultiple);
    }

    public long saveGameFillItemData(GameFillItem mFill,long subjectId,long gameId) {
        updateContent(subjectId,gameId);
        return mFillItemDao.insert(mFill);
    }

    public long saveGameSubjectiveItemData(GameSubjectiveItem mSubjective,long subjectId,long gameId) {
        updateContent(subjectId,gameId);
        return mSubjectiveItemDao.insert(mSubjective);
    }

    //关卡中心的id
    public void getGameStartAllData(final long gameId, final BaseGamePresenter.TidyAllData tidyAllData) {
        new Thread(() -> {
            List<OverViewListItem> mOverViews =  getGameListBean(getGameListData(gameId).getOverviewId());
            List<GameStartContent> mStarts = new ArrayList<>();
            int type = GameConfigUtils.CONFIG_TYPE;
            boolean isError = GameConfigUtils.CONFIG_CONTAIN;
            int difficulty = GameConfigUtils.CONFIG_DIFFICULTY;
            boolean isRepeat = GameConfigUtils.CONFIG_REPEAT;
            for (OverViewListItem mItem:mOverViews) {
                long SubjectId = getSubjectContentData(mItem.getId()).getId();
                List<GameSubjectItem> mSubjects = mSubjectItemDao
                        .queryBuilder()
                        .where(GameSubjectItemDao.Properties.SubjectId.eq(SubjectId))
                        .limit(GameConfigUtils.CONFIG_SINGLE)
                        .list();
                Log.e(TAG,"mSubjects ="+mSubjects.size());
                for(GameSubjectItem mSubject :mSubjects){
                    if(isError&&mSubject.getIsError()){
                        if(!isRepeat&&mSubject.getIsRepeat()){
                            if(difficulty <= mSubject.getLevel()){
                                GameStartContent mStart = new GameStartContent();
                                mStart.setStart_id(mSubject.getId());
                                mStart.setGame_id(gameId);
                                mStart.setContent_id(SubjectId);
                                if(type == 0){
                                    switch (mSubject.getSelect()){
                                        case 1:
                                            initRadio(mSubject, mStart);
                                            break;
                                        case 2:
                                            initMultiple(mSubject, mStart);
                                            break;
                                        case 3:
                                            initFill(mSubject, mStart);
                                            break;
                                        case 4:
                                            initSubjective(mSubject, mStart);
                                            break;
                                    }
                                }else if(type == 1){
                                    initRadio(mSubject,mStart);
                                }else if(type == 2){
                                    initMultiple(mSubject,mStart);
                                }else if(type == 3){
                                    initFill(mSubject,mStart);
                                }else if(type == 4){
                                    initSubjective(mSubject,mStart);
                                }
                                mStarts.add(mStart);
                            }
                        }
                    }
                }
            }
            Log.e("looper","size ="+mStarts.size());



            if(mStarts.size() > 0){
                int size = GameConfigUtils.CONFIG_NUMBER;
                if(size>mStarts.size()){
                    size = mStarts.size();
                }
                Random mRandom = new Random();
                List<GameStartContent> mFinishStart = new ArrayList<>();
                for (int i = 0; i < size; i++){
                    int length = mRandom.nextInt(mStarts.size());
                    mFinishStart.add(mStarts.get(length));
                    mStarts.remove(length);
                }
                tidyAllData.showFinish(mFinishStart);
            }else{
                tidyAllData.showError(404,"没有找到合适的答题项!");
            }
        }).start();
    }

    //添加单选到闯关开始
    private void initRadio(GameSubjectItem mSubject, GameStartContent mStart) {
        GameRadioItem mRadio = mSubject.getRadioItem();
        //处理单选需要的数据
        mStart.setType(1);
        mStart.setRadio_title(mRadio.getTitle());
        mStart.setRadio_yes(mRadio.getYes());
        mStart.setRadio_first_title(mRadio.getFirst_title());
        mStart.setRadio_second_title(mRadio.getSecond_title());
        mStart.setRadio_third_title(mRadio.getThird_title());
        mStart.setRadio_fourth_title(mRadio.getFourth_title());
    }

    //添加多选到闯关开始
    private void initMultiple(GameSubjectItem mSubject, GameStartContent mStart) {
        GameMultipleItem mMultiple = mSubject.getMultipleItem();
        mStart.setType(2);
        mStart.setMultiple_title(mMultiple.getTitle());
        mStart.setMultiple_first_title(mMultiple.getFirst_title());
        mStart.setMultiple_first_answer(mMultiple.getFirst_answer());
        mStart.setMultiple_second_title(mMultiple.getSecond_title());
        mStart.setMultiple_second_answer(mMultiple.getSecond_answer());
        mStart.setMultiple_third_title(mMultiple.getThird_title());
        mStart.setMultiple_third_answer(mMultiple.getThird_answer());
        mStart.setMultiple_fourth_title(mMultiple.getFourth_title());
        mStart.setMultiple_fourth_answer(mMultiple.getFourth_answer());
    }

    //添加填空到闯关开始
    private void initFill(GameSubjectItem mSubject, GameStartContent mStart) {
        GameFillItem mFill = mSubject.getFillItem();
        mStart.setType(3);
        mStart.setFill_title(mFill.getTitle());
        mStart.setFill_answer(mFill.getAnswer());
        mStart.setFill_first_title(mFill.getFirst_answer());
        mStart.setFill_second_title(mFill.getSecond_answer());
        mStart.setFill_third_title(mFill.getThird_answer());
        mStart.setFill_fourth_title(mFill.getFourth_answer());
    }

    //添加主观到闯关开始
    private void initSubjective(GameSubjectItem mSubject, GameStartContent mStart) {
        GameSubjectiveItem  mSubjective= mSubject.getSubjectiveItem();
        mStart.setType(4);
        mStart.setSubjective_title(mSubjective.getTitle());
        mStart.setSubjective_answer(mSubjective.getAnswer());
    }

    public boolean updateAnswerSheetData(long gameId,ArrayList<GameAnswerSheetBean> mBean) {
        int gameError = 0;
        int contentError = 0;
        int contentAnswer = 0;
        int size = mBean.size();
        for (int j = 0;j<size;j++){
            GameAnswerSheetBean bean = mBean.get(j);
            GameSubjectItem mItem = mSubjectItemDao.load(bean.getAnswer_id());
            mItem.setIsRepeat(true);
            mItem.setIsError(bean.isWin());
            if(j==0){
                if(!bean.isWin()){
                    contentError++;
                }else{
                    contentAnswer++;
                }
            }else if(j == size-1){
                GameSubjectContent mContent = mSubjectContentDao.load(mBean.get(j).getContent_id());
                mContent.setLast_time(TimeUtils.getTime());
                mContent.setAnswer(mContent.getAnswer()+contentAnswer);
                mContent.setError(mContent.getError()+contentError);
                contentError = 0;
                contentAnswer = 0;
            }else{
                if(!bean.isWin()){
                    contentError++;
                }else{
                    contentAnswer++;
                }
                if(!mBean.get(j).getContent_id().equals(mBean.get(j-1).getContent_id())){
                    GameSubjectContent mContent = mSubjectContentDao.load(mBean.get(j-1).getContent_id());
                    mContent.setLast_time(TimeUtils.getTime());
                    mContent.setAnswer(mContent.getAnswer()+contentAnswer);
                    mContent.setError(mContent.getError()+contentError);
                    contentError = 0;
                    contentAnswer = 0;
                }
            }
            mSubjectItemDao.update(mItem);
            if(!bean.isWin()){
                gameError++;
            }
        }
        //修改闯关中心的错题数
        GameContent gameContent = mGameContentDao.load(gameId);
        gameContent.setError(gameContent.getError()+gameError);
        gameContent.setError_current(gameContent.getError_current()+gameError);
        mGameContentDao.update(gameContent);
        return true;
    }

    //选择关卡大厅数据
    public boolean selectHallData(long hallId) {
        //TODO 选择关卡大厅数据
        return true;
    }

    //得到知识体系大厅的数据
    public GameHallBean getGameHallData() {
        //TODO 知识大厅数据
        return null;
    }


    //添加关注到个人体系中
    public boolean insertGameData(long gameId) {
        //TODO 没处理好
        return true;
    }

    //根据难度来排序关卡体系大厅数据
    public List<GameHallRankBean> getHallDifferentData() {
        //TODO 没处理

        return null;
    }

    //根据质量来排序关卡体系大厅数据
    public List<GameHallRankBean> getHallSpecialityData() {
        //TODO 没处理
        return null;
    }

    //根据数量来排序关卡体系大厅数据
    public List<GameHallRankBean> getHallHeightData() {
        //TODO 没处理
        return null;
    }
}
