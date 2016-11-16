package cn.oywj.newscenter.stu.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.stu.rxjava
 * date:2016/11/15
 * author：欧阳维骏
 * instructions:*RxJavaDemo2 主要是对Observable from()方法以及变换操作
 * {
 * **** 1. map(Func1<T,R>>),
 * **** 2. flatMap(Func1< ? extends T, Observable< ? extends R>),
 * **** 3. filter(Func< T,Boolean >),
 * **** 4. take( int  ),
 * **** 5. doOnNext( Action1<T>)
 * } 进行了讲解*
 */
public class RxJavaDemo2 extends AppCompatActivity {

    private static final String TAG = RxJavaDemo2.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        /**
         * 在RxJavaDemo1中我们的Observable都是只生产了一个事件进行分发，
         * 那么如何进行事件集分发呢？
         */
        //1.生产多个事件进行分发
        rudeTransform();


        flatMapTransform();


        flatMapTransformAdvance();

    }

    private void rudeTransform() {
        /**
         * 这种做法虽然能够获取每一个(Url)String对象，但是却不是在被观察者中发生的，
         * 而是在Subscriber中进行的处理的，这样做简直就是粗暴，它最大缺点就是丧失了数据流变化的能力，
         * 以至于以后需要修改url只能在Subscriber中才能完成，这样做显然违背RxJava设计宗旨。
         *
         * 针对这种情况：Observable 提供了
         * form() -- 支持创建多个数据进行分发的Observable 、
         *
         * --->map(Func1(T,R)) map变化就是把事件参数(T)变换成指定类型(R)
         * --->flatMap(Func1(? extends T,? extends Observable< ? extends R >)) -- 支持将事件参数(T)转换为Observable< ? extends R >
         */
//        getObservable().subscribe(new Action1<List<String>>() {
//            @Override
//            public void call(List<String> list1) {
//                for (String str : list1) {
//                    Log.d(TAG, str);
//                }
//            }
//        });

        getObservable().subscribe(list1 -> {
            for (String str : list1) {
                Log.d(TAG, str);
            }
        });
    }

    private void flatMapTransform() {
//        getObservable().flatMap(new Func1<List<String>, Observable<String>>() {
//            @Override
//            public Observable<String> call(List<String> strings) {
//                return Observable.from(strings);
//            }
//        }).subscribe(new Action1<String>() {
//            @Override
//            public void call(String string) {
//                Log.d(TAG, "flatMap after : " + string);
//            }
//        });

        getObservable().flatMap(strings -> Observable.from(strings))
                .subscribe(string -> {
                    Log.d(TAG, "flatMap after : " + string);
                });

    }

    /**
     * 将每一个Url地址转换成title名称输出
     */
    private void flatMapTransformAdvance() {
        getObservable().flatMap(new Func1<List<String>, Observable<String>>() {
            @Override
            public Observable<String> call(List<String> urls) {
                return Observable.from(urls);
            }
        }).flatMap(new Func1<String, Observable<String>>() {
            @Override
            public Observable<String> call(String url) {
                /**
                 * 那么这个时候问题来了，如果返回的title是null值能不能过滤掉呢?
                 */
                return getTitle(url);
            }
        }).filter(new Func1<String, Boolean>() {
            /**
             * filter可以过滤点不需要的Observable对象事件参数
             */

            @Override
            public Boolean call(String s) {
                // 只有返回为true的不会被过滤
                return !TextUtils.isEmpty(s);
            }
        }).take(10)// take限定输出的结果的数量
                .doOnNext(new Action1<String>() {
                    // doOnNext允许我们在输出每一个数据之前可以做一些操作
                    @Override
                    public void call(String s) {
                        Log.d(TAG, s + "主题，保存至磁盘");
                    }
                }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG, "flatMap Advance : " + s);
            }
        });

//        getObservable().flatMap(urls -> Observable.from(urls))
//                .flatMap(url -> getTitle(url))
//                .subscribe(s -> {Log.d(TAG, "flatMap Advance : " + s);
//        });
    }

    /**
     * 这里的每一个网址代表的是单独的事件，只有让Subscriber获取到里面的每个网址才说明事件被成功消费
     */
    Observable<List<String>> getObservable() {
        ArrayList<String> list = new ArrayList<>();
        list.add("www.baidu.com");
        list.add("www.huawei.com");
        list.add("www.tenxun.com");
        list.add("www.souhu.com");
        return Observable.just(list);
    }

    Observable<String> getTitle(String url) {
        ArrayList<String> list = new ArrayList<>();
        list.add("www.baidu.com");
        list.add("www.huawei.com");
        list.add("www.tenxun.com");
        list.add("www.souhu.com");

        int pos = -1;
        for (int i = 0; i < list.size(); i++) {
            if (url.equals(list.get(i))) {
                pos = i;
            }
        }
        Observable<String> observable;
        switch (pos) {
            case 0:
                observable = Observable.just("百度");
                break;
            case 1:
                observable = Observable.just("华为");
                break;
            case 2:
                observable = Observable.just("腾讯");
//                observable = Observable.just("");
                break;
            case 3:
                observable = Observable.just("搜狐");
//                observable = null;
                break;
            default:
                observable = Observable.just("");
                break;
        }
        return observable;
    }

}
