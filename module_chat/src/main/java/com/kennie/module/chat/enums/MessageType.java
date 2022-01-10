package com.kennie.module.chat.enums;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @项目名 KennieProject
 * @类名称 MessageType
 * @类描述 消息类型
 * @创建人 Kennie
 * @修改人
 * @创建时间 2021/12/3 22:39
 */
@Retention(RetentionPolicy.SOURCE)
@IntDef({MessageType.TEXT, MessageType.PICTURE, MessageType.VOICE,
        MessageType.VIDEO, MessageType.FILE, MessageType.AT_TEXT,
        MessageType.MERGER, MessageType.REVOKE, MessageType.HAS_READ_RECEIPT,
        MessageType.TYPING})
public @interface MessageType {


    /**
     * 文本
     */
    public static final int TEXT = 101;
    /**
     * 图片
     */
    public static final int PICTURE = 102;
    /**
     * 声音
     */
    public static final int VOICE = 103;
    /**
     * 视频
     */
    public static final int VIDEO = 104;
    /**
     * 文件
     */
    public static final int FILE = 105;
    /**
     * _@消息
     */
    public static final int AT_TEXT = 106;
    /**
     * 合并
     */
    public static final int MERGER = 107;
    /**
     * 撤回
     */
    public static final int REVOKE = 111;
    /**
     * 已读
     */
    public static final int HAS_READ_RECEIPT = 112;
    /**
     * 正在输入
     */
    public static final int TYPING = 113;
}
