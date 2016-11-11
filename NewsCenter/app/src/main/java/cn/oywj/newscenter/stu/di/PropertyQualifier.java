package cn.oywj.newscenter.stu.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.stu.di
 * date:2016/11/10
 * author：欧阳维骏
 * instructions:**
 */
@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface PropertyQualifier {
    String value() default "";
}
