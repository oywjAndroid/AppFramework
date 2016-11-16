package cn.oywj.newscenter.model.http;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import cn.oywj.newscenter.BuildConfig;
import cn.oywj.newscenter.app.Constants;
import cn.oywj.newscenter.utils.SystemUtil;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.di
 * date:2016/11/11
 * author：欧阳维骏
 * instructions:*Retrofit的使用帮助类*
 * ---首先需要清楚的是该项目对于网络请求有哪些需求：
 * ------ 1.
 */
public final class RetrofitHelper {
    private static OkHttpClient mHttpClient;
    private static Interceptor mCacheInterceptor;

    static {
        initOkHttpClient();
        initCacheInterceptor();
    }

    private static void initCacheInterceptor() {
        mCacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!SystemUtil.isNetworkConnected()) {
                    // 没有网络的情况，使用缓存
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (SystemUtil.isNetworkConnected()) {
                    int maxAge = 0;
                    // 有网络时, 不缓存, 最大保存时长为0
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")
                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };
    }

    private static void initOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(loggingInterceptor);
        }
        // cache process
        File cacheFile = new File(Constants.PATH_CACHE);
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        builder.addNetworkInterceptor(mCacheInterceptor);
        builder.addInterceptor(mCacheInterceptor);
        builder.cache(cache);
        builder.connectTimeout(20, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        mHttpClient = builder.build();
    }

    public static <Api> Api getApiEntity(Class<Api> clazz, String baseUrl) {
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.baseUrl(baseUrl)
                .client(mHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(clazz);
    }

}
