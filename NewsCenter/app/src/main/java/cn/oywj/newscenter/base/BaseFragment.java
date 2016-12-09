package cn.oywj.newscenter.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import cn.oywj.newscenter.di.component.DaggerAppComponents;
import cn.oywj.newscenter.di.component.DaggerFragmentComponents;
import cn.oywj.newscenter.di.component.FragmentComponents;

/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.base
 * date:2016/11/17
 * author：欧阳维骏
 * instructions:*使用了MVP模式Fragment的基类*
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {
    @Inject
    P mPresenter;
    protected Context mContext;
    protected Activity mActivity;
    protected View mContentView;
    private boolean mInited;

    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContentView = inflater.inflate(getLayoutId(), null);
        initInject();
        return mContentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        if (mPresenter != null)
            mPresenter.attachView(this);
        ButterKnife.bind(mContentView);
        if (!isHidden()) {
            mInited = true;
            initEventAndData();
        }
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!mInited && !hidden) {
            mInited = true;
            initEventAndData();
        }
        super.onHiddenChanged(hidden);
    }

    public FragmentComponents getFragmentComponents() {
        return DaggerFragmentComponents.builder()
                .build();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null)
            mPresenter.detachView();
        super.onDestroyView();
    }

    protected abstract int getLayoutId();

    protected abstract void initInject();

    protected abstract void initEventAndData();
}
