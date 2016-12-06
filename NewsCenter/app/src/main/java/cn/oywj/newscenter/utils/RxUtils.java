package cn.oywj.newscenter.utils;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.utils
 * date:2016/11/21
 * author：欧阳维骏
 * instructions:**
 */
public class RxUtils {

    /**
     * 为RxJava提供统一的线程处理，即Observable的操作执行在子线程，其他执行在主线程。
     *
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<T, T> rxSchedulerHelper() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}
