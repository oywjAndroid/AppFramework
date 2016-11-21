package cn.oywj.newscenter.presenter;

import java.util.HashMap;

import javax.inject.Inject;

import cn.oywj.newscenter.app.Constants;
import cn.oywj.newscenter.base.RxBasePresenter;
import cn.oywj.newscenter.model.bean.WelcomeBean;
import cn.oywj.newscenter.model.http.RetrofitHelper;
import cn.oywj.newscenter.model.http.WelcomeApi;
import cn.oywj.newscenter.presenter.contract.WelcomeContract;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

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
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<WelcomeBean>() {
                            @Override
                            public void call(WelcomeBean welcomeBean) {
                                mView.showContent(welcomeBean);
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                mView.useErrorView(true, throwable.toString());
                                throwable.printStackTrace();
                            }
                        });
        addSubscription(subscription);
    }
}
