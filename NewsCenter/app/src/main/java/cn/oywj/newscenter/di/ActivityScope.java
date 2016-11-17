package cn.oywj.newscenter.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.di
 * date:2016/11/17
 * author：欧阳维骏
 * instructions:*Activity域标记*
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScope {
}
