package cn.oywj.newscenter.di;

import javax.inject.Qualifier;

/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.di
 * date:2016/11/11
 * author：欧阳维骏
 * instructions:*Application Context 的限定符*
 */
@Qualifier
public @interface ContextQualifier {
    String value() default "Application";
}
