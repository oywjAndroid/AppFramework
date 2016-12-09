package cn.oywj.newscenter.presenter;

import javax.inject.Inject;

import cn.oywj.newscenter.base.RxBasePresenter;
import cn.oywj.newscenter.presenter.contract.NewsContract;

/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.presenter
 * date:2016/12/9
 * author：欧阳维骏
 * instructions:**
 */
public class NewsPresenter extends RxBasePresenter<NewsContract.ViewContract> implements NewsContract.PresenterContract{

    @Inject
    public NewsPresenter(){

    }
    

}
