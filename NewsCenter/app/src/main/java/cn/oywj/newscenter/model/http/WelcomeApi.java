package cn.oywj.newscenter.model.http;

import java.util.Map;

import cn.oywj.newscenter.model.bean.WelcomeBean;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.model.http
 * date:2016/11/18
 * author：欧阳维骏
 * instructions:*美女欢迎页以及新闻资讯类接口*
 */
public interface WelcomeApi {

    String host = "http://api.tianapi.com/";

    @GET("meinv/")
    Observable<WelcomeBean> getWelcomeImage(@QueryMap Map<String, Object> map);

    @GET("social/")
    Observable<WelcomeBean> getSocialData(@QueryMap Map<String, Object> map);

    @GET("guonei/")
    Observable getDomesticData(@QueryMap Map<String, Object> map);

    @GET("world/")
    Observable<WelcomeBean> getInternationalData(@QueryMap Map<String, Object> map);

    @GET("huabian/")
    Observable<WelcomeBean> getDistractionData(@QueryMap Map<String, Object> map);

    @GET("tiyu/")
    Observable<WelcomeBean> getSportsData(@QueryMap Map<String, Object> map);

    @GET("nba/")
    Observable<WelcomeBean> getNbaData(@QueryMap Map<String, Object> map);

    @GET("football/")
    Observable<WelcomeBean> getFootballData(@QueryMap Map<String, Object> map);

    @GET("keji/")
    Observable<WelcomeBean> getScienceData(@QueryMap Map<String, Object> map);

    @GET("startup/")
    Observable<WelcomeBean> getBusinessData(@QueryMap Map<String, Object> map);

    @GET("apple/")
    Observable<WelcomeBean> getAppleData(@QueryMap Map<String, Object> map);

    @GET("mobile/")
    Observable<WelcomeBean> getMobileData(@QueryMap Map<String, Object> map);

    @GET("travel/")
    Observable<WelcomeBean> getTravelData(@QueryMap Map<String, Object> map);

    @GET("health/")
    Observable<WelcomeBean> getHealthData(@QueryMap Map<String, Object> map);

    @GET("qiwen/")
    Observable<WelcomeBean> getAnecdoteData(@QueryMap Map<String, Object> map);

    @GET("vr/")
    Observable<WelcomeBean> getVRData(@QueryMap Map<String, Object> map);

    @GET("it/")
    Observable<WelcomeBean> getITData(@QueryMap Map<String, Object> map);

}
