package cn.oywj.newscenter.ui.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import cn.oywj.newscenter.base.BaseFragment;

/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.ui.main.adapter
 * date:2016/11/29
 * author：欧阳维骏
 * instructions:**
 */
public class MainPagerAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> mData;

    public MainPagerAdapter(FragmentManager fm, List<BaseFragment> data) {
        super(fm);
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mData.get(position);
    }

}
