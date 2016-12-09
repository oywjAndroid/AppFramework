package cn.oywj.newscenter.presenter.contract;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;

import cn.oywj.newscenter.base.BaseFragment;
import cn.oywj.newscenter.base.BasePresenter;
import cn.oywj.newscenter.base.BaseView;

/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.presenter.contract
 * date:2016/11/21
 * author：欧阳维骏
 * instructions:*主界面的View层与Presenter层的契约接口*
 */
public interface MainContract {

    interface ViewContract extends BaseView {
        /**
         * 更新MainActivities中的Toolbar中的logo以及title.
         *
         * @param logo
         * @param title
         */
        void updateToolbar(Drawable logo, String title);

    }

    interface PresenterContract extends BasePresenter<ViewContract> {
        ArrayList<BaseFragment> getPagerData();
    }
}
