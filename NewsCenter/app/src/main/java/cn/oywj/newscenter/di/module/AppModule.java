package cn.oywj.newscenter.di.module;

import javax.inject.Singleton;

import cn.oywj.newscenter.app.App;
import cn.oywj.newscenter.di.ContextQualifier;
import dagger.Module;
import dagger.Provides;

/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.di.module
 * date:2016/11/11
 * author：欧阳维骏
 * instructions:* AppModule -- Dagger2中的Module类，用于为指定Component提供注入所需的对象 *
 */
@Module
public class AppModule {
    private App mApplication;

    public AppModule(App application) {
        mApplication = application;
    }

    @Provides
    @ContextQualifier
    @Singleton
    public App provideContext() {
        return mApplication;
    }
}
