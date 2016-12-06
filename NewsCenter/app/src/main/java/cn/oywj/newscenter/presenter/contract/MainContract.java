package cn.oywj.newscenter.presenter.contract;

import java.util.ArrayList;

import cn.oywj.newscenter.base.BaseFragment;
import cn.oywj.newscenter.base.BasePresenter;
import cn.oywj.newscenter.base.BaseView;

/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.presenter.contract
 * date:2016/11/21
 * author：欧阳维骏
 * instructions:**
 */
public interface MainContract {

    interface ViewContract extends BaseView {
        // TODO
    }

    interface PresenterContract extends BasePresenter<ViewContract> {

        ArrayList<BaseFragment> getPagerData();

    }
}
