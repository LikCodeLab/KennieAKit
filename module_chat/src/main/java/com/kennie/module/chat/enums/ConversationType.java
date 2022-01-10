package com.kennie.module.chat.enums;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @项目名 KennieProject
 * @类名称 ConversationType
 * @类描述 会话类型
 * @创建人 Kennie
 * @修改人
 * @创建时间 2021/12/3 22:10
 */
@Retention(RetentionPolicy.SOURCE)
@IntDef({ConversationType.SINGLE_CHAT, ConversationType.GROUP_CHAT})
public @interface ConversationType {

    /**
     * 当前会话是单聊
     */
    public static final int SINGLE_CHAT = 1;
    /**
     * 当前会话是群聊
     */
    public static final int GROUP_CHAT = 2;
}
