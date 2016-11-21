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

    public static void start_Activity(Activity activity, Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(activity, cls);
        activity.startActivity(intent);
    }
}
