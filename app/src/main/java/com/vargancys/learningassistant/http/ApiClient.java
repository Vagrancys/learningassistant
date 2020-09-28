package com.vargancys.learningassistant.http;

import android.util.Log;

import com.vargancys.learningassistant.bean.common.HelpCommendItem;
import com.vargancys.learningassistant.bean.common.HelpContentBean;
import com.vargancys.learningassistant.bean.home.ArticleBean;
import com.vargancys.learningassistant.bean.home.ArticleDataBean;
import com.vargancys.learningassistant.bean.home.BookBean;
import com.vargancys.learningassistant.bean.home.BookDataBean;
import com.vargancys.learningassistant.bean.home.KnowLedgeBean;
import com.vargancys.learningassistant.model.common.bean.NoDataBean;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static String BASE_URI = "http://192.168.1.103:8080/";
    private static ApiClient apiClient;
    private static Retrofit mRetrofit;
    private ApiService mService;
    private ApiClient(){
        OkHttpClient.Builder okHttp = buildOkHttpClient();
        okHttp.retryOnConnectionFailure(true);
        okHttp.connectTimeout(5, TimeUnit.SECONDS);
        okHttp.readTimeout(5,TimeUnit.SECONDS);
        OkHttpClient client = okHttp.build();
        okHttp.addInterceptor((new HttpLoggingInterceptor(message -> Log.i("HttpLoggingInterceptor", message)).setLevel(HttpLoggingInterceptor.Level.BODY)));
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URI)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mService = mRetrofit.create(ApiService.class);
    }

    private static OkHttpClient.Builder buildOkHttpClient(){
        try {
            TrustManager[] trustManagers = buildTrustManagers();
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null,trustManagers,new SecureRandom());
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory,(X509TrustManager) trustManagers[0]);
            builder.hostnameVerifier(((hostname, session) -> true));
            return builder;
        }catch (NoSuchAlgorithmException | KeyManagementException e){
            e.printStackTrace();
            return new OkHttpClient.Builder();
        }
    }

    private static TrustManager[] buildTrustManagers(){
        return new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[]{};
                    }
                }
        };
    }

    public static ApiClient getInstance(){
        if(apiClient == null){
            synchronized (ApiClient.class){
                if(apiClient == null){
                    apiClient = new ApiClient();
                }
            }
        }
        return apiClient;
    }

    /**
     * 获取知识
     */
    public void getKnowledge(int page,int limit,MySubscriber<BaseBean<List<KnowLedgeBean>>> subscriber) {
        toSubscribe(mService.getKnowledge(page,limit), subscriber);
    }

    /**
     * 查询知识是否重复
     * @param title 校验知识
     * @param subscriber
     */
    public void queryKnowLedgeRepeat(String title, MySubscriber<BaseBean<NoDataBean>> subscriber){
        toSubscribe(mService.queryKnowLedgeRepeat(title),subscriber);
    }

    /**
     * 添加知识数据
     * @param knowLedge
     * @param subscriber
     */
    public void saveKnowLedge(Map<String,Object> knowLedge,MySubscriber<BaseBean<NoDataBean>> subscriber){
        toSubscribe(mService.saveKnowLedge(knowLedge),subscriber);
    }

    /**
     * 获取所有的帮助数据
     * @param subscriber
     */
    public void getAllHelpData(MySubscriber<BaseBean<List<HelpContentBean>>> subscriber){
        toSubscribe(mService.getAllHelpData(),subscriber);
    }

    /**
     * 删除数组中的帮助数据
     * @param delete
     * @param subscriber
     */
    public void deleteHelpData(List<Integer> delete,MySubscriber<BaseBean<NoDataBean>> subscriber){
        toSubscribe(mService.deleteHelpData(delete),subscriber);
    }

    /**
     * 得到单条帮助数据
     * @param id
     * @param subscriber
     */
    public void getSingleHelpData(int id,MySubscriber<BaseBean<HelpContentBean>> subscriber){
        toSubscribe(mService.getSingleHelpData(id),subscriber);
    }

    /**
     * 更新帮助
     * @param id 帮助的id
     * @param title 帮助的标题
     * @param summary 帮助的内容
     * @param subscriber
     */
    public void updateHelpData(int id,String title,String summary,long time,MySubscriber<BaseBean<NoDataBean>> subscriber){
        toSubscribe(mService.updateHelpData(id,title,summary,time),subscriber);
    }

    /**
     * 添加帮助数据
     * @param map 帮助数据合集
     * @param subscriber
     */
    public void addSingleHelpData(Map<String,Object> map,MySubscriber<BaseBean<NoDataBean>> subscriber){
        toSubscribe(mService.addSingleHelpData(map),subscriber);
    }

    /**
     * 根据id得到该帮助下的所有评论
     * @param id id
     * @param subscriber
     */
    public void getAllCommendData(int id, MySubscriber<BaseBean<List<HelpCommendItem>>> subscriber){
        toSubscribe(mService.getAllCommendData(id),subscriber);
    }

    /**
     * 添加赞扁的数据
     * @param state 判断是否是那个
     * @param id 帮助id
     * @param subscriber
     */
    public void addPraiseOrPoor(int state,int id,MySubscriber<BaseBean<NoDataBean>> subscriber){
        toSubscribe(mService.addPraiseOrPoor(state,id),subscriber);
    }

    /**
     * 取消赞扁的数据
     * @param state 判断是否是那个
     * @param id 帮助id
     * @param subscriber
     */
    public void subPraiseOrPoor(int state,int id,MySubscriber<BaseBean<NoDataBean>> subscriber){
        toSubscribe(mService.subPraiseOrPoor(state,id),subscriber);
    }

    /**
     * 添加评论
     * @param id 帮助id
     * @param title 内容
     * @param time 时间
     * @param subscriber
     */
    public void addHelpCommendData(int id,String title,long time,MySubscriber<BaseBean<HelpCommendItem>> subscriber){
        toSubscribe(mService.addHelpCommendData(id,title,time),subscriber);
    }

    /**
     * 添加文章型知识
     * @param article map
     * @param subscriber
     */
    public void addArticle(Map<String,Object> article, MySubscriber<BaseBean<NoDataBean>> subscriber) {
        toSubscribe(mService.addArticleData(article),subscriber);
    }

    /**
     * 删除特定文章
     * @param id
     * @param subscriber
     */
    public void deleteArticle(int[] id,MySubscriber<BaseBean<NoDataBean>> subscriber){
        toSubscribe(mService.deleteArticle(id),subscriber);
    }

    /**
     * 查询特定的文章
     * @param id
     * @param subscriber
     */
    public void queryArticle(int id, MySubscriber<BaseBean<ArticleBean>> subscriber){
        toSubscribe(mService.queryArticle(id),subscriber);
    }

    /**
     * 查询多个特定的文章
     * @param query
     * @param subscriber
     */
    public void queryAllArticle(List<Integer> query,MySubscriber<BaseBean<List<ArticleBean>>> subscriber){
        toSubscribe(mService.queryAllArticle(query),subscriber);
    }

    /**
     * 更新文章
     * @param update
     * @param subscriber
     */
    public void updateArticle(Map<String,Object> update,MySubscriber<BaseBean<NoDataBean>> subscriber){
        toSubscribe(mService.updateArticle(update),subscriber);
    }

    /**
     * 删除多个特定书籍
     * @param id
     * @param subscriber
     */
    public void deleteAllBook(int[] id,MySubscriber<BaseBean<NoDataBean>> subscriber){
        toSubscribe(mService.deleteBook(id),subscriber);
    }


    /**
     * 删除特定书籍
     * @param id
     * @param subscriber
     */
    public void deleteBook(int[] id,MySubscriber<BaseBean<NoDataBean>> subscriber){
        toSubscribe(mService.deleteBook(id),subscriber);
    }

    /**
     * 查询特定的书籍
     * @param id
     * @param subscriber
     */
    public void queryBook(int id, MySubscriber<BaseBean<BookBean>> subscriber){
        toSubscribe(mService.queryBook(id),subscriber);
    }

    /**
     * 查询多个特定的书籍
     * @param query
     * @param subscriber
     */
    public void queryAllBook(int[] query,MySubscriber<BaseBean<List<BookBean>>> subscriber){
        toSubscribe(mService.queryAllBook(query),subscriber);
    }

    /**
     * 更新书籍
     * @param book
     * @param subscriber
     */
    public void updateBook(RequestBody book,MySubscriber<BaseBean<NoDataBean>> subscriber){
        toSubscribe(mService.updateBook(book),subscriber);
    }




    /**
     * 删除多个特定文章
     * @param id
     * @param subscriber
     */
    public void deleteAllArticle(int[] id,MySubscriber<BaseBean<NoDataBean>> subscriber){
        toSubscribe(mService.deleteArticle(id),subscriber);
    }


    /**
     * 添加书籍型文章
     * @param body 主体
     * @param subscriber
     */
    public void addBook(RequestBody body, MySubscriber<BaseBean<NoDataBean>> subscriber){
        toSubscribe(mService.addBook(body),subscriber);
    }

    /**
     *
     * @param o
     * @param s
     * @param <T>
     */

    private <T> void toSubscribe(Observable<T> o, MySubscriber<T> s) {
        o.subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
                //回调在主线程
                .observeOn(AndroidSchedulers.mainThread()).subscribe((Observer<? super T>) s);
    }

    /**
     * 查询文章的数据
     * @param article_id
     * @param subscriber
     */
    public void queryArticleData(int article_id, MySubscriber<BaseBean<ArticleDataBean>> subscriber) {
        toSubscribe(mService.queryArticleData(article_id),subscriber);
    }

    /**
     * 查询书籍的数据
     * @param article_id
     * @param subscriber
     */
    public void queryBookData(int article_id, MySubscriber<BaseBean<BookDataBean>> subscriber) {
        toSubscribe(mService.queryBookData(article_id),subscriber);
    }
}
