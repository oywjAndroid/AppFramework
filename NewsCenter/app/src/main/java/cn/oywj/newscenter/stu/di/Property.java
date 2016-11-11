package cn.oywj.newscenter.stu.di;

import javax.inject.Inject;

/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.stu.di
 * date:2016/11/8
 * author：欧阳维骏
 * instructions:* *
 */
public class Property {
    private String mPropertiesName;

    @Inject
    public Property() {
        mPropertiesName = "我叫库日天";
    }

    public Property(String propertiesName) {
        mPropertiesName = propertiesName;
    }

    public String getPropertiesName() {
        return mPropertiesName;
    }
}
