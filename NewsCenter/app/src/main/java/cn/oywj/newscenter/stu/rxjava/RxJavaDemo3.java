package cn.oywj.newscenter.stu.rxjava;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.stu.rxjava
 * date:2016/11/15
 * author：欧阳维骏
 * instructions:**
 */
public class RxJavaDemo3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    private void loadImage(){
        Observable.just("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png")
                .subscribeOn(Schedulers.io())
                .map(new Func1<String, Bitmap>() {
                    @Override
                    public Bitmap call(String s) {
                        return null;
                    }
                })
//                .flatMap(new Func1<String, Observable<Bitmap>>() {
//                    @Override
//                    public Observable<Bitmap> call(String s) {
//                        return null;//执行网络图片加载的方法，获取Bitmap对象
//                    }
//                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) {

                    }
                });
    }
}
