package cn.oywj.newscenter.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import java.util.HashSet;
import java.util.Set;

import cn.oywj.newscenter.di.component.AppComponents;
import cn.oywj.newscenter.di.component.DaggerAppComponents;
import cn.oywj.newscenter.di.module.AppModules;
import cn.oywj.newscenter.stu.di.AppComponent;
import cn.oywj.newscenter.stu.di.DaggerAppComponent;

/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.app
 * date:2016/11/4
 * author：欧阳维骏
 * instructions:*Application实现管理所有的Activity，有对手机屏幕的宽高进行计算。*
 */
public class App extends Application {

    private static App instance;
    private Set<Activity> allActivities;
    public static int SCREEN_WIDTH = -1;
    public static int SCREEN_HEIGHT = -1;
    public static float DIMEN_RATE = -1.0F;
    public static int DIMEN_DPI = -1;

    public static synchronized App getInstance() {
        return instance;
    }

    static {
        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_NO);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //初始化屏幕宽高
        getScreenSize();
    }

    public void addActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<Activity>();
        }
        allActivities.add(act);
    }

    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }

    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    public void getScreenSize() {
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(dm);
        DIMEN_RATE = dm.density / 1.0F;
        DIMEN_DPI = dm.densityDpi;
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
        if (SCREEN_WIDTH > SCREEN_HEIGHT) {
            int t = SCREEN_HEIGHT;
            SCREEN_HEIGHT = SCREEN_WIDTH;
            SCREEN_WIDTH = t;
        }
    }

    public static AppComponents getAppComponents() {
        return DaggerAppComponents.builder()
                .appModules(new AppModules(instance))
                .build();
    }

    // STU TEST:
    public static AppComponent getAppComponent() {
        return DaggerAppComponent.builder().build();
    }
}
