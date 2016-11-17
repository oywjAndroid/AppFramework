package cn.oywj.newscenter.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.base
 * date:2016/11/17
 * author：欧阳维骏
 * instructions:*Simple base class for fragment*
 * 如果是业务逻辑比较简单的Fragment可实现此类，从而避免繁琐的BaseFragment。
 */
public abstract class SimpleBaseFragment extends Fragment {

    protected Activity mActivity;
    protected Context mContext;
    protected View mContentView;
    private boolean mIsInited;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContentView = inflater.inflate(getLayoutId(), null);
        return mContentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ButterKnife.bind(mContentView);
        if (!isHidden()) {
            mIsInited = true;
            initEventAndData();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!mIsInited && !hidden) {
            mIsInited = true;
            initEventAndData();
        }
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    protected abstract int getLayoutId();

    protected abstract void initEventAndData();
}
