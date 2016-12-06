package cn.oywj.newscenter.stu.view.navigate;

/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.stu.view.navigate
 * date:2016/11/24
 * author：欧阳维骏
 * instructions:*The navigation bar basic interface.*
 */
public interface INavigate {

    /**
     * switch to the specified page.
     *
     * @param position page position
     */
    void switchPages(int position);

    /**
     * hide the navigate bar.
     */
    void hide();

    /**
     * show the navigate bar.
     */
    void show();

    /**
     * Whether to have a sliding switch attribute
     *
     * @param isSlide If true, it can be slided otherwise false.
     */
    void isSlideSwitch(boolean isSlide);

}
