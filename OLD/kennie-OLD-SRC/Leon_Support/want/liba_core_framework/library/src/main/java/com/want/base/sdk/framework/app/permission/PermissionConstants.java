package com.want.base.sdk.framework.app.permission;

/**
 * <b>Project:</b> AndroidCrashReportor<br>
 * <b>Create Date:</b> 16/2/24<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * 权限请求相关的常量定义
 * <br>
 */
public interface PermissionConstants {
    /** 权限请求ACTION */
    String ACTION = "com.cnbleu.crashreport.ACTION_REQUEST_PERMISSION";
    /** 需要请求的权限 */
    String PERMISSION = "ext_permission";
    /** 权限授予结果 */
    String GRANTRESULTS = "ext_grantresults";
    /** 权限请求ID */
    String REQUEST_CODE = "code";
    /** 额外数据 */
    String EXTRAS = "ext_extras";
}