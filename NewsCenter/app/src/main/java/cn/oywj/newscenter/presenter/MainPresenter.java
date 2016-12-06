package cn.oywj.newscenter.presenter;

import java.util.ArrayList;

import javax.inject.Inject;

import cn.oywj.newscenter.base.BaseFragment;
import cn.oywj.newscenter.base.RxBasePresenter;
import cn.oywj.newscenter.presenter.contract.MainContract;
import cn.oywj.newscenter.ui.main.fragment.ChatFragment;
import cn.oywj.newscenter.ui.main.fragment.MapFragment;
import cn.oywj.newscenter.ui.main.fragment.NewsFragment;
import cn.oywj.newscenter.ui.main.fragment.VideoFragment;

/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.presenter
 * date:2016/11/21
 * author：欧阳维骏
 * instructions:**
 */
public class MainPresenter extends RxBasePresenter<MainContract.ViewContract> implements MainContract.PresenterContract {

    @Inject
    public MainPresenter() {

    }

    @Override
    public ArrayList<BaseFragment> getPagerData() {
        ArrayList<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new NewsFragment());
        fragments.add(new VideoFragment());
        fragments.add(new MapFragment());
        fragments.add(new ChatFragment());
        return fragments;
    }
}
