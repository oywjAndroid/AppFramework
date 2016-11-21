package cn.oywj.newscenter.ui.main.activity;

import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.BindView;
import cn.oywj.newscenter.R;
import cn.oywj.newscenter.base.BaseActivity;
import cn.oywj.newscenter.model.bean.WelcomeBean;
import cn.oywj.newscenter.presenter.WelcomePresenter;
import cn.oywj.newscenter.presenter.contract.WelcomeContract;
import cn.oywj.newscenter.utils.ToastUtil;

/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.ui.main
 * date:2016/11/18
 * author：欧阳维骏
 * instructions:**
 */
public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements WelcomeContract.ViewContract {
    private static final String TAG = WelcomeActivity.class.getSimpleName();

    @BindView(R.id.welcome_im)
    ImageView mImageView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initInject() {
        getActivityComponents().inject(this);
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getWelcomeData();
    }

    @Override
    public void useErrorView(boolean isError, String errorMsg) {
        if (isError) {
            ToastUtil.show("errorMsg:" + errorMsg);
        }
    }

    @Override
    public void useLoadingView(boolean isLoading, String loadingMsg) {

    }

    @Override
    public void showContent(WelcomeBean welcomeBean) {
        Log.d(TAG, "mImageView == null ? " + (mImageView == null));
        if (mImageView != null) {
            Glide.with(mActivity).load(welcomeBean.newslist.get(0).picUrl).crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(mImageView);
            mImageView.animate().scaleX(1.12f).scaleY(1.12f).setDuration(2000).setStartDelay(100).start();
        }
    }

    @Override
    public void jumpToMain() {
//        Utils.start_Activity(this,);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mImageView != null)
            Glide.clear(mImageView);
    }
}
