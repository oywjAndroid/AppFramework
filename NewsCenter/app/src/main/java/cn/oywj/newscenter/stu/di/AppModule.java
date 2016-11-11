package cn.oywj.newscenter.stu.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.stu.di
 * date:2016/11/9
 * author：欧阳维骏
 * instructions:**
 */
@Module
public class AppModule {

    @Singleton
    @Provides
    int provideDescStr() {
        return 1234567890;
    }
}
