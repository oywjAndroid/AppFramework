package cn.oywj.newscenter.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.View;

import javax.inject.Inject;

import butterknife.ButterKnife;
import cn.oywj.newscenter.app.App;
import cn.oywj.newscenter.di.component.ActivityComponents;
import cn.oywj.newscenter.di.component.DaggerActivityComponents;
import cn.oywj.newscenter.di.module.ActivityModules;

/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.base
 * date:2016/11/4
 * author：欧阳维骏
 * instructions:* 作为整个项目中有采用MVP模式Activity的基类：
 * 1.抽象出View层的基本流程规范。
 * (1) createLayout -- 创建出整个Activity依赖的布局。
 * (2) initInject -- 执行依赖注入。
 * (3) initEventAndData -- 执行View事件和数据的绑定。
 * 2.MVP模式的使用：
 * <-- View 与 Presenter 的交互是双向的，View 不与 Model层直接进行交互，而是通过Presenter与Model进行交互，
 * View层只负责UI处理逻辑，至于业务逻辑等都由Presenter执行。-->
 * 3.View 如何与 Presenter进行双向的交互呢？
 * 例如：现在Activity中需要向服务器端获取数据？
 * --- 不使用MVP模式：直接在Activity中做网络请求，然后拿到了数据进行展示。
 * <p>
 * --- 使用MVP模式：Activity作为View层，只负责UI的展示动作，也就是说这个时候的Activity只需要拿到数据展示即可，
 * 至于数据的获取的动作应交给Presenter去执行。
 * 那么这个时候从我们程序的角度来说的话，这个Activity应该需要持有一个Presenter对象以方便执行业务逻辑(比如从服务器获取数据)，
 * 那么在获取数据之后应该如何将结果集(获取的数据)回传给Activity呢?可想而知，也应该让Presenter持有这个Activity对象。
 * <p>
 * *
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {

    protected Activity mActivity;

    @Inject
    protected P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        mActivity = this;
        initInject();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        App.getInstance().addActivity(this);
        initEventAndData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();
        App.getInstance().removeActivity(this);
    }

    /**
     * 通过Toolbar来统一设置应用导航样式。
     *
     * @param toolbar Toolbar
     * @param title   标题
     */
    protected void setToolBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }

    /**
     * 获取Activity的依赖注入连接器(Component)
     *
     * @return ActivityComponents
     */
    protected ActivityComponents getActivityComponents() {
        return DaggerActivityComponents.builder()
                .appComponents(App.getAppComponents())
                .activityModules(getActivityModules())
                .build();
    }

    protected ActivityModules getActivityModules() {
        return new ActivityModules(this);
    }

    /**
     * 获取该Activity对应的内容布局Id
     *
     * @return layoutId
     */
    protected abstract int getLayoutId();

    /**
     * 初始化Dagger2的依赖注入动作
     */
    protected abstract void initInject();

    /**
     * 初始化事件以及数据
     */
    protected abstract void initEventAndData();

    @Override
    public void useNightMode(boolean isNight) {
        AppCompatDelegate.setDefaultNightMode(isNight ? AppCompatDelegate.MODE_NIGHT_YES
                : AppCompatDelegate.MODE_NIGHT_NO);
        recreate();
    }


}
