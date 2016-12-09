package cn.oywj.newscenter.presenter.contract;

import cn.oywj.newscenter.base.BasePresenter;
import cn.oywj.newscenter.base.BaseView;

/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.presenter.contract
 * date:2016/12/9
 * author：欧阳维骏
 * instructions:**
 */
public interface NewsContract {

    interface ViewContract extends BaseView {

    }

    interface PresenterContract extends BasePresenter<ViewContract> {

    }
}
