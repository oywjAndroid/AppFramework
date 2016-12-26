package cn.oywj.newscenter.component;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.component
 * date:2016/12/12
 * author：欧阳维骏
 * instructions:**
 */
public class RxBus {

    private final Subject<Object, Object> rxBus;

    private RxBus() {
        rxBus = new SerializedSubject<>(PublishSubject.create());
    }

    public static RxBus getDefault() {
        return RxBusHolder.sInstance;
    }

    private static class RxBusHolder {
        private static final RxBus sInstance = new RxBus();
    }

    /**
     * 用于发送指定事件
     *
     * @param obj
     */
    public void post(Object obj) {
        rxBus.onNext(obj);
    }

    /**
     * 用于接收指定事件
     *
     * @param eventType 事件类型Class对象
     * @param <T>       事件类型
     * @return Observable
     */
    public <T> Observable<T> toObservable(Class<T> eventType) {
        return rxBus.ofType(eventType);
    }
}
