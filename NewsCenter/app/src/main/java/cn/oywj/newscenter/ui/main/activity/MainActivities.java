package cn.oywj.newscenter.ui.main.activity;

import android.support.v4.view.ViewPager;

import java.util.ArrayList;

import butterknife.BindView;
import cn.oywj.newscenter.R;
import cn.oywj.newscenter.base.BaseActivity;
import cn.oywj.newscenter.base.BaseFragment;
import cn.oywj.newscenter.presenter.MainPresenter;
import cn.oywj.newscenter.presenter.contract.MainContract;
import cn.oywj.newscenter.ui.main.adapter.MainPagerAdapter;
import cn.oywj.newscenter.widget.navigation.GradientButton;
import cn.oywj.newscenter.widget.navigation.GradientGroup;

/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.ui.main.activity
 * date:2016/11/21
 * author：欧阳维骏
 * instructions:**
 */
public class MainActivities extends BaseActivity<MainPresenter> implements MainContract.ViewContract {

    @BindView(R.id.ui_main_pager)
    ViewPager mPagers;

    @BindView(R.id.gradient_group)
    GradientGroup mGradientGroup;

    @BindView(R.id.gradient_btn_one)
    GradientButton mButton;

    private MainPagerAdapter mMainAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activities_mian;
    }

    @Override
    protected void initInject() {
        getActivityComponents().inject(this);
    }

    @Override
    protected void initEventAndData() {
        ArrayList<BaseFragment> pagerData = mPresenter.getPagerData();
        mMainAdapter = new MainPagerAdapter(getSupportFragmentManager(), pagerData);
        mPagers.setAdapter(mMainAdapter);
        mGradientGroup.setViewPager(mPagers);
        mButton.showBadgeNumber(999);
    }

    @Override
    public void useErrorView(boolean isError, String errorMsg) {

    }

    @Override
    public void useLoadingView(boolean isLoading, String loadingMsg) {

    }
}
