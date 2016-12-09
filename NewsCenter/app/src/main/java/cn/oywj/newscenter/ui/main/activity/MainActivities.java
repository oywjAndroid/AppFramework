package cn.oywj.newscenter.ui.main.activity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindArray;
import butterknife.BindView;
import cn.oywj.newscenter.R;
import cn.oywj.newscenter.base.BaseActivity;
import cn.oywj.newscenter.base.BaseFragment;
import cn.oywj.newscenter.presenter.MainPresenter;
import cn.oywj.newscenter.presenter.contract.MainContract;
import cn.oywj.newscenter.ui.main.adapter.MainPagerAdapter;
import cn.oywj.newscenter.widget.navigation.GradientGroup;

/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.ui.main.activity
 * date:2016/11/21
 * author：欧阳维骏
 * instructions:**
 */
public class MainActivities extends BaseActivity<MainPresenter> implements MainContract.ViewContract {

    public static final int TITLE_SIZE = 16;

    @BindView(R.id.ui_main_pager)
    ViewPager mPagers;
    @BindView(R.id.gradient_group)
    GradientGroup mGradientGroup;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindArray(R.array.main_model_names)
    String[] mModelNames;

    int[] mModelDrawables = new int[]{
            R.mipmap.browse,
            R.mipmap.play,
            R.mipmap.discover,
            R.mipmap.info
    };

    TextView mTitleView;
    MainPagerAdapter mMainAdapter;

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
        initMainActionBar();
        statusBarFullScreen();
        ArrayList<BaseFragment> pagerData = mPresenter.getPagerData();
        mMainAdapter = new MainPagerAdapter(getSupportFragmentManager(), pagerData);
        mPagers.setAdapter(mMainAdapter);
        mGradientGroup.setViewPager(mPagers);
        mGradientGroup.setOnPageChangeListener(new GradientGroup.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                updateToolbar(getResources().getDrawable(mModelDrawables[position]), mModelNames[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void useErrorView(boolean isError, String errorMsg) {


    }

    @Override
    public void useLoadingView(boolean isLoading, String loadingMsg) {


    }

    private void initMainActionBar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            mTitleView = new TextView(this);
            mTitleView.setTextColor(Color.WHITE);
            mTitleView.setTextSize(TITLE_SIZE);
            mTitleView.setLayoutParams(
                    new Toolbar.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            Gravity.CENTER
                    )
            );
            mToolbar.addView(mTitleView);
            updateToolbar(getResources().getDrawable(mModelDrawables[0]), mModelNames[0]);
        }
    }

    // Android只有在4.4之后才能实现状态栏着色
    private void statusBarFullScreen() {
        // 5.0以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = getWindow().getDecorView();
            int options = View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(options);
            // 5.0以上可以直接设置状态栏颜色
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        // 4.4
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams params = getWindow().getAttributes();
            params.flags |= WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        }
    }

    @Override
    public void updateToolbar(Drawable logo, String title) {
        mToolbar.setLogo(logo);
        mTitleView.setText(title);
    }
}
