package cn.oywj.newscenter.base;

/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.base
 * date:2016/11/10
 * author：欧阳维骏
 * instructions:*整个项目中MVP模式中View层的基类*
 * <p>
 * *** View层对象只负责UI逻辑操作 ***
 * View层最基本的UI操作有哪些？
 * 1.显示数据出错的UI
 * 2.显示加载数据的UI
 * 3.是否使用夜间模式
 * ....如有更多基本操作抽象至此接口.
 */
public interface BaseView {

    /**
     * 是否使用错误视图
     *
     * @param isError  如果为true，需要展示错误视图；否则需隐藏错误视图。
     * @param errorMsg 如果需要显示错误视图的时候，可自定义错误消息。
     */
    void useErrorView(boolean isError, String errorMsg);

    /**
     * 是否使用加载数据视图
     *
     * @param isLoading  如果为true，则需要展示加载数据视图；否则需加载数据视图。
     * @param loadingMsg 如果需要显示加载数据视图的时候，可自定义加载数据时等待消息。
     */
    void useLoadingView(boolean isLoading, String loadingMsg);

    /**
     * 是否使用了夜间模式
     *
     * @param isNight 是否使用夜间模式
     */
    void useNightMode(boolean isNight);
}
