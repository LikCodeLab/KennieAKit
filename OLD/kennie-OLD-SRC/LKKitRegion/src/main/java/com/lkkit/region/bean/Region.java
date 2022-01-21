//┏┓　　　┏┓
//┏┛┻━━━┛┻┓
//┃　　　　　　　┃ 　
//┃　　　━　　　┃
//┃　┳┛　┗┳　┃
//┃　　　　　　　┃
//┃　　　┻　　　┃
//┃　　　　　　　┃
//┗━┓　　　┏━┛
//  ┃　　　┃   神兽保佑　　　　　　　　
//  ┃　　　┃   代码无BUG！
//  ┃　　　┗━━━┓
//  ┃　　　　　　　┣┓
//  ┃　　　　　　　┏┛
//  ┗┓┓┏━┳┓┏┛
//    ┃┫┫　┃┫┫
//    ┗┻┛　┗┻┛

package com.lkkit.region.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @TITLE: Region
 * @PACKAGE com.jackylee.region.bean
 * @DESCRIPTION: ${TODO}(用一句话描述该文件做什么)
 * @AUTHOR: JackyLee
 * @DATE: 2019/04/05 14:51
 * @VERSION V 0.1
 */
public class Region implements Parcelable {

    private static final int MASK_PROVINCE = 0x000;

    public enum Type {
        PROVINCE,
        CITY,
        DISTRICT,
        UNKNOWN
    }

    public int id;
    public String name;

    public Type getType() {
        // TODO: 16/6/14

        return Type.UNKNOWN;
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
    }

    public Region() {}

    private Region(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
    }

    @Override
    public String toString() {
        return "Region{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static final Creator<Region> CREATOR = new Creator<Region>() {
        @Override
        public Region createFromParcel(Parcel source) {return new Region(source);}

        @Override
        public Region[] newArray(int size) {return new Region[size];}
    };
}
