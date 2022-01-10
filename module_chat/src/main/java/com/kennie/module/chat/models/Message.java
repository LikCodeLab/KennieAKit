package com.kennie.module.chat.models;

/**
 * @项目名 KennieProject
 * @类名称 Message
 * @类描述 消息Model
 * @创建人 Kennie
 * @修改人
 * @创建时间 2021/12/4 0:35
 */
public class Message {

    /**
     * 消息唯一序列号
     */
    private long uuid;

    /**
     * 消息唯一ID
     */
    private String msgId;

    /**
     * 消息服务器ID，暂时不使用
     */
    private String serverMsgId;

    /**
     * 消息创建时间，单位纳秒
     */
    private long createTime;
    /**
     * 消息发送时间，单位纳秒
     */
    private long sendTime;

    /**
     * 发送者ID
     */
    private String senderId;

    /**
     * 发送者昵称
     */
    private String senderNickName;

    /**
     * 发送者头像
     */
    private String senderProfilePic;

    /**
     * 接收者ID
     */
    private String receiverId;

    /**
     * 接收者昵称
     */
    private String receiverNickName;

    /**
     * 接收者头像
     */
    private String receiverProfilePic;

    /**
     * 标识消息是用户级别还是系统级别 100:用户 200:系统
     */
    private int msgFrom;

    /**
     * 消息类型：<br/>
     * 101:文本消息<br/>
     * 102:图片消息<br/>
     * 103:语音消息<br/>
     * 104:视频消息<br/>
     * 105:文件消息<br/>
     * 106:@消息<br/>
     * 107:合并消息<br/>
     * 108:转发消息<br/>
     * 109:位置消息<br/>
     * 110:自定义消息<br/>
     * 111:撤回消息回执<br/>
     * 112:C2C已读回执<br/>
     * 113:正在输入状态
     *
     * @MessageType
     */
    private int msgType;

    /**
     * 平台类型：<br/>
     * 1:android<br/>
     * 2:ios<br/>
     *
     * @Platform
     */
    private int platform;

    /**
     * 消息内容
     */
    private String msgContent;

    /**
     * 是否已读
     */
    private boolean isRead;

    /**
     * 消息状态：<br/>
     * 1:发送中<br/>
     * 2:发送成功<br/>
     * 3:发送失败<br/>
     * 4:已删除<br/>
     * 5:已撤回<br/>
     *
     * @MessageStatus
     */
    private int msgStatus;

    /**
     * 消息备注
     */
    private String remark;

    /**
     * 会话类型 1:单聊 2:群聊
     */
    /**
     * 会话类型：<br/>
     * 1:单聊<br/>
     * 2:群聊<br/>
     *
     * @ConversationType
     */
    private int sessionType;


    /**
     * 扩展信息，可以放置任意的数据内容，也可以去掉此属性
     */
    private String extra;


}
