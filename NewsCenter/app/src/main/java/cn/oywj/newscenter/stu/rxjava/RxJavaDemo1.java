package cn.oywj.newscenter.stu.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.stu.rxjava
 * date:2016/11/14
 * author：欧阳维骏
 * instructions:*RxJavaDemo1主要是对RxJava的被观察者(Observable)、观察者（Observer || Subscriber）以及订阅过程进行了介绍。*
 */
public class RxJavaDemo1 extends AppCompatActivity {

    public static final String TAG = RxJavaDemo1.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        originalSubscribe();

        simplifySubscribe();

        transformation1();

        transformation2();

    }

    /**
     * 原始的订阅过程：
     * 1.通过create()创建Observable对象，用于发出具体的事件。
     * 2.创建Subscribe对象，用于处理被观察者发出的响应。
     * 3.执行订阅(subscriber)操作。
     */
    private void originalSubscribe() {
        // 1.创建Observable对象 --- 用于生产事件
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

                /**
                 * 在不指定线程的情况下，RxJava遵循的是线程不变的原则，
                 * 即在哪个线程调用的订阅方法subscribe()，
                 * 即在哪个线程生产事件；在哪个线程生产事件，就在哪个线程消费事件；
                 * 因为subscribe()方法的执行，才意味着Observable发出的事件是有效。
                 */
                subscriber.onNext("Hello RxJava..." + Thread.currentThread().getName());
                subscriber.onCompleted();
            }
        });

        // 生产事件所在的线程 --- 被观察者代码执行所处的线程。subscribe()
        observable.subscribeOn(AndroidSchedulers.mainThread());
        // 消费事件所在的线程 --- 观察者||订阅者代码执行所处的线程，Subscriber 中的 onNext() onCompleted() onError()发生的线程。
        observable.observeOn(AndroidSchedulers.mainThread());

        // 2.创建Subscriber对象 --- 用于消费事件
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, s);
            }
        };
        // 3.执行订阅功能
        observable.subscribe(subscriber);
    }

    /**
     * 简化的订阅过程：
     * RxJava内置了很多简化创建Observable对象的函数，如：just(T t)、from(T[] t)
     * RxJava同时也简化了Subscriber对象，如：Action1,Action0
     * 1.利用just()方法快速创建一个发射指定动作的Observable对象，Value值就代表了具体的数据。
     * 2.subscribe()方法有提供多种重载方法，如果只需要关注onNext()可以只传递一个Action1对象。
     */
    private void simplifySubscribe() {
        Observable<String> just = Observable.just("Hello RxJava...");
//        Action1<String> actionNext = new Action1<String>() {
//            @Override
//            public void call(String s) {
//                Log.e(TAG, s);
//            }
//        };
//      如果在jdk8以上，上面的代码可以简化为:
        Action1<String> actionNext = s -> Log.e(TAG, s);
        just.subscribe(actionNext);
    }

    /**
     * 变换：当我们想要对Observable发出事件中的数据进行一些处理，如：在上述打印的"Hello RxJava"加上自己的签名-->"Hell RxJava -- oywj"。
     * RxJava为我们提供了执行变换的操作符：map -- 将Observable发出的事件进行一个Func1操作，之后再返回一个Observable对象。
     * 例：将"Hello RxJava" --> "Hello RxJava -- oywj"输出打印。
     */
    private void transformation1() {
        Observable<String> observable = Observable.just("Hello RxJava");
//        observable.map(new Func1<String, String>() {
//            @Override
//            public String call(String s) {
//                return s + " -- oywj";
//            }
//        }).subscribe(s -> Log.e(TAG,s));
//      简化之后:
        observable.map(f -> f + " -- oywj")
                .subscribe(s -> Log.e(TAG, s));
    }

    /**
     * map变换的进阶：map操作符不必返回Observable对象返回的类型，你可以使用map操作符返回一个发出新的数据类型的observable对象。
     */
    private void transformation2() {
        Observable<String> observable = Observable.just("Hello RxJava");
        observable.map(new Func1<String, Integer>() {
            @Override
            public Integer call(String s) {
                return s.hashCode();
            }
        }).map(new Func1<Integer, String>() {
            @Override
            public String call(Integer o) {
                return Integer.toString(0);
            }
        }).subscribe(s -> Log.e(TAG, s));
    }
}
