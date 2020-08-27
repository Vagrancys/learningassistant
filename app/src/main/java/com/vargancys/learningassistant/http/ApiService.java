package com.vargancys.learningassistant.http;

import com.vargancys.learningassistant.db.home.HomeKnowItem;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    @GET("")
    Observable<BaseBean<HomeKnowItem>> getKnowledge();
}
