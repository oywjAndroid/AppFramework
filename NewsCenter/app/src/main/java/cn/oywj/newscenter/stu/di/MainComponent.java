package cn.oywj.newscenter.stu.di;

import cn.oywj.newscenter.MainActivity;
import cn.oywj.newscenter.app.App;
import cn.oywj.newscenter.stu.activity.OtherActivity;
import dagger.Component;

/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.stu.di
 * date:2016/11/8
 * author：欧阳维骏
 * instructions:**
 */
//用@Component表示这个接口是一个连接器，能用@Component注解的只
//能是interface或者抽象类
@PropertyScop
@Component(dependencies = AppComponent.class, modules = {MainModule.class, PropertyModule.class})
public abstract class MainComponent {

    private static MainComponent sComponent;

    public static MainComponent getInstance() {
        if (sComponent == null) {
            sComponent = DaggerMainComponent.builder()
                    .appComponent(App.getAppComponent())
                    .build();
        }
        return sComponent;
    }

    /**
     * 需要用到这个连接器的对象，就是这个对象里面有需要注入的属性
     * （被标记为@Inject的属性）
     * 这里inject表示注入的意思，这个方法名可以随意更改，但建议就
     * 用inject即可。
     */
    public abstract void inject(MainActivity activity);

    public abstract void inject(OtherActivity activity);
}
