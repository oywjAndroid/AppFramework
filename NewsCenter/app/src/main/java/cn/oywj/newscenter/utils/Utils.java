package cn.oywj.newscenter.utils;

import android.app.Activity;
import android.content.Intent;

/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.utils
 * date:2016/11/18
 * author：欧阳维骏
 * instructions:**
 */
public class Utils {

    /**
     * 执行不带参数的跳转页面，使用了淡入淡出的切换动画
     *
     * @param activity
     * @param cls
     */
    public static void start_Activity(Activity activity, Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(activity, cls);
        activity.startActivity(intent);
        activity.finish();
        activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
