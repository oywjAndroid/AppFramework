package cn.oywj.newscenter.di.component;

import javax.inject.Singleton;

import cn.oywj.newscenter.app.App;
import cn.oywj.newscenter.di.ContextQualifier;
import cn.oywj.newscenter.di.module.AppModules;
import dagger.Component;

/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.di
 * date:2016/11/11
 * author：欧阳维骏
 * instructions:* AppComponent -- 为整个App提供注入常用的对象类*
 *
 */
@Singleton
@Component(modules = AppModules.class)
public interface AppComponents {

    @ContextQualifier
    App getAppContext();
}
