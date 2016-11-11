package cn.oywj.newscenter.stu.di;

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
public class PropertyModule {
    @PropertyScop
    @Provides
    public Property provideProperty(String propertyName) {
        return new Property(propertyName);
    }

    @PropertyQualifier("One")
    @PropertyScop
    @Provides
    public Property provideOne() {
        return new Property("我啥子的");
    }

    @PropertyQualifier("Two")
    @PropertyScop
    @Provides
    public Property provideTwo() {
        return new Property("我啥子都不晓得");
    }

    @Provides
    public String providePropertyName() {
        return "努力的人终究会到达彼岸!";
    }
}
