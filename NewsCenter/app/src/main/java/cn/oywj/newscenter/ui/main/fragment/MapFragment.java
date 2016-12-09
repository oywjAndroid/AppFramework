package cn.oywj.newscenter.ui.main.fragment;

import android.util.Log;

import cn.oywj.newscenter.R;
import cn.oywj.newscenter.base.BaseFragment;

/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.ui.main.fragment
 * date:2016/11/29
 * author：欧阳维骏
 * instructions:**
 */
public class MapFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_map_layout;
    }

    @Override
    protected void initInject() {
        Log.d("Test-Fragment", "MapFragment -- onCreateView().");
    }

    @Override
    protected void initEventAndData() {
        Log.d("Test-Fragment", "MapFragment -- initEventAndData(). UserVisible == " + getUserVisibleHint());
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
