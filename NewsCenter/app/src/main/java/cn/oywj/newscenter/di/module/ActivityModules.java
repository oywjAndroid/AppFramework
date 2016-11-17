package cn.oywj.newscenter.di.module;

import android.app.Activity;

import cn.oywj.newscenter.di.ActivityScope;
import dagger.Module;
import dagger.Provides;

/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.di.module
 * date:2016/11/17
 * author：欧阳维骏
 * instructions:**
 */
@Module
public class ActivityModules {
    private Activity mActivity;

    public ActivityModules(Activity activity) {
        mActivity = activity;
    }

    @ActivityScope
    @Provides
    public Activity provideActivity() {
        return mActivity;
    }
}
