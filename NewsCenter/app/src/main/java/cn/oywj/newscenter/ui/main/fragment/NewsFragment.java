package cn.oywj.newscenter.ui.main.fragment;

import android.support.v4.view.ViewPager;

import butterknife.BindView;
import cn.oywj.newscenter.R;
import cn.oywj.newscenter.base.BaseFragment;
import cn.oywj.newscenter.presenter.NewsPresenter;
import cn.oywj.newscenter.presenter.contract.NewsContract;

/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.ui.main.fragment
 * date:2016/11/29
 * author：欧阳维骏
 * instructions:**
 */
public class NewsFragment extends BaseFragment<NewsPresenter> implements NewsContract.ViewContract {

    @BindView(R.id.news_details_pager)
    ViewPager mNewsPager;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news_layout;
    }

    @Override
    protected void initInject() {
        getFragmentComponents().inject(this);
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void useErrorView(boolean isError, String errorMsg) {

    }

    @Override
    public void useLoadingView(boolean isLoading, String loadingMsg) {

    }

    @Override
    public void useNightMode(boolean isNight) {

    }
}
