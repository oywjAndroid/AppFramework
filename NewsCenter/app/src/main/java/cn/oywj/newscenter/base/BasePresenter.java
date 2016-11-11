package cn.oywj.newscenter.base;

/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.base
 * date:2016/11/10
 * author：欧阳维骏
 * instructions:*整个项目中Presenter层的基类*
 * <p>
 * 1.Presenter层最重要的任务就是处理View层的各种业务逻辑，因此Presenter是必须有依附的View层对象。
 */
public interface BasePresenter<V extends BaseView> {

    /**
     * 依附其对应的View层对象
     *
     * @param view View层对象
     */
    void attachView(V view);

    /**
     * 与依附的View层对象分离
     */
    void detachView();
}
