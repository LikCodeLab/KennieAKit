package com.liba_module_update.net;

import android.os.Parcel;

/**
 * <b>Create Date:</b> 16/9/21<br>
 * <b>Author:</b> ldc <br>
 * <b>Description:</b>
 * <br>
 */
public class CodeInfo extends Model {

    public String url;
    public String version;
    //用于校验 APP 包数据的完整性
    //APP 从服务器下载完 APP 包后,计算出 MD5 值,
    // 并和从服务器获取的 MD5 值比较是否一样
    public String fileMd5;
    //提示框标题栏显示的文字
    public String title;
    //显示服务器端的更新日志
    public String changes;
    public int size;
    // 800不强制升级 801强制升级
    public int upgrade;
    //显示提示信息的时间间隔,以小时为单位,不支持小数
    public int interval;
    //显示提示信息次数,不支持小数;如为 0,则没有限制
    public int limitTimes;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.url);
        dest.writeString(this.version);
        dest.writeString(this.fileMd5);
        dest.writeString(this.title);
        dest.writeString(this.changes);
        dest.writeInt(this.size);
        dest.writeInt(this.upgrade);
        dest.writeInt(this.interval);
        dest.writeInt(this.limitTimes);
    }

    @Override
    public String toString() {
        return "CodeInfo{" +
                "url='" + url + '\'' +
                ", version='" + version + '\'' +
                ", fileMd5='" + fileMd5 + '\'' +
                ", title='" + title + '\'' +
                ", changes='" + changes + '\'' +
                ", size=" + size +
                ", upgrade=" + upgrade +
                ", interval=" + interval +
                ", limitTimes=" + limitTimes +
                '}';
    }

    public CodeInfo() {
    }

    protected CodeInfo(Parcel in) {
        super(in);
        this.url = in.readString();
        this.version = in.readString();
        this.fileMd5 = in.readString();
        this.title = in.readString();
        this.changes = in.readString();
        this.size = in.readInt();
        this.upgrade = in.readInt();
        this.interval = in.readInt();
        this.limitTimes = in.readInt();
    }

    public static final Creator<CodeInfo> CREATOR = new Creator<CodeInfo>() {
        @Override
        public CodeInfo createFromParcel(Parcel source) {
            return new CodeInfo(source);
        }

        @Override
        public CodeInfo[] newArray(int size) {
            return new CodeInfo[size];
        }
    };
}
