package com.kennie.module.chat.enums;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @项目名 KennieProject
 * @类名称 Platform
 * @类描述 各平台类型
 * @创建人 Administrator
 * @修改人
 * @创建时间 2021/12/4 0:31
 */
@Retention(RetentionPolicy.SOURCE)
@IntDef({Platform.ANDROID, Platform.IOS, Platform.WIN_PC, Platform.MAC_PC, Platform.WEB})
public @interface Platform {

    public static final int ANDROID = 1;
    public static final int IOS = 2;
    public static final int WIN_PC = 3;
    public static final int MAC_PC = 4;
    public static final int WEB = 5;
}
