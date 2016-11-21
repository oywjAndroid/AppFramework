package cn.oywj.newscenter.presenter.contract;

import cn.oywj.newscenter.base.BasePresenter;
import cn.oywj.newscenter.base.BaseView;
import cn.oywj.newscenter.model.bean.WelcomeBean;
import cn.oywj.newscenter.model.http.WelcomeApi;

/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.presenter.contract
 * date:2016/11/18
 * author：欧阳维骏
 * instructions:*
 * WelcomeContract是WelcomePresenter的契约接口，由于Presenter层既要负责View层的业务逻辑，还需要根据业务逻辑通知View层执行一些
 * UI操作逻辑，这个时候我们可以通过Contract契约接口规范具体的Presenter需要执行的业务逻辑和UI通知。
 * *
 */
public interface WelcomeContract {

    /**
     * ViewContract：Welcome页面View层契约，该接口当由Welcome页面的(Activity || Fragment)实现
     */
    interface ViewContract extends BaseView{

        void showContent(WelcomeBean welcomeBean);

        void jumpToMain();
    }

    /**
     * PresenterContract: Welcome页面Presenter层契约，该接口当由Welcome页面的(Presenter)实现
     */
    interface PresenterContract extends BasePresenter<ViewContract>{

        void getWelcomeData();

    }
}
