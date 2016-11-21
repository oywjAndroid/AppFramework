package cn.oywj.newscenter.model.bean;

import java.util.List;

/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.model.bean
 * date:2016/11/18
 * author：欧阳维骏
 * instructions:**
 */
public class WelcomeBean {
    public String code;//	200
    public String msg;//	success
    public List<News> newslist;//	Array

    public class News {
        public String ctime;//	2016-03-06 14:11
        public String description;//	美女图片
        public String picUrl;//	http://m.xxxiao.com/wp-content/uploads/sites/3/2015/04/m.xxxiao.com_6bb61e3b7bce0931da574d19d1d82c884-760x500.jpg
        public String title;//	王馨瑶性感写真壁纸
        public String url;//	http://m.xxxiao.com/308
    }
}
