package cn.oywj.newscenter.di.component;

import cn.oywj.newscenter.di.module.FragmentModules;
import cn.oywj.newscenter.ui.main.fragment.NewsFragment;
import dagger.Component;

/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.di.component
 * date:2016/11/17
 * author：欧阳维骏
 * instructions:**
 */
@Component(modules = FragmentModules.class)
public interface FragmentComponents {

    void inject(NewsFragment fragment);
}
