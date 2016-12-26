package cn.oywj.newscenter.presenter;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import cn.oywj.newscenter.app.Constants;
import cn.oywj.newscenter.base.RxBasePresenter;
import cn.oywj.newscenter.model.bean.WelcomeBean;
import cn.oywj.newscenter.model.http.RetrofitHelper;
import cn.oywj.newscenter.model.http.WelcomeApi;
import cn.oywj.newscenter.presenter.contract.WelcomeContract;
import cn.oywj.newscenter.utils.RxUtils;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.presenter
 * date:2016/11/18
 * author：欧阳维骏
 * instructions:**
 */
public class WelcomePresenter extends RxBasePresenter<WelcomeContract.ViewContract> implements WelcomeContract.PresenterContract {

    private static final String TAG = WelcomePresenter.class.getSimpleName();
    private HashMap<String, Object> mParamMap;
    private WelcomeApi mWelcomeApi;

    @Inject
    public WelcomePresenter() {
        mParamMap = new HashMap<>();
    }

    @Override
    public void getWelcomeData() {
        mWelcomeApi = RetrofitHelper.getApiEntity(WelcomeApi.class, WelcomeApi.host);
        if (mParamMap.size() > 0) {
            mParamMap.clear();
        }
        mParamMap.put("key", Constants.API_KEY);
        mParamMap.put("num", 1);
        mParamMap.put("rand", 1);
        Subscription subscription = mWelcomeApi.getWelcomeImage(mParamMap)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribe(new Action1<WelcomeBean>() {
                    @Override
                    public void call(WelcomeBean welcomeBean) {
                        mView.showContent(welcomeBean);
                        timerTask();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.useErrorView(false, throwable.getMessage());
                        throwable.printStackTrace();
                        timerTask();
                    }
                });
        addSubscription(subscription);


    }

    /**
     * 执行定时任务
     */
    private void timerTask() {
        Subscription subscribe = Observable.timer(2000, TimeUnit.MILLISECONDS)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        mView.jumpToMain();
                    }
                });
        addSubscription(subscribe);
    }
}
