package com.want.model.region.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * <b>Project:</b> project_hotclub<br>
 * <b>Create Date:</b> 16/6/12<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class Region implements Parcelable {

    private static final int MASK_PREOVINCE = 0x000;

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

    protected Region(Parcel in) {
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
