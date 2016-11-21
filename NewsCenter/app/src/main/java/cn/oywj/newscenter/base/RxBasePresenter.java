package cn.oywj.newscenter.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.base
 * date:2016/11/18
 * author：欧阳维骏
 * instructions:*基于RxJava的BasePresenter类，主要是用来解除订阅，防止内存泄漏*
 */
public class RxBasePresenter<V extends BaseView> implements BasePresenter<V> {
    protected V mView;

    protected CompositeSubscription mSubscription;

    @Override
    public void attachView(V view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
        unSubscribe();
    }

    protected void addSubscription(Subscription subscription) {
        if (mSubscription == null) {
            mSubscription = new CompositeSubscription();
        }
        mSubscription.add(subscription);
    }

    protected void unSubscribe() {
        if (mSubscription != null)
            mSubscription.unsubscribe();
    }
}
