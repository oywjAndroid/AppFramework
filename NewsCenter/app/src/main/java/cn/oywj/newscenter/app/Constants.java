package cn.oywj.newscenter.app;

import java.io.File;

/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.app
 * date:2016/11/16
 * author：欧阳维骏
 * instructions:*Constants用于保存App中的所有常量信息*
 */
public interface Constants {

    String API_KEY = "d270865499a53085c5267f9d5c1fea5e";

    String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";

    String PATH_CACHE = PATH_DATA + File.separator + "NetCache";
}
