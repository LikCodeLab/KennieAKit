package com.kennie.module.chat.enums;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @项目名 KennieProject
 * @类名称 MessageStatus
 * @类描述 消息发送状态
 * @创建人 Kennie
 * @修改人
 * @创建时间 2021/12/3 22:35
 */
@Retention(RetentionPolicy.SOURCE)
@IntDef({MessageStatus.SENDING, MessageStatus.SUCCEEDED, MessageStatus.FAILED,
        MessageStatus.DELETED, MessageStatus.REVOKE})
public @interface MessageStatus {

    /**
     * 发送中
     */
    public static final int SENDING = 1;
    /**
     * 发送成功
     */
    public static final int SUCCEEDED = 2;
    /**
     * 发送失败
     */
    public static final int FAILED = 3;
    /**
     * 发送取消(已删除)
     */
    public static final int DELETED = 4;
    /**
     * 发送撤回(已撤回)
     */
    public static final int REVOKE = 5;
}
