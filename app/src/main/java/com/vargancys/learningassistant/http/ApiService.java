package com.vargancys.learningassistant.http;

import com.vargancys.learningassistant.db.home.KnowLedgeBean;
import com.vargancys.learningassistant.model.common.bean.NoDataBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
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
}
