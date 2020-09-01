package com.vargancys.learningassistant.http;

import android.util.Log;

import com.vargancys.learningassistant.db.home.KnowLedgeBean;
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

    private <T> void toSubscribe(Observable<T> o, MySubscriber<T> s) {
        o.subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
                //回调在主线程
                .observeOn(AndroidSchedulers.mainThread()).subscribe((Observer<? super T>) s);
    }
}
