package cn.oywj.newscenter.stu.di;

import javax.inject.Singleton;

import dagger.Component;

/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.stu.di
 * date:2016/11/9
 * author：欧阳维骏
 * instructions:**
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    int getDescStr();
}
