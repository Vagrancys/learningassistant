package com.vargancys.learningassistant.http;

import com.vargancys.learningassistant.bean.common.HelpCommendItem;
import com.vargancys.learningassistant.bean.common.HelpContentBean;
import com.vargancys.learningassistant.bean.home.ArticleBean;
import com.vargancys.learningassistant.bean.home.ArticleDataBean;
import com.vargancys.learningassistant.bean.home.BookBean;
import com.vargancys.learningassistant.bean.home.BookDataBean;
import com.vargancys.learningassistant.bean.home.KnowLedgeBean;
import com.vargancys.learningassistant.model.common.bean.NoDataBean;

import java.util.List;
import java.util.Map;

import butterknife.internal.ListenerClass;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @GET("query_knowledge")
    Observable<BaseBean<List<KnowLedgeBean>>> getKnowledge(@Query("page") int page, @Query("limit") int limit);

    @GET("query_repeat_knowledge")
    Observable<BaseBean<NoDataBean>> queryKnowLedgeRepeat(@Query("title") String title);

    @POST("save_knowledge")
    @FormUrlEncoded
    Observable<BaseBean<NoDataBean>> saveKnowLedge(@FieldMap Map<String,Object> map);

    @GET("query_all_help")
    Observable<BaseBean<List<HelpContentBean>>> getAllHelpData();

    @POST("delete_help")
    @FormUrlEncoded
    Observable<BaseBean<NoDataBean>> deleteHelpData(@Field("delete[]") List<Integer> delete);

    @POST("delete_most_article")
    @FormUrlEncoded
    Observable<BaseBean<NoDataBean>> deleteAllArticle(@Field("delete[]") int[] delete);

    @POST("delete_single_article")
    @FormUrlEncoded
    Observable<BaseBean<NoDataBean>> deleteArticle(@Field("delete") int delete);

    @POST("delete_single_article")
    @FormUrlEncoded
    Observable<BaseBean<NoDataBean>> deleteBook(@Field("delete") int delete);

    @POST("delete_most_article")
    @FormUrlEncoded
    Observable<BaseBean<NoDataBean>> deleteAllBook(@Field("delete[]") int[] delete);

    @GET("query_single_help")
    Observable<BaseBean<HelpContentBean>> getSingleHelpData(@Query("id") int id);

    @POST("update_single_help")
    @FormUrlEncoded
    Observable<BaseBean<NoDataBean>> updateHelpData(@Field("id") int id,@Field("title") String title,@Field("summary") String summary,@Field("time") long time);

    @POST("add_single_help")
    @FormUrlEncoded
    Observable<BaseBean<NoDataBean>> addSingleHelpData(@FieldMap Map<String,Object> map);

    @GET("query_all_help_commend")
    Observable<BaseBean<List<HelpCommendItem>>> getAllCommendData(@Query("id") int id);

    @POST("add_help_commend_data")
    @FormUrlEncoded
    Observable<BaseBean<NoDataBean>> addPraiseOrPoor(@Field("state") int state,@Field("id") int id);

    @POST("sub_help_commend_data")
    @FormUrlEncoded
    Observable<BaseBean<NoDataBean>> subPraiseOrPoor(@Field("state") int state,@Field("id") int id);

    @POST("add_help_commend")
    @FormUrlEncoded
    Observable<BaseBean<HelpCommendItem>> addHelpCommendData(@Field("help_id") int id,@Field("title") String title,@Field("time") long time);

    @POST("add_single_article")
    @FormUrlEncoded
    Observable<BaseBean<NoDataBean>> addArticleData(@FieldMap Map<String,Object> map);

    @POST("query_single_article_data")
    @FormUrlEncoded
    Observable<BaseBean<ArticleDataBean>> queryArticleData(@Field("article_id") int id);

    @GET("query_single_book_data")
    Observable<BaseBean<BookDataBean>> queryBookData(@Query("article_id") int id);

    @POST("add_single_book")
    @FormUrlEncoded
    Observable<BaseBean<NoDataBean>> addBook(@Body RequestBody body);

    @GET("query_single_article")
    Observable<BaseBean<ArticleBean>> queryArticle(@Query("query") int query);

    @POST("update_single_article")
    @FormUrlEncoded
    Observable<BaseBean<NoDataBean>> updateArticle(@FieldMap Map<String,Object> update);

    @GET("query_all_article")
    Observable<BaseBean<List<ArticleBean>>> queryAllArticle(@Query("query[]") int[] query);

    @GET("query_single_book")
    Observable<BaseBean<BookBean>> queryBook(@Query("query") int query);

    @POST("update_single_book")
    @FormUrlEncoded
    Observable<BaseBean<NoDataBean>> updateBook(@Body RequestBody body);

    @GET("query_all_book")
    Observable<BaseBean<List<BookBean>>> queryAllBook(@Query("query[]") int[] query);

    @POST("add_single_aided")
    @FormUrlEncoded
    Observable<BaseBean<NoDataBean>> addAided(@FieldMap Map<String, Object> aided);
}
