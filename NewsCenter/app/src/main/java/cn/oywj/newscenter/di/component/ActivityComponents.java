package cn.oywj.newscenter.di.component;

import android.app.Activity;

import cn.oywj.newscenter.di.ActivityScope;
import cn.oywj.newscenter.di.module.ActivityModules;
import cn.oywj.newscenter.ui.main.activity.WelcomeActivity;
import dagger.Component;

/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.di.component
 * date:2016/11/17
 * author：欧阳维骏
 * instructions:* ActivityComponent作为Activity的连接器，可以方便的为BaseActivity以及子类进行
 * 依赖注入的动作，如需提供特殊的对象可在ActivityModules中操作。*
 */
@ActivityScope
@Component(dependencies = AppComponents.class, modules = ActivityModules.class)
public interface ActivityComponents {

    Activity getActivty();

    void inject(WelcomeActivity welcome);

}
