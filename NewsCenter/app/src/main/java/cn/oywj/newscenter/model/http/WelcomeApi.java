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
 * instructions:**
 */
public interface WelcomeApi {

    String host = "http://api.tianapi.com/";

    @GET("meinv/")
    Observable<WelcomeBean> getWelcomeImage(@QueryMap Map<String, Object> map);
}
