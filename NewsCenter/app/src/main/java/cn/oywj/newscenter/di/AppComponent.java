package cn.oywj.newscenter.di;

import javax.inject.Singleton;

import cn.oywj.newscenter.app.App;
import cn.oywj.newscenter.di.module.AppModule;
import dagger.Component;

/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.di
 * date:2016/11/11
 * author：欧阳维骏
 * instructions:* AppComponent -- 为整个App提供注入常用的对象类*
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    @ContextQualifier
    App getAppContext();

//    RetrofitHelper getRetrofitHelper();

}
